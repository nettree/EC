/**   
* @Title: InputFile.java 
* @Package ec.master.assignment1.model 
* @Description: TODO
* @author David Fa
* @date 17/08/2015 2:07:42 pm 
* @version V1.0   
*/
package ec.master.assignment1.model;

import java.util.Scanner;

/**
 * Model used to represent the structure of tsp file
 * @ClassName: InputFile
 * @Description: TODO
 * @date 17/08/2015 2:07:42 pm
 * 
 */
public class InputFile {
	
	final String iName = "NAME";
	final String iType = "TYPE";
	final String iComment = "COMMENT";
	final String iDimension = "DIMENSION";
	final String iCapacity = "CAPACITY";
	final String iEdgeWeightType = "EDGE_WEIGHT_TYPE";
	final String iEdgeWeightFormat = "EDGE_WEIGHT_FORMAT";
	final String iEdgeDataFormat = "EDGE_DATA_FORMAT";
	final String iNodeCoordType = "NODE_COORD_TYPE";
	final String iDisPlayDataType = "DISPLAY_DATA_TYPE";
	
	/**
	 * parse each line in tsp file then set attribute acquired
	 * @param line line read from tsp file
	 * @return
	 */
	public boolean setAttributes(String line) {
		Scanner scanner = new Scanner(line).useDelimiter("\\s*:\\s*");
		while (scanner.hasNext()) {
			String next = scanner.next();
			if (iName.equalsIgnoreCase(next)) {
				if (scanner.hasNext())
					this.setName(scanner.next());
			} else if (iType.equalsIgnoreCase(next)) {
				if (scanner.hasNext())
					this.setType(scanner.next());
			} else if (iComment.equalsIgnoreCase(next)) {
				if (scanner.hasNext())
					this.setComment(scanner.next());
			} else if (iDimension.equalsIgnoreCase(next)) {
				if (scanner.hasNext())
					this.setDimension(scanner.next());
			} else if (iCapacity.equalsIgnoreCase(next)) {
				if (scanner.hasNext())
					this.setCapacity(scanner.next());
			} else if (iEdgeWeightType.equalsIgnoreCase(next)) {
				if (scanner.hasNext())
					this.setEdgeWeightType(scanner.next());
			} else if (iEdgeWeightFormat.equalsIgnoreCase(next)) {
				if (scanner.hasNext())
					this.setEdgeWeightFormat(scanner.next());
			} else if (iEdgeDataFormat.equalsIgnoreCase(next)) {
				if (scanner.hasNext())
					this.setEdgeDataFormat(scanner.next());
			} else if (iNodeCoordType.equalsIgnoreCase(next)) {
				if (scanner.hasNext())
					this.setNodeCoordType(scanner.next());
			} else if (iDisPlayDataType.equalsIgnoreCase(next)) {
				if (scanner.hasNext())
					this.setDisplayDataType(scanner.next());
			} else {
				return false;
			}
		}
		return true;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getEdgeWeightType() {
		return edgeWeightType;
	}
	public void setEdgeWeightType(String edgeWeightType) {
		this.edgeWeightType = edgeWeightType;
	}
	public String getEdgeWeightFormat() {
		return edgeWeightFormat;
	}
	public void setEdgeWeightFormat(String edgeWeightFormat) {
		this.edgeWeightFormat = edgeWeightFormat;
	}
	public String getEdgeDataFormat() {
		return edgeDataFormat;
	}
	public void setEdgeDataFormat(String edgeDataFormat) {
		this.edgeDataFormat = edgeDataFormat;
	}
	public String getNodeCoordType() {
		return nodeCoordType;
	}
	public void setNodeCoordType(String nodeCoordType) {
		this.nodeCoordType = nodeCoordType;
	}
	public String getDisplayDataType() {
		return displayDataType;
	}
	public void setDisplayDataType(String displayDataType) {
		this.displayDataType = displayDataType;
	}
	String name;
	String type;
	String comment;
	String dimension;
	String capacity;
	String edgeWeightType;
	String edgeWeightFormat;
	String edgeDataFormat;
	String nodeCoordType;
	String displayDataType;
	
}
