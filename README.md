# Dijkstras_Shortest_Path

This program implements Dijkstas algorithm for the follwing problem. 

The railroad charges passengers a fixed fare to travel between any two towns. These fares are shown in the figure below. That is, fare from Sacramento to SFO is $50; from SFO to LA is $60 and so on. These rates are the same whether the ride between two cities is part of a longer itinerary or not.
The edges in the figure are directed. They represent single track railroad lines, on which, travel is permitted in only one direction. For e.g. you can go directly from SFO to LA, but not from LA to SFO.
Although in this situation we are interested in the cheapest fares, the graph problem is always referred to as Shortest-path problem. Here shortest doesn’t necessarily means shortest in terms of the distance; it can also mean cheapest, fastest, or the best route by some other measures.

Problem: For a given starting point (say Sacramento) and a destination what’s the cheapest route (that is, minimum cost path to travel from Sacramento to all the cities). For example, in figure below, you can see that the cheapest route from Sacramento to Chicago passes through Atlanta and Detroit; it will cost you $65.
Note: The figure is not given, look to the java code for the costs from the location to destination. 
