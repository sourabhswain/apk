package com.vouov.util;

import com.vouov.annotation.JSONField;
import com.vouov.exception.SVException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * User: yuminglong
 * Date: 11-9-2
 * Time: 下午2:33
 * Version: 1.0.0
 */
public class HttpUtils {
    public static <T> T json2Bean(JSONObject json, Class<T> clazz) throws InstantiationException, IllegalAccessException, SVException {
        Field[] fields = clazz.getDeclaredFields();
        T instance = clazz.newInstance();
        for (Field field : fields) {
            String jsonName = field.getName();
            if (field.isAnnotationPresent(JSONField.class)) {
                jsonName = field.getAnnotation(JSONField.class).value();
            }
            Class type = field.getType();

            if (byte.class.equals(type) || Byte.class.equals(type)) {
                field.setAccessible(true);
                field.setByte(instance, Byte.valueOf(json.optString(jsonName)));
            } else if (short.class.equals(type) || Short.class.equals(type)) {
                field.setAccessible(true);
                field.setShort(instance, Short.valueOf(json.optString(jsonName)));
            } else if (int.class.equals(type) || Integer.class.equals(type)) {
                field.setAccessible(true);
                field.setInt(instance, json.optInt(jsonName));
            } else if (long.class.equals(type) || Long.class.equals(type)) {
                field.setAccessible(true);
                field.setLong(instance, json.optLong(jsonName));
            } else if (float.class.equals(type) || Float.class.equals(type)) {
                field.setAccessible(true);
                field.setFloat(instance, Float.valueOf(json.optString(jsonName)));
            } else if (double.class.equals(type) || Double.class.equals(type)) {
                field.setAccessible(true);
                field.setDouble(instance, json.optDouble(jsonName));
            } else if (boolean.class.equals(type) || Boolean.class.equals(type)) {
                field.setAccessible(true);
                field.setBoolean(instance, json.optBoolean(jsonName));
            } else if (char.class.equals(type) || Character.class.equals(type)) {
                if (!json.isNull(jsonName) && json.optString(jsonName).length() > 0) {
                    field.setAccessible(true);
                    field.setChar(instance, json.optString(jsonName).charAt(0));
                }

            } else if (String.class.equals(type)) {
                String setMethod = "set" + field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
                try {
                    Method method = clazz.getMethod(setMethod, type);
                    method.invoke(instance, json.optString(jsonName));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else if (Date.class.equals(type)) {

            } else {

                throw new SVException("type is array ");
            }
        }

        return instance;
    }

    public static <T> List<T> json2Bean(JSONArray json, Class<T> clazz) {
        return null;
    }
}
