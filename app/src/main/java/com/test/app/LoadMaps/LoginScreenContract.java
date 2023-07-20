package com.test.app.LoadMaps;

public interface LoginScreenContract {
    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void showError(String ErrorMsg);
        void navigateHomeActivity();
        void navigateRegisterPage();
    }

    interface Presenter extends BasePresenter{
        void onLoggedIn(String username,String password);
        void redirectRegisterPage();
    }
}
