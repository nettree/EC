/**   
* @Title: Selector.java 
* @Package ec.master.assignment1.selection 
* @Description: TODO
* @author David Fa
* @date 19/08/2015 12:11:47 pm 
* @version V1.0   
*/
package ec.master.assignment1.selection;

import java.util.ArrayList;

import ec.master.assignment1.model.Individual;

/**
 * @ClassName: Selector
 * @Description: TODO
 * @author David Fa
 * @date 19/08/2015 12:11:47 pm
 * 
 */
public interface Selector {
	public ArrayList<Individual> doSelection(ArrayList<Individual> individuals, int groupSize, int resultSize);
}
