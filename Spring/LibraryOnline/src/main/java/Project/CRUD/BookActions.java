package Project.CRUD;

import Project.Entities.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;

/**
 * Created by User on 16.03.2016.
 */
public class BookActions {

    //@Autowired
    EntityManager entityManager = Persistence.createEntityManagerFactory("ApplicationJPA").createEntityManager();

    public void AddBook(BookEntity book){
        entityManager.getTransaction().begin();
        BookEntity bookFromDB = entityManager.merge(book);
        entityManager.getTransaction().commit();
    }

    public ArrayList<BookEntity> GetListOfBooks()
    {
        Query query = entityManager.createQuery("SELECT a FROM BookEntity a", BookEntity.class);
        return (ArrayList<BookEntity>) query.getResultList();
    }

    public ArrayList<BookEntity> GetListOfBooks(String pattern)
    {
        Query query = entityManager.createQuery("SELECT a FROM BookEntity a WHERE a.bookName LIKE :pattern", BookEntity.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return (ArrayList<BookEntity>) query.getResultList();
    }

    public void DeleteBook(long id)
    {
        try {
            entityManager.getTransaction().begin();
            BookEntity book = entityManager.find(BookEntity.class, id);
            entityManager.remove(book);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    public void UpdateBook(BookEntity book){
        entityManager.getTransaction().begin();
        entityManager.merge(book);
        entityManager.getTransaction().commit();
    }
}
