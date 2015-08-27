package ec.master.assignment1.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ec.master.assignment1.city.City;
import ec.master.assignment1.model.Individual;
import ec.master.assignment1.model.Population;

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
	 * main program to do the logic of inver-over algorithm
	 */
	public static void operation() {
		// random initialization of the population
		population = new Population(cityList, pps.getPopSize(), inputFile.getEdgeWeightType(), pps.getElite());
		Random random = new Random();
		// TODO: add termination condition in future
		Double probability = 0.1d;
		int gen = 1;
		while (gen <= 30000) {
			//population.mutate(pps.getMutation(), 0.2);
			// for each individual S` belongs to population P do
			for (int sindex = 0; sindex < population.getIndividuals().size(); sindex ++) {
				Individual originalIndividual = population.getIndividuals().get(sindex);
				originalIndividual.updateFitness();
				Individual copyOfIndividual = new Individual(originalIndividual.getCityList(), false);
				// randomly select a city c from cityList of copied individual
				int city1Index = random.nextInt(copyOfIndividual.getCityList().size());
				City city1 = copyOfIndividual.getCityList().get(city1Index);
				City city2 = null;
				int city2Index = -1;
				// repeat while loop
				while (true) {
					// if rand() <= p, select the city2 from remaining cities in copied individual
					if (random.nextDouble() <= probability) {
						// then remove this city from cityList
						// List<City> copyList = new ArrayList(copyOfIndividual.getCityList());
						// copyList.remove(city1);
						
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
					// inverse the section from the next city of city1 to the
					// city2 in copyOfIndividual
					// int midIndex = ((NEXT_CITY_INDEX + city2Index) + 1) / 2;
					int startIndex = NEXT_CITY_INDEX;
					int endIndex = city2Index;
					if (NEXT_CITY_INDEX > city2Index) {
						startIndex = city2Index;
						endIndex = NEXT_CITY_INDEX;
					}
					List<City> subList = new ArrayList<City>(copyOfIndividual.getCityList()).subList(startIndex, endIndex);
					Collections.reverse(subList);
					int subIndex = 0;
					List<City> allCities = copyOfIndividual.getCityList();
					for(int index = startIndex; index < endIndex; index ++) {
						allCities.set(index, subList.get(subIndex));
						subIndex ++;
					}
					copyOfIndividual.setCityList(allCities);
//					for (int index = startIndex; index < midIndex; index++) {
//						Collections.swap(copyOfIndividual.getCityList(), index, endIndex);
//						endIndex--;
//					}

					// Collections.swap(copyOfIndividual.getCityList(),
					// nextCityIndex, city2Index);
					city1 = city2;
				}
				// if fitness of new individual is less than fitness of old one
				// replace the old individual with new individual
				copyOfIndividual.updateFitness();
				//originalIndividual.updateFitness();
				//System.out.println(copyOfIndividual.getFitness() <= originalIndividual.getFitness());
				if (copyOfIndividual.getFitness() <= originalIndividual.getFitness()) {
					population.getIndividuals().set(sindex, copyOfIndividual);
				}

				// print out the result
				List<Individual> fList = new ArrayList<Individual>();
				for (Individual indi : population.getIndividuals()) {
					//indi.updateFitness();
					fList.add(indi);
				}
				Collections.sort(fList);
				//System.out.println(fList.get(0).getFitness());
//				for(City city : fList.get(0).getCityList()) {
//					System.out.println(city);
//				}
			}
			if(gen == 5000) {
				System.out.println("Generation 5000!!!!!!!!!!!!!!");
				System.out.println("Generation 5000!!!!!!!!!!!!!!");
				System.out.println("Generation 5000!!!!!!!!!!!!!!");
				System.out.println("Generation 5000!!!!!!!!!!!!!!");
				System.out.println("Generation 5000!!!!!!!!!!!!!!");
				System.out.println("Generation 5000!!!!!!!!!!!!!!");
				System.out.println("Generation 5000!!!!!!!!!!!!!!");
				System.out.println("Generation 5000!!!!!!!!!!!!!!");
				System.out.println("Generation 5000!!!!!!!!!!!!!!");
				System.out.println("Generation 5000!!!!!!!!!!!!!!");
				System.out.println("Generation 5000!!!!!!!!!!!!!!");
				System.out.println("Generation 5000!!!!!!!!!!!!!!");
				System.out.println("Generation 5000!!!!!!!!!!!!!!");
				List<Individual> indi = population.getIndividuals();
				Collections.sort(indi);
				System.out.println("Best is:" + indi.get(0));
			} else if(gen == 10000) {
				System.out.println("Generation 10000!!!!!!!!!!!!");
				System.out.println("Generation 10000!!!!!!!!!!!!");
				System.out.println("Generation 10000!!!!!!!!!!!!");
				System.out.println("Generation 10000!!!!!!!!!!!!");
				System.out.println("Generation 10000!!!!!!!!!!!!");
				System.out.println("Generation 10000!!!!!!!!!!!!");
				System.out.println("Generation 10000!!!!!!!!!!!!");
				System.out.println("Generation 10000!!!!!!!!!!!!");
				System.out.println("Generation 10000!!!!!!!!!!!!");
				System.out.println("Generation 10000!!!!!!!!!!!!");
				System.out.println("Generation 10000!!!!!!!!!!!!");
				List<Individual> indi = population.getIndividuals();
				Collections.sort(indi);
				System.out.println("Best is:" + indi.get(0));
			} else if(gen == 20000) {
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				System.out.println("Generation 20000!!!!!!!!!!!!!");
				List<Individual> indi = population.getIndividuals();
				Collections.sort(indi);
				System.out.println("Best is:" + indi.get(0));
			}
			gen ++;
		}
		List<Individual> indi = population.getIndividuals();
		Collections.sort(indi);
		System.out.println("Best is:" + indi.get(0));
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
