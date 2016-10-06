package com.Model.DataBase.Entities;

import javax.persistence.*;

/**
 * Created by User on 20.08.2016.
 */
@Entity
@Table(name = "parser", schema = "newsmonitoringdb")
public class ParserEntity {
    public ParserEntity() {
    }

    public ParserEntity(String name, ParserTypesEntity type) {
        this.name = name;
        this.type = type;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name="type",referencedColumnName="id")
    private ParserTypesEntity type;

    @Basic
    @Column(name = "url")
    private String url;
    @Basic
    @Column(name = "prefixUrl")
    private String prefixUrl;
    @Basic
    @Column(name = "pageUrlFormat")
    private String pageUrlFormat;
    @Basic
    @Column(name = "cssTitle")
    private String cssTitle;
    @Basic
    @Column(name = "cssText")
    private String cssText;
    @Basic
    @Column(name = "cssNextButton")
    private String cssNextButton;
    @Basic
    @Column(name = "cssDate")
    private String cssDate;
    @Basic
    @Column(name = "dateFormat")
    private String dateFormat;
    @Basic
    @Column(name = "nextDateFormat")
    private String nextDateFormat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParserTypesEntity getType() {
        return type;
    }

    public void setType(ParserTypesEntity type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrefixUrl() {
        return prefixUrl;
    }

    public void setPrefixUrl(String prefixUrl) {
        this.prefixUrl = prefixUrl;
    }

    public String getPageUrlFormat() {
        return pageUrlFormat;
    }

    public void setPageUrlFormat(String pageUrlFormat) {
        this.pageUrlFormat = pageUrlFormat;
    }

    public String getCssTitle() {
        return cssTitle;
    }

    public void setCssTitle(String cssTitle) {
        this.cssTitle = cssTitle;
    }

    public String getCssText() {
        return cssText;
    }

    public void setCssText(String cssText) {
        this.cssText = cssText;
    }

    public String getCssNextButton() {
        return cssNextButton;
    }

    public void setCssNextButton(String cssNextButton) {
        this.cssNextButton = cssNextButton;
    }

    public String getCssDate() {
        return cssDate;
    }

    public void setCssDate(String cssDate) {
        this.cssDate = cssDate;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getNextDateFormat() {
        return nextDateFormat;
    }

    public void setNextDateFormat(String nextDateFormat) {
        this.nextDateFormat = nextDateFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParserEntity that = (ParserEntity) o;

        if (id != that.id) return false;
        if (type.getId() != that.type.getId()) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (prefixUrl != null ? !prefixUrl.equals(that.prefixUrl) : that.prefixUrl != null) return false;
        if (pageUrlFormat != null ? !pageUrlFormat.equals(that.pageUrlFormat) : that.pageUrlFormat != null)
            return false;
        if (cssTitle != null ? !cssTitle.equals(that.cssTitle) : that.cssTitle != null) return false;
        if (cssText != null ? !cssText.equals(that.cssText) : that.cssText != null) return false;
        if (cssNextButton != null ? !cssNextButton.equals(that.cssNextButton) : that.cssNextButton != null)
            return false;
        if (cssDate != null ? !cssDate.equals(that.cssDate) : that.cssDate != null) return false;
        if (dateFormat != null ? !dateFormat.equals(that.dateFormat) : that.dateFormat != null) return false;
        if (nextDateFormat != null ? !nextDateFormat.equals(that.nextDateFormat) : that.nextDateFormat != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        long result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + type.getId();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (prefixUrl != null ? prefixUrl.hashCode() : 0);
        result = 31 * result + (pageUrlFormat != null ? pageUrlFormat.hashCode() : 0);
        result = 31 * result + (cssTitle != null ? cssTitle.hashCode() : 0);
        result = 31 * result + (cssText != null ? cssText.hashCode() : 0);
        result = 31 * result + (cssNextButton != null ? cssNextButton.hashCode() : 0);
        result = 31 * result + (cssDate != null ? cssDate.hashCode() : 0);
        result = 31 * result + (dateFormat != null ? dateFormat.hashCode() : 0);
        result = 31 * result + (nextDateFormat != null ? nextDateFormat.hashCode() : 0);
        return (int)result;
    }
}
