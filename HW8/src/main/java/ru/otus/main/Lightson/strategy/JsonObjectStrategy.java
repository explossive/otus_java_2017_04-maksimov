package ru.otus.main.Lightson.strategy;

import ru.otus.main.Lightson.JsonBuilder;
import ru.otus.main.Lightson.elements.JsonElement;
import ru.otus.main.Lightson.elements.JsonField;
import ru.otus.main.Lightson.elements.JsonNull;
import ru.otus.main.Lightson.elements.JsonObject;
import ru.otus.main.Lightson.helper.JsonHelper;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;


public class JsonObjectStrategy implements JsonStrategy {
    @Override
    public JsonElement buildElement(Object value, String name) {
        if (value == null) {
            return new JsonNull();
        }
        List<JsonField> fieldValues  =  new ArrayList<>();
        Field[] fields = value.getClass().getDeclaredFields();
        for (Field field1 : fields) {
            if (!Modifier.isStatic(field1.getModifiers())) {
                boolean isAccessible = true;
                if (!field1.isAccessible()) {
                    isAccessible = false;
                    field1.setAccessible(true);
                }
                Object fieldValue = null;
                try {
                    fieldValue = field1.get(value);
                } catch (IllegalAccessException ignored) {

                } finally {
                    if (!isAccessible) field1.setAccessible(false);
                }
                fieldValues.add(new JsonField(field1.getName(), fieldValue));
            }
        }
        JsonObject jsonObject = new JsonObject(name);


        for (JsonField field : fieldValues){
            JsonBuilder jsonElementBuilder = new JsonBuilder(JsonHelper.getStrategy(field.getValue()));
            jsonObject.add(jsonElementBuilder.setName(field.getName()).build(field.getValue()));
        }
        return jsonObject;
    }
}
