package task2;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by anton on 19.10.17.
 */
public class ResBundle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose language(Выберите язык):\n1 - English\n2 - Русский");
        ResourceBundle questAnswBundle = sc.nextInt() == 1
                ? ResourceBundle.getBundle("AnsweresAndQuestions", Locale.ENGLISH)
                : ResourceBundle.getBundle("AnsweresAndQuestions");

        questAnswBundle.keySet().forEach
                (x -> { if (x.contains("q"))
                    System.out.println(questAnswBundle.getString(x));
                });

        System.out.println(questAnswBundle.getString("a" + sc.nextInt()));
    }
}
