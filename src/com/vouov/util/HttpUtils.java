package com.vouov.util;

import com.vouov.annotation.JSONField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

/**
 * User: yuminglong
 * Date: 11-9-2
 * Time: 下午2:33
 * Version: 1.0.0
 */
public class HttpUtils {
    public static <T> T json2Bean(JSONObject json, Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            String jsonName = field.getName();
            if(field.isAnnotationPresent(JSONField.class)){
              jsonName = field.getAnnotation(JSONField.class).value();
            }
            Type type = field.getType();
            if(Boolean.TYPE.equals(type) || int.class.equals(type)){

            }
        }

        return null;
    }

    public static <T> List<T> json2Bean(JSONArray json, Class<T> clazz){
        return null;
    }
}
