package com.etech.mockito.vsdoreturn;

import java.util.HashMap;
import java.util.Map;

public class CarShop {

    public Integer getStockForBrand(String brand){

        Map<String, Integer> carsInStock = getCarsInStock();

        if (carsInStock.containsKey(brand)){
            return carsInStock.get(brand);
        }else {
            throw new RuntimeException("Car brand not in stock");
        }
    }


    public Map<String, Integer> getCarsInStock(){
        // calls database
        System.out.println("Getting cars in stock");
        return new HashMap<String, Integer>();
    }



}
