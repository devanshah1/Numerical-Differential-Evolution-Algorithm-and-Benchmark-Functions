import java.text.DecimalFormat;
import java.util.*;

public class QueenGA {
	// rounding
	DecimalFormat dformat = new DecimalFormat("##.##");

	// Maximum possible collisions is 28
	final int MAXFITNESS = 28;

	// Total fitness for population
	int totalFitness = 0;

	// GA Parameters
	final int populationSize = 30;
	final int nGenes = 8;//Fixed parameter
	final double crossoverProbablity = 0.7;
	final double mutationProbability = 0.12;

	Chromosome[] population = new Chromosome[populationSize];//Current Population
	Chromosome[] offSpring = new Chromosome[populationSize];


	//Creating initial populations by randomizing the placement of values 0 to 7 in the chromosome genes
	public Chromosome[] initialPop() {
		List<Chromosome> initial = new ArrayList<Chromosome>();

		for (int i = 0; i < populationSize; i++) {
			int[] x = new int[nGenes];
			Random randGenerator = new Random();
			
			for (int j = 0; j < nGenes; j++) {
				int randomInt = randGenerator.nextInt(8);
				x[j] = randomInt;
				
			}

			initial.add(new Chromosome(x));

		}
		return listToGenes(initial);

	}

	//Converting a list of chromomes in into an array of chromosomes that depicts the population
	public Chromosome[] listToGenes(List<Chromosome> array) {
		Chromosome[] cArray = new Chromosome[populationSize];
		for (int i = 0; i < populationSize; i++) {
			Chromosome c = array.get(i);
			cArray[i] = c;
		}
		return cArray;

	}

	// Assigning fitness values and fitness ratio to the correspondning chromosomes and their fitness ratio
	public void calculateFitness(){
		totalFitness = 0;
		if (population!=null){
			for (int p = 0; p < populationSize; p++) {
				population[p].fitness = fitnessValue(population[p]);
				totalFitness += population[p].fitness;
			}

		
			for (int p = 0; p < populationSize; p++) {
				population[p].fRatio = (((double) population[p].fitness) / (double) totalFitness) * 100;
			}
		
		}

	}

	//Uses the fitness function to calculate the fitness value
	public int fitnessValue(Chromosome a){
		int s = 0;
		for (int i = 0; i < nGenes; i++) {
			for (int j = 0; j < nGenes; j++) {
				if (isColliding(a.genes[i], i, a.genes[j], j)) {
					s++;
				}
			}
		}
		//Removing duplicate counted collisions
		if(s>0){
			s = (s - 8) / 2;
		}
		
		//Max number of possible collisions is 28 
		return (MAXFITNESS - s);
	}

	// Returns true if the two queens compared are colliding
	public boolean isColliding(int row1, int col1, int row2, int col2) {

		//The expression checks that no two queens share the same row, column, and are not diagonal to each other 
		return (row1 != row2 && col1 != col2 && (row1 - row2) != (col1 - col2)
				&& (row1 - row2) != (col2 - col1) ? false : true);

	}

	// Crossover operator on chromosomes
	public Chromosome[] crossover() {
		
		//Selecting parents using the roulette wheel
		Chromosome parent1 = rouletteWheel();
		Chromosome parent2 = rouletteWheel();

		Chromosome[] offspring = new Chromosome[2];

		Random randGenerator = new Random();
		if (crossoverProbablity >= randGenerator.nextDouble()) {
			int[] temp = new int[8];

			int breakPoint = randGenerator.nextInt(4) + 3;
			for (int i = 7; i > breakPoint; i--) {
				// Swapping genes from the selected break point of the chromosomes from both parents
				temp[i] = parent1.genes[i];
				parent1.genes[i] = parent2.genes[i];
				parent2.genes[i] = temp[i];
			}

			offspring[0] = parent1;
			offspring[1] = parent2;
			return offspring;
		}

		else {
			//Cloning parents
			offspring[0] = parent1;
			offspring[1] = parent2;
			return offspring;
		}

	}
	
	//Implementing the roulette wheel using the data in the current population
	public Chromosome rouletteWheel() {
		Random randGenerator = new Random();
		double roulette = randGenerator.nextDouble() * 100;
		roulette = Double.valueOf(dformat.format(roulette));
		double interval1 = 0;
		double interval2 = 0;
		
		int index=0;
		for (int i=0; i<populationSize; i++){
			interval1 = interval2;
			if(interval2<100){
				interval2 += population[i].fRatio;
			}else if(interval2>100){
				interval2=100;
			}
			if(roulette>=interval1 && roulette<interval2){
				index=i;
				break;
			}
		}
		
		return population[index];

	

	}
	

	// Mutation operator on chromosome
	public Chromosome mutation(Chromosome m) {
		Random randGenerator = new Random();
		Chromosome mutated = m;
		if(mutationProbability>=randGenerator.nextDouble()){
			//Selecting random gene from the chromosome
			int randomGene = randGenerator.nextInt(nGenes);
			//Selecting a random valid gene between 0-7
			int randomValue = randGenerator.nextInt(8);
			mutated.genes[randomGene] = randomValue;
		}
		

		return mutated;

	}

}