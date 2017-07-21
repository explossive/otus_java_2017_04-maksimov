package ru.otus.main;

public class Main {

    private static final int COUNT_ELEMENTS = 10_000_000;

    public static void main(String... args) {
        int[] data = ArraysHelper.generateArray(COUNT_ELEMENTS);
        Sorting sorting = new Sorting(data);
        sorting.start();
    }
}

