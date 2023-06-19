package com.example.project;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private CartItem temp = new CartItem("TestProduct", "TestPrice","TestCategory", "TestImageUrl");

    @Before
    public void setUp(){
        System.out.println("Test Başladı");
    }
    @Test
    public void GetterTest(){

        // To test Getters
        String productCase = temp.getProduct();
        String priceCase = temp.getPrice();
        String categoryCase = temp.getCategory();
        String imageUrlCase = temp.getImageUrl();
        assertEquals(typeCase, "TestProduct");
        assertEquals(nameCase, "TestPrice");
        assertEquals(equipmentCase, "TestCategory");
        assertEquals(imgIDCase, "TestImageUrl");
    }
    @After
    public void tearDown(){
        System.out.println("Test Bitti.");
    }
}