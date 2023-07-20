package com.test.app.LoadMaps.presenter;

import com.test.app.LoadMaps.LoginScreenContract;
import com.test.app.LoadMaps.MyApplication;
import com.test.app.LoadMaps.SplashScreenContract;
import com.test.app.LoadMaps.model.MVPModelImplementor;

public class LoginPresenterImpl implements LoginScreenContract.Presenter{
    private LoginScreenContract.View mLoginView;
    MVPModelImplementor model;

    public LoginPresenterImpl(LoginScreenContract.View view){
        this.mLoginView = view;
        this.model = MyApplication.getModel();
        //this.view.setPresenter(this);
    }

    @Override
    public void onLoggedIn(String username, String password) {
        if(username.length()>0){
            if(password.length()>0){
                mLoginView.showProgress();
                try {
                    if(model.LoginAuthentication(username,password)){
                        mLoginView.hideProgress();
                        model.LoggedInUser(username);
                        mLoginView.navigateHomeActivity();
                    } else {
                        mLoginView.showError("Invalid UserName and Password");
                        mLoginView.hideProgress();
                    }
                } catch (Exception e) {
                    mLoginView.showError(e.getMessage());
                }
            } else {
                mLoginView.showError("Please enter Password");
            }
        } else {
            mLoginView.showError("Please enter eMail Id");
        }
    }

    @Override
    public void redirectRegisterPage() {
        mLoginView.navigateRegisterPage();
    }
}
