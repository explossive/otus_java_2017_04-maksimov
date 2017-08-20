package ru.otus.main;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class TestClass implements Serializable {

    private int valueInt = 1;

    private int[] arr = {1, 32, 213, 32};

    public String str = "text";

    public PrimitiveClass primitiveClass = new PrimitiveClass();
}


class PrimitiveClass {
    private double value1 = 1;
    private int value2 = 1;
}