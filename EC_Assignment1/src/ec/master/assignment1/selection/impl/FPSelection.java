package ec.master.assignment1.selection.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ec.master.assignment1.model.Individual;
import ec.master.assignment1.selection.Selector;

public class FPSelection implements Selector {
	
	public List<Individual> doSelection(List<Individual> individuals, int groupSize, int resultSize) {
		Collections.shuffle(individuals);
		ArrayList<Individual> selectedIndividuals = new ArrayList<Individual>(resultSize);
		double sum = 0;
		//sum all the fitness of each individual
		for(int i=0;i<individuals.size();i++){
			sum += individuals.get(i).getFitness();
		}
		Random random = new Random();
		double compare;
		
		//select populationSize individuals
		for (int p = 0; p < resultSize; p++) {
			compare =  random.nextDouble()*sum;
			for(int i=0;i<individuals.size();i++){
				compare -= individuals.get(i).getFitness();
				//choose individual when compared value is >former fitness && <later fitness
				if(compare<=0){
					selectedIndividuals.add(individuals.get(i));
					break;
				}
			}
		}
		
		return selectedIndividuals;
		
	}
}
