package ec.master.assignment1.selection.factory;

import ec.master.assignment1.selection.Selector;
import ec.master.assignment1.selection.impl.ElitismSelection;
import ec.master.assignment1.selection.impl.FPSelection;
import ec.master.assignment1.selection.impl.TournamentSelection;

/**
 * @ClassName: SelectionFactory
 * @Description: TODO
 * @date 19/08/2015 12:16:03 pm
 * 
 */
public class SelectorFactory {
	public Selector createSelector(String type) {
		if ("tournament".equalsIgnoreCase(type)) {
			return new TournamentSelection();
		} else if ("elitism".equalsIgnoreCase(type)) {
			return new ElitismSelection();
		} else if ("fp".equalsIgnoreCase(type)) {
			return new FPSelection();
		} else {
			return new TournamentSelection();
		}
	}
}
