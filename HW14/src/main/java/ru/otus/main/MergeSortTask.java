package ru.otus.main;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

class MergeSortTask extends RecursiveAction {

    private int[] arr;
    private int start, end;
    private int threshold;

    /**
     * @param arr
     * @param start
     * @param end
     * @param threshold
     */
    MergeSortTask(int[] arr, int start, int end, int threshold) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    /**
     *
     */
    @Override
    protected void compute() {
        if (end - start <= threshold) {
            Arrays.sort(arr, start, end);
            return;
        }
        int mid = start + (end - start) / 2;
        invokeAll(new MergeSortTask(arr, start, mid, threshold), new MergeSortTask(arr, mid, end, threshold));
        sequentialMerge(mid);
    }

    /**
     * @param mid
     */
    private void sequentialMerge(int mid) {
        int[] temp = new int[end - start];
        System.arraycopy(arr, start, temp, 0, temp.length);
        int tempStart = 0;
        int tempEnd = mid - start;
        int i = start;
        while (i < end) {
            if ((tempEnd >= end - start) ||
                    (tempStart < mid - start && temp[tempStart] < temp[tempEnd])) {
                arr[i] = temp[tempStart++];
            } else {
                arr[i] = temp[tempEnd++];
            }
            i++;
        }
    }
}