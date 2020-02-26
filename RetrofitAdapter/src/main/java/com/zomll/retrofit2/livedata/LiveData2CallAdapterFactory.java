package com.zomll.retrofit2.livedata;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author Zomll
 * @date 2020-02-24 01:47
 * @describe
 * @email zzh5659@qq.com
 */
public class LiveData2CallAdapterFactory extends CallAdapter.Factory{

    public static LiveData2CallAdapterFactory create(){
        return new LiveData2CallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> rawType = getRawType(returnType);
        boolean isResultLiveData = rawType == ApiRespLiveData.class;
        if (!isResultLiveData) {
            return null;
        }
        boolean isBody = false;
        Type responseType;

        Type liveDataType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawObservableType = getRawType(liveDataType);
        if (rawObservableType == Response.class) {
            if (!(liveDataType instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parameterized"
                        + " as Response<Foo> or Response<? extends Foo>");
            }
            responseType = getParameterUpperBound(0, (ParameterizedType) liveDataType);
        }else {
            responseType = liveDataType;
            isBody = true;
        }

        return new LiveData2CallAdapter(responseType,isBody);
    }
}
