package com.Model.DataBase.Entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by User on 07.03.2016.
 */
@Entity
@Table(name = "user_log", schema = "newsmonitoringdb")//, catalog = "")
public class UserLogEntity {
    private Long userLogId;
    private Long userId;
    private String logName;
    private int actionType;
    private Date actionTime;

    public UserLogEntity(){}

    public UserLogEntity(long userId, String logName, int actionType, Date actionTime) {
        this.userId = userId;
        this.logName = logName;
        this.actionType = actionType;
        this.actionTime = actionTime;
    }

    @Id
    @Column(name = "userLogID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getUserLogId() {
        return userLogId;
    }

    public void setUserLogId(Long userLogId) {
        this.userLogId = userLogId;
    }

    @Basic
    @Column(name = "userID")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "logName")
    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    @Basic
    @Column(name = "actionType")
    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    @Basic
    @Column(name = "actionTime")
    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLogEntity that = (UserLogEntity) o;

        if (userLogId != that.userLogId) return false;
        if (userId != that.userId) return false;
        if (actionType != that.actionType) return false;
        if (logName != null ? !logName.equals(that.logName) : that.logName != null) return false;
        if (actionTime != null ? !actionTime.equals(that.actionTime) : that.actionTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long result = userLogId;
        result = 31 * result + userId;
        result = 31 * result + (logName != null ? logName.hashCode() : 0);
        result = 31 * result + actionType;
        result = 31 * result + (actionTime != null ? actionTime.hashCode() : 0);
        return (int)result;
    }
}
