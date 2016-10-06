package com.View.AuthSection;

import com.Controller.ForXML;
import com.Controller.UsersController.JSONUser;
import com.Model.CurrentProjectData.JavaFXStaticData;
import com.Model.CurrentProjectData.UserData;
import com.Model.DataBase.Entities.UserEntity;
import com.Model.DataBase.Entities.UserLogEntity;
import com.Model.DataBase.EntitiesActions.Implementations.UserActions;
import com.Model.DataBase.EntitiesActions.Implementations.UserLogActions;
import com.Model.DataBase.InitPersistence;
import com.View.MainSection.MainPageStarter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @web http://zoranpavlovic.blogspot.com/
 */
public class AuthController extends Application {
    public static Stage primaryStage;
    String login, pasw;
    static String[] args2;
    public ArrayList<JSONUser> users;

    public static void main(String[] args) {
        args2 = args;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage1) {
        //users = ForJSON.CorrectReading();
        String[] xml = ForXML.readXML();

        //required for the server change process. Removed, because it is still in work
        //InitPersistence pers = new InitPersistence(xml[0], xml[1], xml[2]);


        addDefaultUsersIfNeeded();

        primaryStage1 = new Stage();
        ImageView image = new ImageView();
        image.setImage(new Image("/Pictures/sign.png"));
        primaryStage = primaryStage1;
        primaryStage1.setTitle("Система авторизації");

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(20, 20, 20, 30));

        //Adding GridPane
        GridPane gridPane = InitGridPane();

        //Implementing Nodes for GridPane
        Label lblUserName = new Label("Користувач");
        final TextField txtUserName = new TextField();
        Label lblPassword = new Label("Пароль");
        final PasswordField pf = new PasswordField();
        Button btnLogin = new Button("Увійти");
        final Label lblMessage = new Label();
        Button chngServerBtn = new Button("Змінити сервер");

        //Adding Nodes to GridPane layout
        AddingNodes(gridPane, lblUserName, txtUserName, lblPassword, pf, btnLogin, lblMessage, chngServerBtn);


        //Reflection for gridPane
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        gridPane.setEffect(r);

        //DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        //Adding text and DropShadow effect to it
        Text text = new Text("Авторизація");
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        text.setEffect(dropShadow);

        //Adding text to HBox
        hb.getChildren().add(text);

        //Add ID's to Nodes
        bp.setId("bp");
        gridPane.setId("root");
        btnLogin.setId("btnLogin");
        text.setId("text");


        chngServerBtn.setOnAction(event -> {
            try {
                JavaFXStaticData.NewPAgeStarter("/fxml/ChangeServer.fxml", "Зміна серверу",
                        "com.View.AuthSection.ChangeServerController");

            } catch (IOException e) {
            }
        });

        btnLogin.setOnAction(event -> {
            login = txtUserName.getText().toString();
            pasw = pf.getText().toString();

            UserEntity user = UserActions.getUserByLogin(login);

            if (user != null && user.loginPasswordEquals(login, pasw)) {
                UserLogEntity userLog = new UserLogEntity(user.getUserId(), "auth", 1, new Date());
                UserLogActions.add(userLog);

                lblMessage.setText("Вітаю!");
                lblMessage.setTextFill(Color.GREEN);
                newFormWithPrLVL(user);

            } else {
                lblMessage.setText("Помилкові логін або пароль");
                lblMessage.setTextFill(Color.RED);
                txtUserName.setText(login);
                pf.setText("");
                //TODO add log!!!!
            }
        });

        //Add HBox and GridPane layout to BorderPane Layout
        bp.setLeft(image);
        bp.setTop(hb);
        bp.setCenter(gridPane);
        //Adding BorderPane to the scene and loading CSS
        Scene scene = new Scene(bp);
        //scene.getStylesheets().add(AuthController.class.getResource("/login/login3.css").toExternalForm());
        primaryStage1.setScene(scene);

        //primaryStage.setResizable(false);
        primaryStage1.getIcons().add(new Image(AuthController.class.getClass().getResourceAsStream("/Pictures/login.jpg")));

        primaryStage1.show();
    }


    private void addDefaultUsersIfNeeded() {
        UserEntity admin;
        UserEntity user;
        long amount = UserActions.ifUsersExist();
        if (amount == 0) {
            UserActions.add(new UserEntity("admin", BCrypt.hashpw("admin", BCrypt.gensalt()), 0));
            UserActions.add(new UserEntity("user", BCrypt.hashpw("user", BCrypt.gensalt()), 1));
        }
    }


    //old user validation, left to show the usage of JSON
                /*for (JSONUser jsonUser : users) {
                    if (jsonUser.loginPasswordEquals(login, pasw)) {
                        lblMessage.setText("Вітаю!");
                        lblMessage.setTextFill(Color.GREEN);
                        newFormWithPrLVL(jsonUser);



                        break;//if exists two users with equal pairs.
                    } else {
                        lblMessage.setText("Помилкові логін або пароль");
                        lblMessage.setTextFill(Color.RED);
                        txtUserName.setText(login);
                        pf.setText("");
                    }
                }*/


    private void AddingNodes(GridPane gridPane, Label lblUserName, TextField txtUserName,
                             Label lblPassword, PasswordField pf, Button btnLogin,
                             Label lblMessage, Button changeServerSettingBtn) {
        gridPane.add(lblUserName, 0, 0);
        gridPane.add(txtUserName, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(pf, 1, 1);
        gridPane.add(btnLogin, 2, 0);
        gridPane.add(lblMessage, 1, 2);
        gridPane.add(changeServerSettingBtn, 2, 1);
        lblMessage.setMinWidth(200);
    }

    private GridPane InitGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        return gridPane;
    }

    //new one
    private void newFormWithPrLVL(UserEntity userEntity) {
        if (userEntity.getUserAccessLvl() == 0)
            try {
                UserData.setUser(userEntity);
                UserData.setUsers(users);
                JavaFXStaticData.NewPAgeStarter("/fxml/AdminWindow.fxml", "Адміністратор", "com.View.AdminSection.AdminWindowController");
                primaryStage.close();

            } catch (Exception e) {
            }
        else
            try {
                MainPageStarter b = new MainPageStarter(userEntity);
                try {
                    b.start(primaryStage);
                } catch (ExceptionInInitializerError e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    /*old method
    private void newFormWithPrLVL(JSONUser jsonUser) {
        if(jsonUser.privacyLvl == 0)
            try{
                GUIForm.setUser(jsonUser);
                GUIForm.setUsers(users);
                GUIForm.NewPAgeStarter("/fxml/AdminWindow.fxml", "Адміністратор", "com.View.AdminSection.AdminWindowController");
                primaryStage.close();

            }
            catch(Exception e){}
        else
            try{
                GUIForm b = new GUIForm(jsonUser);
                try {
                    //a.main(args2);
                    b.start(primaryStage);
                }catch(Exception e){}
            }
            catch (Exception e){}
    }*/
}
