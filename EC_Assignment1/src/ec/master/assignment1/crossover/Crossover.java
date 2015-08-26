/**   
* @Title: Crossover.java 
* @Package ec.master.assignment1.crossover 
* @Description: TODO
* @author David Fa
* @date 17/08/2015 3:36:12 pm 
* @version V1.0   
*/
package ec.master.assignment1.crossover;

import ec.master.assignment1.model.Children;
import ec.master.assignment1.model.Individual;

/**
 * @ClassName: Crossover
 * @Description: define the interface for all crossover implementations
 * @date 17/08/2015 3:36:12 pm
 * 
 */
public interface Crossover {
	public Children doCrossover(Individual a, Individual b, String dataType);
}
