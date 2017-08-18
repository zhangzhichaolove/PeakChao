package com.chao.peak.util;

import com.chao.peak.bean.Result;
import com.chao.peak.bean.UserBean;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created by Chao on 2017/8/18.
 */
public class JsonUtil {
    //    private static Gson gson = new Gson();
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    public static String toJson(Object bean) {
        return gson.toJson(bean);
    }

    public static String toJsonData(Object bean) {
        Result result = new Result();
        result.setData(bean);
        return gson.toJson(result);
    }

    public static <T> String toJsonArray(List<T> bean) {
        Result result = new Result();
        result.setData(bean);
        return gson.toJson(result);
    }

    @Test
    public void testJson() {
        UserBean bean = new UserBean();
        bean.setUsername("test");
        bean.setEmail("test@qq.com");
        String s = toJson(bean);
        System.out.println(s);
    }

    @Test
    public void testGson() {
        ExclusionStrategy strategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return fieldAttributes.getName().startsWith("id");
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        };
        Gson gson = new GsonBuilder().setExclusionStrategies(strategy).serializeNulls().create();
        UserBean bean = new UserBean();
        bean.setUsername("test");
        bean.setEmail("test@qq.com");
        String s = gson.toJson(bean);
        System.out.println(s);
    }

}
