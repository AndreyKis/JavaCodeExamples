package com.View.AdminSection;

import com.Controller.JSONActionsController.ForJSON;
import com.Controller.UsersController.JSONUser;
import com.Model.CurrentProjectData.UserData;
import com.Model.DataBase.Entities.UserEntity;
import com.Model.DataBase.Entities.UserLogEntity;
import com.Model.DataBase.EntitiesActions.Implementations.UserActions;
import com.Model.DataBase.EntitiesActions.Implementations.UserLogActions;
import com.View.MainSection.MainPageStarter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class DeleteUserController implements Initializable {
    private TableView<UserEntity> table = new TableView<>();
    private ObservableList data = getInitialTableData();
    private Text actionStatus;
    @FXML
    private AnchorPane anch;
    @FXML
    private Button delBtn;
    @FXML
    private Button acceptChangesBtn;
    private VBox vBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        // users label
        Label label = new Label("Користувачі");
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
        HBox labelHb = new HBox();
        labelHb.setAlignment(Pos.CENTER);
        labelHb.getChildren().add(label);

        // Table view, data, columns and properties
        //table.row

        table.setEditable(true);

        TableColumn loginCol = new TableColumn("Логін");
        TableColumn passCol = new TableColumn("Пароль");
        TableColumn privacyCol = new TableColumn("Рівень доступу");


        passCol.setCellValueFactory(new PropertyValueFactory<UserEntity,String>("userPass"));
        loginCol.setCellValueFactory(new PropertyValueFactory<UserEntity,String>("userLogin"));
        privacyCol.setCellValueFactory(new PropertyValueFactory<UserEntity,Integer>("userAccessLvl"));


        table.getColumns().setAll(loginCol, passCol, privacyCol);

        table.setItems(data);


        //titleCol.setCellValueFactory(new PropertyValueFactory("title"));
        //loginCol.setCellFactory(TextFieldTableCell.forTableColumn());
        /*loginCol.setOnEditCommit(new EventHandler<CellEditEvent>() {
            @Override
            public void handle(CellEditEvent t) {
                ((JSONUser) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).login = ((String) t.getNewValue());
            }
        });
        //authorCol.setCellValueFactory(new PropertyValueFactory("author"));
        privacyCol.setOnEditCommit(new EventHandler<CellEditEvent>() {
            @Override
            public void handle(CellEditEvent t) {

                ((JSONUser) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setPassword((String) t.getNewValue());
            }
        });*/


        table.setPrefWidth(600);
        table.setPrefHeight(450);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        ArrayList<UserEntity> prevData = new ArrayList<>(data);
        //Collections.copy(prevData, data);

        delBtn.setOnAction(event -> {
            UserEntity currentUser = new UserEntity();
            if (table.getSelectionModel().getSelectedItems().size() != 0) {
                currentUser = table.getItems().get(table.getSelectionModel().getFocusedIndex());
                //currentUser = (JSONUser) table.getItems().get(table.getSelectionModel().getFocusedIndex());
                data.remove(currentUser);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Помилка");
                alert.setHeaderText("");
                alert.setContentText("Оберіть користувача для видалення");

                alert.showAndWait();
            }

        });

        acceptChangesBtn.setOnAction(event -> {
            /*ArrayList<JSONUser> users = new ArrayList<JSONUser>(data);
            saveUsersToFile(users);*/
            ArrayList<UserEntity> deletedUsers = new ArrayList<>(prevData);
            //Collections.copy(deletedUsers, prevData);
            deletedUsers.removeAll(data);
            UserActions userActions = new UserActions();
            if (deletedUsers.size() != 0) {
                for (UserEntity deletedUser : deletedUsers) {
                    long id = deletedUser.getUserId();
                    userActions.delete(id);
                    UserLogEntity userLog = new UserLogEntity(UserData.getUser().getUserId(), "deleted user: " +
                            deletedUser.getUserLogin(), 3, new Date());
                    UserLogActions log_action = new UserLogActions();
                    log_action.add(userLog);
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Вітаю");
                alert.setHeaderText("");
                alert.setContentText("Видалення користувача пройшло успішно");

                alert.showAndWait();
            }

        });





















        /*OLD VERSIONS OF METHODS

        delBtn.setOnAction(event -> {
            UserEntity currentUser = new UserEntity();
            if(table.getSelectionModel().getSelectedItems().size() != 0) {
                currentUser = table.getItems().get(table.getSelectionModel().getFocusedIndex());
                data.remove(currentUser);

                UserLogEntity userLog = new UserLogEntity(UserData.getUser().getUserId(), "deleted user: " + currentUser.getUserLogin(), 3, new Date());
                UserLogActions log_action = new UserLogActions();
                log_action.add(userLog);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Помилка");
                alert.setHeaderText("");
                alert.setContentText("Оберіть користувача для видалення");

                alert.showAndWait();
            }

        });

        acceptChangesBtn.setOnAction(event -> {
            ArrayList<JSONUser> users = new ArrayList<JSONUser>(data);
            saveUsersToFile(users);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Вітаю");
            alert.setHeaderText("");
            alert.setContentText("Видалення користувача пройшло успішно");

            alert.showAndWait();
        });*/
















        MainPageStarter.enter.setOnCloseRequest(event1 -> {
            MainPageStarter.enter.close();
            //saveUsersToFile(getUsersFromTable());
        });
        setElementsLayout();
        vBox = new VBox(20);
        vBox.setPadding(new Insets(25, 25, 25, 25));

        vBox.getChildren().addAll(labelHb, table);
        vBox.setLayoutX(200);
        vBox.setLayoutY(0);
        //vBox.setAlignment(Pos.CENTER);
        anch.getChildren().add(vBox);
    } // start()


    private void setElementsLayout() {
        table.setPrefWidth(900);
        table.setPrefHeight(400);
        table.setLayoutX(0);
        table.setLayoutY(0);
    }

    private ArrayList<JSONUser> getUsersFromTable() {
        return null;
    }

    private void setUserInfo(int index, String newName, String newPass, Integer lvl) {
        ArrayList<JSONUser> newusers = UserData.getUsers();
        String userName = newName;
        String userPass = newPass;
        Integer userLvl = lvl;
        newusers.set(index, new JSONUser(userName, userPass, userLvl));

        saveUsersToFile(newusers);
    }

    private void addUserInfo(String newName, String newPass, Integer lvl) {
        ArrayList<JSONUser> newusers = UserData.getUsers();
        String userName = newName;
        String userPass = newPass;
        Integer userLvl = lvl;
        newusers.add(new JSONUser(userName, userPass, userLvl));

        saveUsersToFile(newusers);
    }

    private void deleteUser(int index) {
        ArrayList<JSONUser> newusers = UserData.getUsers();
        newusers.remove(index);

        saveUsersToFile(newusers);
    }

    private void saveUsersToFile(ArrayList<JSONUser> newusers) {
        UserData.setUsers(newusers);
        ForJSON.writeListToJSON(newusers);
    }

    /*private class RowSelectChangeListener implements ChangeListener {

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            int ix = (int) newValue;

            if ((ix == data.size())) {

                return; // invalid data
            }

            TableNode node = (TableNode) data.get(ix);
            actionStatus.setText(node.toString());
        }
    }*/

    private String getUserLvlName(int value) {
        String lvlName = "Гостьовий користувач";
        switch (value) {
            case 0:
                lvlName = "Адміністратор";
                break;
            case 1:
                lvlName = "Привілейований користувач";
                break;
            case 2:
                lvlName = "Користувач";
                break;
            case 3:
                lvlName = "Гостьовий користувач";
                break;
        }
        return lvlName;
    }
    class TableNode {
        SimpleStringProperty login;
        SimpleStringProperty privacyLvl;

        public TableNode(String login, String privacyLvl) {
            this.login = new SimpleStringProperty(login);
            this.privacyLvl = new SimpleStringProperty(privacyLvl);
        }
        @Override
        public String toString() {//I don't know why I use this(

            return (login + ", by " + privacyLvl);
        }
    }
    private ObservableList getInitialTableData() {

        //List list = new ArrayList();
        //ArrayList<JSONUser> users = ForJSON.CorrectReading();
        //System.out.println(getUserLvlName(users.get(0).privacyLvl));
        List list = new ArrayList();
        /*for (JSONUser user : users) {
            list.add(new TableNode(user.login.toString(), getUserLvlName(user.privacyLvl)));
        }*/
        //ArrayList list = users;


        UserActions actions = new UserActions();
        List<UserEntity> users = actions.list();



        ObservableList data = FXCollections.observableList(users);


        return data;
        /*list.add("Hello");
        list.add("GoodBye");
        list.add("How");
        list.add("you");
        list.add("doing");




        list.add(new TableNode("Of Human Bondage", "Somerset Maugham"));
        list.add(new TableNode("Of Human Bondage", "Somerset Maugham"));
        list.add(new TableNode("Of Human Bondage", "Somerset Maugham"));*/





        /*list.add(new user("The Thief", "Fuminori Nakamura"));
        list.add(new user("Of Human Bondage", "Somerset Maugham"));
        list.add(new user("The Bluest Eye", "Toni Morrison"));
        list.add(new user("I Am Ok You Are Ok", "Thomas Harris"));
        list.add(new user("Magnificent Obsession", "Lloyd C Douglas"));
        list.add(new user("100 Years of Solitude", "Gabriel Garcia Marquez"));
        list.add(new user("What the Dog Saw", "Malcolm Gladwell"));*/
    }

    private class AddButtonListener implements EventHandler {

        @Override
        public void handle(Event event) {
            // Create a new row after last row
            JSONUser user = new JSONUser("", "", 3);
            data.add(user);
            int row = data.size() - 1;

            // Select the new row
            table.requestFocus();
            table.getSelectionModel().select(row);
            table.getFocusModel().focus(row);

            actionStatus.setText("New user: Enter title and author. Press .");
        }
    }

    private class DeleteButtonListener implements EventHandler {

        @Override
        public void handle(Event e) {

            // Get selected row and delete
            int ix = table.getSelectionModel().getSelectedIndex();
            //TableNode node = (TableNode) table.getSelectionModel().getSelectedItem();
            data.remove(ix);
            //actionStatus.setText("Deleted: " + node.toString());

            // Select a row

            if (table.getItems().size() == 0) {

                actionStatus.setText("No data in table !");
                return;
            }

            if (ix != 0) {

                ix = ix - 1;
            }

            table.requestFocus();
            table.getSelectionModel().select(ix);
            table.getFocusModel().focus(ix);
        }
    }
}