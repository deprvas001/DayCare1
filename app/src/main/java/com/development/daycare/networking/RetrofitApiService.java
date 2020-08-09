package com.development.daycare.networking;

import com.development.daycare.constant.ApiConstant;
import com.development.daycare.model.dayCareList.CareListResponse;
import com.development.daycare.model.dayCareList.DayCareListPost;
import com.development.daycare.model.filter.FilterPostRequest;
import com.development.daycare.model.showCareModel.ShowCareResponse;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiService {

    private static Retrofit retrofit =null;
    private static final String URL = ApiConstant.BASE_URL;
    private ShipmentApi shipmentApi;

   /* public  static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return  retrofit;

    }*/

    public RetrofitApiService(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        shipmentApi = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build()
                .create(ShipmentApi.class);
    }

    public Single<ShowCareResponse> getDayCareList(Map<String,String> headers, DayCareListPost post){
        return shipmentApi.getDayCareList(headers,post);
    }

    public Single<ShowCareResponse> getSearchCare(Map<String,String> headers, DayCareListPost post){
        return shipmentApi.getSearchCare(headers,post);
    }


    public Single<ShowCareResponse> getFilterData(Map<String,String> headers, FilterPostRequest post){
        return shipmentApi.getFilterList(headers,post);
    }

}
