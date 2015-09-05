package com.raulavila.triangle.utils;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;


public class TriangleBuilderTest {

    @Test
    public void testTriangleBuilder() {

        long [][] lines = {{1}, {1,2}, {1,2,3}, {1,2,3,4}};

        List<List<Long>> triangle = getBidimensionalArrayAsList(lines);

        TriangleBuilder builder = TriangleBuilder.newInstance(triangle.get(0).get(0));

        for(Iterator<List<Long>> itLevel = triangle.listIterator(1); itLevel.hasNext(); ) {
            builder.addLevel(itLevel.next());
        }

        assertTrue("Triangle create by builder should match test input numbers", triangle.equals(builder.getTriangleAsLists()));
    }


    private List<List<Long>> getBidimensionalArrayAsList(long [][] array) {
        List<List<Long>> resultList = new ArrayList<List<Long>>();

        for(long [] row : array) {
            List<Long> level = new ArrayList<Long>();

            for(long number: row)
                level.add(number);

            resultList.add(level);
        }

        return resultList;
    }


}
