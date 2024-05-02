package com.supply;
import com.supply.EnumFactory.*;

import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Driver {
    public static void main(String[] args) {
        Random random = new Random();
        GraphRep graph = new GraphRep();
        SourceNode source = SourceNode.getInstance();
        graph.addNode(source);
        for(WarehouseName warehouse : WarehouseName.values()){
            graph.addNode(Warehouse.getWarehouse(warehouse));
            graph.addEdge(source, Warehouse.getWarehouse(warehouse));
            for(ProductTypeName productTypeName : ProductTypeName.values()) {
                ProductType productType = new ProductType(productTypeName);
                productType.setName(productTypeName.toString() + "_" + warehouse.toString());
                graph.addNode(productType);
                graph.addEdge(Warehouse.getWarehouse(warehouse), productType);
                for (int i = 0; i < 2; i++) {
                    String id = productTypeName.toString() + "_" + warehouse.toString() + "_" + i;
                    Graph_Node connectedNode;
                    Carrier c1 = Carrier.getCarrier(CarrierName.values()[random.nextInt(CarrierName.values().length)]);
                    Supplier s1 = Supplier.getSupplier(SupplierName.values()[random.nextInt(SupplierName.values().length)]);
                    SKR skr = new SKR(id);
                    skr.setCost(100.0 + (500.0 - 100.0) * random.nextDouble());
                    graph.addNode(c1);
                    graph.addNode(s1);
                    graph.addNode(skr);
                    graph.addEdge(productType, skr);
                    graph.addEdge(skr, c1);
                    graph.addEdge(skr, s1);
                }
            }

        }
        GraphVisualizer.visualize(graph);
        List<Graph_Node> dataList = graph.getSuppliersAndCarriers(WarehouseName.MUMBAI);
        graph.calculateTotalValuation();
        System.out.println("===================================");
        System.out.println("Total valuation of each warehouse using DFS");
        System.out.println("===================================");
        for(WarehouseName warehouseName : WarehouseName.values()){
            System.out.println(warehouseName+" : valuation is "+Warehouse.getWarehouse(warehouseName).getTotalCost());
        }
        System.out.println();
        System.out.println("===================================");
        System.out.println("Suppliers and Carriers for Mumbai using DFS");
        System.out.println("===================================");
        for(Graph_Node node : dataList){
            System.out.println(node.getName());
        }

        System.out.println();
        System.out.println("===================================");
        System.out.println("Toplogical Sort using Kahns Algorithm");
        System.out.println("===================================");

        for(Graph_Node node : graph.topologicalSortKahns()){
            System.out.println(node.getName());
        }
//

//        MUMBAI : valuation is 1492.1463881205755
//        KOLKATA : valuation is 1949.4721168001024
//        DELHI : valuation is 2000.891722374614
//        BANGALORE : valuation is 1421.7564481312254
//        CHENNAI : valuation is 1436.4355119821964
//        CARRIER_5
//                SUPPLIER_2
//        CARRIER_1
//                SUPPLIER_3
//        SUPPLIER_5
//                CARRIER_4
//        CARRIER_2

    }
}
