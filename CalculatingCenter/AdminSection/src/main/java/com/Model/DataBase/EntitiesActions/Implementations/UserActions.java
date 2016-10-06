package com.Model.DataBase.EntitiesActions.Implementations;

import com.Model.DataBase.Entities.UserEntity;
import com.Model.DataBase.EntityManagerHelper;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by User on 26.02.2016.
 */
// TODO add interface implementation to all of the entities.
public class UserActions extends EntityActions {
    public static List<UserEntity> list() {
        Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT a FROM UserEntity a", UserEntity.class);
        //CommonDBInfo.entityManager.flush();
        return  (List <UserEntity>) query.getResultList();
    }

    public static long ifUsersExist() {
        Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT COUNT(a.userId) FROM UserEntity a", Long.class);
        //CommonDBInfo.entityManager.flush();
        return  (Long) query.getSingleResult();
    }

    public static UserEntity getUserByLogin(String login) {
        Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT a FROM UserEntity a WHERE a.userLogin " +
                "LIKE :txtField1Pattern", UserEntity.class);
        query.setParameter("txtField1Pattern", "%" + login + "%");
        try {
            return (UserEntity) query.getSingleResult();
        }catch(NoResultException e)
        {
            return null;
        }
    }

    public static void delete(long id) {
        try {
            /*UserEntity user = EntityManagerHelper.getEntityManager().find(UserEntity.class, id);
            EntityManagerHelper.getEntityManager().getTransaction().begin();
            EntityManagerHelper.getEntityManager().remove(user);
            EntityManagerHelper.getEntityManager().getTransaction().commit();*/
            UserEntity user = EntityManagerHelper.find(UserEntity.class, id);
            EntityManagerHelper.begin();
            EntityManagerHelper.remove(user);
            EntityManagerHelper.commit();
        } catch (Exception ex) {
            EntityManagerHelper.rollback();
            ex.printStackTrace();
        }
    }
}
