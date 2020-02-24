package com.zomll.retrofit2.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * @author Zomll
 * @date 2020-02-24 01:04
 * @describe
 * @email zzh5659@qq.com
 */
public class ResultLiveData<T> extends MutableLiveData<LiveDataResponse<T>> {

    public ResultLiveData() {
    }

    protected void onLoading(){
        postValue(new LiveDataResponse(LiveDataResponse.Status.LOADING,null));
    }

    protected void onSuccess(@NonNull T t){
        postValue(new LiveDataResponse<T>(LiveDataResponse.Status.SUCCESS,t));
    }

    protected void onError(Throwable throwable){
        postValue(new LiveDataResponse(LiveDataResponse.Status.ERROR,throwable));
    }

}
