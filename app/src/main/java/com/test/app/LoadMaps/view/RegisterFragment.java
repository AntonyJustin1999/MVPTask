package com.test.app.LoadMaps.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.test.app.LoadMaps.RegisterScreenContract;
import com.test.app.LoadMaps.presenter.RegisterPresenterImpl;
import com.test.app.R;

public class RegisterFragment extends Fragment implements RegisterScreenContract.View {
    private View mRegisterView;
    private EditText etUserName,etPassword,et_mobile_number,et_con_password,etEmail;
    private Boolean IsPwdShow = false,IsPwdShowCon = false;
    private ImageView iv_pwd_eye,iv_pwd_eye_con;
    private Button btnRegister;
    private TextView tv_login;
    private LottieAnimationView progress_bar;
    private Context context;
    private RegisterPresenterImpl mPresenter;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public RegisterFragment(Context context) {
        this.context = context;
    }

    // Override function when the view is being created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // Inflates the custom fragment layout
        mRegisterView = inflater.inflate(R.layout.fragment_register, container, false);

        Bundle bundle = getArguments();
        String message = bundle.getString("mText");

        mPresenter = new RegisterPresenterImpl(this);

        etUserName = mRegisterView.findViewById(R.id.et_username);
        etEmail = mRegisterView.findViewById(R.id.et_email);
        et_mobile_number = mRegisterView.findViewById(R.id.et_mobile_number);
        etPassword = mRegisterView.findViewById(R.id.et_password);
        et_con_password = mRegisterView.findViewById(R.id.et_con_passwrod);
        tv_login = mRegisterView.findViewById(R.id.tv_login);
        btnRegister = mRegisterView.findViewById(R.id.btn_register);
        progress_bar = mRegisterView.findViewById(R.id.progress_bar);

        iv_pwd_eye = mRegisterView.findViewById(R.id.iv_password_eye);

        iv_pwd_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsPwdShow){
                    hideeye();
                } else {
                    showeye();
                }
            }
        });

        if(IsPwdShow){
            iv_pwd_eye.setImageResource(R.drawable.ic_eye);
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            iv_pwd_eye.setImageResource(R.drawable.ic_eye_off);
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }


        iv_pwd_eye_con = mRegisterView.findViewById(R.id.iv_password_eye1);

        iv_pwd_eye_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsPwdShowCon){
                    hideconeye();
                } else {
                    showconeye();
                }
            }
        });

        if(IsPwdShowCon){
            iv_pwd_eye_con.setImageResource(R.drawable.ic_eye);
            et_con_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            iv_pwd_eye_con.setImageResource(R.drawable.ic_eye_off);
            et_con_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onRegistration(etUserName.getText().toString().trim(),etPassword.getText().toString().trim(),et_con_password.getText().toString().trim(), et_mobile_number.getText().toString().trim(),etEmail.getText().toString().trim());
            }
        });

        tv_login=mRegisterView.findViewById(R.id.tv_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.redirectLoginPage();
            }
        });

        return mRegisterView;
    }

    @Override
    public void showProgress() {
        progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String ErrorMsg) {
        showAlertDialogBox("",ErrorMsg);
    }

    @Override
    public void navigateToLoginPage() {
        FragmentManager mFragmentManager = getParentFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        LoginFragment mFragment = new LoginFragment(getContext());
        Bundle mBundle = new Bundle();
        mBundle.putString("mText", "TestData");
        mFragment.setArguments(mBundle);
        mFragmentTransaction.replace(R.id.layout_container, mFragment).addToBackStack("LoginFragment");
        mFragmentTransaction.commitAllowingStateLoss();
    }

    public void showAlertDialogBox(String title, String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    public void showeye() {
        iv_pwd_eye.setImageResource(R.drawable.ic_eye);
        etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        etPassword.setSelection(etPassword.length());
        IsPwdShow = !IsPwdShow;
    }
    public void hideeye() {
        iv_pwd_eye.setImageResource(R.drawable.ic_eye_off);
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        etPassword.setSelection(etPassword.length());
        IsPwdShow = !IsPwdShow;
    }
    public void showconeye() {
        iv_pwd_eye_con.setImageResource(R.drawable.ic_eye);
        et_con_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        et_con_password.setSelection(et_con_password.length());
        IsPwdShowCon = !IsPwdShowCon;
    }
    public void hideconeye() {
        iv_pwd_eye_con.setImageResource(R.drawable.ic_eye_off);
        et_con_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        et_con_password.setSelection(et_con_password.length());
        IsPwdShowCon = !IsPwdShowCon;
    }

}