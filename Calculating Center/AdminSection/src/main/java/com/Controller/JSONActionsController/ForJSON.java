package com.Controller.JSONActionsController;


import com.Controller.UsersController.JSONUser;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Andrey on 12.12.2015.
 */
//Deprecated file, added to show the usages of JSON
public class ForJSON {

    public static void main(String[] args) {
        //CreateDefaultJSON();
        CorrectReading();
    }

    public static ArrayList<JSONUser> CorrectReading() {
        try {

            return (readJSON());

        } catch (Exception e) {
            // e.printStackTrace();
            return CreateDefaultJSON();
        }
    }

    private static ArrayList<JSONUser> readJSON() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("UsersController.json"));

        JSONObject jsonObject = (JSONObject) obj;


        JSONArray listOfUsers = (JSONArray) jsonObject.get("users");

        ArrayList<JSONUser> users = UsersToArray(listOfUsers);
        /*for (JSONUser user : users) {
            System.out.println(user);
        }*/
        return users;
    }

    private static ArrayList<JSONUser> UsersToArray(JSONArray listOfUsers) {
        ArrayList<JSONUser> users = new ArrayList<>();
        for (Object user : listOfUsers) {
            JSONUser codeUser = new JSONUser((JSONObject) user);
            users.add(codeUser);
        }
        return users;
    }

    private static ArrayList<JSONUser> CreateDefaultJSON() {


        JSONObject resultJson = new JSONObject();//object of all file.
        ArrayList<JSONUser> users = new ArrayList<>();//list of users
        JSONArray usersOfJSON = new JSONArray(); //json array of users.
        setListDefault(users);

        for (JSONUser user : users) {
            usersOfJSON.add(user.getJSONObj());
        }

        resultJson.put("users", usersOfJSON);
        writeJSONToFile(resultJson);
        return users;
    }

    public static void writeListToJSON(ArrayList<JSONUser> users) {


        JSONObject resultJson = new JSONObject();//object of all file.
        JSONArray usersOfJSON = new JSONArray(); //json array of users.

        for (JSONUser user : users) {
            usersOfJSON.add(user.getJSONObj());
        }

        resultJson.put("users", usersOfJSON);
        writeJSONToFile(resultJson);
    }

    private static void setListDefault(ArrayList<JSONUser> users) {
        JSONUser admin = new JSONUser("admin", "admin", 0);
        JSONUser user1 = new JSONUser("user", "user", 1);
        users.add(admin);
        users.add(user1);
    }

    private static void writeJSONToFile(JSONObject resultJson) {
        try (FileWriter file = new FileWriter("UsersController.json")) {
            file.write(JSONValue.toJSONString(resultJson));
            System.out.println("Successfully Copied JSON Object to File...");
        } catch (IOException e) {
        }
    }

}
