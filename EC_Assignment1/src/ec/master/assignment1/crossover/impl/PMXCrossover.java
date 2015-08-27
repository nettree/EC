package ec.master.assignment1.crossover.impl;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import ec.master.assignment1.city.City;
import ec.master.assignment1.crossover.Crossover;
import ec.master.assignment1.model.Children;
import ec.master.assignment1.model.Individual;
/**
 * @ClassName: PMXCrossover
 * @Description: implementation of PMX crossover
 * @date 17/08/2015 11:15:33 pm
 * 
 */
public class PMXCrossover implements Crossover{
	
	public Children doCrossover(Individual fParent, Individual sParent){
		Random random = new Random();
		//randomly choose two points
		int a = random.nextInt(fParent.getSize());
		int b = random.nextInt(fParent.getSize()-1);
		if(a==b){
			b++;
		}
		int max = Math.max(a, b);
		int min = Math.min(a, b);
		
		//initiate two children
		List<City> fChild = sParent.getCityList();
		List<City> sChild = fParent.getCityList();
		List<City> fSub = fChild.subList(min, max);
		//find the same element in the parent,
		//set the element in the place of the second parent
		for(int i=min;i<max;i++){
			City c1 = fSub.get(i-min);
			City c2 = sChild.get(i-min);
			int com = sChild.indexOf(c1);
			sChild.set(com, sChild.get(i));
			sChild.set(i, c1);

			com = fChild.indexOf(c2);
			fChild.set(com,fChild.get(i));
			fChild.set(i, c2);
		}
		return new Children(new Individual(fChild), new Individual(sChild));
	}
}
