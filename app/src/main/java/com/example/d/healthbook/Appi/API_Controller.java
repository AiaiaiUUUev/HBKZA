package com.example.d.healthbook.Appi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by D on 26.05.2017.
 */

public class API_Controller {
    private static JsonObject jsonGenerator(JsonObject baseObj, String parameterName, Boolean parameterValue) {
        baseObj.addProperty(parameterName, parameterValue);
        return baseObj;
    }

    private static JsonObject jsonGenerator(JsonObject baseObj, String parameterName, String parameterValue) {
        baseObj.addProperty(parameterName, parameterValue);
        return baseObj;
    }

    private static JsonObject jsonGenerator(JsonObject baseObj, String parameterName, int parameterValue) {
        baseObj.addProperty(parameterName, parameterValue);
        return baseObj;
    }

    private static JsonObject jsonGenerator(JsonObject baseObj, String parameterName, Character parameterValue) {
        baseObj.addProperty(parameterName, parameterValue);
        return baseObj;
    }


    private static JsonObject jsonGenerator(JsonObject baseObj, String parameterName, List<String> parameterValue) {
        JsonArray arr_param = new JsonArray();
        for (String param_item:parameterValue) {
            arr_param.add(param_item);
        }
        baseObj.add(parameterName, arr_param);
        return baseObj;
    }

    public static JsonObject loginJson(String email, String password, Boolean rememberMe) {
        JsonObject createdJson = new JsonObject();
        jsonGenerator(createdJson, "email", email);
        jsonGenerator(createdJson, "password", password);
        jsonGenerator(createdJson, "rememberMe", rememberMe);
        return createdJson;
    }

    public static JsonObject registerJson(String type, String email, String phone, String name, String surnmae, String password) {
        JsonObject createdJson = new JsonObject();
        jsonGenerator(createdJson, "type", type);
        jsonGenerator(createdJson, "email", email);
        jsonGenerator(createdJson, "phone", phone);
        jsonGenerator(createdJson, "name", name);
        jsonGenerator(createdJson, "surname", surnmae);
        jsonGenerator(createdJson, "password", password);
        return createdJson;
    }

    public static JsonObject visitJson(Integer user_id, String expert_room_id, String visit_date) {
        JsonObject createdJson = new JsonObject();
        jsonGenerator(createdJson, "user_id", user_id);
        jsonGenerator(createdJson, "expert_room_id", expert_room_id);
        jsonGenerator(createdJson, "visit_date", visit_date);
        return createdJson;
    }

    public static JsonObject feedList(Integer page, String lang, String type) {
        JsonObject createdJson = new JsonObject();
        jsonGenerator(createdJson, "page", page);
        jsonGenerator(createdJson, "lang", lang);
        jsonGenerator(createdJson, "type", type);
        return createdJson;
    }

    public static JsonObject subscribeJson(Integer type, String channel_id) {
        JsonObject createdJson = new JsonObject();
        jsonGenerator(createdJson, "type", type);
        jsonGenerator(createdJson, "channel_id", channel_id);
        return createdJson;
    }

    public static JsonObject subscribeToDoctorJson(String expert_id) {
        JsonObject createdJson = new JsonObject();
        jsonGenerator(createdJson, "expert_id", expert_id);
        return createdJson;
    }

    public static JsonObject editUserProfile(String name, String surname, String middleName, String gender,
                                             String birthday, String height, String weight,
                                             String allergy, String pastIllnesses, String chronicDiseases,
                                             String bloodGroup, String heredity,
                                             String badHabits, String homePhone, String city_id) {
        JsonObject createdJson = new JsonObject();
        jsonGenerator(createdJson, "name", name);
        jsonGenerator(createdJson, "surname", surname);
        jsonGenerator(createdJson, "middleName", middleName);
        jsonGenerator(createdJson, "gender", gender);
        jsonGenerator(createdJson, "birthday", birthday);

        jsonGenerator(createdJson, "height", height);
        jsonGenerator(createdJson, "weight", weight);
        jsonGenerator(createdJson, "allergy", allergy);
        jsonGenerator(createdJson, "pastIllnesses", pastIllnesses);
        jsonGenerator(createdJson, "chronicDiseases", chronicDiseases);

        jsonGenerator(createdJson, "bloodGroup", bloodGroup);
        jsonGenerator(createdJson, "heredity", heredity);

        jsonGenerator(createdJson, "badHabits", badHabits);
        jsonGenerator(createdJson, "homePhone", homePhone);
        jsonGenerator(createdJson, "city_id", city_id);

        return createdJson;
    }

    public static JsonObject saveNoteType1Json(String type, String title, String text, String date) {
        JsonObject createdJson = new JsonObject();
        jsonGenerator(createdJson, "type", type);
        jsonGenerator(createdJson, "title", title);
        jsonGenerator(createdJson, "text", text);
        jsonGenerator(createdJson, "date", date);

        return createdJson;
    }

    public static JsonObject saveNewTakingDrus(String type, String title, String body, String dateBegin,
                                               List<String> times, Integer period,Integer duration,String pills) {
        JsonObject createdJson = new JsonObject();
        jsonGenerator(createdJson, "type", type);
        jsonGenerator(createdJson, "title", title);
        jsonGenerator(createdJson, "body", body);
        jsonGenerator(createdJson, "dateBegin", dateBegin);
        jsonGenerator(createdJson, "times", times);
        jsonGenerator(createdJson, "period", period);
        jsonGenerator(createdJson, "duration", duration);
        jsonGenerator(createdJson, "pills", pills);


        return createdJson;
    }


    public static JsonObject createFamilyMember(String name, String user_name, String user_surname,
                                                    String user_patronymic, String gender, String role,
                                                    String birthday, String height, String weight,
                                                    String type, String email,String id) {
        JsonObject createdJson = new JsonObject();
        jsonGenerator(createdJson, "name", name);
        jsonGenerator(createdJson, "user_name", user_name);
        jsonGenerator(createdJson, "user_surname", user_surname);
        jsonGenerator(createdJson, "user_patronymic", user_patronymic);
        jsonGenerator(createdJson, "gender", gender);
        jsonGenerator(createdJson, "birthday", birthday);

        jsonGenerator(createdJson, "id", id);

        jsonGenerator(createdJson, "height", height);
        jsonGenerator(createdJson, "weight", weight);

        jsonGenerator(createdJson, "role", role);
        jsonGenerator(createdJson, "email", email);

        jsonGenerator(createdJson, "type", type);

        return createdJson;
    }
//    public static JsonObject createFamilyMember(String name, String user_name, String user_surname,
//                                                String user_patronymic, String gender, String role,
//                                                String birthday, String height, String weight,
//                                                String type, String email,String id) {
//        JsonObject createdJson = new JsonObject();
//        jsonGenerator(createdJson, "name", name);
//        jsonGenerator(createdJson, "user_name", user_name);
//        jsonGenerator(createdJson, "user_surname", user_surname);
//        jsonGenerator(createdJson, "user_patronymic", user_patronymic);
//        jsonGenerator(createdJson, "gender", gender);
//        jsonGenerator(createdJson, "birthday", birthday);
//        jsonGenerator(createdJson, "id", id);
//
//        jsonGenerator(createdJson, "height", height);
//        jsonGenerator(createdJson, "weight", weight);
//
//        jsonGenerator(createdJson, "role", role);
//        jsonGenerator(createdJson, "email", email);
//
//        jsonGenerator(createdJson, "type", type);
//
//        return createdJson;
//    }

}
