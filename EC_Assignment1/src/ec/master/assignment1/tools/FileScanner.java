package ec.master.assignment1.tools;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ec.master.assignment1.TSPProblem;
import ec.master.assignment1.city.City;
import ec.master.assignment1.city.factory.CityFactory;
import ec.master.assignment1.model.InputFile;

/**
 * @ClassName: FileScanner
 * @Description: TODO
 * @date 15/08/2015 11:37:30 am
 * 
 */
public class FileScanner {
	/**
	 * scan tsp file to get city list
	 * @param file configure.properties file
	 * @param inputFile specific tsp file
	 * @return
	 */
	public static ArrayList<City> scanner(String file, InputFile inputFile) {
		TSPProblem.log.debug(FileScanner.class + ".scanner()");
		TSPProblem.log.debug(file);
		ArrayList<City> cityList = new ArrayList<City>();
		BufferedReader read = null;
		Scanner input = null;
		Scanner scanner = null;
		CityFactory cityFactory = new CityFactory();
		try {
			read = new BufferedReader(new FileReader(file));
			input = new Scanner(read);
			
			boolean isCity = false;
			String nextLine;
			
			while (input.hasNextLine() && !"EOF".equalsIgnoreCase((nextLine = input.nextLine()))) {
				if (!isCity) {
					isCity = !inputFile.setAttributes(nextLine);
				} else {
					scanner = new Scanner(nextLine.trim());
					City city = cityFactory.createCity(inputFile.getEdgeWeightType(), scanner);
					TSPProblem.log.debug(city.toString());
					cityList.add(city);
				}
			}
		} catch (FileNotFoundException e) {
			TSPProblem.log.errorException("java.io.FileNotFoundException");
			TSPProblem.log.error(e.getMessage());
			return null;
		} finally {
			if (scanner != null)
				scanner.close();
			if (input != null)
				input.close();
			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
					TSPProblem.log.errorException("java.io.IOException");
					TSPProblem.log.error(e.getMessage());
					return null;
				}
			}
		}
		return cityList;
	}

}
