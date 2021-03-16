package unpackingString;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unpack {


    private static int count=0;
    private static int count2=0;
    private static int counterBracket1=0;
    private static int counterBracket2=0;
    private static String finalText = "";
    private static final List<String> list = new ArrayList<>();


        public static void unpack(String text) {

            String validity = text.replaceAll("[A-Za-z0-9\\[\\]]","");
            String[] strArr = {"\\[", "\\]"};
            for (int i = 0; i < strArr.length; i++) {
                Pattern p = Pattern.compile(strArr[i]);
                Matcher m = p.matcher(text);
                switch (i%2){
                    case 0: while (m.find()){ counterBracket1++; }
                    case 1: while (m.find()){ counterBracket2++; }
                }}
                if (counterBracket1!=counterBracket2){
                    System.out.println("Invalid bracket");
                    counterBracket1 = 0;counterBracket2 = 0;
                    return;
                }

            if (validity.length()>0){
                System.out.println(validity +" Invalid char");
                return;
            }
            Pattern pattern1 = Pattern.compile("а-я");
            Matcher matcher1 = pattern1.matcher(text);
            if (matcher1.find()) {

            }
            //сборка финального текста
            if (text.equals("")){
                for (String s : list) {
                    //если одно повторение содержит другое.
                    if (s.split("\\[").length > 2) {

                        String[] numSplit = s.split("\\[");
                        int a = Integer.parseInt(numSplit[0]);
                        int b = Integer.parseInt(numSplit[1]);
                        String[] wordSplit = numSplit[numSplit.length-1].split("\\]");
                        for (int i = 0; i < a; i++) {
                            for (int j = 0; j < b; j++) {
                                finalText = finalText+wordSplit[0];
                            }
                            finalText = finalText + wordSplit[1];
                        }
                    }
                    else{
                        String text1 = s.replaceAll("\\]", " ");
                    if (text1.endsWith(" ")) {
                        String[] splitText = text1.split("\\[");
                        int a = Integer.parseInt(splitText[0]);

                        for (int j = 0; j < a; j++) {
                            finalText = finalText + splitText[1].trim();
                        }
                    }else finalText = finalText + s;
                }}
                System.out.println(finalText + " final text");
                return;
            }

            String[] splitText = text.split("");

            try {
                int x = Integer.parseInt(splitText[0]);
            }
            catch (NumberFormatException e) {
                count++;
            }
            //проверка если одно повторение содержит другое.
            if (splitText.length>=3 & count==0){
            try {
                int y = Integer.parseInt(splitText[2]);
            }
            catch (NumberFormatException e) {
                count2++;
            }}

            //если одно повторение содержит другое.
            if (count == 0 & count2 == 0) {
                Pattern pattern = Pattern.compile("\\d.+?\\].+?\\]");
                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    list.add(text.substring(matcher.start(), matcher.end()));
                   // System.out.println(list);
                    text = text.replaceFirst("\\d.+?\\].+?\\]","");
                    count=0;count2=0;
                }
            }
            if (count == 0 & count2!=0) {
                Pattern pattern = Pattern.compile("\\d.+?\\]");
                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    list.add(text.substring(matcher.start(), matcher.end()));
                    //System.out.println(list);
                    text = text.replaceFirst("\\d.+?\\]","");
                    count=0;
                }
            }
            if (count != 0) {
                Pattern pattern = Pattern.compile("\\D+\\D");
                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    list.add(text.substring(matcher.start(), matcher.end()));
                    //System.out.println(list);
                    text = text.replaceFirst("\\D+\\D","");
                    count=0;
                }
            }
            unpack(text);
        }

    public static void main(String[] args) {

     // String text = "3[xy]xx2[xx]2[xy]";
        String text = "2[3[x]y]xy2[2[yy]x]";

        System.out.println(text + " Original text");

        unpack(text);
    }
}


