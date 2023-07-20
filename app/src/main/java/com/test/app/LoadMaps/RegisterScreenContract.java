package com.test.app.LoadMaps;

public interface RegisterScreenContract {
    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void showError(String ErrorMsg);
        void navigateToLoginPage();
    }

    interface Presenter extends BasePresenter{
        void onRegistration(String username,String password,String con_password,String mobilenum,String emailId);
        void redirectLoginPage();
    }
}
