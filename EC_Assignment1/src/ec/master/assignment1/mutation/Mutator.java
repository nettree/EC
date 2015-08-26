package ec.master.assignment1.mutation;

import ec.master.assignment1.model.Individual;

/**
 * @ClassName: Mutator
 * @Description: To define the interface for all mutator to implementations
 * @date 16/08/2015 3:53:00 pm
 * 
 */
public interface Mutator {
	public Individual doMutate(Individual individual);
}
