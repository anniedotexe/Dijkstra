# Dijkstra's Algorithm for a Graph of Cities and Roads

### Dijkstra's Shortest Path Algorithm
Given a graph and a source vertex in the graph, find the shortest path from a single source vertex to all other vertices in the given graph. <br>

We maintain two sets, one set contains vertices included in shortest path tree, other set includes vertices not yet included in shortest path tree. 

At every step of the algorithm, we find a vertex which is in the other set (set of not yet included) and has a minimum distance from the source.

### The Program

The program will read in two text files and store the information in ArrayLists: 
* city.dat - contains city data
    * City number
    * City code
    * City name
    * City population
    * City elevation
* road.dat - contains road data <br>
    * Source city
    * Destination city
    * Distance between them
<br>

The user can choose to:
* Q to query the city information by entering the city code
* D to find the minimum distance between two cities
* I to insert a road by entering two city codes and distance
* R to remove an existing road by entering two city codes
* H for help to display this message
* E to exit the program


#### Sample Output

```

=============================================================================
 Q Query the city information by entering the city code
 D Find the minimum distance between two cities
 I Insert a road by entering two city codes and distance
 R Remove an existing road by entering two city codes
 H Display this message
 E Exit
Command? Q
City code: MP
13 MP MOUNTAIN PASS 76 7190
=============================================================================
 Q Query the city information by entering the city code
 D Find the minimum distance between two cities
 I Insert a road by entering two city codes and distance
 R Remove an existing road by entering two city codes
 H Display this message
 E Exit
Command? D
City codes: AN BK
The minimum distance between ANAHEIM and BAKERSFIELD is 225 through the route: ANAHEIM, TORRANCE, BREA CANYON, GARDENA, KERNVILLE, BAKERSFIELD
=============================================================================
 Q Query the city information by entering the city code
 D Find the minimum distance between two cities
 I Insert a road by entering two city codes and distance
 R Remove an existing road by entering two city codes
 H Display this message
 E Exit
Command? I
City codes and distance: AN BK 100
The road from ANAHEIM to BAKERSFIELD already exists.
=============================================================================
 Q Query the city information by entering the city code
 D Find the minimum distance between two cities
 I Insert a road by entering two city codes and distance
 R Remove an existing road by entering two city codes
 H Display this message
 E Exit
Command? I
City codes and distance: TR BK 100
You have inserted a road from TORRANCE to BAKERSFIELD with a distance of 100.
=============================================================================
 Q Query the city information by entering the city code
 D Find the minimum distance between two cities
 I Insert a road by entering two city codes and distance
 R Remove an existing road by entering two city codes
 H Display this message
 E Exit
Command? D
City codes: TR BK
The minimum distance between TORRANCE and BAKERSFIELD is 100 through the route: TORRANCE, BAKERSFIELD
=============================================================================
 Q Query the city information by entering the city code
 D Find the minimum distance between two cities
 I Insert a road by entering two city codes and distance
 R Remove an existing road by entering two city codes
 H Display this message
 E Exit
Command? R
City codes: TR BK
You have removed a road from TORRANCE to BAKERSFIELD.
=============================================================================
 Q Query the city information by entering the city code
 D Find the minimum distance between two cities
 I Insert a road by entering two city codes and distance
 R Remove an existing road by entering two city codes
 H Display this message
 E Exit
Command? D
City codes: TR BK
The minimum distance between TORRANCE and BAKERSFIELD is 182 through the route: TORRANCE, GARDEN GRPVE, LAKE ISABELLA, BAKERSFIELD, BAKERSFIELD
=============================================================================
 Q Query the city information by entering the city code
 D Find the minimum distance between two cities
 I Insert a road by entering two city codes and distance
 R Remove an existing road by entering two city codes
 H Display this message
 E Exit
Command? E

Exiting the program!
```