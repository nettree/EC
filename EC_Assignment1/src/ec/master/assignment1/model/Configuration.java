/**   
* @Title: Properties.java 
* @Package ec.master.assignment1.model 
* @Description: TODO
* @author David Fa
* @date 17/08/2015 11:20:18 am 
* @version V1.0   
*/
package ec.master.assignment1.model;

/**
 * 
 * @ClassName: Properties
 * @Description: model defined to represent configure.properties
 * @date 17/08/2015 11:20:18 am
 * 
 */
public class Configuration {
	String fileName;
	String logType;
	String mutation;
	String crossover;
	String selection;
	int popSize;
	
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	public int getPopSize() {
		return popSize;
	}
	public void setPopSize(int popSize) {
		this.popSize = popSize;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getMutation() {
		return mutation;
	}
	public void setMutation(String mutation) {
		this.mutation = mutation;
	}
	public String getCrossover() {
		return crossover;
	}
	public void setCrossover(String crossover) {
		this.crossover = crossover;
	}

}
