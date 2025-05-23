package com.cerad.authservice.utils;

import com.common.dto.ApiResponse;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AuthUtils {

    private AuthUtils(){

    }


    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
    }

    public static ResponseEntity<ApiResponse> getApiResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(new ApiResponse(message, null), status);
    }

    public static ResponseEntity<ApiResponse> apiResponseEntity(String message,Object data, HttpStatus status) {
        return new ResponseEntity<>(new ApiResponse(message, data), status);
    }

    public static String getUUID(){
        Date date = new Date();
        long time = date.getTime();
        return "BILL-"+ time;
    }

    public static JSONArray getJsonArrayFormString(String data) throws JSONException {
        JSONArray jsonArray = new JSONArray(data);
        return jsonArray;
    }

    public static Map<String,Object> getMapFromJson(String data){
        if(!Strings.isNullOrEmpty(data))
            return new Gson().fromJson(data,new TypeToken<Map<String,Object>>(){
            }.getType());
        return new HashMap<>();
    }

    public static Boolean isFileExist(String path){
        log.info("Inside isFileExist {}", path);
        try{
            File file = new File(path);
            return (file != null && file.exists()) ? Boolean.TRUE : Boolean.FALSE;
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
}
