package me.picknchew.teachassistapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Session {

    @SerializedName("student_id")
    @Expose
    private final String studentId;

    @SerializedName("token")
    @Expose
    private final String token;

    private Session(String studentId, String token) {
        this.studentId = studentId;
        this.token = token;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (studentId != null ? !studentId.equals(session.studentId) : session.studentId != null) return false;
        return token != null ? token.equals(session.token) : session.token == null;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}
