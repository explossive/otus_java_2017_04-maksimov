package ru.otus.main;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

class Sorting {

    private int[] data;
    private static final int COUNT_THREAD = 4;

    /**
     * @param data
     */
    Sorting(int[] data) {
        this.data = data;
    }

    /**
     *
     */
    void start(){
        int threshold = data.length / Runtime.getRuntime().availableProcessors();
        ForkJoinTask rootTask = new MergeSortTask(data, 0, data.length, threshold);
        ForkJoinPool pool = new ForkJoinPool(COUNT_THREAD);
        pool.invoke(rootTask);
    }
}
