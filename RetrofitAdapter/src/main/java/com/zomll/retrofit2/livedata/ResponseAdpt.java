package com.zomll.retrofit2.livedata;

import android.support.annotation.NonNull;

import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Zomll
 * @date 2020-02-24 02:38
 * @describe
 * @email zzh5659@qq.com
 */
final class ResponseAdpt<R> extends ApiRespLiveData<Response<R>> {
    private Call<R> call;
    private final AtomicBoolean flag;

    public ResponseAdpt(Call<R> call) {
        this.call = call.clone();
        flag = new AtomicBoolean(false);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        call.cancel();
    }

    @Override
    protected void onActive() {
        super.onActive();

        if(flag.compareAndSet(false,true)){
            call.enqueue(new Callback<R>() {
                @Override
                public void onResponse(@NonNull Call<R> call, @NonNull Response<R> response) {
                    onSuccess(response);
                }

                @Override
                public void onFailure(@NonNull Call<R> call, @NonNull Throwable t) {
                    onError(t);
                }
            });
        }
    }
}
