import java.util.Arrays;
import java.util.Random;




public class Main {
	
	public static void main(String arg[]){
		
		QueenGA algorithm = new QueenGA();
		
	
		
		int[] solution= new int[8];
				
		//Running the full genetic algorithm in order until termination criteria is satisfied.
		boolean terminate = false;
		
		//Creating initial population at generation 0
		algorithm.population = algorithm.initialPop();
		int generations = 0;
		
		while(!terminate){
				
		algorithm.calculateFitness();
		
		//TERMINATION CRITERIA: Checking for solution to find maximum fitness of 28
		for (int i=0; i<algorithm.populationSize ; i++){
		
			if (algorithm.population[i].fitness==28){
				solution = algorithm.population[i].genes.clone();
				terminate=true;
				break;
			}
		}
		int index = 0;
			//CROSSOVER and MUTATION
			do{
				if(index<algorithm.populationSize-1){
					Chromosome [] crossover = algorithm.crossover();
					algorithm.offSpring[index] = crossover[0];
					algorithm.offSpring[index] = algorithm.mutation(algorithm.offSpring[index]);
					index++;
					algorithm.offSpring[index] = crossover[1];
					algorithm.offSpring[index] = algorithm.mutation(algorithm.offSpring[index]);
					index++;
				}
						
				
			}while(index<algorithm.populationSize);
		
		
			
			//Making the offspring the current population
		algorithm.population = algorithm.offSpring;
		
		System.out.println("generation: " + generations);
		generations++;
		}
		
		System.out.println("SOLUTION: " + Arrays.toString(solution) + " after " + generations + " generations");
		
		
		//Printing the solution on chess board
		for (int p=0; p<8; p++){
			for(int q=0; q<8; q++){
				if(solution[p]==q){
					System.out.print("Q ");
				}else
				{
					System.out.print("+ ");
				}
			}
			System.out.print("\n");
			
		}
		
			
		
		
		
	}
}
