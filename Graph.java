/**
 * File:		Graph.java
 * Author:		Annie Wu
 * Class:		CS 241 - Data Structures and Algorithms II
 * 
 * Assignment:	Program 4
 * Date:		9 March 2018
 * 
 * Purpose:		This program contains the any functions regarding the graph of cities and roads.
 * 
 */

import java.util.*;

public class Graph {
	
	private final int CITY_COUNT = 20;
	private int[][] graph = new int[CITY_COUNT][CITY_COUNT];
	private ArrayList<City> cities;
	
	/**
	 * This is the default constructor.
	 * The purpose is to create a new array list for the cities;
	 */
	public Graph() {
		cities = new ArrayList<City>();
	}
	
	
	/**
	 * This is the function addCity.
	 * The purpose is to add a city to the array list of cities.
	 */
	public void addCity(City city) {
		cities.add(city);
	}
	
	/**
	 * This is the function insertRoad.
	 * The purpose is to insert a road between two cities with a given distance.
	 */
	public void insertRoad(int start, int end, int distance) {
		graph[start][end] = distance;
	}
	
	/**
	 * This is the function removeRoad.
	 * The purpose is to remove a road between two cities, if it exists.
	 * It will return false if the road already does not exist.
	 * Else it will remove the road and return true.
	 */
	public boolean removeRoad(int start, int end) {
		if(graph[start][end] == 0) {
			return false;
		}
		else {
			graph[start][end] = 0;
		}
		return true;
	}
	
	/**
	 * This is the function getCities.
	 * The purpose is to get the array list of cities.
	 */
	public ArrayList<City> getCities() {
		return cities;
	}
	
	/**
	 * This is the function getCityWithNumber.
	 * The purpose is to get the city from the array list with a given city number.
	 */
	public City getCityWithNumber(int cityNumber) {
		return cities.get(cityNumber);
	}
	
	/**
	 * This is the function getNumberWithCode.
	 * The purpose is to get the city number with a given city code.
	 */
	public int getNumberWithCode(String code) {
		for(int i = 0; i < cities.size(); i++) {
			if(cities.get(i).getCode().equalsIgnoreCase(code)) {
				return cities.get(i).getNumber();
			}
		}
		return -1;
	}
	
	/**
	 * This is the function getNameWithCode.
	 * The purpose is to get the city name with a given city code.
	 */
	public String getNameWithCode(String cityCode) {
		for(int i = 0; i < cities.size(); i++) 		{
			if(cities.get(i).getCode().equalsIgnoreCase(cityCode)) {
				return cities.get(i).getName();
			}
		}
		return null;
	}
	
	/**
	 * This is the function getNameWithNumber.
	 * The purpose is to get the city name with a given city number.
	 */
	public String getNameWithNumber(int number)	{
		for(int i = 0; i < cities.size(); i++) {
			if(cities.get(i).getNumber() == number) {
				return cities.get(i).getName();
			}
		}
		return null;
	}
	
	/**
	 * This is the function getGraph.
	 * The purpose is to get the graph of roads and cities.
	 */
	public int[][] getGraph() {
		return graph;
	}
	
	/**
	 * This is the function getDistance.
	 * The purpose is to get the distance between two cities.
	 */
	public int getDistance(int start, int end) {
		return graph[start][end];
	}
}