package com.test.app.LoadMaps.presenter;

import com.test.app.LoadMaps.AccountScreenContract;
import com.test.app.LoadMaps.MyApplication;
import com.test.app.LoadMaps.SplashScreenContract;
import com.test.app.LoadMaps.model.MVPModelImplementor;

public class AccountPresenterImpl implements AccountScreenContract.Presenter{

    AccountScreenContract.View view;
    MVPModelImplementor model;

    public AccountPresenterImpl(AccountScreenContract.View view){
        this.view = view;
        this.model = MyApplication.getModel();
        //this.view.setPresenter(this);
    }

    @Override
    public void redirectLoginPage() {
        view.navigateToLoginPage();
    }

}
