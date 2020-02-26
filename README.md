# retrofit2-livedata-adapter
retrofit2-livedata-adapter
## 作用：
支持将使用retrofit定义的 service 接口 返回数据转换为LiveData
## 特点：
简洁，高效，通用

## 使用步骤：
### 1.配置JitPack 仓库及依赖

未配置JitPack 仓库的项目需要添加Jitpack仓库，在项目的build.gradle文件配置如下
    
    allprojects {
           repositories {
            ...
                maven { url 'https://jitpack.io' }
            }
	}
	

添加依赖

    //使用此库，项目需要依赖retrofit，此库基于 retrofit 2.*.* 版本编写，其他版本不确定兼容性
    implementation "com.squareup.retrofit2:retrofit:2.*.*"
    
    //添加本库的依赖
     implementation 'com.github.zomll:retrofit2-livedata-adapter:1.0.1'
    
    
### 2.配置Retrofit,添加LiveData2CallAdapterFactory

    Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveData2CallAdapterFactory.create())
                .client(okhttpBuilder.build())
                .build();
                
### 3.定义 service 接口,使用 ApiRespLiveData 接收结果

    public interface ApiService {
    
        
        @GET("api/detail")
         //使用ResultLiveData<Json实体类>则返回的结果为解析后的Json实体对象
         ApiRespLiveData<GetVideoDetailsResp> getVideoDetails(@Query("id") String id);
     
        @GET("api/detail")
         //使用ResultLiveData<Response<Json实体类>>则返回的结果为Response(retrofit的HTTP response)包含解析后的Json实体对象
        ApiRespLiveData<Response<GetVideoDetailsResp>> getVideoDetailsResponse(@Query("id") String id);
    }
  这里返回使用 ApiRespLiveData,它是LiveData 的子类
  
  GetVideoDetailsResp 为自定义的Json解析结果实体类
  
### 4.调用

    ApiRespLiveData<GetVideoDetailsResp>  resultLiveData=retrofit
                                                            .create(ApiService.class)
                                                            .getVideoDetails(videoId);
 
获取的resultLiveData 对象可在Activity/Fragment 中监听状态及结果
  
    resultLiveData.observe(activity/fragment, new Observer<ApiResponse<GetVideoDetailsResp>>() {
                    @Override
                    public void onChanged(@Nullable ApiResponse<GetVideoDetailsResp> tLiveDataResponse) {
                        if (tLiveDataResponse != null) {
                            ApiResponse.Status status = tLiveDataResponse.getStatus();
                            switch (status) {
                                case ERROR:
                                    //失败
                                    //获取失败信息
                                    Throwable throwable = tLiveDataResponse.getThrowable();
                                     
                                    break;
                         
                                case SUCCESS:
                                    //成功
                                    GetVideoDetailsResp data = tLiveDataResponse.getData();
                                    
                                    break;
                                default:
                            }
                        }
                    }
   }

建议配合ViewModel使用