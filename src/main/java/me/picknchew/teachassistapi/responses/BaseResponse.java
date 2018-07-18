package me.picknchew.teachassistapi.responses;

public class BaseResponse<T> {
    private final T response;
    private final String error;

    public BaseResponse(T response, String error) {
        this.response = response;
        this.error = error;
    }

    public T get() {
        return response;
    }

    public boolean hasError() {
        return error != null;
    }

    public String getError() {
        return error;
    }
}
