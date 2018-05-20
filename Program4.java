/**
 * File:		Dijkstra.java
 * Author:		Annie Wu
 * Class:		CS 241 - Data Structures and Algorithms II
 * 
 * Assignment:		Program 4
 * Date:		16 March 2018
 * 
 * Purpose:		This program contains the user interaction and implements Dijkstra's algorithm.
 * 
 */

import java.io.*;
import java.util.*;

public class Program4 {
	
	private static String cityFile = "C:\\Users\\annie\\Desktop\\CS 241\\city.dat";
	private static String roadFile = "C:\\Users\\annie\\Desktop\\CS 241\\road.dat";
	private static String fileNotFound = "The file could not be found.";
	
	private static String prompt = "\nCommand? ";
	private static String commands = " Q Query the city information by entering the city code" +
					"\n D Find the minimum distance between two cities" +
					"\n I Insert a road by entering two city codes and distance" +
					"\n R Remove an existing road by entering two city codes" +
					"\n H Display this message" +
					"\n E Exit";
	private static String invalidInput = "Invalid input!";
	private static String exiting = "Thank you for using my program!";
	
	private static String comma = ", ";
	private static String cityCode = "City code: ";
	private static String cityCodes = "City codes: ";
	private static String cityCodesDistance = "City codes and distance: ";
	private static String cityDoesNotExist = "The city code %s does not exist.";
	private static String citiesDoNotExist = "The city codes %s and %s do not exist.";

	private static String minDistance = "The minimum distance between %s and %s is %d through the route: ";
	private static String roadInserted = "You have inserted a road from %s to %s with a distance of %d.";
	private static String roadRemoved = "You have removed a road from %s to %s.";
	private static String roadDoesNotExist = "The road from %s to %s does not exist.";
	private static String roadAlreadyExists = "The road from %s to %s already exists.";
	
	private static Graph graph;
	
	/**
	 * This is the function main.
	 * The purpose is to print the user menu and display the correct output accordingly.
	 *  - Q Query the city information by entering the city code.
	 *  - D Find the minimum distance between two cities.
	 *  - I Insert a road by entering two city codes and distance.
	 *  - R Remove an existing road by entering two city codes.
	 *  - H Display this message.
	 *  - E Exit.
	 */
	public static void main(String[] args) {
		
		try {
			loadFromFile(cityFile, roadFile);
		} 
		catch (IOException e) {
			System.out.println(fileNotFound);
		}
		
		String input;
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while (!exit) {
			System.out.print(prompt);
			input = sc.next();
			input = input.toUpperCase();
			switch (input) {
				//query
				case "Q":
					System.out.print(cityCode);
					try {
						input = sc.next();
						input = input.toUpperCase();
						query(input);
					}
					catch (InputMismatchException e) {
						System.out.println(invalidInput);
					}
						break;
				//find minimum distance
				case "D":
					System.out.print(cityCodes);
					try {
						String source = sc.next();
						source = source.toUpperCase();
						
						String target = sc.next();
						target = target.toUpperCase();
						
						dijkstra(source, target);
					}
					catch (InputMismatchException e) {
						System.out.println(invalidInput);
					}
					break;
				//insert road
				case "I":
					System.out.print(cityCodesDistance);
					try {
						String source = sc.next();
						source = source.toUpperCase();
						
						String target = sc.next();
						target = target.toUpperCase();
						
						int distance = sc.nextInt();
						
						insert(source, target, distance);
					}
					catch (InputMismatchException e) {
						System.out.println(invalidInput);
					}
					break;
				//remove road
				case "R":
					System.out.print(cityCodes);
					try { 
						String source = sc.next();
						source = source.toUpperCase();
						
						String target = sc.next();
						target = target.toUpperCase();
						
						remove(source, target);
					}
					catch (InputMismatchException e) {
						System.out.println(invalidInput);
					}
					break;
				//help
				case "H":
					System.out.print(commands);
					break;
				//exit
				case "E":
					System.out.print(exiting);
					exit = true;
					break;
				default:
					System.out.print(invalidInput);
					break;
			}
		}
		sc.close();
	}

