/**
 * File:		City.java
 * Author:		Annie Wu
 * Class:		CS 241 - Data Structures and Algorithms II
 * 
 * Assignment:		Program 4
 * Date:		16 March 2018
 * 
 * Purpose:		This program contains the cities and any functions regarding the city information.
 * 
 */

public class City {
	
	private int cityNumber, population, elevation;
	private String cityCode, cityName;

	/**
	 * This is the constructor.
	 * It will create a new city with the given city number, code, name, population, elevation.
	 */
	public City(int cityNumber, String cityCode, String cityName, int population, int elevation) {
		this.cityNumber = cityNumber;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.population = population;
		this.elevation = elevation;
	}
	
	/**
	 * This is the function getNumber.
	 * The purpose is to get the city number.
	 */
	public int getNumber() {
		return cityNumber;
	}
	
	/**
	 * This is the function getCode.
	 * The purpose is to get the city code.
	 */
	public String getCode() {
		return cityCode;
	}
	
	/**
	 * This is the function getName.
	 * The purpose is to get the full city name.
	 */
	public String getName() {
		return cityName;
	}
	
	/**
	 * This is the function getPopulation.
	 * The purpose is to get the population of the city.
	 */
	public int getPopulation() {
		return population;
	}
	
	/**
	 * This is the function setPopulation.
	 * The purpose is to set the population of the city.
	 */
	public void setPopulation(int population) {
		this.population = population;
	}
	
	/**
	 * This is the function getElevation.
	 * The purpose is to get the elevation of the city.
	 */
	public int getElevation() {
		return elevation;
	}
	
	/**
	 * This is the function setElevation.
	 * The purpose is to set the elevation of the city.
	 */
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	
	/**
	 * This is the function cityInfo.
	 * The purpose is to display all of the city's information.
	 * This is for the query option in the user menu.
	 */
	public String cityInfo()
	{
		return getNumber() + " " + getCode() + " " + getName() + " " + getPopulation() + " " + getElevation();
	}	
}
