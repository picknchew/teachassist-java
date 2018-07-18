package me.picknchew.teachassistapi.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthenticationRequest {
    @SerializedName("student_number")
    @Expose
    private final String studentNumber;

    @SerializedName("password")
    @Expose
    private final String password;

    public AuthenticationRequest(String studentNumber, String password) {
        this.studentNumber = studentNumber;
        this.password = password;
    }
}
