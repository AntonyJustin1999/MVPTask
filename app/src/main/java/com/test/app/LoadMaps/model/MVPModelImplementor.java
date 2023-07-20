package com.test.app.LoadMaps.model;


import com.test.app.LoadMaps.API.ApiCall;
import com.test.app.LoadMaps.DataSets.CountriesApi;
import com.test.app.LoadMaps.Databases.UserData;
import com.test.app.LoadMaps.model.db.RealmDBAdapter;

public class MVPModelImplementor implements MVPModel {

    RealmDBAdapter realmDBAdapter;
    public MVPModelImplementor(RealmDBAdapter realmDBAdapter){
        this.realmDBAdapter = realmDBAdapter;
    }

    @Override
    public Boolean IsAnyAccountActive() throws Exception {

        try{
            boolean result = realmDBAdapter.IsloginAccountExists();
            if (result){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new Exception("Some thing went wrong!!!");
        }

    }

    @Override
    public Boolean FindUserNameExists(String userName) throws Exception {
        return realmDBAdapter.findUserNameExists(userName);
    }

    @Override
    public Boolean UserRegisteration(UserData registerData) throws Exception {
        try{
            return realmDBAdapter.RegisterData(registerData);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean LoginAuthentication(String UserName, String Password) throws Exception {
        try{
            boolean result = realmDBAdapter.IsloginCredentialExists(UserName,Password);
            if (result){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new Exception("Some thing went wrong!!!");
        }
    }

    @Override
    public void LoggedInUser(String UserName) throws Exception {
        try{
            realmDBAdapter.loginUser(UserName);
        } catch (Exception e){
            throw new Exception("Some thing went wrong!!!");
        }
    }

    @Override
    public void LogOut() throws Exception {
        try{
            realmDBAdapter.LogOut();
        } catch (Exception e){
            throw new Exception("Some thing went wrong!!!");
        }
    }
}
