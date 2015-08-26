package ec.master.assignment1;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ec.master.assignment1.city.City;
import ec.master.assignment1.model.Configuration;
import ec.master.assignment1.model.Individual;
import ec.master.assignment1.model.InputFile;
import ec.master.assignment1.model.Population;
import ec.master.assignment1.tools.FileOperator;
import ec.master.assignment1.tools.FileScanner;
import ec.master.assignment1.tools.Logger;
import ec.master.assignment1.tools.Printer;

/**
 * @ClassName: TSPProblem
 * @Description: TODO
 * @date 14/08/2015 10:26:06 pm
 * 
 */
public class TSPProblem {
	
	public static String localPath = TSPProblem.class.getClassLoader().getResource("").getPath().replace("%20", " ") + "ec/master/assignment1/";
	public static String INPUT_PATH = localPath + "input/";
	public static String OUTPUT_PATH = localPath + "output/";
	public static String CONFIG_PATH = localPath + "configuration/";
	public static String CONFIG = CONFIG_PATH + "configure.properties";
	
	public static Logger log = Logger.getInstance();
	public static Configuration pps;

	protected static InputFile inputFile;
	protected static ArrayList<City> cityList;
	protected static Population population;
	protected static Individual bestIndividual = null;

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
		printResult();
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
		String file = INPUT_PATH + pps.getFileName();
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
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		Date start = new Date();
		String startDate = format.format(start);
		
		long startTime = System.nanoTime();
		population = new Population(cityList, pps.getPopSize(), inputFile.getEdgeWeightType(), pps.getElite());
		for (int i = 0; i < pps.getGenerationsize(); i++) {
			population.crossover(pps.getCrossover(), 0.75);
			population.mutate(pps.getMutation(), 0.2);
			
			population.select(pps.getSelection(), pps.getPopSize());
			Individual individual = population.getBest();
			if (bestIndividual == null) {
				bestIndividual = new Individual(individual.getCityList());
				log.info(bestIndividual.getFitness());
			} else if (bestIndividual.getFitness() > individual.getFitness()) {
				bestIndividual = new Individual(individual.getCityList());
				log.info(bestIndividual.getFitness());
			}
		}
		long endTime = System.nanoTime();
		Date end = new Date();
		String endDate = format.format(end);
		log.info("Start time: " + startDate);
		log.info("End   time: " + endDate);
		log.info("Time-consuming: " + (endTime - startTime) / 1000000000 + " second");
	}
	
	public static void printResult() {
		Printer.printResult(inputFile, bestIndividual, OUTPUT_PATH);
	}
}
