/**   
* @Title: MutatorFactory.java 
* @Package ec.master.assignment1.mutation.factory 
* @Description: TODO
* @author David Fa
* @date 16/08/2015 3:46:41 pm 
* @version V1.0   
*/
package ec.master.assignment1.mutation.factory;

import ec.master.assignment1.mutation.Mutator;
import ec.master.assignment1.mutation.impl.InsertMutator;
import ec.master.assignment1.mutation.impl.InversionMutator;
import ec.master.assignment1.mutation.impl.ScrambleMutator;
import ec.master.assignment1.mutation.impl.SwapMutator;

/**
 * @ClassName: MutatorFactory
 * @Description: TODO
 * @date 16/08/2015 3:46:41 pm
 * 
 */
public class MutatorFactory {
	public Mutator createMutator(String type) {
		if ("insert".equalsIgnoreCase(type)) {
			return new InsertMutator();
		} else if ("swap".equalsIgnoreCase(type)) {
			return new SwapMutator();
		} else if ("inversion".equalsIgnoreCase(type)) {
			return new InversionMutator();
		} else if ("scramble".equalsIgnoreCase(type)) {
			return new ScrambleMutator();
		} else {
			return new SwapMutator();
		}
	}
}
