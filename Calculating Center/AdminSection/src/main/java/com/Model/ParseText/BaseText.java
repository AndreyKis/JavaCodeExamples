package com.Model.ParseText;

import com.Controller.TonalityAnalyzingController.TonalityData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Andrey on 30.10.2015.
 */
public class BaseText {
    // <textInfo>
    private String text;
    private String title;
    private String source;
    private Date dateOfText;
    // </textInfo>

    // <userInfo>
    private String userPhotoUrl;
    private String userName;
    private String userUrl;
    // </userInfo>

    private Date dateOfParse = new Date();
    private ArrayList<Paragraph> paragraphs = new ArrayList<>();
    private TonalityData tonalityData = new TonalityData();

    public Date getDateOfText() {
        return dateOfText;
    }

    public ArrayList<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TonalityData getTonalityData() {
        return tonalityData;
    }

    public void setTonalityData(TonalityData tonalityData) {
        this.tonalityData = tonalityData;
    }

    public String getTitle() {
        return title;
    }

    public String getParagraphesString() {
        String temp = "\n";
        for (Paragraph paragraph : paragraphs) {
            temp += paragraph.toString();
            temp += "\n\n";
        }
        temp += "  Створення : " + dateOfText;
        temp += ";  Скачано в" + dateOfParse;
        return temp;
        // return text;
    }


    public void setText(String text) {

        this.text = text;
        String stringParagraphs[] = text.split("\\r?\\n");
        paragraphs = new ArrayList<Paragraph>();
        for (String baseText : stringParagraphs) {
            Charset.forName("UTF-8").encode(baseText);
            //byte[] ptext = baseText.getBytes("UTF-8");
            paragraphs.add(new Paragraph(baseText));
        }
        //ArrayList<Paragraph> lines = Arrays.asList(stringParagraphs);
    }

    public Date getDateOfParse() {
        return dateOfParse;
    }

    public void setDateOfParse(Date dateOfParse) {
        this.dateOfParse = dateOfParse;
    }

    public BaseText() {
    }

    public BaseText(String text) {
        this.text = text;
    }

    public BaseText(JSONObject obj) {
        this.paragraphs = new ArrayList<Paragraph>();
        setTonalityData(new TonalityData((obj).get("tonality").toString()));
        JSONArray listOfBaseTexts = (JSONArray) obj.get("paragraph");
        for (Object baseText : listOfBaseTexts) {
            paragraphs.add(new Paragraph((JSONObject) baseText));
        }
    }


    public void setTextInfo(String text, String title, Date dateOfText, String source) {
        this.text = text;
        this.title = title;
        this.dateOfText = dateOfText;
        this.source = source;
    }

    public void setUserInfo(String userName, String userUrl, String userPhotoUrl) {
        this.userName = userName;
        this.userUrl = userUrl;
        this.userPhotoUrl = userPhotoUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public String getUserUrl() {
        return userUrl;
    }
}