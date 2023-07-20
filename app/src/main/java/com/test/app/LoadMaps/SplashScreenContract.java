package com.test.app.LoadMaps;

import java.util.List;

public interface SplashScreenContract {
    interface View extends BaseView<Presenter> {
        void redirectToAccount();
        void redirectToHome();
        void showError(String ErrorMsg);
    }

    interface Presenter extends BasePresenter{
        void onCheckAlreadyLogin();
    }
}
