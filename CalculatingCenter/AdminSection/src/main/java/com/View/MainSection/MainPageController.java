package com.View.MainSection;

import com.Controller.ExtFunctController.Alerts;
import com.Model.CurrentProjectData.JavaFXStaticData;
import com.Model.CurrentProjectData.TextData;
import com.Model.CurrentProjectData.UserData;
import com.Model.ParseText.BaseText;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

//import java.awt.*;

/**
 * Created by Andrey on 29.10.2015.
 */
public class MainPageController implements Initializable {
    @FXML
    private Button goToAdmin;
    @FXML
    private Button SourcesSettingsBtn;
    @FXML
    private AnchorPane anchPane;
    @FXML
    private MenuButton chooseSourceBtn;
    @FXML
    private Button studyCombBtn;
    @FXML
    private Button analyzeTxtBtn;
    @FXML
    private Button graphsBtn;
    @FXML
    private TextArea presentTxtArea;
    private Date beginDate;
    private Date endDate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //GUIForm.getBeginDate(); GUIForm.getEndDate();


        //anchPane.
        /*PravdaParse pravda = new PravdaParse();
        pravda.setAllNews();*/
        //Text t = new Text(50,100, "Міністерство оборони України. Управління інформаційними технологіями");
        //t.setFill(Color.GREEN);
        //доступ
        int accessLvl = UserData.getUser().getUserAccessLvl();
        if (accessLvl == 1) {
            goToAdmin.setDisable(true);
        }
        if (accessLvl == 2) {
            studyCombBtn.setDisable(true);
            goToAdmin.setDisable(true);
        }
        if (accessLvl == 3) {
            SourcesSettingsBtn.setDisable(true);
            chooseSourceBtn.setDisable(true);
            studyCombBtn.setDisable(true);
            analyzeTxtBtn.setDisable(true);
            goToAdmin.setDisable(true);
        }
        presentTxtArea.setText("Міністерство оборони України" + "\n\n" + "Управління інформаційних технологій");
        presentTxtArea.setEditable(false);
        presentTxtArea.autosize();
        SourcesSettingsBtn.setOnAction(event1 -> {
            try {
                JavaFXStaticData.NewPAgeStarter("/fxml/SourcesSettings.fxml", "Ресурси для завантаження текстів", "com.View.MainSection.SourcesSettingsController");
            } catch (IOException e) {
            }
        });
        goToAdmin.setOnAction(event1 -> {
            try {
                JavaFXStaticData.NewPAgeStarter("/fxml/AdminWindow.fxml", "Адміністратор", "com.View.AdminSection.AdminWindowController");
                MainPageStarter.primaryStage.close();
            } catch (IOException e) {
            }
        });
        chooseSourceBtn.getItems().get(0).setOnAction(event -> {
            //addTextBtn.setOnAction(event -> {
            //Перестала работать внешняя апишка
            /*String twitWord;
            try {
                twitWord = alertEnterTag();
                int number = getTweets(twitWord);
                alertNewWasGrabbed(number, GUIForm.textsObject.allWords.size(), "твіти");
            } catch (NoSuchElementException e) {
                twitWord = "null";
            }*/
                Alerts.alertDataGrab("соціальних мереж");
        });
        chooseSourceBtn.getItems().get(1).setOnAction(event -> {
            Alerts.alertTestInfo();
            /*Grabber grabber = new Grabber();
            grabber.newsGrabbing(SourcesData.getChosenParsers());*/
            Alerts.alertTestWasGrabbed();
        });
        chooseSourceBtn.getItems().get(2).setOnAction(event -> {
            Alerts.alertTestInfo();
            /*Grabber grabber = new Grabber();
            grabber.blogsGrabbing(SourcesData.getChosenParsers());*/
            Alerts.alertTestWasGrabbed();
            //Alerts.alertDataGrab("блогів та форумів!");
        });
        chooseSourceBtn.getItems().get(3).setOnAction(event1 -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Оберіть файл");

            File file = fileChooser.showOpenDialog(MainPageStarter.enter);
            ReadTxtFile(file);
        });
        chooseSourceBtn.getItems().get(4).setOnAction(event1 -> {
            DirectoryChooser fileDirectory = new DirectoryChooser();
            fileDirectory.setTitle("Оберіть папку");

            File file = fileDirectory.showDialog(MainPageStarter.enter);
            ReadDirectory(file);
        });
        chooseSourceBtn.getItems().get(5).setOnAction(event1 -> {
            Alerts.alertDataGrab("буферу (простого вводу)!!");
        });

        studyCombBtn.setOnAction(event2 -> {
            try {
                JavaFXStaticData.NewPAgeStarter("/fxml/StudyBtn.fxml", "Етап навчання", "com.View.MainSection.StudyBtnController");
            } catch (IOException e) {
            }
        });


        graphsBtn.setOnAction(event -> {
            //some url to the python website was here
            JavaFXStaticData.WebFormStarter("", "Система перевірки проведенної оцінки");
            /*try {
                JavaFXStaticData.NewPAgeStarter("/fxml/fileForVibirkaa.fxml", "Отримані тексти",
                "com.View.MainSection.fileForVibirkaController");
            } catch (IOException e) {
            }*/
        });

        analyzeTxtBtn.setOnAction(event -> {
            try {
                JavaFXStaticData.NewPAgeStarter("/fxml/AnalyzingForm.fxml", "Проаналізувати тексти", "com.View.MainSection.AnalyzingFormController");
            } catch (IOException e) {
            }
        });
    }

    private void ReadDirectory(File dir) {
        File[] listOfFiles = dir.listFiles();
        //GUIForm.setFileTag(GUIForm.getFileTag())
        for (File currFile : listOfFiles) {
            if (currFile.exists())
                ReadTxtFile(currFile);
        }
    }


    private void ReadTxtFile(File file) {

        String expansion = null;
        if (file != null) {
            expansion = getFileExtention(file.getAbsolutePath());
            if ("txt".equals(expansion)) {
                String path = file.getAbsolutePath();
                StringBuilder sb = new StringBuilder();
                //try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file), "UTF-8"))) {
                //try(Scanner reader = new Scanner(new InputStreamReader(
                        //new FileInputStream(file), "UTF-8"))){
                    String title = null;
                    try {
                        String s;
                        title = reader.readLine();
                        while ((s = reader.readLine()) != null) {
                        //while (reader.hasNext()) {
                            s = reader.readLine();
                            sb.append(s);
                            sb.append("\n");
                        }
                    } finally {
                        reader.close();
                    }
                    System.out.println(sb);
                    BaseText text = new BaseText();
                    text.setText(sb.toString());
                    text.setTitle(title);
                    TextData.textsObject.add(text);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else
                alertNotTxt();
        }
        else
            Alerts.alertFileAbsence();
        /*GUIForm.setFileTag(GUIForm.getFileTag() == null ? file.getName() :
                GUIForm.getFileTag() + file.getName());*/
    }

    private void alertNotTxt() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Помилка");
        alert.setHeaderText("Помилка під час считування файлу");
        alert.setContentText("Ви обрали файл з розширенням, відмінним від .txt");

        alert.showAndWait();
    }

    public static String getFileExtention(String fullPath) {
        int sepPos = fullPath.lastIndexOf(File.separator);
        String nameAndExt = fullPath.substring(sepPos + 1, fullPath.length());
        int dotPos = nameAndExt.lastIndexOf(".");
        return dotPos != -1 ? nameAndExt.substring(dotPos + 1) : "";
    }

    public String alertEnterTag() {
        TextInputDialog dialog = new TextInputDialog("АТО");
        dialog.setTitle("Пошуковий запит");
        dialog.setHeaderText(null);
        dialog.setContentText("Введіть пошуковий запит:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();

// The Java 8 way to get the response value (with lambda expression).
        //result.ifPresent(name -> System.out.println("Your name: " + name));
        if (result != null)
            return result.get();
        else return "АТО";
    }

}
