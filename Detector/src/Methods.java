import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Methods {

//    Ще използваме следните feature-и за идентифициране автора на даден текст:
//    Средна дължина на думите - средният брой символи в дума, след strip-ване на пунктуацията.
//            Тype-Token Ratio - броят на всички различни думи, използвани в текста, разделен на броя на всички думи.
//            Измерва колко повтаряща се е лексиката.
//    Hapax Legomena Ratio - броят на думите, срещащи се само по веднъж в даден текст, разделен на броя на всички думи.
//    Среден брой думи в изречение - броят на всички думи, използвани в текста, разделен на броя на изреченията.
//    Програмата приема текст, изкарва стойностите на feature-ите му, приема втори текст,
//    също изкарва стойностите на feature-ите му и след това изкарва резултат на сходство между двата текста.
//    Резултата на сходство се пресмята по формулата:
//    abs(F1T1-F1T2) * 11 + abs(F2T1-F2T2) * 33 + abs(F3T1-F3T2) * 50 + abs(F4T1-F4T2) * 0.4,
//    където F1T1 e feature 1 (средна дължина на думите) за текст 1, F1T2 е feature 1 за текст 2,
//Similarity: 3.666 + 76.989 + 23.1 + 1.4 = 105.155
    public String smilarity(String text1, String text2){
        double f1t1 = averageWordLenght(text1);
        double f1t2 = averageWordLenght(text2);
        double f2t1 = calculateTypeTokenRatio(text1);
        double f2t2 = calculateTypeTokenRatio(text2);
        double f3t1 = calculateHapaxLegomenaRatio(text1);
        double f3t2 = calculateHapaxLegomenaRatio(text2);
        double f4t1 = calculateAverageSentanceLenght(text1);
        double f4t2 = calculateAverageSentanceLenght(text2);

        double calculate = Math.abs(f1t1-f1t2)*11+Math.abs(f2t1-f2t2)*33+
                Math.abs(f3t1-f3t2)*50+Math.abs(f4t1-f4t2)*0.4;

        return "\nSimilarity: "+Math.abs(f1t1-f1t2)+" + "+Math.abs(f2t1-f2t2)+" + "+Math.abs(f3t1-f3t2)+" + "+Math.abs(f4t1-f4t2)+" = "+calculate;
    }
    public double averageWordLenght(String str){
//    Средна дължина на думите - средният брой символи в дума, след strip-ване на пунктуацията.
        String removePunct = str.replaceAll("\\p{Punct}", "");
        String[] words = removePunct.split("\\s+");

        int wordsArrLenght = words.length;
        int totalChars = 0;

        for (int i = 0; i < words.length; i++) {
            totalChars+=words[i].length();
        }
        Double result = (double)totalChars/wordsArrLenght;
        return result;
    }

    public double calculateTypeTokenRatio(String str){
//            Тype-Token Ratio - броят на всички различни думи, използвани в текста, разделен на броя на всички думи.
//            Измерва колко повтаряща се е лексиката.
        String removePunct = str.replaceAll("\\p{Punct}", "");
        String[] words = removePunct.split("\\s+");
        ArrayList<String> unigueWords = new ArrayList<>();

        for (int i = 0; i < words.length ; i++) {
            boolean check = unigueWords.contains(words[i]);
            if (!check) {
                unigueWords.add(words[i]);
            }
        }
        return (double)unigueWords.size()/words.length;
    }

    public double calculateHapaxLegomenaRatio(String str){
//    Hapax Legomena Ratio - броят на думите, срещащи се само по веднъж в даден текст, разделен на броя на всички думи.

        HashMap<String, Integer> wordCount = new HashMap<>();
        String removePunct = str.replaceAll("\\p{Punct}", "");
        String[] words = removePunct.split("\\s+");
        int totalWords = words.length;

        for (int i = 0; i < words.length; i++) {
            String temp = words[i].toLowerCase();
            int count = wordCount.getOrDefault(temp,0);
            wordCount.put(temp, count+1);
        }
        int hipaxCount =0;

        for (int count: wordCount.values()
             ) {
            if (count == 1) {
                hipaxCount++;
            }
        }
        double result =(double) hipaxCount/totalWords;

        return result;
    }
    public double calculateAverageSentanceLenght(String str){

        //    Среден брой думи в изречение - броят на всички думи, използвани в текста, разделен на броя на изреченията.

        String removePunct = str.replaceAll("\\p{Punct}", "");
        String[] sentence = str.split("[.!?]+");
        int totalSentace=sentence.length;
        int totalWords =0;

        for (int i = 0; i < sentence.length; i++) {
            String[] words = sentence[i].trim().split("\\s+");
            totalWords += words.length;
        }


        return (double) totalWords/ totalSentace;
    }

    public void printResult(String str){
//        1. Avg. word length: 3.666
//        2. Type-Token Ratio: 1
//        3. Hapax Legomena Ratio: 1
//        4. Avg. sentence length: 3
        String avgWordLenght = "\n1. Avg. word length: "+averageWordLenght(str);
        String typeTokenRatio = "2. Type-Token Ratio: "+ calculateTypeTokenRatio(str);
        String hapaxLegomenaRatio = "3. Hapax Legomena Ratio: "+calculateHapaxLegomenaRatio(str);
        String avrSentanceLenght = "4. Avg. sentence length: "+calculateAverageSentanceLenght(str);

        System.out.println();
        System.out.println(avgWordLenght);
        System.out.println(typeTokenRatio);
        System.out.println(hapaxLegomenaRatio);
        System.out.println(avrSentanceLenght);
        System.out.println();

    }

}
