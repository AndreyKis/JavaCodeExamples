package com.Model.DataBase.EntitiesActions.Implementations;

import com.Model.DataBase.Entities.ParserTypesEntity;
import com.Model.DataBase.EntityManagerHelper;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by User on 17.08.2016.
 */
public class ParserTypesActions {

    public static List<ParserTypesEntity> list() {
        Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT a FROM ParserTypesEntity a", ParserTypesEntity.class);
        return (List<ParserTypesEntity>) query.getResultList();

    }
}
