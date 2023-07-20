package com.test.app.LoadMaps.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.test.app.LoadMaps.AccountScreenContract;
import com.test.app.LoadMaps.presenter.AccountPresenterImpl;
import com.test.app.R;

public class AccountActivity extends AppCompatActivity implements AccountScreenContract.View {

    AccountScreenContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        presenter = new AccountPresenterImpl(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        presenter.redirectLoginPage();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Log.e("Test","onOptionsItemSelected - home Clicked");
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Log.e("Test","onBackPressed - Called()");
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void navigateToLoginPage() {
        getSupportActionBar().setTitle("Account");
        LoginFragment mLoginFragment = new LoginFragment(getApplicationContext());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.layout_container, mLoginFragment, "mLoginFragment").addToBackStack("mLoginFragment");
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void showError(String ErrorMsg) {
        Toast.makeText(getApplicationContext(), ErrorMsg, Toast.LENGTH_SHORT).show();
    }

}
