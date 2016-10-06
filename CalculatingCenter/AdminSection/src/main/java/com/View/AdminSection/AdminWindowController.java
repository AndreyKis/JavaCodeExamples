package com.View.AdminSection;

import com.Model.CurrentProjectData.JavaFXStaticData;
import com.Model.CurrentProjectData.UserData;
import com.Model.DataBase.Entities.UserEntity;
import com.Model.DataBase.Entities.UserLogEntity;
import com.Model.DataBase.EntitiesActions.Implementations.UserActions;
import com.Model.DataBase.EntitiesActions.Implementations.UserLogActions;
import com.View.MainSection.MainPageStarter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Andrey on 16.11.2015.
 */
public class AdminWindowController implements Initializable{
    static public Stage primaryStage;
    @FXML
    private ChoiceBox box;
    @FXML
    private TextField login;
    @FXML
    private TextField pass;
    @FXML
    private Button addUserBtn;
    @FXML
    private Button deleteUserBtn;
    @FXML
    private Button goToMainPage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        box.setItems(
                FXCollections.observableArrayList(
                        "Адміністратор",  "Привілейований користувач", "Користувач","Гостьовий користувач")
        );
        box.setValue("Гостьовий користувач");
        goToMainPage.setOnAction(event1 -> {

            //AuthController.primaryStage.close();
            //GUIForm.primaryStage.close();
            MainPageStarter a = new MainPageStarter();
            try {

                a.start(a.enter);
            }catch(Exception e){}
        });


        addUserBtn.setOnAction(event1 -> {
            addUserToDB();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Вітаю");
            alert.setHeaderText(null);
            //alert.setHeaderText("");
            alert.setContentText("Успішно додано користувача \"" + login.getCharacters() + "\"");

            alert.showAndWait();
        });
        deleteUserBtn.setOnAction(event -> {
            try {
                JavaFXStaticData.NewPAgeStarter("/fxml/DeleteUser.fxml", "Видалення користувача", "com.View.AdminSection.DeleteUserController");
            } catch (IOException e) {
            }
        });
    }

    private void addUserToDB() {
        //ArrayList<JSONUser> newusers= GUIForm.getUsers();
        String userName=login.getCharacters().toString();
        String userPass=pass.getCharacters().toString();
        Integer userLvl=getUserLvl(box.getValue().toString());
        //newusers.add(new JSONUser(userName, userPass, userLvl));

        //New way to add users
        userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());

        UserEntity userToAdd = new UserEntity(userName, userPass, userLvl);
        UserActions.add(userToAdd);

        UserLogEntity userLog = new UserLogEntity(UserData.getUser().getUserId(), "added new user: " + userName, 2, new Date());
        UserLogActions.add(userLog);

        //SaveUsersToFile(newusers);
    }


    public Integer getUserLvl(String value) {
        Integer lvl = 3;
        switch (value) {
            case "Адміністратор":
                lvl = 0;
                break;
            case "Привілейований користувач":
                lvl = 1;
                break;
            case "Користувач":
                lvl = 2;
                break;
            case "Гостьовий користувач":
                lvl = 3;
                break;
        }
        return lvl;
    }

}
