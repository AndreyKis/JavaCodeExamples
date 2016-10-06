package com.Model.DataBase.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by User on 17.08.2016.
 */
@Entity
@Table(name = "source_types", schema = "newsmonitoringdb")
public class ParserTypesEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Basic
    @Column(name = "name", unique=true)
    private String name;

    /*Need to fix this connection
    * try @ElementCollection*/
    @OneToMany(mappedBy="type", fetch=FetchType.EAGER, targetEntity = ParserEntity.class)
    private Collection<ParserEntity> siteParser;

    public Collection<ParserEntity> getParser() {return siteParser;}

    public void setSiteParser(Collection<ParserEntity> siteParser) {
        this.siteParser = siteParser;
    }
    public Collection<ParserEntity> getSiteParser() {
        return siteParser;
    }

    public void setSiteParser(ArrayList<ParserEntity> siteParser) {
        this.siteParser = siteParser;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParserTypesEntity that = (ParserTypesEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return (int)result;
    }
}
