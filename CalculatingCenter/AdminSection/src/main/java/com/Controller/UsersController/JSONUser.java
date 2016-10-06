package com.Controller.UsersController;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by Andrey on 12.12.2015.
 */
//Deprecated file, added to show the usages of JSON
public class JSONUser {
    public static long userCount = 0;
    public long id;
    public String login;

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public int getPrivacyLvl() {
        return privacyLvl;
    }

    public void setPassword(String password) {
        this.password = getHashPass (password);
    }

    private String password;
    public int privacyLvl;

    public JSONUser() {
        this.login = "user";
        this.password = "qwerty";
    }

    public JSONUser(String login, String password, int privacyLvl) {
        userCount++;
        this.id = userCount;
        this.login = login;
        setPassword(password);
        this.privacyLvl = privacyLvl;
    }
    private String getHashPass (String pass)
    {
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }
public Boolean loginPasswordEquals(String login, String password)
{
    Boolean loginCorrect=login.equals(this.login);
    return loginCorrect ? Boolean.valueOf(BCrypt.checkpw(password, getPassword())) : loginCorrect;
}
    public JSONObject getJSONObj(){
        JSONObject objUser = new JSONObject();
        objUser.put("userID", this.id);
        objUser.put("userLogin", this.login);
        objUser.put("userPass", this.password);
        objUser.put("userPrivacy", this.privacyLvl);
        return objUser;
    }

    public JSONUser(org.json.simple.JSONObject user){
        userCount++;
        this.id = Integer.parseInt(user.get("userID").toString());
        this.login = user.get("userLogin").toString();
        this.password = user.get("userPass").toString();
        this.privacyLvl = Integer.parseInt(user.get("userPrivacy").toString());
    }

    @Override
    public String toString() {
        return "JSONUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", privacyLvl=" + privacyLvl +
                '}';
    }
}
