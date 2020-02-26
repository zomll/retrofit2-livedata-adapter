package com.zomll.retrofit2.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * @author Zomll
 * @date 2020-02-24 01:04
 * @describe
 * @email zzh5659@qq.com
 */
public class ApiRespLiveData<T> extends MutableLiveData<ApiResponse<T>> {

    public ApiRespLiveData() {
    }

    protected void onSuccess(@NonNull T t){
        postValue(new ApiResponse<T>(ApiResponse.Status.SUCCESS,t));
    }

    protected void onError(Throwable throwable){
        postValue(new ApiResponse(ApiResponse.Status.ERROR,throwable));
    }

}
