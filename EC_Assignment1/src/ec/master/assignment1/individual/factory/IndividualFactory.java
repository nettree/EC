/**   
 * @Title: IndividualFactory.java 
 * @Package ec.master.assignment1.individual.factory 
 * @Description: TODO
 * @author David Fa
 * @date 17/08/2015 8:42:25 pm 
 * @version V1.0   
 */
package ec.master.assignment1.individual.factory;

import java.util.ArrayList;

import ec.master.assignment1.city.City;
import ec.master.assignment1.individual.Individual;
import ec.master.assignment1.individual.impl.EUC2DIndividual;
import ec.master.assignment1.individual.impl.EUC3DIndividual;

/**
 * @ClassName: IndividualFactory
 * @Description: TODO
 * @date 17/08/2015 8:42:25 pm
 * 
 */
public class IndividualFactory {
	public Individual createIndividual(String type, ArrayList<City> citys,
			boolean shuffle) {
		if (type == null) {
			return null;
		}
		if ("EUC_2D".equalsIgnoreCase(type)) {
			return new EUC2DIndividual(citys, shuffle);
		} else if ("EUC_2D".equalsIgnoreCase(type)) {
			return new EUC3DIndividual(citys, shuffle);
		} else {
			return null;
		}
	}

	public Individual createIndividual(String type, ArrayList<City> citys) {
		if (type == null) {
			return null;
		}
		if ("EUC_2D".equalsIgnoreCase(type)) {
			return new EUC2DIndividual(citys);
		} else if ("EUC_2D".equalsIgnoreCase(type)) {
			return new EUC3DIndividual(citys);
		} else {
			return null;
		}
	}
}
