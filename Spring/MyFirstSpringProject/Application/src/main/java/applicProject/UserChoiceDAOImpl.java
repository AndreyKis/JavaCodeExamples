package applicProject;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Andrey on 10.11.2015.
 */
public class UserChoiceDAOImpl implements UserChoiceDAO{
    @Autowired
    private EntityManager entityManager;


    public List<UserChoice> list() {
        Query query = entityManager.createQuery("SELECT a FROM UserChoice a", UserChoice.class);
        return  (List <UserChoice>) query.getResultList();

    }


    public List<UserChoice> list(String txtField1Pattern) {
        Query query = entityManager.createQuery("SELECT a FROM UserChoice a WHERE a.txtField1 LIKE :txtField1Pattern", UserChoice.class);
        query.setParameter("txtField1Pattern", "%" + txtField1Pattern + "%");
        return (List<UserChoice>) query.getResultList();
    }


    public void add(UserChoice choice) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(choice);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }


    public void delete(long id) {
        try {
            entityManager.getTransaction().begin();
            UserChoice choice = entityManager.find(UserChoice.class, id);
            entityManager.remove(choice);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    public String getMulti(long id) {
        try {
            MultiChoice multi = entityManager.find(MultiChoice.class, id);
            return multi.getName();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public long getMultiId(String name) {
        try {
            MultiChoice multi = entityManager.find(MultiChoice.class, name);
            return multi.getIdMultiChoice();
        } catch(Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    //That was an idea. The description of it is in the MainController comment.
    public String transformMultiToString(long num) {
        //long num = this.multiSelId;
        String res = null;
        for (int i = 1; num != 0; i++) {
            boolean b = ((num & 1) != 0);
            if (b)
                res += getMulti(i);
            num /= 2;
        }
        return res;
    }
}
