package com.Model.ParseText;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

/**
 * Created by Andrey on 30.10.2015.
 */

public class Texts {
    private static int negativeCount = 0;
    private final int MinimumWordLength = 2;
    public  ArrayList<BaseText> listOfText;
    public TreeMap<String,List<Integer>> allWords;//string , count allTexts,count words
    public final String symbolsToSplit ="[^0-9][.,]|[.,][^0-9]|\\s+|(?![.,])\\p{Punct}";
    public void createListAllWords(){
        for (BaseText baseText : listOfText) {
            addWordsFromParagraphes(baseText);
        }

    }

    public void addAllWordsToList(String text){
        //мама мама мыла раму

        ArrayList<String> words = new ArrayList<String>(Arrays.asList(text.toLowerCase().split(symbolsToSplit)));
        //Записуємо в список усі окремі слова.
        TreeMap<String,Integer> wordCountInCurrentText=new TreeMap<>();

        for (String word : words) {
            Integer count=wordCountInCurrentText.get(word);
            wordCountInCurrentText.put(word,count==null?1:count+1);
            //Записуємо унікальні слова, зберігаючі їх кількість.
        }

        //мама:2 мыла:1 раму:1
        for (Map.Entry<String, Integer> stringIntegerEntry : wordCountInCurrentText.entrySet()) {
            String word=stringIntegerEntry.getKey();
            if(word.length()<MinimumWordLength) continue; //фільтруємо слова коротші за 2і літери.
            Integer countWords=stringIntegerEntry.getValue();

            List<Integer> pairIntegers = new ArrayList<>();
            if (allWords.get(word) != null) {//слово есть

                pairIntegers.add(allWords.get(word).get(0)+1);//количество текстов+1
                pairIntegers.add(allWords.get(word).get(1)+countWords);//общее количество слов ++++

            } else //new word
            {
                pairIntegers.add(0, 1);
                pairIntegers.add(1, countWords);
            }

            allWords.put(word, pairIntegers);

        }


    }



    public void add(BaseText baseText)
    {
        listOfText.add(baseText);
        addWordsFromParagraphes(baseText);
    }

    private void addWordsFromParagraphes(BaseText baseText) {
        for (Paragraph p:baseText.getParagraphs())
        {
        addAllWordsToList(p.getText());
        }
    }

    public Texts(JSONObject obj) {
        this.listOfText = new ArrayList<BaseText>();
        allWords = new TreeMap<String,List<Integer>>();
        JSONArray listOfTexts = (JSONArray)obj.get("Texts");
        for (Object ofText : listOfTexts) {
            BaseText text = new BaseText((JSONObject)ofText);

            listOfText.add(text);
        }
    }

    public Texts() {
        this.listOfText = new ArrayList<BaseText>();
        allWords = new TreeMap<String,List<Integer>>();
    }
}
