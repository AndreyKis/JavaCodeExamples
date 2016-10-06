package com.View.AuthSection;

import com.Controller.ExtFunctController.Alerts;
import com.Controller.ForXML;
import com.Model.DataBase.InitPersistence;
import com.View.MainSection.MainPageStarter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by User on 28.03.2016.
 */
public class ChangeServerController implements Initializable {
    @FXML
    private Button ChangeServerBtn;
    @FXML
    private Button EndChangeServerBtn;
    @FXML
    private TextField UrlTxtField;
    @FXML
    private TextField UserNameTxtField;
    @FXML
    private TextField UserPassTxtField;

    @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
        UrlTxtField.setText(InitPersistence.getNewIP());
        UserNameTxtField.setText(InitPersistence.getUserName());
        UserPassTxtField.setText(InitPersistence.getUserPass());
            ChangeServerBtn.setOnAction(event -> {
                if(!UrlTxtField.getText().equals(""))
                {
                    InitPersistence pers = new InitPersistence(UrlTxtField.getText(),
                            UserNameTxtField.getText(),
                            UserPassTxtField.getText());
                    Alerts.alertChangingServer(UrlTxtField.getText());
                    ForXML.modifyXML(UrlTxtField.getText(), UserNameTxtField.getText(),
                            UserPassTxtField.getText());
                }
                else
                    Alerts.AlertEmptyIP();
        });
        EndChangeServerBtn.setOnAction(event -> {
            MainPageStarter.enter.close();
        });
    }
}
