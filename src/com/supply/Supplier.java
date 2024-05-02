package com.supply;

import java.util.HashMap;
import java.util.Map;
import com.supply.EnumFactory.SupplierName;
import com.supply.EnumFactory.Node_Type;


public class Supplier extends Graph_Node {
    private static final Map<SupplierName, Supplier> supplierMap = new HashMap<>();
    static {
        for(SupplierName supplierName : SupplierName.values()){
            supplierMap.put(supplierName, new Supplier(supplierName));
        }
    }
    private final SupplierName supplierName;
    private Supplier(SupplierName supplierName){
        super(Node_Type.SUPPLIER, supplierName.toString());
        this.supplierName = supplierName;
    }

    public static Supplier getSupplier(SupplierName supplierName){
        return supplierMap.get(supplierName);
    }

    public SupplierName getSupplierName(){
        return supplierName;
    }
}
