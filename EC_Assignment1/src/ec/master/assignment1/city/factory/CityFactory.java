/**   
* @Title: CityFactory.java 
* @Package ec.master.assignment1.city.factory 
* @Description: TODO
* @author David Fa
* @date 17/08/2015 8:29:29 pm 
* @version V1.0   
*/
package ec.master.assignment1.city.factory;

import java.util.Scanner;

import ec.master.assignment1.city.City;
import ec.master.assignment1.city.impl.EUC2DCity;
import ec.master.assignment1.city.impl.EUC3DCity;

/**
 * @ClassName: CityFactory
 * @Description: city factory used for creating the different type of city according to the 
 *               type passed in
 * @date 17/08/2015 8:29:29 pm
 * 
 */
public class CityFactory {
	public City createCity(String type, Scanner scanner) {
		if (type == null || scanner == null) {
			return null;
		}
		if ("EUC_2D".equalsIgnoreCase(type)) {
			int id = scanner.nextInt();
			double x = scanner.nextDouble();
			double y = scanner.nextDouble();
			return new EUC2DCity(id, x, y);
		} else if ("EUC_2D".equalsIgnoreCase(type)) {
			int id = scanner.nextInt();
			double x = scanner.nextDouble();
			double y = scanner.nextDouble();
			double z = scanner.nextDouble();
			return new EUC3DCity(id, x, y, z);
		} else {
			return null;
		}
	}
}
