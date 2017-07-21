package ru.otus.main;


class ArraysHelper {

    /**
     * @param count
     * @return
     */
    static int[] generateArray(int count) {
        int[] array = new int[count];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.round((Math.random() * 30) - 15);
        }
        return array;
    }

    /**
     * @param array
     * @return
     */
    static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

}
