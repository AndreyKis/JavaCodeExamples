package applicProject;

import java.util.List;

/**
 * Created by Andrey on 10.11.2015.
 */
public interface UserChoiceDAO {
    List<UserChoice> list();
    List<UserChoice> list(String pattern);
    void add(UserChoice choice);
    void delete(long id);
    String getMulti(long id);
    long getMultiId(String name);
}
