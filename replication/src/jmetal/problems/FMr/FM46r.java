package jmetal.problems.FMr;
//  FM46r.java
//
//  Author:
//       Abdel Salam Sayyad
//
//  Copyright (c) Abdel Salam Sayyad - West Virginia University
//
import jmetal.core.*;
import jmetal.encodings.solutionType.BinarySolutionType;
import jmetal.encodings.variable.Binary;
import jmetal.util.JMException;
import jmetal.util.Configuration.*;
import main.ObjectiveParser;
import main.ParsedFM;

public class FM46r extends Problem {   

  private static ParsedFM featureModel;
    
  public FM46r(String solutionType) throws ClassNotFoundException {
    this(solutionType, 46, 8, new ParsedFM("..\\trunk2\\jmetal\\problems\\FMr\\FM-46.xml"));
  }

  public FM46r(String solutionType, 
               Integer numberOfBits, 
  		         Integer numberOfObjectives,
                         	ParsedFM pfm) throws ClassNotFoundException {
    numberOfVariables_  = 1;
    numberOfObjectives_ = numberOfObjectives.intValue();
    numberOfConstraints_= 0;
    problemName_        = "FM4600r";
	
    featureModel=pfm;
    featureModel.setRequiresArray(requires_pairs);
    
    this.pfm = pfm;
        
    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];        
    //for (int var = 0; var < numberOfBits.intValue(); var++){
      lowerLimit_[0] = 0.0;
      upperLimit_[0] = 1.0;
   // } //for
        
