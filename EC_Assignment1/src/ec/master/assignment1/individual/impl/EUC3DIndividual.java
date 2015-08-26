/**   
* @Title: EUC3DIndividual.java 
* @Package ec.master.assignment1.individual.impl 
* @Description: TODO
* @author David Fa
* @date 17/08/2015 4:45:16 pm 
* @version V1.0   
*/
package ec.master.assignment1.individual.impl;

import java.util.ArrayList;
import java.util.Collections;

import ec.master.assignment1.city.City;
import ec.master.assignment1.individual.Individual;

/**
 * @ClassName: EUC3DIndividual
 * @Description: TODO
 * @date 17/08/2015 4:45:16 pm
 * 
 */
public class EUC3DIndividual implements Individual {
	private ArrayList<City> cityList;
	private int fitness;
	
	public EUC3DIndividual(ArrayList<City> citys, boolean shuffle) {
		cityList = new ArrayList<City>(citys);
		if (shuffle) {
			shuffle();
		} else {
			updateFitness();
		}
	}
	
	public EUC3DIndividual(ArrayList<City> citys) {
		cityList = new ArrayList<City>(citys);
		updateFitness();
	}

	public int getFitness() {
		return fitness;
	}

	public void shuffle() {
		Collections.shuffle(cityList);
		updateFitness();
	}

	public void updateFitness() {
		fitness = 0;
		for (int i = 0; i < getSize(); i++) {
			fitness += toCalculateDist(cityList.get(i), cityList.get((i + 1) % getSize()));
		}
	}
	
	private int toCalculateDist(City city1, City city2) {
		double xd = (city1.getX() - city2.getX());
		double yd = (city1.getY() - city2.getY());
		double zd = (city1.getZ() - city2.getZ());
		return (int) Math.round(Math.sqrt(Math.pow(xd, 2) + Math.pow(yd, 2) + Math.pow(zd, 2)));
	}

	public int getSize() {
		return cityList.size();
	}

	public ArrayList<City> getCityList() {
		return cityList;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Individual, Fitness=" + getFitness());
		sb.append(" Size=" + cityList.size());
		sb.append(", [");
		for (City city : cityList) {
			sb.append(city.getId() + ", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append("]");
		return sb.toString();
	}
}
