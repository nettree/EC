/**   
* @Title: MutatorFactory.java 
* @Package ec.master.assignment1.mutation.factory 
* @Description: TODO
* @author David Fa
* @date 16/08/2015 3:46:41 pm 
* @version V1.0   
*/
package ec.master.assignment1.mutation.factory;

import java.util.Random;

import ec.master.assignment1.mutation.Mutator;
import ec.master.assignment1.mutation.impl.InsertMutator;
import ec.master.assignment1.mutation.impl.InversionMutator;
import ec.master.assignment1.mutation.impl.ScrambleMutator;
import ec.master.assignment1.mutation.impl.SwapMutator;

/**
 * @ClassName: MutatorFactory
 * @Description: The factory of mutator which generates mutator instance
 * @date 16/08/2015 3:46:41 pm
 * 
 */
public class MutatorFactory {
	
	private final String[] mutators = new String[] { "insert", "swap", "inversion", "scramble" };
	
	/**
	 * 
	* @Title: createMutator
	* @Description: The method of mutator generation
	* @param @param type
	* @param @return    
	* @return Mutator   
	* @throws
	 */
	public Mutator createMutator(String type) {
		if (mutators[0].equalsIgnoreCase(type)) {
			return chooseMutator(0);
		} else if (mutators[1].equalsIgnoreCase(type)) {
			return chooseMutator(1);
		} else if (mutators[2].equalsIgnoreCase(type)) {
			return chooseMutator(2);
		} else if (mutators[3].equalsIgnoreCase(type)) {
			return chooseMutator(3);
		} else if ("random".equalsIgnoreCase(type)) {
			Random rand = new Random();
			return chooseMutator(rand.nextInt(mutators.length));
		} else {
			return chooseMutator(0);
		}
	}
	
	/**
	 * 
	* @Title: chooseMutator
	* @Description: The method which choose the mutator according to type
	* @param @param type
	* @param @return    
	* @return Mutator   
	* @throws
	 */
	private Mutator chooseMutator(int type) {
		switch(type) {
		case 0:
			return new InsertMutator();
		case 1:
			return new SwapMutator();
		case 2:
			return new InversionMutator();
		case 3:
			return new ScrambleMutator();
		}
		return null;
	}
}
