package com.Model.CurrentProjectData;

import com.Controller.JSONActionsController.ForJSON;
import com.Controller.UsersController.JSONUser;
import com.Model.DataBase.Entities.UserEntity;

import java.util.ArrayList;

/**
 * Created by Peter on 28-Mar-16.
 */
public class UserData {
    private static UserEntity user;
    private static ArrayList<JSONUser> users;

    public static UserEntity getUser() {
        return user;
    }

    public static void setUser(UserEntity user) {
        UserData.user = user;
    }

    public static void setUsers(ArrayList<JSONUser> users) {
        UserData.users = users;
    }

    public static ArrayList<JSONUser> getUsers() {
        if (UserData.users == null) {
            return ForJSON.CorrectReading();
        }
        return UserData.users;
    }

}
