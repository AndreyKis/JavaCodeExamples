package com.Model.ParseText;

import com.Controller.TonalityAnalyzingController.TonalityData;
import org.json.JSONObject;

/**
 * Created by yaserg555 on 11/1/2015.
 */
public class Paragraph {
    public String getText() {
        return text + "\n\n" + tonalityData;
    }

    private String text;

    public TonalityData getTonalityData() {
        return tonalityData;
    }
    public void classificateUsingCombineMethod()
    {
        tonalityData.classificationUsingCombineMethod(text);
    }

    public void classificateUsingCombineMethodWord(String word) {tonalityData.classificationUsingCombineMethodWord(text, word);}

    private TonalityData tonalityData=new TonalityData();

    public Paragraph(String text) {
        this.text = text;
        tonalityData.SetDontClassified();
    }

    @Override
    public String toString() {
        return text + '\'' +
                ", TONALITY=(" + tonalityData.toString() +
                ")";


    }
        public JSONObject getJSONObj(){
            JSONObject objUser = new JSONObject();
            objUser.put("Paragraph", this.text);
            objUser.put("Tonality", this.tonalityData.toString());
            return objUser;




}
    public Paragraph(org.json.simple.JSONObject paragraph){
        this.text = paragraph.get("Paragraph").toString();
        this.tonalityData = new TonalityData(paragraph.get("Tonality").toString());
    }

}
