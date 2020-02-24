# retrofit2-livedata-adapter
retrofit2-livedata-adapter
## 作用：
支持将使用retrofit定义的 service 接口 返回数据转换为LiveData
## 特点：

## 使用步骤：
### 1.使用此库，项目需要确定依赖了retrofit，此库基于 retrofit 2.\*.\* 版本编写，其他版本不确定兼容性

    implementation "com.squareup.retrofit2:retrofit:2.*.*"
    
### 2.配置Retrofit

    Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveData2CallAdapterFactory.create())
                .client(okhttpBuilder.build())
                .build();
                
### 3.定义 service 接口

    public interface ApiService {
    @GET("api/detail")
     ResultLiveData<GetVideoDetailsResp> getVideoDetails(@Query("id") String id);
    }
  这里返回使用 ResultLiveData,它是LiveData 的子类
  
  GetVideoDetailsResp 为自定义的Json解析结果实体类
  
  
