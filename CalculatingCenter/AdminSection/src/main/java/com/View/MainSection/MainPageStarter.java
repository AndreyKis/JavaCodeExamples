package com.View.MainSection;/**
 * Created by Andrey on 20.10.2015.
 */

import com.Model.CurrentProjectData.UserData;
import com.Model.DataBase.Entities.UserEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainPageStarter extends Application{
    public static Stage primaryStage, enter;

    public MainPageStarter(UserEntity user) {
        UserData.setUser(user);
    }
    public MainPageStarter() {
        UserData.setUser(new UserEntity("admin", "admin", 0));
    }

    private AnchorPane rootLayout;
    private static Thread Main=Thread.currentThread();

    public static Thread getMain() {
        return Main;
    }
    public static void main(String[] args) throws Exception{launch(args);}

    @Override
    public void start(Stage primaryStage1) throws Exception{

        primaryStage=primaryStage1;
        primaryStage.setMaximized(true);

        try {
            // Load root layout from fxml file.
            String fxmlFile = "/fxml/MainPage.fxml";
            FXMLLoader loader = new FXMLLoader();

            this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Pictures/sign.png")));
            AnchorPane root = loader.load(getClass().getResourceAsStream(fxmlFile));
            // Show the scene containing the root layout.
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
           scene.getStylesheets().add(MainPageStarter.class.getResource("/login/login2.css").toExternalForm());

            primaryStage.setTitle("Система моніторингу та аналізу тональності даних");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
