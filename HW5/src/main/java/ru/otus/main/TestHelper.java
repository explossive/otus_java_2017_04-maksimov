package ru.otus.main;


import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import ru.otus.main.annotations.After;
import ru.otus.main.annotations.Before;
import ru.otus.main.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class TestHelper {

    public static void runTests(Class<?> clazz) throws Exception {
        System.out.println("Started");

        Set<Method> beforeMethods = new HashSet<>();
        Set<Method> testMethods = new HashSet<>();
        Set<Method> afterMethods = new HashSet<>();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getDeclaredAnnotation(Before.class) != null)
                beforeMethods.add(method);
            if (method.getDeclaredAnnotation(Test.class) != null)
                testMethods.add(method);
            if (method.getDeclaredAnnotation(After.class) != null)
                afterMethods.add(method);
        }

        Object object = clazz.newInstance();
        for (Method testMethod : testMethods) {
            System.out.println("---");
            for (Method beforeMethod : beforeMethods)
                beforeMethod.invoke(object);

            try {
                testMethod.invoke(object);
                System.out.println(clazz.getCanonicalName() + testMethod.getName());
            } catch (InvocationTargetException e) {
                System.out.println(clazz.getCanonicalName() + testMethod.getName());
                Throwable cause = e.getCause();
                if (cause != null)
                    cause.printStackTrace(System.out);
            }
            for (Method afterMethod : afterMethods)
                afterMethod.invoke(object);
        }

        System.out.println("---");
        System.out.println("Completed");
    }
}
