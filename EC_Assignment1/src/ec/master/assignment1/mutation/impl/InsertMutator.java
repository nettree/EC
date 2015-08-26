/**   
* @Title: InsertMutator.java 
* @Package ec.master.assignment1.mutation.impl 
* @Description: TODO
* @author David Fa
* @date 16/08/2015 3:47:15 pm 
* @version V1.0   
*/
package ec.master.assignment1.mutation.impl;

import java.util.Random;

import ec.master.assignment1.city.City;
import ec.master.assignment1.model.Individual;
import ec.master.assignment1.mutation.Mutator;

/**
 * @ClassName: InsertMutator
 * @Description: TODO
 * @date 16/08/2015 3:47:15 pm
 * 
 */
public class InsertMutator implements Mutator {

	/**
	 * The method implements the insert mutate operation
	 */
	public Individual doMutate(Individual individual) {
		Random random = new Random();
		//randomly choose two numbers
		int a = random.nextInt(individual.getSize());
		int b = random.nextInt(individual.getSize()-1);
		
		if(a==b){
			b++;
		}
		
		int max = Math.max(a, b);
		int min = Math.min(a, b);
		//insert the max value after the min value
		City city = individual.getCityList().remove(max);
		individual.getCityList().add(min+1,city);
		return individual;
	}

}
