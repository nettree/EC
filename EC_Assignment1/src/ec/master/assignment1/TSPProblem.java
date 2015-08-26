package ec.master.assignment1;
import java.util.ArrayList;

import ec.master.assignment1.city.City;
import ec.master.assignment1.model.Configuration;
import ec.master.assignment1.model.InputFile;
import ec.master.assignment1.model.Population;
import ec.master.assignment1.tools.FileOperator;
import ec.master.assignment1.tools.FileScanner;
import ec.master.assignment1.tools.Logger;

/**
 * @ClassName: TSPProblem
 * @Description: TODO
 * @date 14/08/2015 10:26:06 pm
 * 
 */
public class TSPProblem {
	
	public static String localPath = TSPProblem.class.getClassLoader()
			.getResource("").getPath().replace("%20", " ") + "ec/master/assignment1/";
	public static String TEST_PATH = "input/";
	public static String CONFIG_PATH = "configuration/";
	public static String CONFIG = localPath + CONFIG_PATH + "configure.properties";
	
	public static Logger log = Logger.getInstance();
	public static Configuration pps;

	protected static InputFile inputFile;
	protected static ArrayList<City> cityList;
	protected static Population population;

	/**
	 * @Title: main
	 * @Description: main method of TSP algorithm
	 * @param @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {
		
		if (!init()) {
			System.exit(0);
		}
		operation();
				
	}
	
	/**
	 * initialization read configurations from configure.properties
	 * and tsp file assigned in configure file
	 * @return
	 */
	public static boolean init() {
		if (!readConfigFile()) {
			log.errorException("java.lang.NullPointerException");
			return false;
		}
		if (!readInputFile()) {
			log.errorException("java.lang.NullPointerException");
			return false;
		}
		return true;
	}
	
	/**
	 * read configuration file and assign it to Configuration object
	 * defined
	 * @return read success or fail
	 */
	public static boolean readConfigFile() {
		pps = FileOperator.readConfiguration(CONFIG);
		if (pps == null) {
			return false;
		} else {
			log.setStatus(pps.getLogType());
		}
		return true;
	}
	/**
	 * read citylist from tsp file, from which tsp file was configured 
	 * in configure.properties file
	 * 
	 * @return read success or fail
	 */
	public static boolean readInputFile() {
		String file = localPath + TEST_PATH + pps.getFileName();
		// create inputfile instance to represent the tsp file
		inputFile = new InputFile();
		// get cityList by using configure.properties and tsp file scanner
		cityList = FileScanner.scanner(file, inputFile);
		return cityList == null ? false : true;
	}
	
	/**
	 * main logic of the TSP standard algorithm
	 */
	public static void operation() {
		long startTime = System.nanoTime();
		int best = -1;
		// initial the populations by using the population size
		population = new Population(cityList, pps.getPopSize(), inputFile.getEdgeWeightType());
		for (int i = 0; i < 1000000; i++) {
//			population.select(pps.getSelection(), pps.getPopSize());
//			population.crossover(pps.getCrossover(), 0.80);
			population.mutate(pps.getMutation(), 0.01);
//			population.select(pps.getSelection(), pps.getPopSize());
			
//			population.select(pps.getSelection(), pps.getPopSize());
//			population.crossover(pps.getCrossover(), 0.75);
//			population.mutate(pps.getMutation(), 0.2);
//			population.select(pps.getSelection(), pps.getPopSize());
			
			int temp = population.select(pps.getSelection(), pps.getPopSize());
			if (best == -1) {
				best = temp;
				System.out.println(best);
			} else if (best > temp) {
				best = temp;
				System.out.println(best);
			}
			
		}
		long endTime = System.nanoTime();
		
		System.out.println(endTime);
		System.out.println(endTime - startTime);
	}
}
