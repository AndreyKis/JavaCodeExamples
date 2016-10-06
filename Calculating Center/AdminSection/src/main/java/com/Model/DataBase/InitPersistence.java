package com.Model.DataBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 28.03.2016.
 */
//another file for server changing.
public class InitPersistence {
    private static String NewIP;
    private static String UserName;
    private static String UserPass;

    public static Map<String, String> persistenceMap;

    public InitPersistence(String ip, String userName, String userPass) {
        NewIP = ip;
        UserName = userName;
        UserPass = userPass;
        persistenceMap = new HashMap<>();
        persistenceMap.put("javax.persistence.jdbc.url", "jdbc:mysql://" + NewIP + "/newsmonitoringdb");
        persistenceMap.put("javax.persistence.jdbc.user", UserName);
        persistenceMap.put("javax.persistence.jdbc.password", UserPass);
    }

    public static String getNewIP() {
        return NewIP;
    }

    public static String getUserName() {
        return UserName;
    }

    public static String getUserPass() {
        return UserPass;
    }
}
