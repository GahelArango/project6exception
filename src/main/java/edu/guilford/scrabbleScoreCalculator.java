package edu.guilford;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * scrabbleScoreCalculator class is a subclass of VBox.
 * It contains the TextField, Button, and Label controls for the scrabble score
 * calculator.
 * The TextField control allows the user to enter a word.
 * The Button control allows the user to calculate the scrabble score for the
 * word.
 * The Label control displays the scrabble score for the word.
 */
public class scrabbleScoreCalculator extends VBox {
    /**
     * The Label control displays the scrabble score for the word
     */
    private Label resultLabel;

    /**
     * The scrabbleScoreCalculator constructor initializes the TextField, Button,
     * and Label controls for the scrabble score calculator
     * It sets the margins for the controls and adds them to the VBox
     * The constructor also sets an action for the Button to calculate the scrabble
     * score for the word
     * and display it in the Label
     */
    private void validateWord(String word) throws InvalidInputException {
        if (word.isEmpty()) {
            throw new InvalidInputException ("Word cannot be empty. Please enter a word.");
        }
        if (!word.matches("[a-zA-Z]+")) {
            throw new InvalidInputException("Word can only contain alphabetic characters.");
        }
    }
    public scrabbleScoreCalculator() {
        super();

        // Create UI components
        TextField wordInput = new TextField();
        Button calculateButton = new Button("Calculate Score");
        resultLabel = new Label();

        // Set margins for UI components
        VBox.setMargin(wordInput, new Insets(10));
        VBox.setMargin(calculateButton, new Insets(10));
        VBox.setMargin(resultLabel, new Insets(10));

        // Calculate the score when the button is clicked
        calculateButton.setOnAction(e -> {
            try {
                String word = wordInput.getText();
                validateWord(word);
                int score = calculateScore(word);
                resultLabel.setText("Score for '" + word + "': " + score);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                resultLabel.setText(e1.getMessage());

            }
        });

        // Add UI components to the VBox
        getChildren().addAll(wordInput, calculateButton, resultLabel);
    }

    /**
     * The calculateScore method calculates the scrabble score for the word
     * Convert the word to uppercase and calculate the score for each letter
     * 
     * @param word The word to calculate the score for
     * @return The scrabble score for the word
     */
    private int calculateScore(String word) {
        ScrabbleSet scrabbleSet = new ScrabbleSet();
        return scrabbleSet.getWordValue(word.toUpperCase());
    }

}
