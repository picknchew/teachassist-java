package me.picknchew.teachassistapi.model;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {
    private final String name;
    private final String comment;
    private final List<Assignment> assignments;
    private final Map<Category, Double> categoryWeights;

    public Course(String name, String comment, List<Assignment> assignments, Map<Category, Double> categoryWeights) {
        this.name = name;
        this.comment = comment;
        this.assignments = assignments;
        this.categoryWeights = categoryWeights;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public Map<Category, Double> getCategoryWeights() {
        return categoryWeights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (!name.equals(course.name)) return false;
        if (!comment.equals(course.comment)) return false;
        if (!assignments.equals(course.assignments)) return false;
        return categoryWeights.equals(course.categoryWeights);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + comment.hashCode();
        result = 31 * result + assignments.hashCode();
        result = 31 * result + categoryWeights.hashCode();
        return result;
    }

    public static class ResponseDeserializer implements JsonDeserializer<Course> {

        @Override
        public Course deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            List<Assignment> assignments = new ArrayList<Assignment>();
            Map<Category, Double> categoryWeights = new HashMap<Category, Double>();

            JsonObject data = json.getAsJsonObject().getAsJsonObject("data");

            for (Map.Entry<String, JsonElement> entry : data.getAsJsonObject("assessment").getAsJsonObject("data").entrySet()) {
                if (!entry.getKey().equals("categories")) {
                    assignments.add((Assignment) context.deserialize(entry.getValue(), Assignment.class));
                    continue;
                }

                for (Map.Entry<String, JsonElement> weightEntry : entry.getValue().getAsJsonObject().entrySet()) {
                    if (weightEntry.getKey().equals("title") || weightEntry.getKey().equals("feedback")) {
                        continue;
                    }

                    categoryWeights.put(Category.get(weightEntry.getKey()), weightEntry.getValue().getAsDouble());
                }
            }

            return new Course(data.get("course").getAsString(), data.get("comment").getAsString(), assignments, categoryWeights);
        }
    }
}
