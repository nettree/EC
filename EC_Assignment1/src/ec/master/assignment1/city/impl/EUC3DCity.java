/**   
 * @Title: EUC3DCity.java 
 * @Package ec.master.assignment1.city.impl 
 * @Description: TODO
 * @author David Fa
 * @date 17/08/2015 3:53:26 pm 
 * @version V1.0   
 */
package ec.master.assignment1.city.impl;

import ec.master.assignment1.city.City;

/**
 * @ClassName: EUC3DCity
 * @Description: 3D city model, will not be used in this assignment 
 * @date 17/08/2015 3:53:26 pm
 * 
 */
public class EUC3DCity implements City {
	public EUC3DCity(int id, double x, double y, double z) {
		setId(id);
		setX(x);
		setY(y);
		setZ(z);
	}

	public int id;
	public double x;
	public double y;
	public double z;

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
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public int toCalculateDist(City city2) {
		double xd = (getX() - city2.getX());
		double yd = (getY() - city2.getY());
		double zd = (getZ() - city2.getZ());
		return (int) Math.round(Math.sqrt(Math.pow(xd, 2) + Math.pow(yd, 2)
				+ Math.pow(zd, 2)));
	}

	public String toString() {
		return new String("City:" + getId() + " " + getX() + " " + getY() + " "
				+ getZ());
	}
}
