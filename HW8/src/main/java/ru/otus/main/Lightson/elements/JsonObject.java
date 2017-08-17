package ru.otus.main.Lightson.elements;

import ru.otus.main.Lightson.helper.JsonHelper;

import java.util.ArrayList;
import java.util.List;

public class JsonObject implements JsonElement {

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private List<JsonElement> items  = new ArrayList<>();

    @Override
    public String getJson() {
        StringBuilder sb = new StringBuilder();
        items.forEach(e -> {
            String jsonValue = e.getJson();
            if (!jsonValue.isEmpty()) sb.append(jsonValue).append(',');
        });
        return JsonHelper.formatObjectString(sb);
    }

    public void add(JsonElement item) {
        items.add(item);
    }
}
