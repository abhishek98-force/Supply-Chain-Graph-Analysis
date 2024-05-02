package com.supply;
import com.supply.EnumFactory.*;
public class SKR extends Graph_Node{
    private double cost;
    public SKR(String name){
        super(Node_Type.SKR,name);
    }

    public String getName(){
        return super.getName();
    }
    public void setCost(double cost){
        this.cost = cost;
    }
    public double getCost(){
        return this.cost;
    }

}
