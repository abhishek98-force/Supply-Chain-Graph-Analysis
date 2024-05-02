package com.supply;

import java.util.HashMap;
import java.util.Map;
import com.supply.EnumFactory.CarrierName;
import com.supply.EnumFactory.Node_Type;
public class Carrier extends Graph_Node{

    private static final Map<CarrierName, Carrier> carrierMap = new HashMap<>();

    static {
        for(CarrierName carrierName : CarrierName.values()){
            carrierMap.put(carrierName, new Carrier(carrierName));
        }
    }
    private final CarrierName carrierName;
    private Carrier(CarrierName carrierName){
        super(Node_Type.CARRIER, carrierName.toString());
        this.carrierName = carrierName;
    }

    public static Carrier getCarrier(CarrierName carrierName){
        return carrierMap.get(carrierName);
    }

    public CarrierName getCarrierName(){
        return carrierName;
    }



}
