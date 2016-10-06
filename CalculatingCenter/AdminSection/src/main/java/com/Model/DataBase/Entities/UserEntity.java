package com.Model.DataBase.Entities;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;

/**
 * Created by User on 07.03.2016.
 */
@Entity
@Table(name = "user", schema = "newsmonitoringdb")//, catalog = "")
public class UserEntity {
    private Long userId;
    @Column(unique = true, nullable=false)
    private String userLogin;
    @Column(nullable=false)
    private String userPass;
    @Column(nullable=false)
    private int userAccessLvl;

    public UserEntity(){}

    public UserEntity(String login, String pass, int accessLvl)
    {
        this.userLogin = login;
        this.userPass = pass;
        this.userAccessLvl = accessLvl;

    }

    public Boolean loginPasswordEquals(String login, String password)
    {
        Boolean loginCorrect=login.equals(this.userLogin);
        return loginCorrect ? Boolean.valueOf(BCrypt.checkpw(password, getUserPass())) : loginCorrect;
    }


    @Id
    @Column(name = "userID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "userLogin")
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Basic
    @Column(name = "userPass")
    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Basic
    @Column(name = "userAccessLvl")
    public int getUserAccessLvl() {
        return userAccessLvl;
    }

    public void setUserAccessLvl(int userAccessLvl) {
        this.userAccessLvl = userAccessLvl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (userAccessLvl != that.userAccessLvl) return false;
        if (userLogin != null ? !userLogin.equals(that.userLogin) : that.userLogin != null) return false;
        if (userPass != null ? !userPass.equals(that.userPass) : that.userPass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long result = userId;
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (userPass != null ? userPass.hashCode() : 0);
        result = 31 * result + userAccessLvl;
        return (int)result;
    }
}
