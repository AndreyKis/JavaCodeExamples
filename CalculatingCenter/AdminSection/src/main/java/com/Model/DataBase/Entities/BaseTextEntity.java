package com.Model.DataBase.Entities;

import javax.persistence.*;

@Entity
@Table(name = "baseText", schema = "newsmonitoringdb")
public class BaseTextEntity {
    private int btId;
    private String btText;
    private String btTitle;
    private String btUrl;
    private String btSource;
    private Integer btTonality;

    public BaseTextEntity(String btText, String btTitle, String btUrl, String btSource) {
        this.btText = btText;
        this.btTitle = btTitle;
        this.btUrl = btUrl;
        this.btSource = btSource;
    }

    public BaseTextEntity() {
    }

    @Id
    @Column(name = "bt_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getBtId() {
        return btId;
    }

    public void setBtId(int btId) {
        this.btId = btId;
    }

    @Basic
    @Column(name = "bt_text", nullable = false, length = -1)
    public String getBtText() {
        return btText;
    }

    public void setBtText(String btText) {
        this.btText = btText;
    }

    @Basic
    @Column(name = "bt_title", nullable = true, length = 200)
    public String getBtTitle() {
        return btTitle;
    }

    public void setBtTitle(String btTitle) {
        this.btTitle = btTitle;
    }

    @Basic
    @Column(name = "bt_url", nullable = true, length = 300)
    public String getBtUrl() {
        return btUrl;
    }

    public void setBtUrl(String btUrl) {
        this.btUrl = btUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseTextEntity that = (BaseTextEntity) o;

        if (btId != that.btId) return false;
        if (btText != null ? !btText.equals(that.btText) : that.btText != null) return false;
        if (btTitle != null ? !btTitle.equals(that.btTitle) : that.btTitle != null) return false;
        if (btUrl != null ? !btUrl.equals(that.btUrl) : that.btUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = btId;
        result = 31 * result + (btText != null ? btText.hashCode() : 0);
        result = 31 * result + (btTitle != null ? btTitle.hashCode() : 0);
        result = 31 * result + (btUrl != null ? btUrl.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "bt_source", nullable = true, length = 45)
    public String getBtSource() {
        return btSource;
    }

    public void setBtSource(String btSource) {
        this.btSource = btSource;
    }

    @Basic
    @Column(name = "bt_tonality", nullable = true)
    public Integer getBtTonality() {
        return btTonality;
    }

    public void setBtTonality(Integer btTonality) {
        this.btTonality = btTonality;
    }
}
