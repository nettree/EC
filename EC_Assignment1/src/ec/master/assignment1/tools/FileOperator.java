/**   
 * @Title: FileOperator.java 
 * @Package ec.master.assignment1.tools 
 * @Description: TODO
 * @date 17/08/2015 11:01:27 am 
 * @version V1.0   
 */
package ec.master.assignment1.tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ec.master.assignment1.model.Configuration;

/**
 * @ClassName: FileOperator
 * @Description: TODO
 * @date 17/08/2015 11:01:27 am
 * 
 */
public class FileOperator {

	public static Configuration readConfiguration(String filePath) {
		Configuration config = new Configuration();
		Properties pps = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			pps.load(in);

			String fileName = pps.getProperty("filename");
			config.setFileName(fileName);
			String logType = pps.getProperty("log");
			config.setLogType(logType);
			String mutation = pps.getProperty("mutation");
			config.setMutation(mutation);
			String crossover = pps.getProperty("crossover");
			config.setCrossover(crossover);
			String selection = pps.getProperty("selection");
			config.setSelection(selection);
			String popSize = pps.getProperty("popsize");
			config.setPopSize(Integer.valueOf(popSize));
			return config;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
