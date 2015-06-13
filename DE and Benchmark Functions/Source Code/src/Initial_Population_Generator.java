import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to generate the initial population, of size 60 by 30, which then is used to 
 * optimize a functions.
 * @author Devan Shah 100428864, Parth Patel 100392782, Ravikumar Patel 100423830
 * 
 */
public class Initial_Population_Generator {
	// Static variable definitions 
	static final int PROBLEM_DIMENSIONALITY = 30;
	static final int POPULATION_SIZE = 2 * PROBLEM_DIMENSIONALITY;

	/**
	 * This method is responsible for generating the initial population before the Differential Evolution
	 * Algorithm is applies. This method only generates number that are with in the given range. 
	 * @param lowerBound is an double used to store the lower bound of the range
	 * @param upperBound is an double used to store the upper bound of the range
	 * @return the 2D arraylist of the generated initial population
	 */
	public static List<List<Double>> Generate_Initial_Population(double lowerBound, double upperBound) {
		
		// Variable Definition
		double lowerRangeBound = lowerBound;
		double upperRangeBound = upperBound;
		
		// ArrayList Definitions
		List<List<Double>> generatedPopulation = new ArrayList<List<Double>>();
		List<Double> parameter;
		
		// This for loop is used to fill the generatedpopulation Array with an initial population. Has a complexity of O(n^2).
		for (int j = 0; j < POPULATION_SIZE; j++) {
			
			/* 
			 * Initializing the parameters array in side the for loop because it needs to refresh 
			 * every time the loop runs 
			 */ 
			parameter = new ArrayList<Double>();
			
			// This for loop is used to fill the parameter array 
			for (int i = 0; i < PROBLEM_DIMENSIONALITY; i++) {
				parameter.add((Math.random() * (2 * upperRangeBound) + lowerRangeBound));
			}
			// Add the filled parameter array to the 2D array of generated populations
			generatedPopulation.add(parameter);
		}
		return generatedPopulation;
	}
}