    if (solutionType.compareTo("Binary") == 0)
    	solutionType_ = new BinarySolutionType(this) ;
    else {
    	System.out.println("Error: solution type " + solutionType + " invalid") ;
    	System.exit(-1) ;
    }
        length_    = new int[numberOfVariables_];
        length_[0] = numberOfBits.intValue();
}            
    //public static final ParsedFM featureModel = new ParsedFM("src\\jmetal\\problems\\FMr\\FM-46.xml");
    public static final int FEATURES = 46;
    public static final int [] [] requires_pairs = {};
    
    public static ObjectiveParser op = new ObjectiveParser("..\\trunk2\\jmetal\\problems\\FMr\\qualities46.csv");

    public static final boolean[] USED_BEFORE = op.getBools(0);

    public static final int[] DEFECTS = op.getInts(1);
    
    public static final double[] COST = op.getDoubles(2);
    
    public ParsedFM getParsedFM() {
	  return featureModel;
    }
    
  /** 
  * Evaluates a solution 
  * @param solution The solution to evaluate
   * @throws JMException 
  */    
  public void evaluate(Solution solution) throws JMException {

        Binary variable = ((Binary)solution.getDecisionVariables()[0]) ;
        int requires_viol = featureModel.requiresViolations(variable);
        int excludes_viol = 0;
        int num_features = 0;
        int num_used_before = 0;
        int num_defects = 0;
        double actual_cost = 0;
        
        // check for "excludes" rule violations
        if (variable.bits_.get(2) && variable.bits_.get(9)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(10)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(14)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(15)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(16)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(18)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(22)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(23)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(24)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(25)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(28)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(29)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(31)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(32)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(33)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(37)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(38)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(39)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(44)) excludes_viol++;
        if (variable.bits_.get(2) && variable.bits_.get(45)) excludes_viol++;
        
        if (variable.bits_.get(3) && variable.bits_.get(10)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(11)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(12)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(18)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(19)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(20)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(23)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(24)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(25)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(27)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(29)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(31)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(35)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(40)) excludes_viol++;
        if (variable.bits_.get(3) && variable.bits_.get(45)) excludes_viol++;

        if (variable.bits_.get(4) && variable.bits_.get(9)) excludes_viol++;
        if (variable.bits_.get(4) && variable.bits_.get(11)) excludes_viol++;
        if (variable.bits_.get(4) && variable.bits_.get(12)) excludes_viol++;
        if (variable.bits_.get(4) && variable.bits_.get(17)) excludes_viol++;
        if (variable.bits_.get(4) && variable.bits_.get(18)) excludes_viol++;
        if (variable.bits_.get(4) && variable.bits_.get(19)) excludes_viol++;
        if (variable.bits_.get(4) && variable.bits_.get(20)) excludes_viol++;
        if (variable.bits_.get(4) && variable.bits_.get(31)) excludes_viol++;
        if (variable.bits_.get(4) && variable.bits_.get(34)) excludes_viol++;
        if (variable.bits_.get(4) && variable.bits_.get(35)) excludes_viol++;
        if (variable.bits_.get(4) && variable.bits_.get(39)) excludes_viol++;
        if (variable.bits_.get(4) && variable.bits_.get(40)) excludes_viol++;
        if (variable.bits_.get(4) && variable.bits_.get(42)) excludes_viol++;
        if (variable.bits_.get(4) && variable.bits_.get(45)) excludes_viol++;

        if (variable.bits_.get(5) && variable.bits_.get(9)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(11)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(12)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(17)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(18)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(19)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(20)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(23)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(24)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(34)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(35)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(38)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(39)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(40)) excludes_viol++;
        if (variable.bits_.get(5) && variable.bits_.get(42)) excludes_viol++;

        if (variable.bits_.get(6) && variable.bits_.get(9)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(11)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(12)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(14)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(15)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(16)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(17)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(19)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(20)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(22)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(23)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(24)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(25)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(28)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(29)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(31)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(33)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(35)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(38)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(39)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(40)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(42)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(43)) excludes_viol++;
        if (variable.bits_.get(6) && variable.bits_.get(44)) excludes_viol++;

        if (variable.bits_.get(7) && variable.bits_.get(9)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(11)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(12)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(16)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(17)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(18)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(19)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(20)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(23)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(24)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(25)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(29)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(31)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(33)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(34)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(35)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(38)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(39)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(40)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(42)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(43)) excludes_viol++;
        if (variable.bits_.get(7) && variable.bits_.get(45)) excludes_viol++;
        
        // Find the total number of features in this individual
        for(int x=0; x<variable.getNumberOfBits(); x++)
            num_features += (variable.bits_.get(x) ? 1 : 0);
        
        // Find the total number of features that were used before
        for(int x=0; x<variable.getNumberOfBits(); x++)
            num_used_before += ((variable.bits_.get(x) && USED_BEFORE[x]) ? 1 : 0);

        // Find the total number of known defects in the chosen features
        for(int x=0; x<variable.getNumberOfBits(); x++)
            num_defects += (variable.bits_.get(x) ? DEFECTS[x] : 0);
        
        // Find the total cost of the chosen features
        for(int x=0; x<variable.getNumberOfBits(); x++)
            actual_cost += (variable.bits_.get(x) ? COST[x] : 0);
        
        
        // Assign objectives
        int num_violations = requires_viol + excludes_viol;
        if (numberOfObjectives_ == 8){
          // First: The correctness objective, minimize violations to
          // maximize correctness
      	
          solution.setObjective(0, num_violations);
          solution.setObjective(1, num_violations);
          solution.setObjective(2, num_violations);
          solution.setObjective(3, num_violations);

          // Second: Maximize the total number of features
          // Here: we minimize the missing features
          solution.setObjective(4, FEATURES - num_features); 

          // Third: Maximize the number of features that were used before
          // Here: we minimize the features that WERE'NT used before
          solution.setObjective(5, num_features - num_used_before); 

          // Fourth: Minimize the number of known defects in the chosen features
          solution.setObjective(6, num_defects); 

          // Fifth: Minimize the total cost
          solution.setObjective(7, actual_cost); 
      }
      else if (numberOfObjectives_ == 5){
            // First: The correctness objective, minimize violations to
            // maximize correctness
        	
            solution.setObjective(0, (requires_viol + excludes_viol));

            // Second: Maximize the total number of features
            // Here: we minimize the missing features
            solution.setObjective(1, FEATURES - num_features); 
  
            // Third: Maximize the number of features that were used before
            // Here: we minimize the features that WERE'NT used before
            solution.setObjective(2, num_features - num_used_before); 
  
            // Fourth: Minimize the number of known defects in the chosen features
            solution.setObjective(3, num_defects); 
  
            // Fifth: Minimize the total cost
            solution.setObjective(4, actual_cost); 
        }
        else if (numberOfObjectives_ == 4){
            solution.setObjective(0, requires_viol + excludes_viol);

            solution.setObjective(1, num_features - num_used_before); 
  
            solution.setObjective(2, num_defects); 
  
            solution.setObjective(3, actual_cost); 
        }
 
        else if (numberOfObjectives_ == 3){
            solution.setObjective(0, (requires_viol + excludes_viol)); 

            // Second: Maximize the total number of features
            // Here: we minimize the missing features
            solution.setObjective(1, FEATURES - num_features); 
  
            solution.setObjective(2, actual_cost); 
        }
        else if (numberOfObjectives_ == 2){
            solution.setObjective(0, (requires_viol + excludes_viol)); 

            // Second: Maximize the total number of features
            // Here: we minimize the missing features
            solution.setObjective(1, FEATURES - num_features); 
        }
  
  } // evaluate   
  
}

