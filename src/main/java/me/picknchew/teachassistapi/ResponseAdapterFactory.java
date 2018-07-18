package me.picknchew.teachassistapi;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import me.picknchew.teachassistapi.responses.BaseResponse;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ResponseAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (!BaseResponse.class.isAssignableFrom(type.getRawType())) {
            return null;
        }

        Type parameterizedtype = ((ParameterizedType) type.getType()).getActualTypeArguments()[0];
        TypeAdapter typeAdapter = gson.getAdapter(TypeToken.get(parameterizedtype));

        return new ResponseAdapter<T>(gson.getAdapter(JsonElement.class), typeAdapter);
    }

    private static class ResponseAdapter<T> extends TypeAdapter<T> {
        private final TypeAdapter<JsonElement> elementAdapter;
        private final TypeAdapter<?> typeAdapter;

        ResponseAdapter(TypeAdapter<JsonElement> elementAdapter, TypeAdapter<?> typeAdapter) {
            this.elementAdapter = elementAdapter;
            this.typeAdapter = typeAdapter;
        }

        @Override
        public void write(JsonWriter out, T value) {
        }

        @Override
        public T read(JsonReader in) throws IOException {
            JsonObject object = elementAdapter.read(in).getAsJsonArray().get(0).getAsJsonObject();
            String error = object.has("ERROR") ? object.get("ERROR").getAsString() : null;

            return (T) new BaseResponse<T>((T) typeAdapter.fromJsonTree(object), error);
        }
    }
}
