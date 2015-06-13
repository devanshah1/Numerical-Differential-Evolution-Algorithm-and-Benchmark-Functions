Numerical-Differential-Evolution-Algorithm-and-Benchmark-Functions
==================================================================

Intro to Artifical Intelligen Final project 

Comprehensive explanation of Differential Evolution
The differential evolution consists of certain steps that need to be followed to achieve the correct solution. The steps include initial population generation, fitness calculation, selecting 3 parents, applying mutation, applying crossover and selection between initial population and mutated population.

Step 1: Generating the initial population depending on the problem dimensionality and also the problem size.

Step 2: Calculate the fitness value of each of the individuals that are in the initial population.

Step 3: Select the first individual in the initial population, which is going to run through the differential evolution algorithm.

Step 4: Select three random parent individuals from the initial population that are not the same and also not the individual that that the differential evolution algorithm is being applied on.

Step 5: Apply mutation on the 3 selected parents from step 4 with the use of the mutation equation of
vi <- Xa + F * (Xc - Xb) where vi is the noise vector, F is the mutation constant and Xa,Xb and Xc are the randomly generated parents individuals. Store the mutated population in the noise vector for crossover to be applied on to it.

Step 6: Apply crossover on the noise vector that was retrieved from step 5. Crossover is calculated using a comparison between a randomly generated number between 0 and 1 to the crossover rate; crossover is applied to every parameter in the individual. Also since crossover has to occur at least once there is a second condition for crossover which is generating another number between the 1 and the problem dimensionality and setting it equal to a variable j. So if any of those 2 conditions are true then the element of the noise vector is selected and put into a trial vector, else the parameter from the initial population is selected and put into the trial vector. Once all of the elements have been checked and selected according and the trial vector is filled.

Step 7: Apply Selection between the trial vector and the initial population individual. The selection is done by calculating the cost value of the trial vector and comparing the cost value to the cost value of the initial population. Since the algorithm is set up to minimize the problem we check to see if the cost of the trial vector is smaller than or equal to that of the initial population, if it is then the trial vector selected and added to the new population, else the initial population individual is left unchanged.
