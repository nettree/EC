package ec.master.assignment1.crossover.impl;

import java.util.ArrayList;
import java.util.Random;

import ec.master.assignment1.city.City;
import ec.master.assignment1.crossover.Crossover;
import ec.master.assignment1.model.Children;
import ec.master.assignment1.model.Individual;

/**
 * @ClassName: OrderCrossover
 * @Description: implementation of order crossover
 * @date 17/08/2015 11:15:33 pm
 * 
 */
public class OrderCrossover implements Crossover{
	
	/**
	 * The method is for order crossover
	 * @return children (child1, child2) 
	 */
	
	public Children doCrossover(Individual fParent, Individual sParent){
		Random random = new Random();
		int a = random.nextInt(fParent.getSize());
		int b = random.nextInt(fParent.getSize()-1);
		if(a==b){
			b++;
		}
		int max = Math.max(a, b);
		int min = Math.min(a, b);
		ArrayList<City> fChild = new ArrayList<City>();
		ArrayList<City> sChild = new ArrayList<City>();
		
		//copy the segment from the parents 
		for(int i=min;i<=max;i++){
			fChild.add(fParent.getCityList().get(i));
			sChild.add(sParent.getCityList().get(i));
		}
		
		int fIndex = (max+1)%fParent.getSize();
		int sIndex = (max+1)%sParent.getSize();
		for(int i=max+1;i<sParent.getSize()+min;i++){
			//find the city in the first child
			while(fChild.contains(sParent.getCityList().get(sIndex))){
				sIndex=(sIndex+1)%fParent.getSize();
			}
			//find the city in the second child
			while(sChild.contains(fParent.getCityList().get(fIndex))){
				fIndex=(fIndex+1)%fParent.getSize();
			}
			
			if(i<sParent.getSize()){
				//index is in the later segment
				fChild.add(sParent.getCityList().get(sIndex));
				sChild.add(fParent.getCityList().get(fIndex));
			}else{
				//index is in the former segment
				fChild.add(i%sParent.getSize(),sParent.getCityList().get(sIndex));
				sChild.add(i%sParent.getSize(),fParent.getCityList().get(fIndex));
			}
			
		}
		return new Children(new Individual(fChild), new Individual(sChild));
	}
}
