package ec.master.assignment1.crossover.impl;

import java.util.ArrayList;
import java.util.Collections;
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
		ArrayList<City> fChild = sParent.getCityList();
		ArrayList<City> sChild = fParent.getCityList();
		int sIndex = 0;
		int fIndex = 0;
		
		//swap the cities, which are same as the parents 
		for(int i=min;i<=max;i++){
			fIndex = fChild.indexOf(fParent.getCityList().get(i));
			Collections.swap(fChild, i, fIndex);
			sIndex = sChild.indexOf(sParent.getCityList().get(i));
			Collections.swap(sChild, i, sIndex);
		}
		return new Children(new Individual(fChild), new Individual(sChild));
	}
}
