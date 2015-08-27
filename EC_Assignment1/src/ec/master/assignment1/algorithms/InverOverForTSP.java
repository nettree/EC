package ec.master.assignment1.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ec.master.assignment1.city.City;
import ec.master.assignment1.model.Individual;
import ec.master.assignment1.model.Population;
import ec.master.assignment1.tools.Printer;

/**
 * 
 * Inver over algorithm for TSP
 *
 */
public class InverOverForTSP extends TSPProblem {

	private static int NEXT_CITY_INDEX = 0;

	public static void main(String[] args) {
		if (!init()) {
			System.exit(0);
		}
		operation();
	}

	/**
	 * main method to do the logic of inver-over algorithm
	 */
	public static void operation() {
		// random initialization of the population
		population = new Population(cityList, pps.getPopSize(), inputFile.getEdgeWeightType(), pps.getElite());
		
		// define the printer and fileName
		Printer printer = Printer.getInstance();
		String fileName = pps.getFileName() + "_Inver_Over_50_10000";
		printer.buildFile(inputFile, pps, OUTPUT_PATH, fileName);
		Random random = new Random();
		Double probability = 0.1d;
		int generationSize = 1;
		// when generation size reaches the value set in configure file, exit loop
		while (generationSize <= pps.getGenerationsize()) {
			// for each individual S` belongs to population P do
			for (int sindex = 0; sindex < population.getIndividuals().size(); sindex ++) {
				Individual originalIndividual = population.getIndividuals().get(sindex);
				originalIndividual.updateFitness();
				Individual copyOfIndividual = new Individual(new ArrayList<City>(originalIndividual.getCityList()), false);
				
				// randomly select a city c from cityList of copied individual
				int city1Index = random.nextInt(copyOfIndividual.getCityList().size());
				City city1 = copyOfIndividual.getCityList().get(city1Index);
				City city2 = null;
				int city2Index = -1;
				
				// repeat while loop
				while (true) {
					
					// if rand() <= p, select the city2 from remaining cities in copied individual
					if (random.nextDouble() <= probability) {
						city2Index = random.nextInt(copyOfIndividual.getCityList().size());
						while(city2Index == city1Index) {
							city2Index = random.nextInt(copyOfIndividual.getCityList().size());
						}
						city2 = copyOfIndividual.getCityList().get(city2Index);
					} else {
						// select randomly an individual from population
						Individual selectedIndividual = population.getIndividuals().get(random.nextInt(population.getIndividuals().size()));
						
						// get the next city to city1 in i and assign this city to city2
						List<City> selectedCityList = selectedIndividual.getCityList();
						for (int index = 0; index < selectedCityList.size(); index++) {
							// find the index of city next to city1 in selectedIndividual
							if (selectedCityList.get(index).getId() == city1.getId()) {
								// if the index is the last index of selectedCityList
								// assign the first city of selectedCityList to city2
								if (index == selectedCityList.size() - 1) {
									city2Index = 0;
									city2 = selectedCityList.get(city2Index);
								} else {
									// assign the city next to city1 to city2
									city2Index = index + 1;
									city2 = selectedCityList.get(city2Index);
								}
								break;
							}
						}
					}
					
					// if the next city or the previous city of city1
					// in copyOfIndividual is city2, exit repeat while loop
					City previousCity = getPreviousCity(copyOfIndividual, city1);
					City nextCity = getNextCity(copyOfIndividual, city1);
					if (city2.getId() == previousCity.getId() || city2.getId() == nextCity.getId()) {
						break;
					}
					
					// start index should be lower than endIndex
					int startIndex = NEXT_CITY_INDEX < city2Index ? NEXT_CITY_INDEX : city2Index;
					int endIndex = NEXT_CITY_INDEX < city2Index ? city2Index : NEXT_CITY_INDEX;
					
					// if index of next city is greater than that of city2, then the section from
					// next city index to the end of list to start of list to index of city2 need to
					// be inversed
					List<City> subList = new ArrayList<City>();
					List<City> copiedCityList = new ArrayList<City> (copyOfIndividual.getCityList());
					
					if (NEXT_CITY_INDEX > city2Index) {
						
						// reverse the section from startIndex(exclusive) to endIndex(exclusive)
						for(int index = startIndex + 1; index < endIndex; index ++) {
							subList.add(copiedCityList.get(index));
						}
						Collections.reverse(subList);
						
						// and then set this reversed sublist to the copied individual
						int subIndex = 0;
						for(int index = startIndex + 1; index < endIndex; index ++) {
							copiedCityList.set(index, subList.get(subIndex));
							subIndex ++;
						}
						
						// and reverse the whole list then rotate to the expected city sequence
						Collections.reverse(copiedCityList);
						int currentNextCityIndex = 0;
						for(int index = 0; index < copiedCityList.size(); index ++) {
							if(copiedCityList.get(index).getId() == nextCity.getId()) {
								currentNextCityIndex = index;
							}
						}
						// the rotate distance depends on the start or end element's offset
						// compared with the original end or start element, in this if branch,
						// as the nextCity's index is greater than city2's index, so the index of
						// next city is actually the start index, so we need to find the nextCity's
						// index in current reversed list, and calculate the offset to start index
						Collections.rotate(copiedCityList, startIndex - currentNextCityIndex);
						
						// set this list to copied individual
						copyOfIndividual.setCityList(copiedCityList);
					} else { 
						// normal situation, reverse the section from startIndex(inclusive) to endIndex(inclusive)
						// and then set this reversed sublist to the copied individual
						for (int index = startIndex; index <= endIndex; index ++) {
							subList.add(copiedCityList.get(index));
						}
						Collections.reverse(subList);
						
						// replace the section of copied individual
						int subIndex = 0;
						for(int index = startIndex; index <= endIndex; index ++) {
							copyOfIndividual.getCityList().set(index, subList.get(subIndex));
							subIndex ++;
						}
					}
					city1 = city2;
				}
				// recalculate the tour cost
				copyOfIndividual.updateFitness();
				// if fitness of new individual is less than fitness of old one
				// replace the old individual with new individual
				if (copyOfIndividual.getFitness() <= originalIndividual.getFitness()) {
					population.getIndividuals().set(sindex, copyOfIndividual);
				}
			}
			
			// print out the result after each generation
			List<Individual> fList = new ArrayList<Individual>();
			for (Individual indi : population.getIndividuals()) {
				indi.updateFitness();
				fList.add(indi);
			}
			Collections.sort(fList);
			// print result to file
			printer.print(inputFile, population, fList.get(0), generationSize, fileName);
			
			// at the end of each loop, add generation size
			generationSize ++;
		}
	}

