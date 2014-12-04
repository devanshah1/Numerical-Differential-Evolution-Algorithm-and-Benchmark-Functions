import java.util.*;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * This class is one of the most important class of the entire program because this class is responsible for 
 * handling all of the Differential Evolution Process on a generated initial population. The purpose of differential 
 * evolution is to optimize a problem by iteratively trying to improve the candidate solution. The Differential 
 * evolution algorithm needs to have an initial population with individuals. The differential evolution algorithm 
 * requires the mutation constant, crossover rate, problem dimensionality, problem size and the max number of function calls.
 * This particular differential evolution algorithm run on the provided 5 functions. Total time 
 * Step 1. Selecting 3 random parent individuals from the initial population that are not equal to each other and also can not
 * 		   be the same vector as the individual in question that the algorithm is running on.
 * Step 2. Applying mutation on the selected 3 individuals with the mutation equation vi <- Xa + F * (Xc - Xb)  where vi is the
 * 		   noise vector, F is the mutation constant and Xa,Xb and Xc are the randomly generated parents individuals.
 * Step 3. Applying the crossover operation on the generated trial vector from step 2. Crossover is calculated using 
 * 		   a comparison between a random number generated between 0 and 1 to the crossover rate or if j is equal to
 * 		   jrand then the element of the noise vector is selected and put in to the trial vector else the element from 
 * 		   the initial population is selected and put in the trial vector.
 * Step 4. Applying selection between the trial vector or the initial population individual. This is depended on the cost
 * 		   value of the individual compared to the cost value of the generated trial vector. Since this algorithm is set up 
 * 	       to calculate the min of a select problem so if the cost value of the trial vector is less then the cost of the
 * 		   initial individual then the trial vector is selected and is added to the new population, and if the cost of 
 * 		   the trial vector is greater then the cost of the initial individual then the initial individual is moved over
 * 		   to the new population.
 * 
 * @author Devan Shah 100428864, Parth Patel 100392782, Ravikumar Patel 100423830
 * 
 */
public class Numerical_Differential_Evolution_Algorithm {
	// Parameter Settings for Differential Evolution
	static final int PROBLEM_DIMENSIONALITY = 30;
	static final int POPULATION_SIZE = 2 * PROBLEM_DIMENSIONALITY;
	static final double MUTATION_CONSTANT = 0.5;
	static final double CROSSOVER_RATE = 0.9;
	static final int MAXIMUM_NUMBER_OF_FUNCTION_CALLS = 1000 * PROBLEM_DIMENSIONALITY;

