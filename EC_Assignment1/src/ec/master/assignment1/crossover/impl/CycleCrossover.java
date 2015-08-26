/**   
* @Title: CycleCrossover.java 
* @Package ec.master.assignment1.crossover.impl 
* @Description: TODO
* @author David Fa
* @date 19/08/2015 11:24:01 am 
* @version V1.0   
*/
package ec.master.assignment1.crossover.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ec.master.assignment1.city.City;
import ec.master.assignment1.crossover.Crossover;
import ec.master.assignment1.model.Children;
import ec.master.assignment1.model.Individual;

/**
 * @ClassName: CycleCrossover
 * @Description: implementation of cycle crossover
 * @date 19/08/2015 11:24:01 am
 * 
 */
public class CycleCrossover implements Crossover {

	public Children doCrossover(Individual parentA, Individual parentB) {
		// TODO Auto-generated method stub
		ArrayList<City> cityListA = parentA.getCityList();
		ArrayList<City> cityListB = parentB.getCityList();
//		System.out.println(parentA.toString());
//		System.out.println(parentB.toString());
		int size = cityListA.size();
		Random rand = new Random();
		int index = rand.nextInt(size);
		List<Integer> list = new ArrayList<Integer>();
		City city = cityListA.get(index);
		boolean bool = true;
		while (bool) {
			list.add(index);
//			System.out.println(index);
			if (city.getId() == cityListB.get(index).getId()) {
				bool = false;
			} else {
				index = cityListA.indexOf(cityListB.get(index));
			}
		}
		ArrayList<City> childListA = cityListB;
		ArrayList<City> childListB = cityListA;
		for (int i = 0; i < list.size(); i++) {
			City cityTemp = childListA.get(list.get(i));
			childListA.set(list.get(i), childListB.get(list.get(i)));
			childListB.set(list.get(i), cityTemp);
		}
		Individual childA = new Individual(childListA);
		Individual childB = new Individual(childListB);
		return new Children(childA, childB);
	}

}
