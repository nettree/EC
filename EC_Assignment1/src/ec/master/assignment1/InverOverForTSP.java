package ec.master.assignment1;

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
public class InverOverForTSP extends TSPProblem{
	
	public static void main(String[] args){
		if (!init()) {
			System.exit(0);
		}
		operation();
	}
	
	/**
	 * main program to do the logic of inver-over algorithm
	 */
	public static void operation() {
		population = new Population(cityList, pps.getPopSize(), inputFile.getEdgeWeightType(), pps.getElite());
		//TODO: add termination condition in future
		Random random = new Random();
		while(true) {
			// for each individual S` belongs to population P do
			List<Individual> individuals = population.getIndividuals();
			for(int sindex = 0; sindex < individuals.size(); sindex ++) {
				Individual copyOfIndividual = individuals.get(sindex);
				List<City> cityList = copyOfIndividual.getCityList();
				// randomly select a city c from cityList
				int city1Index = random.nextInt(cityList.size());
				City city1 = cityList.get(city1Index);
				City city2 = null;
				int city2Index = -1;
				// repeat
				while(true){
					// if rand() <= p
					if(random.nextDouble() <= 0.75){
						// then remove this city from cityList
						List<City> copyList = new ArrayList(cityList);
						copyList.remove(city1);
						city2Index = random.nextInt(copyList.size());
						city2 = copyList.get(city2Index);
					} else {
						// whether need to remove the current individual?
						Individual i = individuals.get(random.nextInt(individuals.size()));
						// get the next city to city1 in i and assign this city to city2
						List<City> icityList = i.getCityList(); 
						for(int index = 0; index < icityList.size(); index ++) {
							// find the index of city1 in i
							if(icityList.get(index).getId() == city1.getId()) {
								// if the current index is the last index of icityList
								// assign the first city to city2
								if(index == icityList.size() - 1){
									city2Index = 0;
									city2 = icityList.get(0);
								}else { 
									// assign the next city of current city of index to city2
									city2Index = index + 1;
									city2 = icityList.get(index + 1);
								}
								break;
							}
						}
					}
					// if the next city or the previous city of city1
					// in copyOfIndividual is city2, exit while loop
					City previousCity = getPreviousCity(copyOfIndividual, city1);
					City nextCity = getNextCity(copyOfIndividual, city1);
					if(city2 == null) {
						continue;
					}
					if(city2.getId() == previousCity.getId() || city2.getId() == nextCity.getId()) {
						break;
					}
					// inverse the section from the next city of city1 to the city2 in copyOfIndividual
					int nextCityIndex = 0;
					for(int nextIndex = 0; nextIndex < cityList.size(); nextIndex ++) {
						if(cityList.get(nextIndex).getId() == nextCity.getId()) {
							nextCityIndex = nextIndex;
							break;
						}
					}
					// if city2 locates in the end of 
					if(city2Index > nextCityIndex){
						int midIndex = ((nextCityIndex + city2Index) + 1) / 2;
						int startIndex = nextCityIndex;
						int endIndex = city2Index;
	//					if(nextCityIndex > city2Index) {
	//						startIndex = city2Index;
	//						endIndex = nextCityIndex;
	//					}
						for(int i = startIndex; i < midIndex; i ++){
							Collections.swap(copyOfIndividual.getCityList(), i, endIndex);
							endIndex--;
						}
					}
					
					//Collections.swap(copyOfIndividual.getCityList(), nextCityIndex, city2Index);
					city1 = city2;
				}
				// if fitness of new individual is less than fitness of old one
				// replace the old individual with new individual
				if(copyOfIndividual.getFitness() <= individuals.get(sindex).getFitness()) {
					individuals.set(sindex, copyOfIndividual);
				}
				
				// print out the result
				List<Integer> fList = new ArrayList<Integer>();
				for(Individual indi : individuals){
					fList.add(indi.getFitness());
				}
				Collections.sort(fList);
				for(Integer inte : fList){
					System.out.println(inte);
				}
			}
		}
	}
	/**
	 * get the previous city of city1 in copyOfIndividual
	 * @param copyOfIndividual
	 * @param city1
	 * @return
	 */
	private static City getNextCity(Individual copyOfIndividual, City city1) {
		List<City> cityList = copyOfIndividual.getCityList();
		City nextCity = null;
		for(int index = 0; index < cityList.size(); index ++){
			City c = cityList.get(index);
			if(c.getId() == city1.getId()){
				// if current index is the last index of citylist
				// assign the first city
				if(index == cityList.size() - 1){
					nextCity = cityList.get(0);
				} else {
					// assign the next city to current city of index
					nextCity = cityList.get(index + 1);
				}
				break;
			}
		}
		return nextCity;
	}
	/**
	 * get the previous city of city1 in copyOfIndividual
	 * @param copyOfIndividual
	 * @param city1
	 * @return
	 */
	private static City getPreviousCity(Individual copyOfIndividual, City city1) {
		List<City> cityList = copyOfIndividual.getCityList();
		City previousCity = null;
		for(int index = 0; index < cityList.size(); index ++){
			City c = cityList.get(index);
			if(c.getId() == city1.getId()){
				// if current index is the first index of citylist
				// assign the last city
				if(index == 0){
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
