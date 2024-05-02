package com.supply;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import java.util.Map;

public class GraphVisualizer {

    public static void visualize(GraphRep supplyGraph) {
        // Create a JGraphT graph
        Graph<Graph_Node, DefaultEdge> jGraphTGraph = new SimpleGraph<>(DefaultEdge.class);

        // Add vertices and edges to the JGraphT graph
        for (Map.Entry<Graph_Node, List<Graph_Node>> entry : supplyGraph.getAdjacencyList().entrySet()) {
            Graph_Node node = entry.getKey();
            jGraphTGraph.addVertex(node);
            for (Graph_Node connectedNode : entry.getValue()) {
                jGraphTGraph.addVertex(connectedNode);
                jGraphTGraph.addEdge(node, connectedNode);
            }
        }

        // Create a JGraphX adapter
        JGraphXAdapter<Graph_Node, DefaultEdge> jGraphXAdapter = new JGraphXAdapter<>(jGraphTGraph);

        // Create a layout for the JGraphX adapter
        mxCircleLayout layout = new mxCircleLayout(jGraphXAdapter);
        layout.execute(jGraphXAdapter.getDefaultParent());

        // Create a JGraphX graph component and add it to a frame
        mxGraphComponent graphComponent = new mxGraphComponent(jGraphXAdapter);
        JFrame frame = new JFrame();
        frame.getContentPane().add(graphComponent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);


    }
}