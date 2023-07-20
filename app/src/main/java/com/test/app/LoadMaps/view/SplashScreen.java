package com.test.app.LoadMaps.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.test.app.LoadMaps.SplashScreenContract;
import com.test.app.LoadMaps.presenter.SplashScreenPresenterImpl;
import com.test.app.R;

public class SplashScreen extends AppCompatActivity implements SplashScreenContract.View {

    private Context context;
    SplashScreenContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        presenter = new SplashScreenPresenterImpl(this);
        context = this;

        // This method is used so that your splash activity can cover the entire screen.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onCheckAlreadyLogin();
    }

    @Override
    public void redirectToAccount() {
        Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void redirectToHome() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(String ErrorMsg) {
        Toast.makeText(context, ErrorMsg, Toast.LENGTH_SHORT).show();
    }

}
