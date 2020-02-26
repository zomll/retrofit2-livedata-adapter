package com.zomll.retrofit2.livedata;

/**
 * @author Zomll
 * @date 2020-02-24 04:21
 * @describe
 * @email zzh5659@qq.com
 */
public class ApiResponse<T> {
    public enum Status{
        /**
         * 成功
         */
        SUCCESS,

        /**
         * 失败
         */
        ERROR,

        /**
         * 加载中
         */
        LOADING
    }

    private T data;
    private ApiResponse.Status status;
    private Throwable throwable;

    public ApiResponse(Status status, T data) {
        this.data = data;
        this.status = status;
    }

    public ApiResponse(Status status, Throwable throwable) {
        this.status = status;
        this.throwable = throwable;
    }

    public T getData() {
        return data;
    }

    public ApiResponse.Status getStatus() {
        return status;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
