import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public String readFromFile(String fileName){

        StringBuilder stringBuilder = new StringBuilder();
        try{
            File file = new File(fileName);
            Scanner sc = new Scanner(file, "utf-8");
            while(sc.hasNextLine()) {
                stringBuilder.append(sc.nextLine().toString().trim());
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        return stringBuilder.toString();
    }

    public String smilarity(String text1, String text2){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Искате ли да промените тежестите? -  Да/Не: ");
        String input = scanner.nextLine();

        int weight_1 =0; int weight_2=0; int weight_3=0; double weight_4=0.0;
        if (input.toLowerCase().equals("да")) {
            System.out.print("Въведете стойност за тежест 1: ");
            weight_1 = scanner.nextInt();
            System.out.print("Въведете стойност за тежест 2: ");
            weight_2 = scanner.nextInt();
            System.out.print("Въведете стойност за тежест 3: ");
            weight_3 = scanner.nextInt();
            System.out.print("Въведете стойност за тежест 4: ");
            weight_4 = scanner.nextDouble();
        }else if (input.toLowerCase().equals("не")){
             weight_1 = 11;
             weight_2 = 33;
             weight_3 = 50;
             weight_4 = 0.4;
        }

        double f1t1 = averageWordLenght(text1);
        double f1t2 = averageWordLenght(text2);
        double f2t1 = calculateTypeTokenRatio(text1);
        double f2t2 = calculateTypeTokenRatio(text2);
        double f3t1 = calculateHapaxLegomenaRatio(text1);
        double f3t2 = calculateHapaxLegomenaRatio(text2);
        double f4t1 = calculateAverageSentanceLenght(text1);
        double f4t2 = calculateAverageSentanceLenght(text2);

        DecimalFormat df = new DecimalFormat("#.###");

        double calculate = Math.abs(f1t1-f1t2)*weight_1+Math.abs(f2t1-f2t2)*weight_2+
                Math.abs(f3t1-f3t2)*weight_3+Math.abs(f4t1-f4t2)*weight_4;

        return "\nSimilarity: "+df.format(Math.abs(f1t1-f1t2)*weight_1)+" + "+
                df.format(Math.abs(f2t1-f2t2)*weight_2)+" + "+df.format(Math.abs(f3t1-f3t2)*weight_3)+" + "+
                df.format(Math.abs(f4t1-f4t2)*weight_4)+" = "+df.format(calculate);
    }
    public Double averageWordLenght(String str){
//    Средна дължина на думите - средният брой символи в дума, след strip-ване на пунктуацията.
        String removePunct = str.replaceAll("\\p{Punct}", "");
        String[] words = removePunct.split("\\s+");
        ArrayList<String> newWords = new ArrayList<>();


        for (int i = 0; i < words.length; i++) {
            newWords.add(words[i].trim());
        }
        int totalChars = (String.join("", newWords).length());

        Double result = (double)totalChars/newWords.size();
        return result;
    }

    public double calculateTypeTokenRatio(String str){
//            Тype-Token Ratio - броят на всички различни думи, използвани в текста, разделен на броя на всички думи.
//            Измерва колко повтаряща се е лексиката.
        String removePunct = str.replaceAll("\\p{Punct}", "");
        String[] words = removePunct.split("\\s+");

        Set<String> uniq1 = new HashSet<>();

        for (int i = 0; i < words.length ; i++) {
            String temp = words[i].trim();
            uniq1.add(temp);
        }
        return (double)uniq1.size()/words.length;
    }

    public double calculateHapaxLegomenaRatio(String str){
//    Hapax Legomena Ratio - броят на думите, срещащи се само по веднъж в даден текст, разделен на броя на всички думи.

        HashMap<String, Integer> wordCount = new HashMap<>();
        String removePunct = str.replaceAll("\\p{Punct}", "");
        String[] words = removePunct.split("\\s+");
        int totalWords = words.length;

        for (int i = 0; i < words.length; i++) {
            String temp = words[i].trim().toLowerCase();
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
        String[] sentence = str.split("[.!?]+");
        ArrayList<String> listSentance = new ArrayList<>();
        for (String s: sentence
             ) {
            String trimmedSencen = s.trim();
            if (!trimmedSencen.isEmpty()) {
                listSentance.add(trimmedSencen);
            }

        }

        int totalSentace=listSentance.size();
        String removePunct = str.replaceAll("\\p{Punct}", "");
        String[] wordss = removePunct.split("\\s+");

        return (double) wordss.length/ totalSentace;
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

        String detectorFile = "detector_file.txt";
        try{
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(detectorFile, true)));
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = currentTime.format(formatter);

            writer.newLine();
            writer.write(formattedTime);
            writer.newLine();
           writer.write(str);
           writer.newLine();
            writer.write(avgWordLenght);
            writer.newLine();
            writer.write(typeTokenRatio);
            writer.newLine();
            writer.write(hapaxLegomenaRatio);
            writer.newLine();
            writer.write(avrSentanceLenght);
            writer.close();
            System.out.println("\nФайлът е успешно записан.");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        System.out.println();
        System.out.println(avgWordLenght);
        System.out.println(typeTokenRatio);
        System.out.println(hapaxLegomenaRatio);
        System.out.println(avrSentanceLenght);
        System.out.println();

    }

}
