package me.picknchew.teachassistapi.model;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CourseInfo {
    // message that teachassist sends when mark is hidden.
    private static final String MARK_HIDDEN = "Please see teacher for current status regarding achievement in the course";

    @SerializedName("subject_id")
    @Expose
    private String id;

    @SerializedName("course")
    @Expose
    private String name;

    @SerializedName("mark")
    @Expose
    private String mark;

    // empty constructor for gson
    public CourseInfo() {}

    public CourseInfo(String id, String mark, String name) {
        this.id = id;
        this.mark = mark;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourseCode() {
        // SCH3U1-03 -> SCH3U1
        return name.split("-")[0];
    }

    public String getMark() {
        return mark;
    }

    public boolean isMarkHidden() {
        return mark.equals(MARK_HIDDEN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseInfo that = (CourseInfo) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return mark.equals(that.mark);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + mark.hashCode();
        return result;
    }

    public static class ResponseDeserializer implements JsonDeserializer<List<CourseInfo>> {

        @Override
        public List<CourseInfo> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject rootObject = json.getAsJsonObject();
            JsonArray subjects = rootObject.getAsJsonArray("data").get(0).getAsJsonObject().get("subjects").getAsJsonArray();
            List<CourseInfo> coursesInfo = new ArrayList<CourseInfo>();

            for (JsonElement element : subjects) {
                coursesInfo.add((CourseInfo) context.deserialize(element, CourseInfo.class));
            }

            return coursesInfo;
        }
    }
}
