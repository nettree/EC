/**   
* @Title: EdgeCrossover.java 
* @Package ec.master.assignment1.crossover.impl 
* @Description: TODO
* @author David Fa
* @date 17/08/2015 11:15:33 pm 
* @version V1.0   
*/
package ec.master.assignment1.crossover.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import ec.master.assignment1.TSPProblem;
import ec.master.assignment1.city.City;
import ec.master.assignment1.crossover.Crossover;
import ec.master.assignment1.model.Children;
import ec.master.assignment1.model.Individual;

/**
 * @ClassName: EdgeCrossover
 * @Description: implementation of edge crossover
 * @date 17/08/2015 11:15:33 pm
 * 
 */
public class EdgeCrossover implements Crossover {

	public Children doCrossover(Individual parentA, Individual parentB) {
		int size = parentA.getSize();
		ArrayList<City> cityListA = parentA.getCityList();
		ArrayList<City> cityListB = parentB.getCityList();
		TSPProblem.log.debug(parentA.toString());
		TSPProblem.log.debug(parentB.toString());
		City city;
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		Map<Integer, City> cityMap = new HashMap<Integer, City>();
		for (int i = 0; i < size; i++) {
			city = cityListA.get(i);
			int index00 = (i - 1) < 0 ? (i - 1) + size : (i - 1);
			int index01 = (i + 1) >= size ? (i + 1) - size : (i + 1);
			int index1 = cityListB.indexOf(city);
			int index10 = (index1 - 1) < 0 ? (index1 - 1) + size : (index1 - 1);
			int index11 = (index1 + 1) >= size ? (index1 + 1) - size : (index1 + 1);
			
			int a = cityListA.get(index00).getId();
			int b = cityListA.get(index01).getId();
			int c = cityListB.get(index10).getId();
			int d = cityListB.get(index11).getId();
			
			List<Integer> array = new ArrayList<Integer>();
			array.add(a);
			array.add(b);
			array.add(c);
			array.add(d);
			Collections.sort(array);
			map.put(city.getId(), array);
			cityMap.put(city.getId(), city);
		}
		Random rand = new Random();
		List<Integer> array = new ArrayList<Integer>();
		int choice = rand.nextInt(size - 1) + 1;
		List<Integer> choiceList;
		for (int i = 0; i < size - 1; i++) {
			array.add(choice);
			choiceList = map.get(choice);
			choiceList.removeAll(array);
			map.remove(choice);
			choice = findNextChioce(choice, map, choiceList, array);
			if (choice == -1) {
				Set<Integer> set = map.keySet();
				choice = set.iterator().next();
			}
		}
		array.add(choice);
		ArrayList<City> childList = new ArrayList<City>();
		for (int i = 0; i < array.size(); i++) {
			childList.add(cityMap.get(array.get(i)));
		}
		Individual child = new Individual(childList);
		TSPProblem.log.debug(child.toString());
		return new Children(child, child);
	}
	
	private int findNextChioce(int previous, Map<Integer, List<Integer>> map, List<Integer> choiceList, List<Integer> array) {
		if (choiceList.size() == 0) {
			return -1;
		}
		if (choiceList.size() == 1) {
			return choiceList.get(0);
		}
		// find if there is a common edge
		int size = 100;
		int nextChioce = 0;
		int[] sizeList = new int[choiceList.size()];
		List<Integer> tempList;
		for (int j = 0; j < choiceList.size(); j++) {
			int num = 0;
			int temp = choiceList.get(j);
			tempList =  map.get(temp);
			for (int i = 0; i < tempList.size(); i++) {
				if ((Integer) tempList.get(i) == previous) {
					num++;
					if (num > 1)
						return temp;
				}
			}
			tempList.removeAll(array);
			sizeList[j] = tempList.size();
			if (size > tempList.size()) {
				size = tempList.size();
				nextChioce = temp;
			}
		}
		Arrays.sort(sizeList);
		if (sizeList[0] < sizeList[1]) {
			return nextChioce;
		}
		// ties are split at random
		Random rand = new Random();
		return (Integer) choiceList.get(rand.nextInt(choiceList.size()));
	}
	
}
