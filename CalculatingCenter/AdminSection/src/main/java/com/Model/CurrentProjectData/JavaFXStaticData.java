package com.Model.CurrentProjectData;

import com.View.MainSection.Browser;
import com.View.MainSection.MainPageStarter;
import com.sun.xml.internal.bind.v2.TODO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by User on 22.08.2016.
 */
//        TODO Unite this method with MainPage starter to avoid code duplication
public class JavaFXStaticData {
    public static void NewPAgeStarter(String url, String Title, String ClassName) throws IOException {

        try {
            MainPageStarter.enter = new Stage();
            MainPageStarter.enter.setTitle(Title);
            MainPageStarter.enter.getIcons().add(new Image(MainPageStarter.class.getResourceAsStream("/Pictures/sign.png")));

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainPageStarter.class.getResource(url));
            AnchorPane page = loader.load();

            Scene scene = new Scene(page);
            scene.getStylesheets().add(MainPageStarter.class.getResource("/login/login2.css").toExternalForm());

            MainPageStarter.enter.setScene(scene);
            //Rectangle2D bounds = page.getVisualBounds();
            if(!Title.equals("Зміна серверу"))
                MainPageStarter.enter.setMaximized(true);

            Object object=Class.forName(ClassName).newInstance();


            loader.setController(object);

            MainPageStarter.enter.show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    public static void WebFormStarter(String url, String title) {
            MainPageStarter.enter = new Stage();
            MainPageStarter.enter.setTitle(title);
            MainPageStarter.enter.getIcons().add(new Image(MainPageStarter.class.getResourceAsStream("/Pictures/sign.png")));

            Scene scene = new Scene(new Browser(url),750,500, Color.web("#666970"));

            MainPageStarter.enter.setScene(scene);
            //Rectangle2D bounds = page.getVisualBounds();
            scene.getStylesheets().add("/login/BrowserToolbar.css");
            MainPageStarter.enter.setMaximized(true);

            MainPageStarter.enter.show();
    }

    public static void closeWindow(){
        MainPageStarter.primaryStage.close();
    }
}
