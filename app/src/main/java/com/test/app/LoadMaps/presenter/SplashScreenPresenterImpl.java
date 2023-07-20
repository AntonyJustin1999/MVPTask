package com.test.app.LoadMaps.presenter;

import com.test.app.LoadMaps.MyApplication;
import com.test.app.LoadMaps.SplashScreenContract;
import com.test.app.LoadMaps.model.MVPModelImplementor;

public class SplashScreenPresenterImpl implements SplashScreenContract.Presenter {

    SplashScreenContract.View view;
    MVPModelImplementor model;

    public SplashScreenPresenterImpl(SplashScreenContract.View view){
        this.view = view;
        this.model = MyApplication.getModel();
        //this.view.setPresenter(this);
    }

    @Override
    public void onCheckAlreadyLogin() {
        try {
            boolean isActive = model.IsAnyAccountActive();
            if(isActive){
                view.redirectToHome();
            } else {
                view.redirectToAccount();
            }
        } catch (Exception e) {
            view.showError(e.getMessage());
        }
    }
}
