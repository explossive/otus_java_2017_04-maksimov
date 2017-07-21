package ru.otus.main;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class PrimitiveClass implements Serializable {
    private static final long serialVersionUID = 4253118941810649752L;

    private int one = 1;
    private BigInteger bigInteger = BigInteger.valueOf(100500);
    private float pi = 3.14f;
    private char c = 'c';
    private String str = "str";
    private Integer integer = 111;
    private boolean is = true;
    private AtomicInteger atomicInteger = new AtomicInteger(101010);

    public void setOne(int one) {
        this.one = one;
    }

    public void setPi(float pi) {
        this.pi = pi;
    }

    public void setC(char c) {
        this.c = c;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public void setIs(boolean is) {
        this.is = is;
    }

    public void setAtomicInteger(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimitiveClass that = (PrimitiveClass) o;
        return one == that.one &&
                Float.compare(that.pi, pi) == 0 &&
                c == that.c &&
                is == that.is &&
                Objects.equals(bigInteger, that.bigInteger) &&
                Objects.equals(str, that.str) &&
                Objects.equals(integer, that.integer) &&
                atomicInteger.compareAndSet(that.atomicInteger.get(), atomicInteger.get());
    }

    @Override
    public String toString() {
        return "PrimitiveClass{" +
                "one=" + one +
                ", bigInteger=" + bigInteger +
                ", pi=" + pi +
                ", c=" + c +
                ", str='" + str + '\'' +
                ", integer=" + integer +
                ", is=" + is +
                ", atomicInteger=" + atomicInteger +
                '}';
    }
}