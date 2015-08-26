/**   
 * @Title: EUC2DCity.java 
 * @Package ec.master.assignment1.city.impl 
 * @Description: TODO
 * @author David Fa
 * @date 17/08/2015 3:31:03 pm 
 * @version V1.0   
 */
package ec.master.assignment1.city.impl;

import ec.master.assignment1.city.City;

/**
 * @ClassName: EUC2DCity
 * @Description: 2D city model, mainly used in this program
 * @date 17/08/2015 3:31:03 pm
 * 
 */
public class EUC2DCity implements City {

	public EUC2DCity(int id, double x, double y) {
		setId(id);
		setX(x);
		setY(y);
	}

	public int id;
	public double x;
	public double y;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return 0;
	}

	public void setZ(double z) {
	}

	public int toCalculateDist(City city2) {
		double xd = (getX() - city2.getX());
		double yd = (getY() - city2.getY());
		int dist = (int) Math.round(Math.sqrt(xd * xd + yd * yd));
		return dist;
	}

	public String toString() {
		return new String("City:" + getId() + ", " + getX() + ", " + getY());
	}

}
