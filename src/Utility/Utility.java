package Utility;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utility {

    private static final String capLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lowLetters = "abcdefghijklmnopqrstuvwxyz";
    private static final String dig = "0123456789";
    private static final String letters_digs = capLetters + lowLetters + dig;
    static Random random = new Random();

    //this code is for spaces which we are going to add first and last
    public static String spacesUse(int count) {
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> " ")
                .collect(java.util.stream.Collectors.joining());
    }

    public static String createAlphaNumeric() {
        // we need spaces for start and end
        int startSpace = random.nextInt(11);
        int endSpace = random.nextInt(11);

        // create alphanumeric characters
        int length = random.nextInt(21) + 5;
        String core = IntStream.range(0, length)
                .mapToObj(i -> String.valueOf(letters_digs.charAt(random.nextInt(letters_digs.length()))))
                .collect(Collectors.joining());
        String result = spacesUse(startSpace) + core + spacesUse(endSpace);
//        System.out.println("Result createAlphaNumeric:--->>" + result);
        return result;
    }

    public static String createAlphabetString() {

        int length = random.nextInt(15) + 5;
        //create the alphabet string
        String result = IntStream.range(0, length)
                .mapToObj(i -> (char) (random.nextBoolean() ? random.nextInt(26) + 'A' : random.nextInt(26) + 'a'))
                .map(String::valueOf)
                .collect(Collectors.joining());
//        System.out.println("Result createAlphabetString:--->>" + result);
        return result;
    }

    public static String createRealNumbers() {
        //for 3 decimal numbers
        double number = random.nextDouble() * 1000;
        return String.format("%.3f", number);
    }

    public static String createInteger() {
        return Integer.toString(random.nextInt(1000000));
    }

    public static String createRandomData() {
        List<Supplier<String>> dataCreator = Arrays.asList(
                Utility::createAlphabetString,
                Utility::createRealNumbers,
                Utility::createInteger,
                Utility::createAlphaNumeric
        );
//        System.out.println("Data :--->>> " + dataCreator);
        int type = random.nextInt(dataCreator.size());
        return dataCreator.get(type).get();
    }

    public static boolean checkAlphabet(String text) {
        return !text.isEmpty() && text.matches("[a-zA-Z]+");
    }

    public static boolean checkInteger(String text) {
        return text.matches("\\d+");
    }

    public static boolean checkRealNumber(String text) {
        return text.matches("\\d+\\.\\d+");
    }

    public static boolean checkAlphanumeric(String text) {
        return !text.isEmpty() && text.matches("[a-zA-Z0-9]+");
    }

}
