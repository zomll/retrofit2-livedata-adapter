package com.zomll.retrofit2.livedata;

import android.support.annotation.NonNull;
import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;

/**
 * @author Zomll
 * @date 2020-02-24 01:49
 * @describe
 * @email zzh5659@qq.com
 */
public class LiveData2CallAdapter<R> implements CallAdapter<R,Object> {

    private final Type responseType;
    private final boolean isBody;


    public LiveData2CallAdapter(Type responseType, boolean isBody) {
        this.responseType = responseType;
        this.isBody = isBody;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public Object adapt(@NonNull Call<R> call) {

        return isBody ? new BodyAdapt<R>(call) : new ResponseAdpt<R>(call);
    }


}
