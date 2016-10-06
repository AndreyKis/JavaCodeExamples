package com.Model.DataBase.EntitiesActions.Implementations;

import com.Model.DataBase.Entities.BaseTextEntity;
import com.Model.DataBase.EntityManagerHelper;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by User on 26.07.2016.
 */
public class BaseTextActions {

    public static List<BaseTextEntity> list() {
        Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT a FROM BaseTextEntity a", BaseTextEntity.class);
        return (List<BaseTextEntity>) query.getResultList();

    }


    public static void add(BaseTextEntity site) {
        try {
            EntityManagerHelper.begin();
            EntityManagerHelper.persist(site);
            EntityManagerHelper.commit();

        } catch (Exception ex) {
            //entityManager.getTransaction().rollback();
            //ex.printStackTrace();
        }
    }
}
