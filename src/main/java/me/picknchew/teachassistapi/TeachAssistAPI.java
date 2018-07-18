package me.picknchew.teachassistapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.picknchew.teachassistapi.model.Assignment;
import me.picknchew.teachassistapi.model.Course;
import me.picknchew.teachassistapi.model.CourseInfo;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public final class TeachAssistAPI {
    private static final String BASE_URL = "https://ta.yrdsb.ca/v4/students/json.php/";
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new ResponseAdapterFactory())
            .registerTypeAdapter(Assignment.class, new Assignment.ResponseDeserializer())
            .registerTypeAdapter(Course.class, new Course.ResponseDeserializer())
            .registerTypeAdapter(new TypeToken<List<CourseInfo>>() {}.getType(), new CourseInfo.ResponseDeserializer())
            .create();

    private final Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build();

    public TeachAssistService createService() {
        return retrofit.create(TeachAssistService.class);
    }
}
