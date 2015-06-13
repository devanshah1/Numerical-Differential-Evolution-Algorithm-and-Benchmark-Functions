import java.util.List;

/**
 * This class is used to select the correct functions cost calculation function also know
 * as the fitness value.
 * @author Devan Shah 100428864, Parth Patel 100392782, Ravikumar Patel 100423830
 * 
 */
public class Cost_Evaluator {
	
	/**
	 * This method is responsible for selecting the correct benchmark function and calculating the cost value 
	 * of the given benchmark function, using the provided list of individuals the cost is calculated on the 
	 * parameters of each individual. Each of the functions have designated function that is used to calculate 
	 * the cost value. The cost value determines if the population with applied mutation and crossover is more
	 * fit then the initial population. The new population generation is sole depend on the cost calculation
	 * and comparisons. 
	 * 
	 * @param individual is an List that stores the parameters of that individual, which is used to calculate the
	 * 					 the cost values
	 * @param benchmarkSelection is the integer value that the user inputs, which is used to select the function
	 * @return the cost value that is calculated depending on the selected function
	 */
	public static double cost_Evaluation(List<Double> individual, int benchmarkSelection) {
		
		// Variable Definition
		double cost = 0;
		
		// Switch case used to select the correct function's cost function according to the users input. Has a complexity of O(n).
		switch (benchmarkSelection) {
			
			case 1:
				// Cost evaluation for Function 1 - 1st De Jong. Has a complexity of O(n).
				for (int i = 0; i < individual.size(); i++) {
					cost += Math.pow((individual.get(i)), 2);
				}
				break;
			case 2:
				// Cost evaluation for Function 2 - Axis Parallel Hyper-Ellipsoid. Has a complexity of O(n).
				for (int i = 1; i <= individual.size(); i++) {
					cost += (i) * Math.pow((individual.get(i-1)), 2);
				}
				break;
			case 3:
				// Cost evaluation for Function 3 - Schwefel's Problem 1.2. Has a complexity of O(n^2).
				for (int i = 0; i < individual.size(); i++) {

					double innerSum = 0;
					
					for (int j = 0; j <= i; j++) {
						innerSum += individual.get(i);
					}
					cost += Math.pow(innerSum, 2);
				}
				break;
			case 4:
				// Cost evaluation for Function 4 - Rosenbrock's Valley. Has a complexity of O(n).
				for (int i = 0; i < individual.size() - 1; i++) {
					cost += (100 * Math.pow((individual.get(i + 1) - Math.pow(individual.get(i), 2)),2) + Math.pow((1 - individual.get(i)), 2));
				}
				break;
			case 5:
				double innerSum_Rastrigin = 0;
				// Cost evaluation for Function 5 - Rastrigin's Function. Has a complexity of O(n).
				for (int i = 0; i < individual.size(); i++) {
					innerSum_Rastrigin += Math.pow(individual.get(i), 2) - (10 * Math.cos(2 * Math.PI * individual.get(i)));
				}
				cost = 10 * individual.size() + innerSum_Rastrigin;
				break;
			}
		// return the calculated cost value
		return cost;
	}
}
