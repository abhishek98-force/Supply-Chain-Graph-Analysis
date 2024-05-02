package com.supply;
import com.supply.EnumFactory.*;
public class SourceNode extends Graph_Node{
    private static SourceNode instance;
    private SourceNode(){
        super(Node_Type.SOURCE, "Source");
    }

    public static SourceNode getInstance(){
        if(instance == null){
            instance = new SourceNode();
        }
        return instance;
    }
}
