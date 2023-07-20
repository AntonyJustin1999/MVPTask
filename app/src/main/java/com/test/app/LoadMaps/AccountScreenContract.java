package com.test.app.LoadMaps;

public interface AccountScreenContract {
    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void navigateToLoginPage();
        void showError(String ErrorMsg);
    }

    interface Presenter extends BasePresenter{
        void redirectLoginPage();
    }
}
