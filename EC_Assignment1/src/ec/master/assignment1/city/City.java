/**   
 * @Title: City.java 
 * @Package ec.master.assignment1.city.impl 
 * @Description: TODO
 * @author David Fa
 * @date 17/08/2015 4:19:48 pm 
 * @version V1.0   
 */
package ec.master.assignment1.city;

/**
 * @ClassName: City
 * @Description: model defined to represent city read from tsp files
 * @date 17/08/2015 4:19:48 pm
 * 
 */
public interface City {
	public int getId();

	public void setId(int id);

	public double getX();

	public void setX(double x);

	public double getY();

	public void setY(double y);

	public double getZ();

	public void setZ(double z);

	public String toString();

	public int toCalculateDist(City city2);
}
