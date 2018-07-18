package me.picknchew.teachassistapi.model;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Assignment {
    private final String name;
    private final String feedback;
    private final Map<Category, Mark> marks;

    public Assignment(String name, String feedback, Map<Category, Mark> marks) {
        this.name = name;
        this.feedback = feedback;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getFeedback() {
        return feedback;
    }

    public Map<Category, Mark> getMarks() {
        return marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assignment that = (Assignment) o;

        if (!name.equals(that.name)) return false;
        if (!feedback.equals(that.feedback)) return false;
        return marks.equals(that.marks);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + feedback.hashCode();
        result = 31 * result + marks.hashCode();
        return result;
    }

    public static class ResponseDeserializer implements JsonDeserializer<Assignment> {

        @Override
        public Assignment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();

            String title = jsonObject.get("title").getAsString();
            String feedback = jsonObject.get("feedback").getAsString();

            Map<Category, Mark> marks = new HashMap<Category, Mark>();

            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                String key = entry.getKey();

                if (!key.equals("title") && !key.equals("feedback")) {
                    marks.put(Category.get(key), (Mark) context.deserialize(entry.getValue(), Mark.class));
                }
            }

            return new Assignment(title, feedback, marks);
        }
    }
}
