package ch.hsr.sevi.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ch.hsr.sevi.ea.MergeSort;

public class MergeSortTest {

	@Test
	public void test() {
		
        Integer[] items =
        {
            4, 1, 3, 2, 5, 6
        };
        ArrayList<Integer> list = new ArrayList<>();
        for(Integer i: items) {
        	list.add(i);
        }

        /* Create a new instance of the mergesort Algorithm and sort the array of integers */
        MergeSort<Integer> mergeSort = new MergeSort<>(list);
        mergeSort.sort();
        int i = 1;
        for (Integer x : mergeSort.getSortedItems())
        {
            assertEquals((int)x,i);
            i++;
        }
	}

}
