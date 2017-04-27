package ru.otus.main;

import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Benchmark implements BenchmarkMBean {

    private int size;

    /**
     * @throws IOException
     * @throws InterruptedException
     */
    public void run() throws IOException, InterruptedException {
        Map<String, Property> gc = new HashMap<>();
        List<String> list = new LinkedList<>();

        while (true) {
            for (int j = 0; j < size; j++) {
                list.add("num" + j);
            }
            for (int j = 0; j < size / 2; j++) {
                list.remove(0);
            }
            putProperty(gc);
            writeOut(gc, list.size());
            Thread.sleep(100);
        }
    }

    /**
     * @param gc
     * @param size
     * @throws IOException
     */
    private void writeOut(Map<String, Property> gc, int size) throws IOException {
        StringBuilder data = new StringBuilder("--------------------\n");
        data.append("Amount of elements: ").append(size).append("\n");
        for (Map.Entry<String, Property> gcProperties : gc.entrySet()) {
            data.append("Garbage collector: ").append(gcProperties.getKey()).append("\n");
            data.append("Number of assemblies: ").append(gcProperties.getValue().getCount()).append("\n");
            data.append("working hours:  ").append(gcProperties.getValue().getTime()).append(" milliseconds").append("\n");
        }
        System.out.printf(data.toString());
    }

    /**
     * @param gc
     */
    private void putProperty(Map<String, Property> gc) {
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbageCollector : garbageCollectorMXBeans) {
            if (gc.containsKey(garbageCollector.getName())) {
                Property properties = gc.get(garbageCollector.getName());
                properties.setCount(garbageCollector.getCollectionCount());
                properties.setTime(garbageCollector.getCollectionTime());
                gc.put(garbageCollector.getName(), properties);
            } else {
                gc.put(garbageCollector.getName(), new Property(garbageCollector.getCollectionTime(), garbageCollector.getCollectionCount()));
            }
        }
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }
}