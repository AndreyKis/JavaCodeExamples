package com.Controller.TonalityAnalyzingController;

import javafx.scene.paint.Color;

/**
 * Created by yaserg555 on 11/1/2015.
 */
public class TonalityData {
    public enum TonClassIs {


        NEGATIVE(0),
        POSITIVE(1),
        NEUTRAL(2),
        NOT_CLASSIFIED(3);
        private final int value;

        TonClassIs(final int newValue) {
            value = newValue;
        }

        public int toInt() {
            return value;
        }

        private boolean isClassified() {
            return value <= 2;
        }
    }

    public String getTonalityS() {
        return toString(getTonality());
    }

    public Color getColorOfText() {
        Color tmp = Color.GRAY;
        if (getTonality() == TonClassIs.POSITIVE.toInt()) {
            tmp = Color.GREEN;
        } else if (getTonality() == TonClassIs.NEGATIVE.toInt()) {
            tmp = Color.RED;
        }
        return tmp;
    }

    public Color getRandomColorOfText(int trend, int code) {
        int trendDeviation = 2;// - степень влияния тренда (от 1 и ставить не больше 5)
        //Random ran = new Random();
        //int a = ran.nextInt(Integer.MAX_VALUE) % 100;
        int a = code;
        Color tmp;
        int expectedValueForNeutral = 60;
        if (trend < 4) {
            expectedValueForNeutral += trend * trendDeviation - trendDeviation * 2;
        }
        int deviation = a % (expectedValueForNeutral / 3);
        int borderNeutralNegative = expectedValueForNeutral - expectedValueForNeutral / 6 + deviation;
        int borderNegativePositive = (100 + borderNeutralNegative) / 2;
        if (trend == 4) {
            borderNegativePositive -= trendDeviation;
        }
        if (trend == 5) {
            borderNegativePositive += trendDeviation;
        }
        if (a < borderNeutralNegative)
            tmp = Color.GRAY;
        else if (a < borderNegativePositive)
            tmp = Color.RED;
        else
            tmp = Color.GREEN;
        //-----------------
        System.out.println(trend + " " + code + " " + borderNeutralNegative + " " + borderNegativePositive);
        if (getTonality() == 1) {
            tmp = Color.GREEN;
        } else if (getTonality() == 0) {
            tmp = Color.RED;
        }

        return tmp;
    }

    private Integer getTonality() {
        if (isPositiveExpert.isClassified()) return isPositiveExpert.toInt();
        if (isPositiveSVM == isPositiveKeyWords && isPositiveSVM.isClassified()) return isPositiveKeyWords.toInt();
        if (isPositiveSVM.isClassified()) {
            return isPositiveSVM.toInt();
        }
        if (isPositiveKeyWords.isClassified()) {
            return isPositiveKeyWords.toInt();
        } else {
            if (isPositiveSVM.isClassified()) {
                return isPositiveSVM.toInt();
            }
        }
        //else SVM=[0,1] KW=[0,1] and swm!=KW
        //SWM PREFERRABLE
        //TODO add logic
        return TonClassIs.NOT_CLASSIFIED.toInt();
    }


    public String getExpert() {
        return toString(isPositiveExpert.value);
    }

    //    public String toString(Integer isPositive)
//    {
//        return tonClassIs.values()[isPositive].name().toLowerCase();
//               /* (isPositive == tonClassIs.POSITIVE.toInt()) ? tonClassIs.POSITIVE.name().toLowerCase() :
//                ((isPositive == tonClassIs.NEGATIVE.toInt()) ? tonClassIs.NEGATIVE.name().toLowerCase() :
//                        ((isPositive ==  tonClassIs.NOT_CLASSIFIED.toInt()) ? "don`t classified" : "error"));
//  */  }
    public static String toString(Integer isPositive) {
        return TonClassIs.values()[isPositive].name().toLowerCase();
               /* (isPositive == tonClassIs.POSITIVE.toInt()) ? tonClassIs.POSITIVE.name().toLowerCase() :
                ((isPositive == tonClassIs.NEGATIVE.toInt()) ? tonClassIs.NEGATIVE.name().toLowerCase() :
                        ((isPositive ==  tonClassIs.NOT_CLASSIFIED.toInt()) ? "don`t classified" : "error"));
  */
    }
//    private Integer toInteger(String s)
//    {
//
//        return TonClassIs.valueOf(s.toUpperCase()).toInt();
//            /*    (s.equals(TonClassIs.POSITIVE.name().toLowerCase())) ? TonClassIs.POSITIVE.toInt() :
//                (s.equals(TonClassIs.POSITIVE.name().toLowerCase())) ? TonClassIs.NEGATIVE.toInt() :
//                        (s.equals("don`t classified")) ? TonClassIs.DONTCLASSIFIED.toInt() : 3;
//  */  }

    public TonalityData(String s) {
        this.isPositiveSVM = TonClassIs.valueOf(s.toUpperCase());
        this.isPositiveExpert = TonClassIs.NOT_CLASSIFIED;
        this.isPositiveKeyWords = TonClassIs.NOT_CLASSIFIED;
    }

    public String toString() {
        int isPositive = getTonality();
        //return (isPositive == 1) ? "positive" : ((isPositive == 0) ? "negative" : ((isPositive == -1) ? "don`t classified" : "error"));
        return TonClassIs.values()[isPositive].name().toLowerCase();
    }


    public void setExpertOpinionPositive() {
        this.isPositiveExpert = TonClassIs.POSITIVE;
    }

    public void setExpertOpinionNegative() {
        this.isPositiveExpert = TonClassIs.NEGATIVE;
    }

    public Integer classificationUsingCombineMethod(String text) {
        //some code has been here

        return isPositiveSVM.toInt();
    }

    public Integer classificationUsingCombineMethodWord(String text, String object) {
        String sentiment = "";//Not working, because I removed the file SentimentMashape.getTonality(text, object);
        if (sentiment.equals(TonClassIs.POSITIVE.name()))
            setSVMPositive();
        else if (sentiment.equals(TonClassIs.NEGATIVE.name())) {
            setSVMNegative();
        } else System.out.println(TonClassIs.NEUTRAL.name());
        return isPositiveSVM.toInt();
    }

    public Integer getSVM() {
        return isPositiveSVM.toInt();
    }

    public void setSVMPositive() {
        this.isPositiveSVM = TonClassIs.POSITIVE;
    }

    public void setSVMNegative() {
        this.isPositiveSVM = TonClassIs.NEGATIVE;
    }

    public Integer getKeyWords() {
        return isPositiveKeyWords.toInt();
    }

    public void setKeyWordsNegative() {
        this.isPositiveKeyWords = TonClassIs.NEGATIVE;
    }

    public void setKeyWordsPositive() {
        this.isPositiveKeyWords = TonClassIs.POSITIVE;
    }

    private TonClassIs isPositiveExpert;
    private TonClassIs isPositiveSVM;
    private TonClassIs isPositiveKeyWords;

    public TonalityData() {
        /**
         * isPostive = 1 text - positive
         * isPositive  = 0 text - negative
         * isPositive = -1 text hasn`t beed classified yet
         */
        SetDontClassified();
    }

    public void SetDontClassified() {
        this.isPositiveExpert = TonClassIs.NOT_CLASSIFIED;
        this.isPositiveSVM = TonClassIs.NOT_CLASSIFIED;
        this.isPositiveKeyWords = TonClassIs.NOT_CLASSIFIED;

    }
}
