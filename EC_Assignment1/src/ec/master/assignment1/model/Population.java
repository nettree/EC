package ec.master.assignment1.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ec.master.assignment1.algorithms.TSPProblem;
import ec.master.assignment1.city.City;
import ec.master.assignment1.crossover.Crossover;
import ec.master.assignment1.crossover.factory.CrossoverFactory;
import ec.master.assignment1.mutation.Mutator;
import ec.master.assignment1.mutation.factory.MutatorFactory;
import ec.master.assignment1.selection.Selector;
import ec.master.assignment1.selection.factory.SelectorFactory;


/**
 * 
* @ClassName: Population
* @Description: The population in an evolutionary algorithms represents a set of solutions
* @author David Fa
* @date 26/08/2015 10:20:53 pm
*
 */
public class Population {

	List<Individual> individuals = new ArrayList<Individual>();
	String dataType;
	Individual lastBest = null;
	boolean elite = false;
	boolean selected = false;
	private int average;
	private double standardDeviation;

	public Population(List<City> cityList, int popSize, String dataType, boolean elite) {
		this.dataType = dataType;
		this.elite = elite;
		for (int i = 0; i < popSize; i++) {
			Individual individual = new Individual(cityList, true);
			individuals.add(individual);
		}
	}
	
	/**
	* @Title: mutate
	* @Description: To implement mutation operation
	* @param mutatorType
	* @param p    
	* @return void   
	* @throws
	 */
	public void mutate(String mutatorType, double p) {
		MutatorFactory mFactory = new MutatorFactory();
		Mutator mutator = mFactory.createMutator(mutatorType);
		if (mutator == null) {
			TSPProblem.log.errorException("java.lang.NullPointerException");
			return;
		}
		Random rand = new Random();
		for (Individual individual : individuals) {
			if (rand.nextDouble() < p) {
				mutator.doMutate(individual);
				individual.updateFitness();
			}
		}
	}
	
	/**
	 * 
	* @Title: crossover
	* @Description: To implement crossover operation
	* @param crossoverType
	* @param p    
	* @return void   
	* @throws
	 */
	public void crossover(String crossoverType, double p) {
		
		Random rand = new Random();
		
		if (elite) {
			if (rand.nextDouble() <= 0.8) {
				lastBest = new Individual(getBest().getCityList());
			}
		}
		
		
		CrossoverFactory cFactory = new CrossoverFactory();
		Crossover crossover = cFactory.createCrossover(crossoverType);
		ArrayList<Children> childrenList = new ArrayList<Children>();
		for (int i = 0; i < individuals.size() - 1; i ++) {
//			int randA = rand.nextInt(individuals.size() - 1);
//			int randB = rand.nextInt(individuals.size() - 1);
			SelectorFactory sFactory = new SelectorFactory();
			Selector selector = sFactory.createSelector("tournament");
			ArrayList<Individual> pa = new ArrayList<Individual>(selector.doSelection(individuals, 10, 1));
			ArrayList<Individual> pb = new ArrayList<Individual>(selector.doSelection(individuals, 10, 1));
			if (rand.nextDouble() < p) {
				
				Children child = crossover.doCrossover(pa.get(0), pb.get(0));
//				Children child = crossover.doCrossover(individuals.get(randA), individuals.get(randB));
				childrenList.add(child);
			} else {
				Children child = new Children(pa.get(0),pb.get(0));
//				Children child = new Children(individuals.get(randA), individuals.get(randB));
				childrenList.add(child);
			}
		}
		for (Children child : childrenList) {
			Individual indiA = child.getChildA();
			Individual indiB = child.getChildB();
			if (indiA != null)
				individuals.add(indiA);
			if (indiB != null)
				individuals.add(indiB);
		}
	}
	
	/**
	 * 
	* @Title: select
	* @Description: To implement select operation
	* @param @param selectionType
	* @param @param popSize    
	* @return void   
	* @throws
	 */
	public void select(String selectionType, int popSize){
		SelectorFactory sFactory = new SelectorFactory();
		Selector selector = sFactory.createSelector(selectionType);
		if (elite) {
			individuals = selector.doSelection(individuals, 10, popSize - 1);
			individuals.add(lastBest);
		} else {
			individuals = selector.doSelection(individuals, 10, popSize);
		}
		individuals.add(lastBest);
//		bestList.clear();
//		Collections.sort(individuals);
//		return individuals.get(0).getFitness();
	}
	
	/**
	 * 
	* @Title: getBest
	* @Description: To loop the set of individual array to gain the best individual
	* @param @return    
	* @return Individual   
	* @throws
	 */
	public Individual getBest() {
		int best = -1;
		Individual bestIndividual = null;
		for (Individual individual : individuals) {
			if (best == -1) {
				best = individual.getFitness();
				bestIndividual = individual;
			} else if (best > individual.getFitness()) {
				best = individual.getFitness();
				bestIndividual = individual;
			}
		}
		return bestIndividual;
	}
	
	public List<Individual> getIndividuals(){
		return individuals;
	}
	
	public void setIndividuals(List<Individual> individuals) {
		this.individuals = individuals;
	}
	
	public int getAverage() {
		int fitness = 0;
		for (Individual individual : individuals) {
			fitness += individual.getFitness();
		}
		average = fitness / individuals.size();
		return average;
	}
	
	public double getStandardDeviation() {
		double result = 0;
		for (int i = 0; i < individuals.size(); i++) {
			result += Math.pow((individuals.get(i).getFitness() - average), 2);
		}
		standardDeviation = Math.sqrt(result / (individuals.size() - 1));
		return standardDeviation;
	}
}
