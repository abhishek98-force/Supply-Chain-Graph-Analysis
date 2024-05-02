package com.supply;

import java.util.*;

import com.supply.EnumFactory.*;
import org.jgrapht.Graph;

public class GraphRep {

    Map<Graph_Node, List<Graph_Node>> adjacencyList;
    public GraphRep() {
        this.adjacencyList = new HashMap<>();
    }
    public void addNode(Graph_Node node) {
        adjacencyList.put(node, new ArrayList<>());
    }

    public void addEdge(Graph_Node node1, Graph_Node node2) {
        if(!adjacencyList.containsKey(node1)) {
           throw new IllegalArgumentException("Node 1 does not exist in the graph");
        }
        adjacencyList.get(node1).add(node2);
    }

    public Map<Graph_Node, List<Graph_Node>> getAdjacencyList() {
        return adjacencyList;
    }

    public List<Graph_Node> getSuppliersAndCarriers(WarehouseName warehouseName){
        List<Graph_Node> suppliersAndCarriers = new ArrayList<>();
        Set<Graph_Node> visited = new HashSet<>();
        dfs(Warehouse.getWarehouse(warehouseName), visited, suppliersAndCarriers,  Warehouse.getWarehouse(WarehouseName.CHENNAI), true);
        return suppliersAndCarriers;
    }

    public void calculateTotalValuation(){
        Set<Graph_Node> visited = new HashSet<>();
        List<Graph_Node> dummy = new ArrayList<>();
        SourceNode source = SourceNode.getInstance();
        for(Graph_Node neighbour : adjacencyList.get(source)){
            if(neighbour.getNodeType() == Node_Type.WAREHOUSE){
                Warehouse warehouse = (Warehouse) neighbour;
                dfs(warehouse, visited, dummy, warehouse, false);
            }
        }
    }


    private void dfs(Graph_Node node, Set<Graph_Node> visited, List<Graph_Node> suppliersAndCarriers, Graph_Node warehouse, boolean isValuation){
        visited.add(node);
        if(isValuation) {
            if (node.getNodeType() == Node_Type.SUPPLIER || node.getNodeType() == Node_Type.CARRIER) {
                suppliersAndCarriers.add(node);
            }
            for (Graph_Node neighbour : adjacencyList.get(node)) {
                if (!visited.contains(neighbour)) {
                    dfs(neighbour, visited, suppliersAndCarriers, warehouse, isValuation);
                }
            }
        } else {
            if(node.getNodeType() == Node_Type.SKR){
                ((Warehouse) warehouse).setTotalValuation(((Warehouse)warehouse).getTotalCost() + ((SKR) node).getCost());
            }
            for(Graph_Node neighbour : adjacencyList.get(node)){
                if(!visited.contains(neighbour)){
                    dfs(neighbour, visited, suppliersAndCarriers, warehouse, isValuation);
                }
            }
        }
    }


        public List<Graph_Node> topologicalSortKahns() {

            Map<Graph_Node, Integer> inDegree = new HashMap<>();
            for (Graph_Node node : adjacencyList.keySet()) {
                inDegree.putIfAbsent(node, 0);
                for (Graph_Node neighbour : adjacencyList.get(node)) {
                    inDegree.put(neighbour, inDegree.getOrDefault(neighbour, 0) + 1);
                }
            }


            Queue<Graph_Node> queue = new LinkedList<>();
            for (Graph_Node node : inDegree.keySet()) {
                if (inDegree.get(node) == 0) {
                    queue.offer(node);
                }
            }


            List<Graph_Node> topologicalOrder = new ArrayList<>();
            while (!queue.isEmpty()) {
                Graph_Node currentVertex = queue.poll();
                topologicalOrder.add(currentVertex);


                List<Graph_Node> neighbors = adjacencyList.getOrDefault(currentVertex, new ArrayList<>());
                for (Graph_Node neighbor : neighbors) {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if (inDegree.get(neighbor) == 0) {
                        queue.offer(neighbor);
                    }
                }
            }

            if (topologicalOrder.size() == adjacencyList.size()) {
                return topologicalOrder;
            } else {
                return null;
            }
        }



    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Graph_Node, List<Graph_Node>> entry : adjacencyList.entrySet()) {
            sb.append(entry.getKey().getName());
            sb.append(" -> ");
            for(Graph_Node node : entry.getValue()) {
                sb.append(node.getName());
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
