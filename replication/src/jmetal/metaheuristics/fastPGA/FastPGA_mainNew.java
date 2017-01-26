//  FastPGA_main.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.
package jmetal.metaheuristics.fastPGA;

import java.io.IOException;

import jmetal.core.*;
import jmetal.encodings.variable.*;
import jmetal.metaheuristics.fastPGA.FastPGA;
import jmetal.util.comparators.FPGAFitnessComparator;
import jmetal.util.comparators.FitnessComparator;
import jmetal.operators.crossover.*;
import jmetal.operators.mutation.*;
import jmetal.operators.selection.*;
import jmetal.problems.FMr.*              ;
import jmetal.problems.WFG.*              ;
import jmetal.problems.DTLZ.*             ;
import jmetal.problems.LZ09.* ;
import jmetal.qualityIndicator.QualityIndicator;

import jmetal.util.Configuration;
import jmetal.util.JMException;

import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import main.ParsedFM;

/**
 * Class for configuring and running the FastPGA algorithm
 */
public class FastPGA_mainNew {
  public static Logger      logger_ ;      // Logger object
  public static FileHandler fileHandler_ ; // FileHandler object
  
  /**
   * @param args Command line arguments. The first (optional) argument specifies 
   *             the problem to solve.
   * @throws JMException 
   */
  public static void main(String [] args) throws JMException, IOException, ClassNotFoundException {
    Problem   problem   ;         // The problem to solve
    Algorithm algorithm ;         // The algorithm to use
    Operator  crossover ;         // Crossover operator
    Operator  mutation  ;         // Mutation operator
    Operator  selection ;         // Selection operator

    QualityIndicator indicators ; // Object to get quality indicators

    HashMap  parameters ; // Operator parameters

    // Logger object and file to store log messages
    logger_      = Configuration.logger_ ;
    fileHandler_ = new FileHandler("FastPGA_main.log"); 
    logger_.addHandler(fileHandler_) ;
    
    ParsedFM featureModel = new ParsedFM();
    featureModel.parse("src\\FM-290.xml");
    
    indicators = null;
   
    
    
    indicators = null ;
    problem = new FM290r("Binary", featureModel.getFeatureCount(), 5, featureModel);

    algorithm = new FastPGATM(problem);

    algorithm.setInputParameter("maxPopSize",100);
    algorithm.setInputParameter("initialPopulationSize",100);
    algorithm.setInputParameter("maxEvaluations",25000);
    algorithm.setInputParameter("a",20.0);
    algorithm.setInputParameter("b",1.0);
    algorithm.setInputParameter("c",20.0);
    algorithm.setInputParameter("d",0.0);
    algorithm.setInputParameter("runTime", 5000);

    // Parameter "termination"
    // If the preferred stopping criterium is PPR based, termination must 
    // be set to 0; otherwise, if the algorithm is intended to iterate until 
    // a give number of evaluations is carried out, termination must be set to 
    // that number
    algorithm.setInputParameter("termination",1);

    // Mutation and Crossover for Real codification 
    parameters = new HashMap() ;
    parameters.put("probability", 0.9) ;
    parameters.put("distributionIndex", 20.0) ;
    crossover = CrossoverFactory.getCrossoverOperator("FMCrossover", parameters);                   

    parameters = new HashMap() ;
    parameters.put("probability", 0.8) ;
    parameters.put("distributionIndex", 20.0) ;
    
    //add FM to mutator params
    parameters.put("featureModel", featureModel);
    mutation = MutationFactory.getMutationOperator("FMMutator", parameters);         
    
    parameters = new HashMap() ; 
    parameters.put("comparator", new FPGAFitnessComparator()) ;
    selection = new BinaryTournament(parameters);
    
    algorithm.addOperator("crossover",crossover);
    algorithm.addOperator("mutation",mutation);
    algorithm.addOperator("selection",selection);

    long initTime = System.currentTimeMillis();
    SolutionSet population = algorithm.execute();
    long estimatedTime = System.currentTimeMillis() - initTime;
    
    // Result messages 
    logger_.info("Total execution time: "+estimatedTime + "ms");
    logger_.info("Variables values have been writen to file VAR");
    population.printVariablesToFile("VAR");    
    logger_.info("Objectives values have been writen to file FUN");
    population.printObjectivesToFile("FUN");
  
    if (indicators != null) {
      logger_.info("Quality indicators") ;
      logger_.info("Hypervolume: " + indicators.getHypervolume(population)) ;
      logger_.info("GD         : " + indicators.getGD(population)) ;
      logger_.info("IGD        : " + indicators.getIGD(population)) ;
      logger_.info("Spread     : " + indicators.getSpread(population)) ;
      logger_.info("Epsilon    : " + indicators.getEpsilon(population)) ;  
     
      int evaluations = ((Integer)algorithm.getOutputParameter("evaluations")).intValue();
      logger_.info("Speed      : " + evaluations + " evaluations") ;      
    } // if
  }//main
} // FastPGA_main