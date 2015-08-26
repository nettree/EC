/**   
* @Title: CrossoverFactory.java 
* @Package ec.master.assignment1.crossover.factory 
* @Description: TODO
* @author David Fa
* @date 19/08/2015 11:21:53 am 
* @version V1.0   
*/
package ec.master.assignment1.crossover.factory;

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
	public Crossover createCrossover(String type) {
		if ("pmx".equalsIgnoreCase(type)) {
			return new PMXCrossover();
		} else if ("cycle".equalsIgnoreCase(type)) {
			return new CycleCrossover();
		} else if ("order".equalsIgnoreCase(type)) {
			return new OrderCrossover();
		} else if ("edge".equalsIgnoreCase(type)) {
			return new EdgeCrossover();
		} else {
			return new PMXCrossover();
		}
	}
}
