package com.Model.CurrentProjectData;

import com.Model.ParseText.Texts;

/**
 * Created by Peter on 28-Mar-16.
 */
public class TextData {
    public static Texts textsObject = new Texts();

    public static Texts getTextsObject() {
        return TextData.textsObject;
    }
}
