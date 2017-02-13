package com.linxiangqu.shop.okhttp;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by linxiangqu on 2016/8/18.
 */
public abstract class BaseCallBack<T> {
    public Type type;

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public BaseCallBack() {
        type = getSuperclassTypeParameter(getClass());
    }

    public abstract void onBeforeRequest(String url, HashMap<String, String> params);

    public abstract void onResponse();

    public abstract void onSuccess(Call call, Response response, T t);

    public abstract void onError(Call call, Response response);

    public abstract void onFailure(Call call, IOException e);
}
