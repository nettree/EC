/**   
* @Title: CrossoverFactory.java 
* @Package ec.master.assignment1.crossover.factory 
* @Description: TODO
* @author David Fa
* @date 19/08/2015 11:21:53 am 
* @version V1.0   
*/
package ec.master.assignment1.crossover.factory;

import java.util.Random;

import ec.master.assignment1.crossover.Crossover;
import ec.master.assignment1.crossover.impl.CycleCrossover;
import ec.master.assignment1.crossover.impl.EdgeCrossover;
import ec.master.assignment1.crossover.impl.OrderCrossover;
import ec.master.assignment1.crossover.impl.PMXCrossover;

/**
 * @ClassName: CrossoverFactory
 * @Description: define crossover factory, which is used for generating the crossover 
 * 				 according to the type passed in
 * @date 19/08/2015 11:21:53 am
 * 
 */
public class CrossoverFactory {
	private final String[] crossovers = new String[] { "pmx", "cycle", "order", "edge" };
	public Crossover createCrossover(String type) {
		if (crossovers[0].equalsIgnoreCase(type)) {
			return chooseCrossover(0);
		} else if (crossovers[1].equalsIgnoreCase(type)) {
			return chooseCrossover(1);
		} else if (crossovers[2].equalsIgnoreCase(type)) {
			return chooseCrossover(2);
		} else if (crossovers[3].equalsIgnoreCase(type)) {
			return chooseCrossover(3);
		} else if ("random".equalsIgnoreCase(type)) {
			Random rand = new Random();
			return chooseCrossover(rand.nextInt(crossovers.length));
		} else {
			return chooseCrossover(3);
		}
	}
	
	private Crossover chooseCrossover(int type) {
		switch(type) {
		case 0:
			return new PMXCrossover();
		case 1:
			return new CycleCrossover();
		case 2:
			return new OrderCrossover();
		case 3:
			return new EdgeCrossover();
		}
		return null;
	}
}
