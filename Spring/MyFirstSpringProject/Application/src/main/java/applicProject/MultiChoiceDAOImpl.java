package applicProject;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Andrey on 11.11.2015.
 */
public class MultiChoiceDAOImpl implements MultiChoiceDAO {
    @Autowired
    private EntityManager entityManager;

    public List<MultiChoice> list(){
        Query query = entityManager.createQuery("SELECT a FROM MultiChoice a", MultiChoice.class);
        return (List<MultiChoice>) query.getResultList();
    }
}