	private static void query(String input) {
		for(int i = 0; i< graph.getCities().size(); i++){
			if(graph.getCities().get(i).getCode().equalsIgnoreCase(input)){
				System.out.print(graph.getCities().get(i).cityInfo());
				break;
			}
			else if (i == graph.getCities().size()-1){
				System.out.printf(cityDoesNotExist, input);
				break;
			}
		}
	}
	
	private static void dijkstra(String startCode, String endCode) {
		int source = graph.getNumberWithCode(startCode) -1;
		int target = graph.getNumberWithCode(endCode) -1;
		
		//if both cities do not exists
		if (source + 1 == -1 && target + 1 == -1) {
			System.out.printf(citiesDoNotExist, startCode, endCode);
		}
		
		//if one of the cities do not exist
		else if (source + 1 == -1 || target + 1 == -1) {
			if (source + 1 == -1) {
				System.out.printf(cityDoesNotExist, startCode);
			}
			else 
				System.out.printf(cityDoesNotExist, endCode);
		}
		else {
			//array for shortest distances
			int[] distance = new int[graph.getCities().size() + 1];
			//array for previous vertex/city
			int[] previous = new int[graph.getCities().size() + 1];
			//boolean array to keep track of visited/unvisited nodes
			boolean[] visited = new boolean[graph.getCities().size() + 1];
			//array for the shortest path
			ArrayList<String> path = new ArrayList<String>();
			//array for neighbors
			int[] neighbors = new int[0];
			//distance is set to infinity for all vertices except for source
			for(int i = 1; i < distance.length; i++) {
				distance[i]= Integer.MAX_VALUE;
			}
			//distance of source is set to zero
			distance[source] = 0;
	
			//for every vertex except for source
			for(int j = 1; j < distance.length; j++) {
				//get the vertex with the smallest weight
				int smallest = minWeight(distance, visited);
				visited[smallest] = true;
				//get the neighbor(s) of the vertex with the smallest weight
				neighbors = getNeighbors(smallest);
				for(int k = 0; k < neighbors.length; k++) {
					int temp = neighbors[k];
					int tempDist = distance[smallest] + graph.getGraph()[smallest][temp];
					if(distance[temp] > tempDist) {
						distance[temp] = tempDist;
						previous[temp] = smallest;
					}
				}
			}
			
			System.out.printf(minDistance, graph.getNameWithCode(startCode), 
					graph.getNameWithCode(endCode), distance[target]);
			
			int vertex = target;
			//add the vertices in the shortest path into the array list
			while(previous[vertex] != source) {
				path.add(0, graph.getNameWithNumber(vertex));
				vertex = previous[vertex];
			}
			//add the target vertex to the path
			path.add(0, graph.getNameWithNumber(vertex));
			
			if(vertex != source) {
				path.add(0, graph.getNameWithCode(startCode));
			}
	
			//print the cities in the shortest path
			for(int i = 0; i < path.size()-1; i++) {
				System.out.print(path.get(i) + comma);
			}
			//this is the last city in the path, do not print the comma
			System.out.print(graph.getNameWithCode(endCode));
		}	
	}
		
	/**
	 * This is the function insert.
	 * The purpose is to insert a road between two cities with the given distance.
	 */
	private static void insert(String startCode, String endCode, int distance) {
		int source = graph.getNumberWithCode(startCode) -1;
		int target = graph.getNumberWithCode(endCode) -1;
		
		//if both cities do not exists
		if(source + 1 == -1 && target + 1 == -1) {
			System.out.printf(citiesDoNotExist, startCode, endCode);
		}
		
		//if one of the cities do not exist
		else if(source + 1 == -1 || target + 1 == -1) {
			if (source + 1 == -1) {
				System.out.printf(cityDoesNotExist, startCode);
			}
			else 
				System.out.printf(cityDoesNotExist, endCode);
		}
		
		//if the road already exists
		else if(graph.getGraph()[(source)][(target)] != 0) {
			System.out.printf(roadAlreadyExists, graph.getCities().get(source).getName(),
					graph.getCities().get(target).getName());
		}
		//if the road does not exist, insert it
		else if(graph.getGraph()[source][target] == 0) {
			graph.insertRoad(source, target, distance);
			System.out.printf(roadInserted, graph.getCities().get(source).getName(),
					graph.getCities().get(target).getName(), distance);
		}
	}
	