	/**
	 * This method is responsible for performing all the necessary actions that the given differential evolution algorithm does. 
	 * With the addition of the amount of runs that will be performed to optimize the differential evolution algorithm so that 
	 * the problem that is selected gives the most accurate possible answer depending on if it is a minimizing function or a max
	 * function. This method applies random parent selection, mutation, crossover and selection on a set of initial population data.
	 * Further more since this algorithm is going to be running 50 times depending on the user input the mean and the standard deviation
	 * is calculated on the best fitness of those 50 runs, to what the min is of that problem and how often it is reached. 
	 * Finally, this method has the functionality to graph the fitness value of each of the function calls vs the number of function calls
	 * to see when the function is reaching a global min or local min. The differential evolution algorithm has a complexity of O(n^2).
	 * @param newPopulation is a 2D arraylist that consists of the initial population of the selected problem
	 * @param benchmarkSelection is the integer value that the user inputs, which is used to select the function
	 * @param RUNS is an integer value that can be either 1 or 50, which is used to determine if the program will 
	 * 		       will generated the mean and standard deviation of 50 runs or construct a graph of 30000 generations 
	 * @param optionSelections is an integer value that decides if a graph or mean and standard deviation is generated
	 *                         for a given function
	 * @param lowerBound is the lower range value of the selected function
	 * @param upperBound is the upper range value of the selected function
	 */
	public static void Differential_Evolution_Algorithm(List<List<Double>> newPopulation, int benchmarkSelection, int RUNS, int optionSelection, double lowerBound, double upperBound) {
		// Initializing the fitnessholder array that will store the best fitness of the 60 individuals for each function call  
		List<Double> fitnessHolder = new ArrayList<Double>();

		// Initializing the object to calculate the Min and the mean of the best fitness values, SummaryStatistics is part of the Appache Commons library 
		SummaryStatistics statsMin = new SummaryStatistics();
		SummaryStatistics statsMean = new SummaryStatistics();
		
		// The entire algorithm runs depending on the user selection of the option to graph or display the statistics
		for (int a = 0; a < RUNS; a++) {
			// Variable Deceleration
			int Number_Of_Function_Calls = 0;
			// Clearing the fitness holder array before running the algorithm so that that the array does not expand passed what it is suppose to
			fitnessHolder.clear();
			
			while (Number_Of_Function_Calls < MAXIMUM_NUMBER_OF_FUNCTION_CALLS) {
				// Variable Deceleration
				double fitness = Double.MAX_VALUE;
				SummaryStatistics statsbestmin = new SummaryStatistics();
				// run the mutation, crossover and selecation on the entire population size 
				for (int i = 0; i < POPULATION_SIZE; i++) {

					// Selecting 3 random parents
					int[] randomParents = randomParentSelection(i);

					// Applying Mutation on the selected random parents and storing the results in the noise Vector
					List<Double> noiseVector = mutation(newPopulation, i, randomParents, lowerBound, upperBound);

					// Applying Crossover on the noise vector and storing the results in the trial Vector
					List<Double> trialVector = crossover(newPopulation, noiseVector, i);

					/* 
					 * Applying Selection between the initial population and the trial vector and storing the results in the 
					 * initial population and returning the selected fitness value of the vector that was chosen 
					 */
					fitness = selection(newPopulation, trialVector, benchmarkSelection, i);
					statsbestmin.addValue(fitness);
					
				}
				// Depending on the option selected by the user to graph or calculate the mean and standard deviation
				if (optionSelection == 1) {
					fitnessHolder.add(statsbestmin.getMin()); // store the fitness value previous returned from selection in to and array
				} else if (optionSelection == 2) {
					statsMin.addValue(statsbestmin.getMin()); // store the fitness value previous returned from selection into a SummaryStatistics library for later use
				}
				// incrementing the number of function calls 
				Number_Of_Function_Calls++;
			}
			// Graphing only if user selects the graphing option 
			if (optionSelection == 1) {

				Graph graph = new Graph("Linear Performance Represenation Graph of Selected Function", benchmarkSelection, "linear");

				graph.setVisible(true);
				graph.pack();
				graph.show(fitnessHolder);
				
				Graph logGraph = new Graph("Log Performance Represenation Graph of Selected Function", benchmarkSelection, "log");
				logGraph.setVisible(true);
				logGraph.pack();
				logGraph.showLog(fitnessHolder);
			}
			// calculating the min of each of the 30000 function calls and storing it in to the SummaryStastistic object for latter use
			else if (optionSelection == 2) {
				double min = statsMin.getMin();
				statsMean.addValue(min);
			}
		}

		// Display the mean and the standard deviation of the dataset that is present in the SummaryStastistic object
		if (optionSelection == 2) {
			double mean = 0.0;
			double standardDeviation = 0.0;
			
			mean = statsMean.getMean();

			System.out.printf("The average best fitness value of 50 runs is: %.5f \n", mean);

			standardDeviation = statsMean.getStandardDeviation();

			System.out.printf("The Standard Deviation of 50 runs is: %.5f \n", standardDeviation);
		}
	}

	/**
	 * This method is responsible for selecting 3 random individual parents that will take part in the mutation. The parents that 
	 * are have to be unique and also can not be the same as the individual that the mutation, crossover and selection is occurring on.
	 * Has a complexity of O(n).
	 * @param i is the current index number for the individual that is going to go through mutation, crossover and selection
	 * @return a array of randomly selected parent index numbers to be used in mutation
	 */
	public static int[] randomParentSelection(int i) {
		// variable Deceleration
		int parentA;
		int parentB;
		int parentC;
		// Array for the parent index numbers
		int[] randomParentsIndex = new int[3];
		// Random Object
		Random randomGenerator = new Random();
		// Arraylist for all the index values of the individuals in the population
		ArrayList<Integer> individualsIndex = new ArrayList<Integer>();

		// Select three parents Xa,Xb, and Xc randomly from current population where i != a != b != c
		// Generating and storing number from 0 to 59 in an array 
		for (int k = 0; k < POPULATION_SIZE; k++) {
		
			individualsIndex.add(k, k);
		}
		// removing the index value i from the generated values from 0 to 59 
		individualsIndex.remove(i);
		
		int randomIndividualA = randomGenerator.nextInt(individualsIndex.size());
		parentA = randomIndividualA;
		individualsIndex.remove(parentA);

		int randomIndividualB = randomGenerator.nextInt(individualsIndex.size());
		parentB = randomIndividualB;
		individualsIndex.remove(parentB);

		int randomIndividualC = randomGenerator.nextInt(individualsIndex.size());
		parentC = randomIndividualC;
		individualsIndex.remove(parentC);
		
		// Store the random selected index values of the individuals
		randomParentsIndex[0] = parentA;
		randomParentsIndex[1] = parentB;
		randomParentsIndex[2] = parentC;

		return randomParentsIndex;
	}