	/**
	 * get the previous city of city1 in copyOfIndividual
	 * 
	 * @param copyOfIndividual
	 * @param city1
	 * @return
	 */
	private static City getNextCity(Individual copyOfIndividual, City city1) {
		List<City> cityList = copyOfIndividual.getCityList();
		City nextCity = null;
		for (int index = 0; index < cityList.size(); index++) {
			City c = cityList.get(index);
			if (c.getId() == city1.getId()) {
				// if current index is the last index of citylist
				// assign the first city
				if (index == cityList.size() - 1) {
					nextCity = cityList.get(0);
					NEXT_CITY_INDEX = 0;
				} else {
					// assign the next city to current city of index
					nextCity = cityList.get(index + 1);
					NEXT_CITY_INDEX = index + 1;
				}
				break;
			}
		}
		return nextCity;
	}

	/**
	 * get the previous city of city1 in copyOfIndividual
	 * 
	 * @param copyOfIndividual
	 * @param city1
	 * @return
	 */
	private static City getPreviousCity(Individual copyOfIndividual, City city1) {
		List<City> cityList = copyOfIndividual.getCityList();
		City previousCity = null;
		for (int index = 0; index < cityList.size(); index++) {
			City c = cityList.get(index);
			if (c.getId() == city1.getId()) {
				// if current index is the first index of citylist
				// assign the last city
				if (index == 0) {
					previousCity = cityList.get(cityList.size() - 1);
				} else {
					// assign the previous city to current city of index
					previousCity = cityList.get(index - 1);
				}
				break;
			}
		}
		return previousCity;
	}
}
