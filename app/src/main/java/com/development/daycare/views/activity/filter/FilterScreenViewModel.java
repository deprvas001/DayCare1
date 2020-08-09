package com.development.daycare.views.activity.filter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.daycare.model.dayCareList.DayCareListPost;
import com.development.daycare.model.filter.FilterPostRequest;
import com.development.daycare.model.showCareModel.ShowCareResponse;
import com.development.daycare.networking.RetrofitApiService;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class FilterScreenViewModel extends AndroidViewModel {

    public MutableLiveData<ShowCareResponse> homeResponse = new MutableLiveData<ShowCareResponse>();
    public MutableLiveData<Boolean> error = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private RetrofitApiService retrofitService = new RetrofitApiService();

    public FilterScreenViewModel(@NonNull Application application) {
        super(application);
    }


    public void refresh(Map<String,String> headers, FilterPostRequest post){
        fetchFromRemote(headers, post);
    }

    private void fetchFromRemote(Map<String,String> headers, FilterPostRequest post){
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
