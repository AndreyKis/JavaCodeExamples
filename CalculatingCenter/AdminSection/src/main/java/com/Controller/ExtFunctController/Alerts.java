package com.Controller.ExtFunctController;

import javafx.scene.control.Alert;

/**
 * Created by User on 28.03.2016.
 */
public class Alerts {
    public static void alertNewWasGrabbed(Integer newsAmount, Integer totalWordsAmount, String whatGrabbed) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Данні зібрано.");
        alert.setHeaderText("Вітаю! Ви успішно завантижили " + whatGrabbed + ".");
        alert.setContentText("Кількість нових текстів " + newsAmount + ". Всього унікальних слів збережено (розмірність вектору) = " + totalWordsAmount);

        alert.showAndWait();
    }

    public static void alertDataGrab(String name) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка");
        alert.setHeaderText("Функція не реалізована.  ");
        alert.setContentText("Чекайте нову версію програми з реалізацією збору данних з " + name);

        alert.showAndWait();
    }

    public static void alertTestInfo() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Інформація");
        alert.setHeaderText("Буде зібрано по 5 статтей с кожного сайту");
        alert.setContentText("PS: Це лише тестова версія");

        alert.showAndWait();
    }

    public static void alertTestWasGrabbed() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Данні зібрано.");
        alert.setHeaderText("Вітаю! Ви успішно завантижили 5 статтей с кожного сайту");
        alert.setContentText("PS: Це лише тестова версія");

        alert.showAndWait();
    }

    public static void AlertEmptyIP() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилкові дані");
        alert.setHeaderText("Невірно введені дані ");
        alert.setContentText("Необхідно ввести непорожній адрес сервера");

        alert.showAndWait();
    }

    public static void alertStartedGrabbing(String name) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Початок збору данних.");
        alert.setHeaderText("Починається збір данних з " + name);
        alert.setContentText("Збір данних, при швидкому інтернет з'єднанні займає до 10 сек. Натисніть ОК, та чекайте нового повідомлення.");

        alert.showAndWait();
    }

    public static void alertChangingServer(String url) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Процес зміни Сервера");
        alert.setHeaderText("Відбувається зміна сервера на " + url);
        alert.setContentText("Зміна сервера відбувається миттєво. Натисніть Ок та завершіть зміну сервера для подальшої авторизації");

        alert.showAndWait();
    }

    public static void alertFileAbsence() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Помилка");
        alert.setHeaderText("Помилка під час считування файлу");
        alert.setContentText("Файл не було обрано");

        alert.showAndWait();
    }

    public static void alertDictionary(Integer amountWords) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Словник сформульований.");
        alert.setHeaderText("Створений словник зі слів які входять до текстів.");
        alert.setContentText("Розмір словника = " + amountWords + " унікальних слів");

        alert.showAndWait();
    }

    public static void alertTonality() {
        //http://code.makery.ch/blog/javafx-dialogs-official/

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка");
        alert.setHeaderText("Програма буде занадто довго рахувати тональність.  ");
        alert.setContentText("Чекайте нову версію програми з оптимізацією цього алгоритму!");

        alert.showAndWait();
    }

    public static void AlertServer(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка");
        alert.setHeaderText("Помилка підключення до сервера. Спробуйте підключитись до іншого");
        alert.setContentText("Проблеми з підключенням до сервера могли виникнути через несправність останнього, або " +
                "несправність домену, за яким може відбуватися підключення," +
                "або через проблеми з інтернет з'єднанням");

        alert.showAndWait();
    }
}
