package advent.adventDayOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CoordinateReader {

    public int coordinatesSum(String fileName) {
        int sum = 0;
        try {
            File coordinates = new File(fileName);
            Scanner scanner = new Scanner(coordinates);
            while (scanner.hasNextLine()) {
                String coord = scanner.nextLine();
                sum += lineSum(coord);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
                System.out.println("Could not find file " + fileName);
        }

        return sum;
    }

    public int lineSum(String line) {
        if (line == null || line.length() == 0) return 0;
        int left = -1;
        int right = -1;
        for (int i = 0; i < line.length(); i++) {
            int digit = isDigit(line, i);
            if (digit > 0 && left < 0) {
                left = digit;
                break;
            }
        }
        for (int i = line.length() - 1; i >= 0; i--) {
            int digit = isDigit(line, i);
            if (digit > 0 && right < 0) {
                right = digit;
                break;
            }
        }
        left = Math.max(left, 0);
        right = Math.max(right, 0);
        return (left * 10) + right;
    }

    private int isDigit(String s, int index) {
        String[] digits = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int[] digitVals = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        if (s == null || index >= s.length() || index < 0) return -1;
        if (Character.isDigit(s.charAt(index))) return s.charAt(index) - '0';
        for (int i = 0; i < digits.length; i++) {
            if (s.substring(index).startsWith(digits[i])) {
                return digitVals[i];
            }
        }
        return -1;
    }
}
