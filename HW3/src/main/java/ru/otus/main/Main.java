package ru.otus.main;

import java.util.*;

public class Main {

    public static void main(String ...args) {
        MyArrayList<Integer> myList = new MyArrayList<>();
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();

        myList.add(11);
        myList.add(22);
        myList.add(33);

        arrayList1.add(10);
        arrayList1.add(20);
        arrayList1.add(30);

        arrayList2.add(100);
        arrayList2.add(200);
        arrayList2.add(300);

        System.out.println(Arrays.toString(myList.toArray()));

        myList.addAll(arrayList1);
        System.out.println(Arrays.toString(myList.toArray()));

        Collections.addAll(myList, 12, 22, 32);
        System.out.println(Arrays.toString(myList.toArray()));

        Collections.sort(myList);
        System.out.println(Arrays.toString(myList.toArray()));

        System.out.println(Arrays.toString(myList.toArray()));

        Collections.copy(myList, arrayList2);
        System.out.println(Arrays.toString(myList.toArray()));
    }
}
