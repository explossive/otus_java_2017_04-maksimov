package ru.otus.main;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static ru.otus.main.InstrumentationAgent.printInstrumentationSize;

public class Main {

    public static void main(String ...args){

        StringBuilder sb = new StringBuilder(1000);
        String emptyString = "";
        String string = "ToBeOrNotToBeThatIsTheQuestion";
        String[] strings = {emptyString, string, "Dustin"};
        String[] moreStrings = new String[1000];
        List<String> someStrings = new ArrayList<String>();
        EmptyClass empty = new EmptyClass();
        BigDecimal bd = new BigDecimal("999999999999999999.99999999");
        System.out.println("StringBuilder(1000)");
        printInstrumentationSize(sb);
        System.out.println("emptyString");
        printInstrumentationSize(emptyString);
        System.out.println("string");
        printInstrumentationSize(string);
        System.out.println("strings[]");
        printInstrumentationSize(strings);
        System.out.println("moreStrings[]");
        printInstrumentationSize(moreStrings);
        System.out.println("someStrings");
        printInstrumentationSize(someStrings);
        System.out.println("EmptyClass");
        printInstrumentationSize(empty);
        System.out.println("BigDecimal");
        printInstrumentationSize(bd);



        System.out.println("array string");
        String[] s = new String[1000];
        printInstrumentationSize(s);
        for (int i = 0; i < s.length; i++) {
            s[i] = "Cat";
        }
        System.out.println("filled array");
        printInstrumentationSize(s);

        ArrayList<String> items = new ArrayList<>();
        System.out.println("empty ArrayList");
        printInstrumentationSize(items);
        for (int i = 0; i < 100; i++) {
            items.add("Cat");
        }
        System.out.println("Added 100 items to ArrayList");
        printInstrumentationSize(items);

    }
}