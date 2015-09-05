package com.raulavila.triangle.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class TrianglePathFinderTest {

	@Test
    public void testBigTriangle() {
        TriangleBuilder builder = TriangleBuilder.newInstance(5);
       
        Random random = new Random();
       
        System.out.println("Creating triangle with 500 rows");
        
        for(int i = 2; i<=500; i++) {
            List<Long> list = new ArrayList<Long>();
           
            for(int j=0; j<i; j++) {
                list.add((long)random.nextInt(30)+1);
            }
           
            builder.addLevel(list);
           
        }

        //System.out.println("Calculating minimal path: "+builder.getTriangleAsLists());

        long start = System.nanoTime();
        
        TrianglePathFinder pathFinder = TrianglePathFinder.newInstance(builder.getTriangleRoot());
        
        long end = System.nanoTime();
        
        long elapsedTime = end - start;
        
        double seconds =  (double)elapsedTime / 1000000000.0;
        
        System.out.println(String.format("Sum of minimal path: %d", pathFinder.getMinimalSum()));
        System.out.println(String.format("Minimal path calculated in  %s seconds", String.valueOf(seconds)));
    }


	
}
