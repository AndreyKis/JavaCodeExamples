package applicProject;

import javax.persistence.*;

/**
 * Created by Andrey on 10.11.2015.
 */
@Entity
@Table(name="multichoice")
public class MultiChoice {
    @Id
    @GeneratedValue
    private long idMultiChoice;
    @Column(name = "multiChoiceName")
    private String multiChoiceName;

    public MultiChoice() {
    }

    public MultiChoice(String name) {
        this.multiChoiceName = name;
    }

    public long getIdMultiChoice() {
        return idMultiChoice;
    }

    public void setIdMultiChoice(long id) {
        this.idMultiChoice = id;
    }

    public String getName() {
        return multiChoiceName;
    }

    public void setName(String name) {
        this.multiChoiceName = name;
    }
}
