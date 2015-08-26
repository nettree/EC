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

import ec.master.assignment1.TSPProblem;
import ec.master.assignment1.city.City;
import ec.master.assignment1.model.Individual;
import ec.master.assignment1.model.InputFile;

/**
 * @ClassName: Printer
 * @Description: TODO
 * @date 26/08/2015 12:31:07 pm
 * 
 */
public class Printer {
	public static void printResult(InputFile inputFile, Individual individual, String url) {
		String filename = inputFile.getName()+ ".opt.tour";
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
			for (City city: cityList) {
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
