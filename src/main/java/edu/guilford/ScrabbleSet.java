package edu.guilford;

import java.util.Arrays;
import java.util.Random;

/**
 * The ScrabbleSet class contains the tiles and their point values for the game
 * of Scrabble.
 * It has a constructor that initializes the tiles and their point values.
 * It also has a method to calculate the point value of a word.
 */
public class ScrabbleSet {
    /**
     * The array of letters in the Scrabble set
     */
    private String[] letters;

    /**
     * The array of letter counts in the Scrabble set
     */
    private int[] letterCount;

    /**
     * The array of point values for each letter in the Scrabble set
     */
    private int[] letterValues; // Array to store the point values for each letter

    /**
     * The Random object to generate random values
     */
    private Random randomValue;

    /**
     * The ScrabbleSet constructor initializes the letters, letter counts, and point
     * values for the Scrabble set.
     * It sets the letters, letter counts, and point values for the English version
     * of Scrabble.
     * 
     * @param isEnglish A boolean value to indicate whether the English version of
     *                  Scrabble is used
     */
    public ScrabbleSet(boolean isEnglish) {
        if (isEnglish) {
            letters = new String[] { "A", "A", "A", "A", "A", "A", "A", "A", "A", "B", "B", "C", "C", "D", "D", "D",
                    "D", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "F", "F", "G", "G", "G", "H", "H",
                    "I", "I", "I", "I", "I", "I", "I", "I", "I", "J", "K", "L", "L", "L", "L", "M", "M", "N", "N", "N",
                    "N", "N", "N", "O", "O", "O", "O", "O", "O", "O", "O", "P", "P", "Q", "R", "R", "R", "R", "R", "R",
                    "S", "S", "S", "S", "T", "T", "T", "T", "T", "T", "U", "U", "U", "U", "V", "V", "W", "W", "X", "Y",
                    "Y", "Z", " ", "" };
            letterCount = new int[] { 9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1,
                    2 };
            letterValues = new int[] { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10,
                    0 };
        } else {
            System.out.println("Error: Only English is supported at this time");
        }
    }

    // this is the Second constructor
    /**
     * Second constructor initializes the letters, letter counts, and point values
     * for the Scrabble set.
     */
    public ScrabbleSet() {
        letters = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                "R", "S", "T", "U", "V", "W", "X", "Y", "Z", " " };
        letterCount = new int[27]; // letters and blank
        randomValue = new Random();

        int totalTiles = 100;
        // Assign random counts to each letter
        for (int indexofLetters = 0; indexofLetters < letters.length; indexofLetters++) {
            int maxCount = 9; // You can adjust the maximum count as needed
            int randomCount = randomValue.nextInt(maxCount) + 1;
            letterCount[indexofLetters] = randomCount;
            totalTiles -= randomCount;
        }
        // Handle the blank tile separately
        if (totalTiles < 0) {
            for (int remove = 0; remove < Math.abs(totalTiles); remove++) {
                // Reduce the count of a random letter to adjust the total tiles
                int randomIndex = randomValue.nextInt(letters.length);
                while (letterCount[randomIndex] <= 1) {
                    // Make sure not to reduce the count below 1
                    randomIndex = randomValue.nextInt(letters.length);
                }
                letterCount[randomIndex]--;
            }
        }

        // Initialize letterValues array with random values
        letterValues = new int[] { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10, 0 };

    }

    // Add a method to get the point value for a given letter
    /**
     * The getLetterValue method returns the point value for a given letter
     * 
     * @param word The letter to get the point value for
     * @return The point value for the letter
     */
    public int getWordValue(String word) {
        int total = 0;
        // A copy of the letter counts array to avoid modifying the original lettercount
        // set.
        int[] availableLetterCount = Arrays.copyOf(letterCount, letterCount.length - 1);

        for (int charIndex = 0; charIndex < word.length(); charIndex++) {
            char letter = Character.toUpperCase(word.charAt(charIndex));
            if (letter >= 'A' && letter <= 'Z') {

                int index = letter - 'A';

                if (index >= 0 && index < availableLetterCount.length && availableLetterCount[index] > 0) {
                    total += letterValues[index];
                    availableLetterCount[index]--; // Reduce the count of the used letter
                } else {
                    // Not enough letters of the current kind to spell the word
                    return 0;
                }
            } else {
                // Handle invalid letter in the word
                return 0;
            }
        }

        // Return the total point value of the word
        return total;
    }

    /**
     * The toString method returns a string representation of the ScrabbleSet object
     * 
     * @return A string representation of the ScrabbleSet object
     */
    @Override
    public String toString() {
        return "ScrabbleSet {\n" +
                "Letters: " + Arrays.toString(letters) + "\n" +
                "Letter Count: " + Arrays.toString(letterCount) + "\n" +
                "Letter Values: " + Arrays.toString(letterValues) + "\n" +
                "}\n";
    }

}