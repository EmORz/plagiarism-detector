import java.util.Scanner;

public class Main {
//    Пример:
//            >> Enter first text:
//            << this is the
//    first sentence. Isn't it? Yes ! !! This
//            ,
//    last bit :) is also a sentence, but
//    without a terminator other than the end of the file
//>> 	1. Avg. word length: 4
//            2. Type-Token Ratio: 3.333
//            3. Hapax Legomena Ratio: 0.538
//            4. Avg. sentence length: 6.5
//            >> Enter second text:
//            << Hello! This is just a simple text. Carry on!
//            >>	1. Avg. word length: 3.666
//            2. Type-Token Ratio: 1
//            3. Hapax Legomena Ratio: 1
//            4. Avg. sentence length: 3
//            >> Similarity: 3.666 + 76.989 + 23.1 + 1.4 = 105.155


    public static void main(String[] args) {
        Methods methods = new Methods();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Изберете дали ще въведе текст или ще четете от файл? 1 или 2: ");
        String choose = scanner.nextLine();

        if (choose.equals("1")) {
            System.out.print("Enter text 1: ");
            String text1 = "Hello! This is just a simple text. Carry on!";
            System.out.print(text1);
            methods.printResult(text1);
            System.out.print("Enter text 2: ");
            String text2 = "this is the\n" +
                    "first sentence. Isn't it? Yes ! !! This \n" +
                    ",\n" +
                    "last bit :) is also a sentence, but \n" +
                    "without a terminator other than the end of the file\n";
            System.out.print(text2);
            methods.printResult(text2);
            System.out.println(methods.smilarity(text1, text2));

        } else if (choose.equals("2")) {
            String text1 = methods.readFromFile("D:\\Java_Projects\\plagiarism-detector\\Detector\\src\\text_1.txt");
            System.out.print(text1);
            methods.printResult(text1);
            String text2 = methods.readFromFile("D:\\Java_Projects\\plagiarism-detector\\Detector\\src\\text_2.txt");
            System.out.print(text2);
            methods.printResult(text2);
            System.out.println(methods.smilarity(text1, text2));
        }




    }
}