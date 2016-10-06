package com.Model.DataBase.EntitiesActions.Implementations;

import com.Model.DataBase.Entities.ParserEntity;
import com.Model.DataBase.EntityManagerHelper;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 20.08.2016.
 */
public class ParserActions extends EntityActions {
    public static List<ParserEntity> list() {
        Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT a FROM ParserEntity a", ParserEntity.class);
        return  (List <ParserEntity>) query.getResultList();
    }

    public static List<String> getParserNamesByType(Long sourceTypePattern) {
        Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT a.name FROM ParserEntity a " + "WHERE a.type.id = :sourceTypePattern", String.class);
        query.setParameter("sourceTypePattern", sourceTypePattern);
        return (List<String>)query.getResultList();
    }

    public static Map<Long, String> getParserNamesAndIdsByType(Long sourceTypePattern) {
        Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT a.id, a.name FROM ParserEntity a " + "WHERE a.type.id = :sourceTypePattern");
        query.setParameter("sourceTypePattern", sourceTypePattern);
        Map<Long, String> result = new HashMap<>();
        //List<Object[]> list = query.getResultList();
        for(Object[] obj : (List<Object[]>)query.getResultList()){
            result.put((Long)obj[0], (String)obj[1]);
        }
        return result;
    }

    public static List<ParserEntity> getParsersByNames(List<String> listOfNames){
        Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT a FROM ParserEntity a " +
                "WHERE a.name IN (:listOfNames)", ParserEntity.class);
        query.setParameter("listOfNames", listOfNames);
        return (List<ParserEntity>)query.getResultList();
    }

    public static List<ParserEntity> getParsersByIds(List<Long> listOfIds){
        Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT a FROM ParserEntity a " +
                "WHERE a.id IN (:listOfIds)", ParserEntity.class);
        query.setParameter("listOfIds", listOfIds);
        return (List<ParserEntity>)query.getResultList();
    }

    public static List<ParserEntity> list(String parserNamePattern) {
        Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT a FROM ParserEntity a WHERE a.name " +
                "LIKE :parserNamePattern", ParserEntity.class);
        query.setParameter("parserNamePattern", "%" + parserNamePattern + "%");
        return (List<ParserEntity>) query.getResultList();
    }
}
