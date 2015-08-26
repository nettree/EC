/**   
* @Title: ScrambleMutator.java 
* @Package ec.master.assignment1.mutation.impl 
* @Description: TODO
* @author David Fa
* @date 16/08/2015 3:48:16 pm 
* @version V1.0   
*/
package ec.master.assignment1.mutation.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import ec.master.assignment1.city.City;
import ec.master.assignment1.model.Individual;
import ec.master.assignment1.mutation.Mutator;

/**
 * @ClassName: ScrambleMutator
 * @Description: TODO
 * @date 16/08/2015 3:48:16 pm
 * 
 */
public class ScrambleMutator implements Mutator {

	/**
	 * The method implements the scramble mutate operation
	 */
	public Individual doMutate(Individual individual) {
		
		Random random = new Random();
		ArrayList<City> city = new ArrayList<City>();
		
		//randomly choose two points
		int a = random.nextInt(individual.getSize());
		int b = random.nextInt(individual.getSize()-1);
		
		if(a==b){
			b++;
		}
		
		int min = Math.min(a, b);
		int max = Math.max(a, b);
		//count the numbers between max and min
		int count = max-min+1;
		
		//remove the cities from city list and add them into the new city list
		while(count>0){
			city.add(individual.getCityList().remove(min));
			count--;
		}
		//shuffle the city list
		Collections.shuffle(city);
		//put the shuffled list into the individual city list
		for(int i=city.size()-1;i>=0;i--){
			individual.getCityList().add(min,city.get(i));
		}
		return individual;
	}

}
