package com.development.daycare.views.activity.daycarelist;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.dayCareList.CareListApiResponse;
import com.development.daycare.model.dayCareList.CareListResponse;
import com.development.daycare.model.dayCareList.DayCareListPost;
import com.development.daycare.model.filter.FilterPostRequest;
import com.development.daycare.model.showCareModel.ShowCareResponse;
import com.development.daycare.networking.RetrofitApiService;
import com.development.daycare.networking.RetrofitService;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CareListViewModel extends AndroidViewModel {

    public MutableLiveData<ShowCareResponse> homeResponse = new MutableLiveData<ShowCareResponse>();
    public MutableLiveData<Boolean> error = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private RetrofitApiService retrofitService = new RetrofitApiService();

    public CareListViewModel(@NonNull Application application) {
        super(application);
    }


    public void refresh(Map<String,String> headers, DayCareListPost post){
        fetchFromRemote(headers, post);
    }

    public void getSearch(Map<String,String> headers, DayCareListPost post){
        fetchSearchData(headers, post);
    }

    private void fetchFromRemote(Map<String,String> headers, DayCareListPost post){
        loading.setValue(true);
        disposable.add(
                retrofitService.getDayCareList(headers, post)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ShowCareResponse>() {
                            @Override
                            public void onSuccess(ShowCareResponse response) {

                                homeResponse.setValue(response);
                                error.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                error.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    private void fetchSearchData(Map<String,String> headers, DayCareListPost post){
        loading.setValue(true);
        disposable.add(
                retrofitService.getSearchCare(headers, post)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ShowCareResponse>() {
                            @Override
                            public void onSuccess(ShowCareResponse response) {

                                homeResponse.setValue(response);
                                error.setValue(false);
                                loading.setValue(false);

                            }

                            @Override
                            public void onError(Throwable e) {
                                error.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    public void filterRefresh(Map<String,String> headers, FilterPostRequest post){
        fetchFilterFromRemote(headers, post);
    }

    private void fetchFilterFromRemote(Map<String,String> headers, FilterPostRequest post){
        loading.setValue(true);
        disposable.add(
                retrofitService.getFilterData(headers, post)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ShowCareResponse>() {
                            @Override
                            public void onSuccess(ShowCareResponse response) {

                                homeResponse.setValue(response);
                                error.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                error.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}
