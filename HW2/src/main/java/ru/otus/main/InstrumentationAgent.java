package ru.otus.main;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;

import static java.lang.System.out;


public class InstrumentationAgent {

    private static volatile Instrumentation globalInstrumentation;


    /**
     * @param agentArgs
     * @param inst
     */
    public static void premain(final String agentArgs, final Instrumentation inst) {
        out.println("premain...");
        globalInstrumentation = inst;
    }

    /**
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        out.println("agentmain...");
        globalInstrumentation = inst;
    }

    /**
     * @param object
     * @return
     */
    public static long getObjectSize(final Object object) {
        if (globalInstrumentation == null) {
            throw new IllegalStateException("Agent not initialized.");
        }
        return globalInstrumentation.getObjectSize(object);
    }

    /**
     * @param object
     */
    public static void printInstrumentationSize(final Object object) {
        out.println(
                "2Object of type '" + object.getClass() + "' has size of "
                        + InstrumentationAgent.getObjectSize(object) + " bytes.");
    }

    /**
     * @param objects
     */
    public static void printInstrumentationSize(final String[] objects) {
        int size = (int) InstrumentationAgent.getObjectSize(objects);
        for (String object : objects) {
            if (null == object) continue;
            size += InstrumentationAgent.getObjectSize(object);
        }
        out.println(
                "1Object of type '" + objects.getClass() + "' has size of "
                        + size + " bytes.");
    }

    /**
     * @param objects
     */
    public static void printInstrumentationSize(final ArrayList<String> objects) {
        int size = 0;
        for (String object : objects) {
            size += InstrumentationAgent.getObjectSize(object);
        }
        out.println(
                "Object of type '" + objects.getClass() + "' has size of "
                        + size + " bytes.");
    }
}
