/**   
 * @Title: Individual.java 
 * @Package ec.master.assignment1.individual 
 * @Description: TODO
 * @author David Fa
 * @date 17/08/2015 4:32:48 pm 
 * @version V1.0   
 */
package ec.master.assignment1.individual;

import java.util.ArrayList;

import ec.master.assignment1.city.City;

/**
 * @ClassName: Individual
 * @Description: individual interface, which used for representing the sample in population
 *               it contains the city sequence (tour) and stores them as a city list
 * @author David Fa
 * @date 17/08/2015 4:32:48 pm
 * 
 */
public interface Individual {
	public int getFitness();

	public void shuffle();

	public void updateFitness();

	public int getSize();

	public ArrayList<City> getCityList();

	public String toString();
}
