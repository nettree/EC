package ec.master.assignment1.selection.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ec.master.assignment1.model.Individual;
import ec.master.assignment1.selection.Selector;

/**
 * @ClassName: TournamentSelection
 * @Description: implementation of tournament selection
 * @date 17/08/2015 11:15:33 pm
 * @return selected list
 */
public class TournamentSelection implements Selector {

	public List<Individual> doSelection(List<Individual> inds, int groupSize, int resultSize) {
		List<Individual> selected = new ArrayList<Individual>();
		List<Individual> compared = new ArrayList<Individual>(groupSize);
		Random random = new Random();
		//randomly choose group size individuals
		for (int i = 0; i < resultSize; i++) {
			for (int j = 0; j < groupSize; j++) {
				compared.add(inds.get(random.nextInt(inds.size())));
			}
			//sort the array and choose the best one from the group 
			Collections.sort(compared);
			selected.add(compared.get(0));
			//clear the group in order to do the next round
			compared.clear();
		}
		return selected;
	}
}
