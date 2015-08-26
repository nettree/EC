package ec.master.assignment1.selection.impl;

import java.util.ArrayList;
import java.util.Collections;

import ec.master.assignment1.model.Individual;
import ec.master.assignment1.selection.Selector;

public class ElitismSelection implements Selector {

	public ArrayList<Individual> doSelection(ArrayList<Individual> individuals, int groupSize, int resultSize) {
		// TODO Auto-generated method stub
		Collections.sort(individuals);
		individuals.subList(0, resultSize - 1);
		return individuals;
	}

}
