/**
 * File:		Graph.java
 * Author:		Annie Wu
 * Class:		CS 241 - Data Structures and Algorithms II
 * 
 * Assignment:		Program 4
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
	 * This function will create a new array list for the cities;
	 */
	public Graph() {
		cities = new ArrayList<City>();
	}
	
	
	/**
	 * This function will add a city to the array list of cities.
	 * @param city a city
	 */
	public void addCity(City city) {
		cities.add(city);
	}
	
	/**
	 * This function will insert a road between two cities with a given distance.
	 * @param start starting city
	 * @param end ending city
	 * @param distance distance between the cities
	 */
	public void insertRoad(int start, int end, int distance) {
		graph[start][end] = distance;
	}
	
	/**
	 * This function will remove a road between two cities, if it exists.
	 * It will return false if the road already does not exist.
	 * Else it will remove the road and return true.
	 * @param start starting city
	 * @param end ending city
	 * @return false if road does not exist, else remove road and return true
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
	 * This function will get the array list of cities.
	 * @return arraylist of cities
	 */
	public ArrayList<City> getCities() {
		return cities;
	}

	/**
	 * This function will get the city number with a given city code.
	 * @param code city code
	 * @return city number or -1 if it does not exist
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
	 * This function will get the city name with a given city code.
	 * @param cityCode city code
	 * @return city name or null if it does not exist
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
	 * This function will get the city name with a given city number.
	 * @param number city number
	 * @return city name or null if it does not exist
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
	 * @return graph of roads and cities
	 */
	public int[][] getGraph() {
		return graph;
	}
}
