package com.Model.DataBase.Entities;

import javax.persistence.*;

/**
 * Created by User on 07.03.2016.
 */
@Entity
@Table(name = "learning_selection", schema = "newsmonitoringdb")//, catalog = "")
public class LearningSelectionEntity {
    private int selectionId;
    private String selectionName;
    private String selectionUrl;

    @Id
    @Column(name = "selectionID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(int selectionId) {
        this.selectionId = selectionId;
    }

    @Basic
    @Column(name = "selectionName")
    public String getSelectionName() {
        return selectionName;
    }

    public void setSelectionName(String selectionName) {
        this.selectionName = selectionName;
    }

    @Basic
    @Column(name = "selectionURL")
    public String getSelectionUrl() {
        return selectionUrl;
    }

    public void setSelectionUrl(String selectionUrl) {
        this.selectionUrl = selectionUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LearningSelectionEntity that = (LearningSelectionEntity) o;

        if (selectionId != that.selectionId) return false;
        if (selectionName != null ? !selectionName.equals(that.selectionName) : that.selectionName != null)
            return false;
        if (selectionUrl != null ? !selectionUrl.equals(that.selectionUrl) : that.selectionUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = selectionId;
        result = 31 * result + (selectionName != null ? selectionName.hashCode() : 0);
        result = 31 * result + (selectionUrl != null ? selectionUrl.hashCode() : 0);
        return result;
    }
}
