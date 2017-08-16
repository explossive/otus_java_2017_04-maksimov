package ru.otus.main.Lightson.helper;


import ru.otus.main.Lightson.strategy.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JsonHelper {

    private static final List<Class> primitive = Arrays.asList(Integer.class, Double.class, Long.class, String.class, Character.class, Short.class, Float.class, Byte.class);


    /**
     * @param element
     * @return
     */
    public static JsonStrategy getStrategy(Object element) {
        if (element == null) {
            return new JsonNullStrategy();
        } else if (element.getClass().isArray()) {
            return new JsonArrayStrategy();
        } else if (primitive.contains(element.getClass())) {
            return new JsonPrimitiveStrategy();
        } else if (element instanceof Collection) {
            return new JsonCollectionStrategy();
        }
        return new JsonObjectStrategy();
    }


    /**
     * @param data
     * @return
     */
    public static String formatObjectString(StringBuilder data) {
        if (data.length() > 0) {
            data.deleteCharAt(data.length() - 1);
            return String.format("{%s}", data.toString());
        } else {
            return "";
        }
    }

}
