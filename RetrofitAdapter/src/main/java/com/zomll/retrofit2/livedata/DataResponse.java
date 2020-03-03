package com.zomll.retrofit2.livedata;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Zomll
 * @date 2020-02-24 04:21
 * @describe
 * @email zzh5659@qq.com
 */
public class DataResponse<T> {
    public enum Status {
        /**
         * 加载中
         */
        LOADING,
        /**
         * 成功
         */
        SUCCESS,

        /**
         * 失败
         */
        ERROR,
    }

    private T data;
    @NonNull
    private DataResponse.Status status;
    private Throwable throwable;

    public DataResponse(@NonNull Status status, T data, Throwable throwable) {
        this.data = data;
        this.status = status;
        this.throwable = throwable;
    }


    public T getData() {
        return data;
    }

    public DataResponse.Status getStatus() {
        return status;
    }

    public Throwable getThrowable() {
        return throwable;
    }


    public static <T> DataResponse<T> onLoading(@Nullable T a) {
        return new DataResponse<>(Status.LOADING, a, null);
    }

    public static <T> DataResponse<T> onSuccess(@NonNull T a) {
        return new DataResponse<>(Status.SUCCESS, a, null);
    }

    public static <T> DataResponse<T> onErr(@NonNull Throwable e) {
        return new DataResponse<>(Status.ERROR, null, e);
    }
}
