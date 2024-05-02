package com.supply;
import com.supply.EnumFactory.Node_Type;
import org.w3c.dom.Node;

public abstract class Graph_Node {
    private final Node_Type nodeType;
    private String name;
    public Graph_Node(Node_Type nodeType, String name){
        this.nodeType = nodeType;
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Node_Type getNodeType() {
        return this.nodeType;
    }
}
