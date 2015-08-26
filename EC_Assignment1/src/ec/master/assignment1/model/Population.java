package ec.master.assignment1.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ec.master.assignment1.TSPProblem;
import ec.master.assignment1.city.City;
import ec.master.assignment1.crossover.Crossover;
import ec.master.assignment1.crossover.factory.CrossoverFactory;
import ec.master.assignment1.mutation.Mutator;
import ec.master.assignment1.mutation.factory.MutatorFactory;
import ec.master.assignment1.selection.Selector;
import ec.master.assignment1.selection.factory.SelectorFactory;

/**
 * @ClassName: Population
 * @Description: TODO
 * @date 15/08/2015 3:25:15 pm
 * 
 */
public class Population {

	ArrayList<Individual> individuals = new ArrayList<Individual>();
	String dataType;

	public Population(ArrayList<City> cityList, int popSize, String dataType) {
		this.dataType = dataType;
		for (int i = 0; i < popSize; i++) {
			Individual individual = new Individual(cityList, true);
			individuals.add(individual);
		}
	}
	
	public void mutate(String mutatorType, double p) {
//		ArrayList<Individual> indis = new ArrayList<Individual>();
		MutatorFactory mFactory = new MutatorFactory();
		Mutator mutator = mFactory.createMutator(mutatorType);
		if (mutator == null) {
			TSPProblem.log.errorException("java.lang.NullPointerException");
			return;
		}
		
		for (Individual individual : individuals) {
//			Individual clone = new Individual(individual.getCityList(), true);
//			indis.add(clone);
			TSPProblem.log.debug(individual.toString());
			mutator.doMutate(individual);
			TSPProblem.log.debug(individual.toString());
			individual.updateFitness();
//			TSPProblem.log.debug(clone.toString());
		}
//		for (Individual individual: indis) {
//			individuals.add(individual);
//		}
//		System.out.println(individuals.size());
	}
	
	public void crossover(String crossoverType, double p) {
		CrossoverFactory cFactory = new CrossoverFactory();
		Crossover crossover = cFactory.createCrossover(crossoverType);
		ArrayList<Children> childrenList = new ArrayList<Children>();
		Random rand = new Random();
		for (int i = 0; i < individuals.size() - 1; i+=2) {
			if (rand.nextDouble() < p) {
				Children child = crossover.doCrossover(individuals.get(i), individuals.get(i + 1), dataType);
				childrenList.add(child);
			} else {
				Children child = new Children(individuals.get(i), individuals.get(i + 1));
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
//		System.out.println(individuals.size());
	}
	
	public int select(String selectionType, int popSize){
		SelectorFactory sFactory = new SelectorFactory();
		Selector selector = sFactory.createSelector(selectionType);
		// temporary size
		individuals = selector.doSelection(individuals, 5, popSize);
//		TSPProblem.log.debug("Result:");
//		int best = individuals.get(0).getFitness();
//		for (int i = 1; i < individuals.size(); i++) {
//			int fitness = individuals.ge0-----t(i).getFitness();
//			if (best > fitness) {
//				best = fitness;
//			}
//		}
		Collections.sort(individuals);
		return individuals.get(0).getFitness();
	}
	
	public List<Individual> getIndividuals(){
		return individuals;
	}
}
