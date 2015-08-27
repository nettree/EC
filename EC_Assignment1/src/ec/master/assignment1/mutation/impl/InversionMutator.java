/**   
* @Title: InversionMutator.java 
* @Package ec.master.assignment1.mutation.impl 
* @Description: TODO
* @author David Fa
* @date 16/08/2015 3:47:58 pm 
* @version V1.0   
*/
package ec.master.assignment1.mutation.impl;

import java.util.Collections;
import java.util.Random;

import ec.master.assignment1.model.Individual;
import ec.master.assignment1.mutation.Mutator;

/**
 * @ClassName: InversionMutator
 * @Description: TODO
 * @date 16/08/2015 3:47:58 pm
 * 
 */
public class InversionMutator implements Mutator {

	/**
	 * The method implements the inversion mutate operation
	 */
	public Individual doMutate(Individual individual) {
		Random random = new Random();
		// randomly choose two points
		int a = random.nextInt(individual.getSize());
		int b = random.nextInt(individual.getSize());
		if (a == b) {
			b++;
		}
		
		int max = Math.max(a, b);
		int min = Math.min(a, b);
		int compare = (max - min) / 2;
		//swap the individuals 
		for (int i = 0; i < compare; i++) {
			Collections.swap(individual.getCityList(), min++, max--);
		}
		return individual;
	}

}
