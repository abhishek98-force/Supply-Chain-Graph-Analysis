package com.supply;
import com.supply.EnumFactory.*;

import java.util.HashMap;
import java.util.Map;

public class Warehouse extends Graph_Node{
    private static final Map<WarehouseName, Warehouse> warehouseMap = new HashMap<>();
    private double totalValuation;
    static {
        for(WarehouseName warehouseName : WarehouseName.values()){
            warehouseMap.put(warehouseName, new Warehouse(warehouseName));
        }
    }

    private Warehouse(WarehouseName warehouseName){
        super(Node_Type.WAREHOUSE, warehouseName.toString());
    }

    public static Warehouse getWarehouse(WarehouseName warehouseName){
        return warehouseMap.get(warehouseName);
    }

    public void setTotalValuation(double totalValuation){
        this.totalValuation = totalValuation;
    }

    public double getTotalCost(){
        return this.totalValuation;
    }

    public String getWarehouseName(){
        return super.getName();
    }


}
