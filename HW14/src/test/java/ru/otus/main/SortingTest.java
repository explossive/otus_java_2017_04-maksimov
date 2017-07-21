package ru.otus.main;


import org.junit.Assert;
import org.junit.Test;

public class SortingTest {

    @Test
    public void sortingTest() {
        int[] data = ArraysHelper.generateArray(100_000_000);
        Sorting sorting = new Sorting(data);
        sorting.start();
        Assert.assertTrue("Array not sorted", ArraysHelper.isSorted(data));
    }
}
