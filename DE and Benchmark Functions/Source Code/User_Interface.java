import java.util.Scanner;

/**
 * This class is used to coordinate with the the Benchmark_Function_Selecter depending on the users input.
 * Is used as an user interface with the rest of the program, depending on what the user inputs the according
 * steps and data is analyzed to obtain suitable results.
 * @author Devan Shah 100428864, Parth Patel 100392782, Ravikumar Patel 100423830
 * 
 */
public class User_Interface {
	
	/**
	 * This method is the main method of the entire program, this is the starting point of the program.
	 * Allows the user to select the options according to the results that the user wants such as, 
	 * if the user wants the performance graph for one of the functions he/she selects option 1 and 
	 * if the user wants the mean and standard deviation he/she selects option 2. Once the option is
	 * selected and the desired function is selected the Benchmark_Function_Selection method is used 
	 * to start the Differential Evolution process.
	 * @param args
	 */
	public static void main(String[] args) { 
		// Variable Definition
		int selection = 0; // Function selection 1 through 5 
		int runs = 0; // number of runs either 1 or 50 depending on selection (graph or mean and  standard deviation)
		int optionSelection; // either 1 or 2 (1 graphing option or 2 mean and standard deviation option)
		
		// Initializing the scanner  
		Scanner userInput = new Scanner(System.in);
		
		// First selection option the user selects 
		System.out.println(" 1. Construct a performance graph for a Benchmark Function of your choice \n" + 
						   " 2. Construct the mean and the Standard Devation of a Benchmark Function of your choice for 50 runs");
		// Store the input in optionSelection
		optionSelection = userInput.nextInt();
		
		// Depending on the input of the user select the following options
		if (optionSelection == 1) {
			System.out.println(" Select one of the 5 benchmark functions to be opmitized: \n" + 
					   " 1. 1st De Jong \n" +
					   " 2. Axis parallel Hyper-Ellipsoid \n" +
					   " 3. Schwefel's Problem 1.2 \n" + 
					   " 4. Rosenbrock's Valley \n" +
					   " 5. Rastrigin's Function \n");
			selection = userInput.nextInt();
			System.out.println("Processing Please Wait (approximately 50s to 80s) ...... \n");
			runs = 1;
			
		}
		else if (optionSelection == 2){
			System.out.println(" Select one of the 5 benchmark functions to be opmitized: \n" + 
					   " 1. 1st De Jong \n" +
					   " 2. Axis parallel Hyper-Ellipsoid \n" +
					   " 3. Schwefel's Problem 1.2 \n" + 
					   " 4. Rosenbrock's Valley \n" +
					   " 5. Rastrigin's Function \n");
			
			selection = userInput.nextInt();
			System.out.println("Processing Please Wait (approximately 10min to 20min) ...... \n");
			runs=50;
		}
		
		userInput.close();
		
		final long startTime = System.nanoTime();
		
		// Pass the user selection, user optionSelection and the number of runs to the Benchmark_Function_Selection function
		Benchmark_Function_Selecter.Benchmark_Function_Selection(selection, runs, optionSelection);
		
		// Calculating the time taken to complete the selected option 
		System.out.println("Time Taken to run the selected option: " + (System.nanoTime() - startTime)*(1.0e-9) + "s");
		
	}
}
