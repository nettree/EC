/**   
 * @Title: Printer.java 
 * @Package ec.master.assignment1.tools 
 * @Description: TODO
 * @author David Fa
 * @date 26/08/2015 12:31:07 pm 
 * @version V1.0   
 */
package ec.master.assignment1.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ec.master.assignment1.algorithms.TSPProblem;
import ec.master.assignment1.city.City;
import ec.master.assignment1.model.Configuration;
import ec.master.assignment1.model.Individual;
import ec.master.assignment1.model.InputFile;
import ec.master.assignment1.model.Population;

/**
 * @ClassName: Printer
 * @Description: TODO
 * @date 26/08/2015 12:31:07 pm
 * 
 */
public class Printer {

	private static Printer printer = null;
	private FileWriter writter5000 = null;
	private FileWriter writter10000 = null;
	private FileWriter writter20000 = null;
	private StringBuffer sb5000 = null;
	private StringBuffer sb10000 = null;
	private StringBuffer sb20000 = null;
	int size = -1;

	public static Printer getInstance() {
		if (printer == null) {
			return new Printer();
		}
		return printer;
	}

	public void buildFile(InputFile inputFile, Configuration config, String url) {
		size = config.getGenerationsize();
		if (size == 20000) {
			build5000(inputFile, config, url);
			build10000(inputFile, config, url);
			build20000(inputFile, config, url);
		} else if (size == 10000) {
			build5000(inputFile, config, url);
			build10000(inputFile, config, url);
		} else if (size == 5000) {
			build5000(inputFile, config, url);
		}

	}
	
	private void build5000(InputFile inputFile, Configuration config, String url) {
		try {
			String filePath = getFileName(inputFile, config, url, 5000);
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			sb5000 = new StringBuffer();
			writter5000 = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void build10000(InputFile inputFile, Configuration config, String url) {
		try {
			String filePath = getFileName(inputFile, config, url, 10000);
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			sb10000 = new StringBuffer();
			writter10000 = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void build20000(InputFile inputFile, Configuration config, String url) {
		try {
			String filePath = getFileName(inputFile, config, url, 20000);
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			sb20000 = new StringBuffer();
			writter20000 = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void print(InputFile inputFile, Population population, Individual individual, int index) {
		int generation = index + 1;
		if (size == 5000) {
			print5000(inputFile, population, individual, generation);
		} else if (size == 10000) {
			print5000(inputFile, population, individual, generation);
			print10000(inputFile, population, individual, generation);
		} else if(size == 20000){
			print5000(inputFile, population, individual, generation);
			print10000(inputFile, population, individual, generation);
			print20000(inputFile, population, individual, generation);
		}
	}
	
	private void print5000(InputFile inputFile, Population population, Individual individual, int index) {
		String filename = inputFile.getName();
		if (index < 5000) {
			sb5000.append("GENERATION" +index + ": " + "Average Cost: " + population.getAverage() + " Standard Deviation: " + population.getStandardDeviation() + " Best Fitness: " + individual.getFitness() + "\n");
		} else if(index == 5000) {
			try {
				writter5000.write("NAME : " + filename + "\n");
				writter5000.write("COMMENT : Optimal tour for " + inputFile.getName() + "  (" + individual.getFitness() + ")\n");
				writter5000.write("TYPE : TOUR\n");
				writter5000.write("DIMENSION : " + inputFile.getDimension() + "\n");
				writter5000.write("GENERATION:\n");
				writter5000.write(sb5000.toString());
				writter5000.write("\nTOUR_SECTION\n");
				List<City> cityList = individual.getCityList();
				for (City city : cityList) {
					writter5000.write(city.getId() + "\n");
				}
				writter5000.write("EOF\n");
				writter5000.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (writter5000 != null) {
					try {
						writter5000.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void print10000(InputFile inputFile, Population population, Individual individual, int index) {
		String filename = inputFile.getName();
		if (index < 10000) {
			sb10000.append("GENERATION" +index + ": " + "Average Cost: " + population.getAverage() + " Standard Deviation: " + population.getStandardDeviation() + " Best Fitness: " + individual.getFitness() + "\n");
		} else if(index == 10000) {
			try {
				writter10000.write("NAME : " + filename + "\n");
				writter10000.write("COMMENT : Optimal tour for " + inputFile.getName() + "  (" + individual.getFitness() + ")\n");
				writter10000.write("TYPE : TOUR\n");
				writter10000.write("DIMENSION : " + inputFile.getDimension() + "\n");
				writter10000.write("GENERATION:\n");
				writter10000.write(sb10000.toString());
				
				writter10000.write("\nTOUR_SECTION\n");
				List<City> cityList = individual.getCityList();
				for (City city : cityList) {
					writter10000.write(city.getId() + "\n");
				}
				writter10000.write("EOF\n");
				writter10000.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (writter10000 != null) {
					try {
						writter10000.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void print20000(InputFile inputFile, Population population, Individual individual, int index) {
		String filename = inputFile.getName();
		if (index < 20000) {
			sb20000.append("GENERATION" +index + ": " + "Average Cost: " + population.getAverage() + " Standard Deviation: " + population.getStandardDeviation() + " Best Fitness: " + individual.getFitness() + "\n");
		} else if(index == 20000) {
			try {
				writter20000.write("NAME : " + filename + "\n");
				writter20000.write("COMMENT : Optimal tour for " + inputFile.getName() + "  (" + individual.getFitness() + ")\n");
				writter20000.write("TYPE : TOUR\n");
				writter20000.write("DIMENSION : " + inputFile.getDimension() + "\n");
				writter20000.write("GENERATION:\n");
				writter20000.write(sb20000.toString());
				writter20000.write("\nTOUR_SECTION\n");
				List<City> cityList = individual.getCityList();
				for (City city : cityList) {
					writter20000.write(city.getId() + "\n");
				}
				writter20000.write("EOF\n");
				writter20000.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (writter20000 != null) {
					try {
						writter20000.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private String getFileName(InputFile inputFile, Configuration config, String url, int size) {
		String filename = inputFile.getName() + "_" + config.getCrossover()
				+ "_" + config.getMutation() + "_" + config.getSelection()
				+ "_" + config.getPopSize() + "_" + size
				+ ".opt.tour";
		String filePath = url + filename;
		return filePath;
	}
	

	/**
	 * 
	 * @Title: printResult
	 * @Description: To print out the result in the output file
	 * @param @param inputFile
	 * @param @param individual
	 * @param @param url
	 * @return void
	 * @throws
	 */
	public static void printResult(InputFile inputFile, Individual individual, String url) {
		String filename = inputFile.getName() + ".opt.tour";
		String filePath = url + filename;
		TSPProblem.log.info("Output File: " + filePath);
		File file = new File(filePath);
		List<City> cityList = individual.getCityList();
		FileWriter writter = null;
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			writter = new FileWriter(file);
			writter.write("NAME : " + filename + "\n");
			writter.write("COMMENT : Optimal tour for " + inputFile.getName() + "  (" + individual.getFitness() + ")\n");
			writter.write("TYPE : TOUR\n");
			writter.write("DIMENSION : " + inputFile.getDimension() + "\n");
			writter.write("TOUR_SECTION\n");
			for (City city : cityList) {
				writter.write(city.getId() + "\n");
			}
			writter.write("EOF\n");
			writter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writter != null) {
				try {
					writter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
