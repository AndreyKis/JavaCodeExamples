package com.Model.DataBase.EntitiesActions.Implementations;

import com.Model.DataBase.EntityManagerHelper;

/**
 * Created by User on 20.08.2016.
 */
//Tried another way to describe Entity Actions, need to change back, because it is incorrect
public abstract class EntityActions {
    public static void add(Object objectToAdd) {
        try {
            EntityManagerHelper.begin();
            EntityManagerHelper.persist(objectToAdd);
            EntityManagerHelper.commit();
            /*EntityManagerHelper.getEntityManager().getTransaction().begin();
            EntityManagerHelper.getEntityManager().persist(user);
            EntityManagerHelper.getEntityManager().getTransaction().commit();*/

        } catch (Exception ex) {
            EntityManagerHelper.rollback();
            //EntityManagerHelper.getEntityManager().getTransaction().rollback();
            ex.printStackTrace();
        }
    }
}
