package ec.master.assignment1.selection.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ec.master.assignment1.model.Individual;
import ec.master.assignment1.selection.Selector;

public class TournamentSelection implements Selector {

	public List<Individual> doSelection(List<Individual> inds, int groupSize, int resultSize) {
		List<Individual> selected = new ArrayList<Individual>();
		List<Individual> compared = new ArrayList<Individual>(groupSize);

		// Collections.shuffle(inds);

		Random random = new Random();
		// groupSize = random.nextInt(inds.size()+1);
		// if(groupSize==0){
		// groupSize=1;
		// }
		// Collections.sort(inds);
		for (int i = 0; i < resultSize; i++) {

			// for(int c=0;c<selected.size();c++){
			// for(int f=0;f<compared.size();f++){
			// if(selected.get(c).getFitness()!=compared.get(f).getFitness()){
			// selected.add(compared.get(f));
			// inds.remove(compared.get(f));
			// break;
			// }
			// }
			//
			// }
			for (int j = 0; j < groupSize; j++) {
				compared.add(inds.get(random.nextInt(inds.size())));
			}

			Collections.sort(compared);
			if (random.nextDouble() <= 1) {//0.1 for 51, 0.3 for 442, 0.129 for 105
				selected.add(compared.get(0));
				// inds.remove(compared.get(0));
				compared.clear();
			} else {

				ArrayList<Individual> elsecompared = new ArrayList<Individual>(inds);
				for (int j = 0; j < compared.size(); j++) {
					elsecompared.remove(compared.get(j));
				}
				Collections.sort(elsecompared);
				if (compared.get(compared.size() - 1).getFitness() < elsecompared.get(0).getFitness()) {
					selected.add(compared.get(compared.size() - 1));
				} else {
					selected.add(elsecompared.get(0));
				}
				
				// selected.add(elsecompared.get(0));

				// inds.remove(compared.get(1));
				 compared.clear();
				// int r = random.nextInt(elsecompared.size());
				// selected.add(elsecompared.get(r));
				// inds.remove(inds.get(r));
			}
		}

		return selected;
	}
}
