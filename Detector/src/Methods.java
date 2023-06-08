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

    public double averageWordLenght(String str){

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

    public int calculateTypeTokenRatio(String str){


        return 0;
    }

    public int calculateHapaxLegomenaRatio(String str){


        return 0;
    }
    public int calculateAverageSentanceLenght(String str){


        return 0;
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
        System.out.println(avgWordLenght);
        System.out.println(typeTokenRatio);
        System.out.println(hapaxLegomenaRatio);
        System.out.println(avrSentanceLenght);

    }

}
