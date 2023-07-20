package com.test.app.LoadMaps.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.test.app.LoadMaps.CountryDetailsPage.CountryDetailsActivity;
import com.test.app.LoadMaps.CountryListScreenContract;
import com.test.app.LoadMaps.presenter.CountryListPresenterImpl;
import com.test.app.LoadMaps.DataSets.CountriesApi;
import com.test.app.LoadMaps.view.Adapter.CountiresListAdapter;
import com.test.app.R;

import java.util.ArrayList;

public class CountryListFragment extends Fragment implements CountryListScreenContract.View {
    private View mCountryListView;
    public Context context;
    public Activity activity;
    private LottieAnimationView animationLoader;
    private RecyclerView rv_country_list;
    private CountiresListAdapter mRestaurantListAdapter;
    private CountryListScreenContract.Presenter mPresenter;

    public CountryListFragment() {
        // Required empty public constructor
    }

    public CountryListFragment(Context context) {
        this.context = context;
    }

    // Override function when the view is being created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // Inflates the custom fragment layout
        mCountryListView = inflater.inflate(R.layout.fragment_country_list, container, false);

        Bundle bundle = getArguments();
        String message = bundle.getString("mText");

        mPresenter = new CountryListPresenterImpl(this,getContext());

        animationLoader = mCountryListView.findViewById(R.id.progress_bar);
        rv_country_list = mCountryListView.findViewById(R.id.rv_restaurant_list);

        mPresenter.loadCountryList();

        return mCountryListView;
    }

    @Override
    public void showProgress() {
        animationLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        animationLoader.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessCountrylistLoaded(ArrayList<CountriesApi> countryList) {
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        rv_country_list.setLayoutManager(linearLayoutManager);
        mRestaurantListAdapter = new CountiresListAdapter(countryList,getContext());
        rv_country_list.setAdapter(mRestaurantListAdapter);
    }

    @Override
    public void onFailureCountryList(String message) {
        ArrayList<CountriesApi> countrieslist = new ArrayList<>();
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        rv_country_list.setLayoutManager(linearLayoutManager);
        mRestaurantListAdapter = new CountiresListAdapter(countrieslist,getContext());
        rv_country_list.setAdapter(mRestaurantListAdapter);
    }

    @Override
    public void showError(String ErrorMsg) {
        showAlertDialogBox("",ErrorMsg);
    }

    @Override
    public void showCountryDetailsPage(String commonName) {
        Intent intent = new Intent(context, CountryDetailsActivity.class);
        intent.putExtra("CountryName", commonName);
        context.startActivity(intent);
    }

    private void showAlertDialogBox(String title,String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}