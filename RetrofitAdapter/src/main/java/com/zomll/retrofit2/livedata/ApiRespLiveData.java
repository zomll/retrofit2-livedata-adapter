package com.zomll.retrofit2.livedata;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

/**
 * @author Zomll
 * @date 2020-02-24 01:04
 * @describe
 * @email zzh5659@qq.com
 */
public class ApiRespLiveData<T> extends LiveData<DataResponse<T>> {

    public ApiRespLiveData() {
    }

    protected void onSuccess(@NonNull T t){
        postValue(DataResponse.onSuccess(t));
    }

    protected void onError(Throwable throwable){
        postValue(DataResponse.onErr(throwable));
    }

    protected void onLoading(@NonNull T t){
        postValue(DataResponse.onLoading(t));
    }

}