	/**
	 * This method is responsible for performing the mutation on the selected parent individuals and returning a noise Vector 
	 * that consists of the mutated vector. The mutation is applied using vi <- Xa + F * (Xc - Xb) where Vi is the 
	 * noise vector, Xa Xb Xc are parents and F is the mutation constant. Has a complexity of O(n).
	 * @param newPopulation is a 2D arraylist that consists of the initial population of the selected problem
	 * @param i is the current index number for the individual that is going to go through mutation, crossover and selection
	 * @param randomParents an array of randomly selected parent indexes 
	 * @param lowerBound is the lower range value of the selected function
	 * @param upperBound is the upper range value of the selected function
	 * @return the mutated vector called noiseVector
	 */
	public static List<Double> mutation(List<List<Double>> newPopulation, int i, int[] randomParents, double lowerBound, double upperBound) {
		// Noise Vector Deceleration
		List<Double> noiseVector = new ArrayList<Double>();
		
		for (int c = 0; c < PROBLEM_DIMENSIONALITY; c++) {
			// Calculating the noise vector using vi <- Xa + F * (Xc - Xb) where Vi is the noise vector, Xa Xb Xc are parents and F is the mutation constant
			noiseVector.add(c, ((newPopulation.get(randomParents[2]).get(c) - newPopulation.get(randomParents[1]).get(c)) * MUTATION_CONSTANT) + newPopulation.get(randomParents[0]).get(c));
			
			// This bellow if statement is making sure when the elements in the noise vector are calculated that then do not pass the lower and the upper bounds 
			if (noiseVector.get(c) > upperBound) {
				noiseVector.set(c, upperBound);
			} else if (noiseVector.get(c) < lowerBound) {
				noiseVector.set(c, lowerBound);
			}
		}
		return noiseVector;

	}

	/**
	 * This method is responsible for determine which parameters in the individual are going to be crossed over to the new trial vector
	 * if any. Depending on the crossover rate either the parameter value from the noise vector is selected to be put into the trial vector
	 * or the parameter value is selected to put into the trial vector. Has a complexity of O(n).
	 * @param newPopulation is a 2D arraylist that consists of the initial population of the selected problem
	 * @param noiseVector stores the mutated vector 
	 * @param i is the current index number for the individual that is going to go through mutation, crossover and selection
	 * @return the noise vector which crossover has been applied 
	 */
	public static List<Double> crossover(List<List<Double>> newPopulation, List<Double> noiseVector, int i) {
		// Trial Vector Deceleration
		List<Double> trialVector = new ArrayList<Double>();
		// Random Object 
		Random randomGenerator = new Random();
		
		// Storing a random number between 0 and PROBLEM_DIMENSIONALITY
		int jrand = randomGenerator.nextInt(PROBLEM_DIMENSIONALITY);
		
		// Applying Crossover 
		for (int j = 0; j < PROBLEM_DIMENSIONALITY; j++) {
			// When a random value between 0-1 is less then the crossover rate or when the jrand is equal to the j index ... 
			if (Math.random() < CROSSOVER_RATE || j == jrand) {

				// The trial vector parameter is equal to the noise vector
				trialVector.add(j, noiseVector.get(j));
			} else {
				// The trial vector parameter is equal to the initial individual in initial population
				trialVector.add(j, newPopulation.get(i).get(j));
			}
		}
		return trialVector;
	}

	/**
	 * This method is responsible for selecting the individuals that will be stored in the new population by comparing the cost
	 * values of the trial vector and the initial individuals cost value. Has a complexity of O(n).
	 * @param newPopulation is a 2D arraylist that consists of the initial population of the selected problem
	 * @param trialVector which consists of the crossovered vector   
	 * @param benchmarkSelection is the integer value that the user inputs, which is used to select the function
	 * @param i is the current index number for the individual that is going to go through mutation, crossover and selection
	 * @return the calculated fitness value dependent on the comparision
	 */
	public static double selection(List<List<Double>> newPopulation, List<Double> trialVector, int benchmarkSelection, int i) {

		// Variable Deceleration 
		double fitness = 0.0;
		// Calculating the fitness value of the trial vector and the individual vector 
		double trialVectorCost = Cost_Evaluator.cost_Evaluation(trialVector, benchmarkSelection);
		double individualVectorCost = Cost_Evaluator.cost_Evaluation(newPopulation.get(i), benchmarkSelection);
		
		if (trialVectorCost <= individualVectorCost) {
			// Use the trial vector as the new individual only if the cost of the trial vector is less then that of the individual
			newPopulation.set(i, trialVector);
			fitness = trialVectorCost;

		} else {
			// Don't Change the initial population if the cost value of the trial vector is greater then that of the initial individual
			newPopulation.set(i, newPopulation.get(i));
			fitness = individualVectorCost;
		}
		return fitness;

	}
}
