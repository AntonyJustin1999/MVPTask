package com.test.app.LoadMaps.presenter;

import com.test.app.LoadMaps.Databases.UserData;
import com.test.app.LoadMaps.MyApplication;
import com.test.app.LoadMaps.RegisterScreenContract;
import com.test.app.LoadMaps.Utils.Utils;
import com.test.app.LoadMaps.model.MVPModelImplementor;

public class RegisterPresenterImpl implements RegisterScreenContract.Presenter{

    private RegisterScreenContract.View mRegisterView;
    private MVPModelImplementor model;

    public RegisterPresenterImpl(RegisterScreenContract.View mRegisterView){
        this.mRegisterView = mRegisterView;
        this.model = MyApplication.getModel();
    }
    @Override
    public void onRegistration(String username, String password, String con_password , String mobilenum, String emailId) {
        if(username.length()>0){
            if(mobilenum.length()>0){
                if(emailId.length()>0){
                    if(Utils.isValidEmail(emailId)){
                        if(password.length()>0){
                            if(con_password.length()>0){
                                if(password.equals(con_password)){
                                    mRegisterView.showProgress();

                                    UserData registerData = new UserData();
                                    registerData.setUserName(username);
                                    registerData.setMobileNumber(mobilenum);
                                    registerData.setEmailId(emailId);
                                    registerData.setPassword(password);
                                    registerData.setCurrentUser(false);
                                    try {
                                        if(!model.FindUserNameExists(registerData.userName)){
                                            mRegisterView.hideProgress();
                                            model.UserRegisteration(registerData);
                                            mRegisterView.navigateToLoginPage();
                                        } else {
                                            mRegisterView.hideProgress();
                                            mRegisterView.showError("UserName Already Exists");
                                        }
                                    } catch (Exception e) {
                                        mRegisterView.showError(e.getMessage());
                                    }
                                } else {
                                    mRegisterView.showError("Password and Confirm Password Must be Same");
                                }
                            } else {
                                mRegisterView.showError("Please enter Confirm Password");
                            }
                        } else {
                            mRegisterView.showError("Please enter Password");
                        }
                    } else {
                        mRegisterView.showError("Please enter valid eMail Id");
                    }
                } else {
                    mRegisterView.showError("Please enter eMail Id");
                }
            } else {
                mRegisterView.showError("Please enter MobileNumber");
            }
        } else {
            mRegisterView.showError("Please enter user name");
        }
    }

    @Override
    public void redirectLoginPage() {
        mRegisterView.navigateToLoginPage();
    }
}
