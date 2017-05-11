package ru.otus.main;


import org.junit.Test;

public class AssertTest {

    @Test(expected = java.lang.AssertionError.class)
    public void assertFail() {
        Assert.fail("fail assert");
    }

    @Test
    public void assertTrueGood1() {
        Assert.assertTrue(true);
    }

    @Test
    public void assertTrueGood2() {
        Assert.assertTrue("value is not true", true);
    }

    @Test(expected = java.lang.AssertionError.class)
    public void assertTrueBad1() {
        Assert.assertTrue(false);
    }

    @Test(expected = java.lang.AssertionError.class)
    public void assertTrueBad2() {
        Assert.assertTrue("assert is not false", false);
    }

    @Test
    public void assertFalseGood1() {
        Assert.assertFalse(false);
    }

    @Test
    public void assertFalseGood2() {
        Assert.assertFalse("value is not false", false);
    }

    @Test(expected = java.lang.AssertionError.class)
    public void assertFalseBad1() {
        Assert.assertFalse(true);
    }

    @Test(expected = java.lang.AssertionError.class)
    public void assertFalseBad2() {
        Assert.assertFalse("assert is not true", true);
    }


    @Test
    public void assertNotNullGood1() {
        Assert.assertNotNull(new Object());
    }

    @Test
    public void assertNotNullGood2() {
        Assert.assertNotNull("value is null", new Object());
    }

    @Test(expected = java.lang.AssertionError.class)
    public void assertNotNullBad1() {
        Assert.assertNotNull(null);
    }

    @Test(expected = java.lang.AssertionError.class)
    public void assertNotNullBad2() {
        Assert.assertNotNull("value is not null", null);
    }

}
