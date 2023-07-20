package com.test.app.LoadMaps.model;

import com.test.app.LoadMaps.Databases.UserData;

public interface MVPModel {
    Boolean IsAnyAccountActive() throws Exception;
    Boolean FindUserNameExists(String userName) throws Exception;
    Boolean UserRegisteration(UserData registerData) throws Exception;
    Boolean LoginAuthentication(String UserName,String Password) throws Exception;
    void LoggedInUser(String UserName) throws Exception;
    void LogOut() throws Exception;
}
