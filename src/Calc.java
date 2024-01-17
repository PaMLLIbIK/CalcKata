import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Calc {
        private static final Map<String, Integer> ROMAN_NUMERALS = new HashMap<>();

        static {
            ROMAN_NUMERALS.put("I", 1);
            ROMAN_NUMERALS.put("II", 2);
            ROMAN_NUMERALS.put("III", 3);
            ROMAN_NUMERALS.put("IV", 4);
            ROMAN_NUMERALS.put("V", 5);
            ROMAN_NUMERALS.put("VI", 6);
            ROMAN_NUMERALS.put("VII", 7);
            ROMAN_NUMERALS.put("VIII", 8);
            ROMAN_NUMERALS.put("IX", 9);
        }

        public static void main (String[]args){
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Введите арифметическое выражение (например, 2 + 2 или II + III): ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("До свидания!");
                    break;
                }
                String result = calc(input);
                System.out.println("Результат: " + result);
            }
            scanner.close();
        }

        static String calc (String input){
            String[] elements = input.split(" ");
            String firstElem = elements[0];
            String secondElem = elements[2];
            String op = elements[1];
            int num1;
            int num2;

            if (isRomanNumber(firstElem) && isRomanNumber(secondElem)) {
                num1 = romanToArabic(firstElem);
                num2 = romanToArabic(secondElem);
            } else {
                num1 = Integer.parseInt(firstElem);
                num2 = Integer.parseInt(secondElem);
            }

            int result = 0;

            switch (op) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        return "Ошибка: деление на ноль!";
                    }
                    break;
                default:
                    return "Ошибка: неподдерживаемая операция!";
            }

            if (isRomanNumber(firstElem) && isRomanNumber(secondElem)) {
                return arabicToRoman(result);
            } else {
                return String.valueOf(result);
            }
        }

        static boolean isRomanNumber (String input){
            return input.matches("[IVX]+");
        }

        static int romanToArabic (String input){
            int result = 0;
            int prevValue = 0;
            for (int i = input.length() - 1; i >= 0; i--) {
                int currentValue = ROMAN_NUMERALS.get(String.valueOf(input.charAt(i)));
                if (currentValue < prevValue) {
                    result -= currentValue;
                } else {
                    result += currentValue;
                }
                prevValue = currentValue;
            }
            return result;
        }

        static String arabicToRoman (int number){
            if (number < 1 || number > 20) {
                return "Ошибка: невозможно конвертировать число";
            }
            String[] romanSymbols = {"X", "IX", "V", "IV", "I"};
            int[] romanValues = {10, 9, 5, 4, 1};
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < romanValues.length; i++) {
                while (number >= romanValues[i]) {
                    result.append(romanSymbols[i]);
                    number -= romanValues[i];
                }
            }
            return result.toString();
        }
    }