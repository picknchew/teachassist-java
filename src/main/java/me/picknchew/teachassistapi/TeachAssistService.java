package me.picknchew.teachassistapi;

import me.picknchew.teachassistapi.model.Course;
import me.picknchew.teachassistapi.model.CourseInfo;
import me.picknchew.teachassistapi.model.Session;
import me.picknchew.teachassistapi.requests.AuthenticationRequest;
import me.picknchew.teachassistapi.requests.CourseRequest;
import me.picknchew.teachassistapi.responses.BaseResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

public interface TeachAssistService {

    @POST(".")
    Call<BaseResponse<Session>> authenticate(@Body AuthenticationRequest request);

    @POST(".")
    Call<BaseResponse<List<CourseInfo>>> getCoursesInfo(@Body Session session);

    @POST(".")
    Call<BaseResponse<Course>> getCourse(@Body CourseRequest request);
}
