package com.hawa.scrap.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class TumblrDeserializer implements JsonDeserializer {

    Gson gson;

    public TumblrDeserializer() {
        gson = new GsonBuilder().create();
    }

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject response = jsonObject.get("response").getAsJsonObject();
        return gson.fromJson(response, typeOfT);
    }
}
