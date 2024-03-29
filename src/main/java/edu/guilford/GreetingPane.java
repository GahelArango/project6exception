package edu.guilford;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class GreetingPane extends VBox {


    private TextField nameField;
    private TextField ageField;
    private TextField emailField;
    private Label messageLabel;


    private enum InputState {
        NAME, AGE, EMAIL, DONE
    }


    private InputState currentState = InputState.NAME;


    public GreetingPane() {
        super();
        nameField = new TextField();
        nameField.setPromptText("Enter your name");
        nameField.setMaxWidth(215);
        nameField.setFont(Font.font("Arial", 16));


        ageField = new TextField();
        ageField.setPromptText("Enter your age");
        ageField.setMaxWidth(215);
        ageField.setFont(Font.font("Arial", 16));


        emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setMaxWidth(215);
        emailField.setFont(Font.font("Arial", 16));


        messageLabel = new Label("Please enter your name:");
        messageLabel.setFont(Font.font("Arial", 16));


        setAlignment(Pos.CENTER);
        setSpacing(10);


        getChildren().addAll(nameField, messageLabel);


        nameField.setOnAction(e -> validateName());
    }


    private void validateName() {
        String name = nameField.getText();
        try {
            // Check if name contains any numbers
            if (containsNumbers(name)) {
                throw new IllegalArgumentException("Name cannot contain numbers");
            }
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            currentState = InputState.AGE;
            messageLabel.setText("Please enter your age:");
            getChildren().clear();
            getChildren().addAll(ageField, messageLabel);
            ageField.setOnAction(e -> validateAge());
        } catch (IllegalArgumentException ex) {
            showMessage(ex.getMessage());
        }
    }


    private boolean containsNumbers(String input) {
        // Check if input contains any numbers
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }


    private void validateAge() {
        String ageText = ageField.getText();
        try {
            int age = Integer.parseInt(ageText);
            if (age <= 0) {
                throw new IllegalArgumentException("Age must be a positive number");
            }
            currentState = InputState.EMAIL;
            messageLabel.setText("Please enter your email:");
            getChildren().clear();
            getChildren().addAll(emailField, messageLabel);
            emailField.setOnAction(e -> validateEmail());
        } catch (NumberFormatException ex) {
            showMessage("Please enter a valid age (numbers only).");
        } catch (IllegalArgumentException ex) {
            showMessage(ex.getMessage());
        }
    }


    private void validateEmail() {
        String email = emailField.getText();
        try {
            if (email.isEmpty() || !email.contains("@")) {
                throw new IllegalArgumentException("Please enter a valid email address");
            }
            currentState = InputState.DONE;
            messageLabel.setText("Hello, " + nameField.getText() + "! Your age is: " + ageField.getText()
                    + ". Your email is: " + emailField.getText());
            nameField.setDisable(true);
            ageField.setDisable(true);
            emailField.setDisable(true);
        } catch (IllegalArgumentException ex) {
            showMessage(ex.getMessage());
        }
    }


    private void showMessage(String message) {
        messageLabel.setText(message);
    }
}
