package me.picknchew.teachassistapi.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.picknchew.teachassistapi.model.CourseInfo;
import me.picknchew.teachassistapi.model.Session;

public class CourseRequest {
    @SerializedName("student_id")
    private final String studentId;

    @SerializedName("token")
    @Expose
    private final String token;

    @SerializedName("subject_id")
    @Expose
    private final String subjectId;

    public CourseRequest(Session session, CourseInfo info) {
        this.studentId = session.getStudentId();
        this.token = session.getToken();
        this.subjectId = info.getId();
    }
}
