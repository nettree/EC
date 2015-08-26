/**   
* @Title: Individual.java 
* @Package ec.master.assignment1.model 
* @Description: TODO
* @author David Fa
* @date 20/08/2015 9:30:34 am 
* @version V1.0   
*/
package ec.master.assignment1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ec.master.assignment1.city.City;

/**
 * @ClassName: Individual
 * @Description: TODO
 * @date 20/08/2015 9:30:34 am
 * 
 */
public class Individual implements Comparable<Individual>{

	private List<City> cityList;
	private int fitness;
	
	public Individual(List<City> citys, boolean shuffle) {
		cityList = new ArrayList<City>(citys);
		if (shuffle) {
			shuffle();
		} else {
			updateFitness();
		}
	}
	
	public Individual(List<City> citys) {
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

	/**
	 * update the total length of tour of current city sequence
	 * @return void
	 */
	public void updateFitness() {
		fitness = 0;
		for (int i = 0; i < getSize(); i++) {
			fitness += cityList.get(i).toCalculateDist(cityList.get((i + 1) % getSize()));
//			fitness += toCalculateDist(cityList.get(i), cityList.get((i + 1) % getSize()));
		}
	}
	
	public int getSize() {
		return cityList.size();
	}

	public List<City> getCityList() {
		return cityList;
	}
	
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Individual, Fitness=" + getFitness());
		sb.append(" Size=" + cityList.size());
		sb.append(", [");
		for (City city : cityList) {
			sb.append(city.getId() + ", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		return sb.toString();
	}
	
	public int compareTo(Individual other) {
		return Integer.compare(this.getFitness(), other.getFitness());
	}
	
}
