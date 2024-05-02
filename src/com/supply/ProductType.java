package com.supply;
import com.supply.EnumFactory.*;
public class ProductType extends Graph_Node {

    private ProductTypeName productTypeName;
    private String name;
    public ProductType(ProductTypeName productTypeName){
        super(Node_Type.PRODUCT_TYPE, "");
        this.productTypeName = productTypeName;
    }

    public void setName(String name){
        super.setName(name);
    }

    public ProductTypeName getProductTypeName(){
        return productTypeName;
    }
}
