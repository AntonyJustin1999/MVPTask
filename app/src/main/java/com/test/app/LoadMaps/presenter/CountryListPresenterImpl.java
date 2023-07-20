package com.test.app.LoadMaps.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.NonNull;

import com.test.app.LoadMaps.API.ApiCall;
import com.test.app.LoadMaps.CountryListScreenContract;
import com.test.app.LoadMaps.DataSets.CountriesApi;
import com.test.app.LoadMaps.DataSets.NetworkResponseData;
import com.test.app.LoadMaps.MyApplication;
import com.test.app.LoadMaps.model.MVPModelImplementor;
import com.test.app.LoadMaps.model.api.RestManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListPresenterImpl implements CountryListScreenContract.Presenter {
    private CountryListScreenContract.View mCountryListView;
    RestManager restManager;
    private MVPModelImplementor model;
    private Context context;

    public CountryListPresenterImpl(CountryListScreenContract.View mCountryListView,Context context){
        this.mCountryListView = mCountryListView;
        this.context = context;
        this.model = MyApplication.getModel();
        this.restManager = new RestManager();
    }

    @Override
    public void loadCountryList() {
        if(isNetworkAvailable()){
            Log.e("Test","Network Available");
            LoadCountrylist();
        } else{
            Log.e("Test","No Network Found!!");
            mCountryListView.onFailureCountryList("No network Found!!");
            mCountryListView.showError("No network Found!!");
        }
    }

    @Override
    public void redirectCountryDetails(String commonName) {
        mCountryListView.showCountryDetailsPage(commonName);
    }

    public void LoadCountrylist() {

        Call<ArrayList<CountriesApi>> Call = null;
        try {
            Call = restManager.getAPI().getAllCountrieslist(true,"" + "name,flags");
            if(Call!=null){
                mCountryListView.showProgress();
                Call.enqueue(new Callback<ArrayList<CountriesApi>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<CountriesApi>> call, @NonNull Response<ArrayList<CountriesApi>> response) {
                        try {
                            mCountryListView.hideProgress();
                            ArrayList<CountriesApi> data = response.body();
                            if (data != null) {
                                mCountryListView.onSuccessCountrylistLoaded(response.body());
                            } else{
                                mCountryListView.onFailureCountryList("Something Wrong Try Again!!");
                            }
                        } catch (Exception e) {
                            mCountryListView.hideProgress();
                            mCountryListView.onFailureCountryList("Something Wrong Try Again!!");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<CountriesApi>> call, @NonNull Throwable t) {
                        mCountryListView.hideProgress();
                        mCountryListView.onFailureCountryList("Something Wrong Try Again!!");
                    }
                });
            }
        } catch (Throwable e) {
            mCountryListView.showError(e.getMessage());
        }

    }

    private boolean isNetworkAvailable(){
        try {
            if(context!=null){
                ConnectivityManager connectivity = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivity != null) {
                    NetworkInfo info = connectivity.getActiveNetworkInfo();
                    if (info != null && info.isConnected()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