	/**
	 * This is the function remove.
	 * The purpose is to remove a road between two cities if it exists.
	 */
	private static void remove(String startCode, String endCode) {
		int source = graph.getNumberWithCode(startCode) -1;
		int target = graph.getNumberWithCode(endCode) -1;
		
		//if both cities do not exists
		if (source + 1 == -1 && target + 1 == -1) {
			System.out.printf(citiesDoNotExist, startCode, endCode);
		}
		
		//if one of the cities do not exist
		else if (source + 1 == -1 || target + 1 == -1) {
			if (source + 1 == -1) {
				System.out.printf(cityDoesNotExist, startCode);
			}
			else 
				System.out.printf(cityDoesNotExist, endCode);
		}
		
		//if the road does not exist
		else if (!graph.removeRoad(source, target)){
			System.out.printf(roadDoesNotExist, graph.getCities().get(source).getName(),
					graph.getCities().get(target).getName());
		}
		else {
			System.out.printf(roadRemoved, graph.getCities().get(source).getName(),
					graph.getCities().get(target).getName());
		}
		
	}
	
	/**
	 * This is the function getNeighbors.
	 * The purpose is to get the neighbors of a vertex.
	 */
	public static int[] getNeighbors(int vertex) {
		int counter = 0;
		//get the number of vertices that are a neighbor
		for(int i = 0; i < graph.getGraph()[vertex].length; i++) {
			if(graph.getGraph()[vertex][i] > 0)
				counter++;
		}
		//get the number of neighbors for the given vertex
		int[] temp = new int[counter];	
		counter = 0;
		for(int i = 0; i < graph.getGraph()[vertex].length; i++) {
			if(graph.getGraph()[vertex][i] > 0)
				temp[counter++] = i;
		}
		return temp;
	}
	
	/**
	 * This is the function minWeight.
	 * The purpose is to get the index of the vertex that has the smallest weight.
	 */
	public static int minWeight(int[] distance, boolean[] visited) {
		//the current smallest distance/weight is set to infinity
		int currentMin = Integer.MAX_VALUE;	
		//the vertex index with the smallest distance/weight
		int minVertex = 0;	
		for(int i = 0; i< distance.length; i++){		
			//if the vertex has not been visited and the distance < the current smallest distance
			//set the smallest vertex to index i
			//set the current smallest distance to the distance of index i
			if(!visited[i] && distance[i] < currentMin)
			{
				minVertex = i;	
				currentMin= distance[i];
			}
		}
		return minVertex;	
	}
	
	/**
	 * This is the function loadFromFile.
	 * The purpose is to load the city and road data from their respective resource files.
	 */
	private static void loadFromFile(String cityData, String roadData) throws IOException {
		graph = new Graph();
		File cityDat = new File(cityData);
		File roadDat = new File(roadData);
		Scanner scanCities = new Scanner(cityDat);
		Scanner scanRoads = new Scanner(roadDat);
		
		while(scanCities.hasNext()) {
			//split at spaces
	    	String[] tokens = scanCities.nextLine().trim().split("\\s+");
	    	//if the city name has two words in it
	    	if(tokens.length > 5) {
	    		int num = Integer.parseInt(tokens[0]);
	        	String code = tokens[1];
	        	String name = tokens[2] + " " + tokens[3];
	        	int pop = Integer.parseInt(tokens[4]);
	        	int elev = Integer.parseInt(tokens[5]);
	        	graph.addCity(new City(num, code, name, pop, elev));
	    	}
	    	//if the city is only one word
	    	else {
		    	int num = Integer.parseInt(tokens[0]);
		    	String code = tokens[1];
		    	String name = tokens[2];
		    	int pop = Integer.parseInt(tokens[3]);
		    	int elev = Integer.parseInt(tokens[4]);
		    	graph.addCity(new City(num, code, name, pop, elev));
	    	}
	    }
	    while(scanRoads.hasNext()) {
	    	//-1 because array starts from 0
	    	graph.insertRoad(scanRoads.nextInt() -1, scanRoads.nextInt() -1, scanRoads.nextInt());
	    }
	    scanCities.close();
	    scanRoads.close();
	}
}

