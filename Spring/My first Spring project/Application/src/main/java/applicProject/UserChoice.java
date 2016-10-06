package applicProject;

import javax.persistence.*;

/**
 * Created by Andrey on 10.11.2015.
 */
@Entity
@Table(name = "userchoice")
public class UserChoice {
    @Id
    @GeneratedValue
    private long iduserChoice;

    @Column(name = "txtArea")
    private String txtArea;

    @Column(name = "txtField1")
    private String txtField1;

    @Column(name = "txtField2")
    private String txtField2;

    @Column(name = "radioBtn")
    private String radioBtn;

    @Column(name = "multiSelect")
    public String multiSelect;

    public UserChoice() {
    }

    public UserChoice(String txtArea, String txtField1, String txtField2, String radioBtn, String multiSelect) {
        this.txtArea = txtArea;
        this.txtField1 = txtField1;
        this.txtField2 = txtField2;
        this.radioBtn = radioBtn;
        this.multiSelect = multiSelect;
    }



    public long getIduserChoice() {
        return iduserChoice;
    }

    public void setIduserChoice(long id) {
        this.iduserChoice = id;
    }

    public String getTxtArea() {
        return txtArea;
    }

    public void setTxtArea(String txtArea) {
        this.txtArea = txtArea;
    }

    public String getTxtField1() {
        return txtField1;
    }

    public void setTxtField1(String txtField1) {
        this.txtField1 = txtField1;
    }

    public String getRadioBtn() {
        return radioBtn;
    }

    public void setRadioBtn(String radioBtn) {
        this.radioBtn = radioBtn;
    }

    public String getTxtField2() {
        return txtField2;
    }

    public void setTxtField2(String txtField2) {
        this.txtField2 = txtField2;
    }

    public String getMultiSelect() {return multiSelect;}

    public void setMultiSelect(String multiSelect) { this.multiSelect = multiSelect; }



}

