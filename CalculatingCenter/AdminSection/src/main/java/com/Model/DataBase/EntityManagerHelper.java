package com.Model.DataBase;

/**
 * Created by User on 18.08.2016.
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {

    private static final EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal;

    static {
        //required for the server change process
        if (InitPersistence.persistenceMap != null && InitPersistence.getNewIP() != null)
            emf = Persistence.createEntityManagerFactory("TonalityJPA",
                    InitPersistence.persistenceMap);
        else
            emf = Persistence.createEntityManagerFactory("TonalityJPA");
        threadLocal = new ThreadLocal<EntityManager>();
    }

    public static EntityManager getEntityManager() {
        EntityManager em = threadLocal.get();

        if (em == null) {
            em = emf.createEntityManager();
            // set your flush mode here
            threadLocal.set(em);
        }
        return em;
    }

    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        if (em != null) {
            em.close();
            threadLocal.set(null);
        }
    }

    public static void closeEntityManagerFactory() {
        emf.close();
    }


    public static void begin() {
        getEntityManager().getTransaction().begin();
    }

    public static <T> void remove(T thingToRemove) {
        getEntityManager().remove(thingToRemove);
    }

    public static <T> void persist(T thingToPersist) {
        getEntityManager().persist(thingToPersist);
    }

    public static void rollback() {
        getEntityManager().getTransaction().rollback();
    }

    public static void commit() {
        getEntityManager().getTransaction().commit();

    }

    public static <T> T find(Class<T> a, long id) {
        return getEntityManager().find(a, id);
    }
}
