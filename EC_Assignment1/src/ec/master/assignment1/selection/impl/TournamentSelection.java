package ec.master.assignment1.selection.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import ec.master.assignment1.model.Individual;
import ec.master.assignment1.selection.Selector;

public class TournamentSelection implements Selector {
	
	public ArrayList<Individual> doSelection(ArrayList<Individual> inds, int groupSize, int resultSize) {
		ArrayList<Individual> selected = new ArrayList<Individual>();
		ArrayList<Individual> compared = new ArrayList<Individual>(groupSize);
		Collections.sort(inds);
		Random random = new Random();
		for (int i = 0; i < resultSize; i++) {
			for(int j=0;j<groupSize;j++){
				compared.add(inds.get(random.nextInt(resultSize / 2)));
			}
			//find the lowest fitness
//			int com = Integer.MAX_VALUE;
//			int index = 0;
//			for(int j=0;j<compared.size();j++){
//				if(compared.get(i).getFitness()<=com){
//					com = compared.get(i).getFitness();
//					index = j;
//				}
//			}
//			selected.add(compared.get(index));
//			 
//			inds.remove(compared.get(index));
			Collections.sort(compared);
			selected.add(compared.get(0));
			inds.remove(compared.get(0));
			compared.clear();
		}
		
		return selected;
	}
}
