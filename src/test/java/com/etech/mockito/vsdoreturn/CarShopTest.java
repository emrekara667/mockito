package com.etech.mockito.vsdoreturn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarShopTest {

    @Test
    public void testCarsInShop() {
        CarShop carShopSpy = spy(CarShop.class);

        HashMap<String, Integer> carsInStock = new HashMap<>();
        carsInStock.put("Jaguar", 10);

        //when(carShopSpy.getCarsInStock()).thenReturn(carsInStock);
        doReturn(carsInStock).when(carShopSpy).getCarsInStock();

        assertEquals(carShopSpy.getStockForBrand("Jaguar"), 10);
    }

}
