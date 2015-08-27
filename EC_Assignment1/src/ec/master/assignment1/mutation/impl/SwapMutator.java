/**   
* @Title: SwapMutator.java 
* @Package ec.master.assignment1.mutation.impl 
* @Description: TODO
* @author David Fa
* @date 16/08/2015 3:47:42 pm 
* @version V1.0   
*/
package ec.master.assignment1.mutation.impl;

import java.util.Collections;
import java.util.Random;

import ec.master.assignment1.model.Individual;
import ec.master.assignment1.mutation.Mutator;

/**
 * @ClassName: SwapMutator
 * @Description: TODO
 * @author David Fa
 * @date 16/08/2015 3:47:42 pm
 * 
 */
public class SwapMutator implements Mutator {

	/**
	 * The method implements the swap mutate operation
	 */
	public Individual doMutate(Individual individual) {
		Random random = new Random();
		
		//randomly choose two points
		int min = random.nextInt(individual.getSize());
		int max = random.nextInt(individual.getSize()-1);
		if(max==min){
			max++;
		}
		//swap these individuals  
		Collections.swap(individual.getCityList(), max, min);
		return individual;
	}

}
