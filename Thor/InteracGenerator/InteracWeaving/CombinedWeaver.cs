using System;
using System.Collections.Generic;
using System.Linq;
using SPLConqueror_Core;

namespace InteracGenerator.InteracWeaving
{
    internal class CombinedWeaver : AbstractWeaver<ConfigurationOption>
    {
        private VariabilityModel _vm;
        private InfluenceModel _influenceModel;
        private double[] _featureDegreeValues;

        public CombinedWeaver(Thor model) : base(model)
        {
        }

        public void SetVariabilityModel(VariabilityModel model)
        {
            _vm = model;
            var prop = new NFProperty("NFP");
            GlobalState.currentNFP = prop;

            _influenceModel = new InfluenceModel(_vm, prop);
            GlobalState.infModel = _influenceModel;
            GlobalState.varModel = _vm;
        }

        public override void SetUpWeaver()
        {
            FoundInteractions = new List<List<ConfigurationOption>>();
            FeatureList.RemoveAll((feature) => feature.Name.Equals("root"));
            _featureDegreeValues = Model.DStore.SelectedFeatureDegreeDistribution.Values;
        }

        public override List<ConfigurationOption> SelectRandomInteraction(int order)
        {
            List<ConfigurationOption> availableOptions = new List<ConfigurationOption>(FeatureList);
            List<ConfigurationOption> tempConfig = new List<ConfigurationOption>();
            do
            {
                int featureIndex = Rand.Next(0, availableOptions.Count());
                ConfigurationOption feature = availableOptions[featureIndex];
                if (feature is BinaryOption)
                {
                    tempConfig.Add(feature);
                }
                else if (feature is NumericOption)
                {
                    int degree;
                    do
                    {
                        degree = (int)_featureDegreeValues[Rand.Next(0, _featureDegreeValues.Length)];
                    } while (tempConfig.Count() + degree > order);
                    for (int j = 0; j < degree; j++)
                    {
                        tempConfig.Add(feature);
                    }
                }
                availableOptions.Remove(feature);
            } while (tempConfig.Count() < order);
            tempConfig.Sort((option1, option2) => { return string.Compare(option1.Name, option2.Name, StringComparison.Ordinal); });
            return tempConfig;

        }

        protected override bool AlreadyFoundInteraction(ICollection<ConfigurationOption> newConfig)
        {
            Dictionary<string, int> newConfigOptions = newConfig.GroupBy(option => option.Name)
                                                                .ToDictionary(g => g.Key, g => g.ToList().Count());
            foreach (List<ConfigurationOption> foundInter in FoundInteractions)
            {
                Dictionary<string, int> interactionOptions = foundInter.GroupBy(option => option.Name)
                                                                       .ToDictionary(g => g.Key, g => g.ToList().Count());
                if (newConfigOptions.Count != interactionOptions.Count) { continue; }
                bool isSame = true;
                foreach (ConfigurationOption numOption in newConfig)
                {
                    if (!interactionOptions.ContainsKey(numOption.Name)
                        || newConfigOptions[numOption.Name] != interactionOptions[numOption.Name])
                    {
                        isSame = false;
                        break;
                    }
                }
                if (isSame)
                {
                    return true;
                }
            }
            return false;
        }

        public override void AddAttributesToModel()
        {
        }

        public override void AddInteractionToModel(int index, List<ConfigurationOption> tempConfig)
        {
        }

        public override List<List<ConfigurationOption>> LoadInteractions(string fileName = "foundInteractions.txt")
        {
            throw new NotImplementedException();
        }

        public override bool CheckInteractionSat(List<ConfigurationOption> tempConfig)
        {
            return true;
        }
    }
}
