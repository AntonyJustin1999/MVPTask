package com.test.app.LoadMaps.presenter;

import com.test.app.LoadMaps.HomeScreenContract;
import com.test.app.LoadMaps.MyApplication;
import com.test.app.LoadMaps.model.MVPModelImplementor;

public class HomePresenterImpl implements HomeScreenContract.Presenter{
    private HomeScreenContract.View mHomeView;
    private MVPModelImplementor model;

    public HomePresenterImpl(HomeScreenContract.View mHomeView){
        this.mHomeView = mHomeView;
        this.model = MyApplication.getModel();
    }

    @Override
    public void redirectCountryListPage() {
        mHomeView.navigateToCountryListPage();
    }

    @Override
    public void reloadHomePage() {
        mHomeView.navigateToReloadPage();
    }

    @Override
    public void logOut() {
        try {
            model.LogOut();
            mHomeView.navigateToLoginPage();
        } catch (Exception e) {
            mHomeView.showError(e.getMessage());
        }
    }
}
