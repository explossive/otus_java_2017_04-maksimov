package ru.otus.main.Lightson;


import ru.otus.main.PrimitiveClass;

import java.lang.reflect.Field;

public class Lightson {


    public String toJson(PrimitiveClass primitiveClass) throws IllegalAccessException {
        Json json = new Json();


        Field[] fields = primitiveClass.getClass().getDeclaredFields();
        for (Field field : fields) {

                

        }

        return json.toString();


    }


}
