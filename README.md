# Supply Chain Graph Analysis using Graph Algorithms

The project is aimed at analyzing a supply chain dataset through the use of graph algorithms. The graph is modelled as a DAG graph with each node represented as an object. All the nodes derive from a Graph_Node class. The algorithms implemented uses an adjaceny list implemented using a HashMap. 

1. Depth-First Search for Supplier and Carrier Nodes
This algorithm uses Depth-First Searcch to find all the supplier and carrier nodes connected to a given warehouse node. The algorithm keeps track of all the visited nodes to avoid cycles.

2. Topological Sort using Kahn's Algorithm
Here the alorithm is used  find a the linear ordering of nodes in the supply chain DAG graph such that for every directed edge(u,v), vertex u comes before v in the ordering. This is useful for scheduling tasks

3. Total Valuation Calculation Using Depth-First Search
This algorithm calculates the total valuation of each warehouse in the graph by traversing throug the skrs under each warehouse and finding the sum valuation. It used DFS to traverse the graph starting from the source node.
