package edu.guilford;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Project5 extends Application {

   /**
     * The Scene object is used to display the contents of the application in a
     * window.
     */
    private static Scene scene;
    /**
     * The width of the scene
     */
    private int sceneWidth = 400;
    /**
     * The height of the scene
     */
    private int sceneHeight = 400;

    /**
     * The start method is called when the application is started.
     * It initializes the main container for the application and adds the greeting
     * pane and the scrabble score calculator to the container.
     * The main container is then added to the scene and the scene is set to the
     * stage.
     * The title of the stage is set and the stage is shown.
     * 
     * @param primaryStage The primary stage for the application
     * @throws IOException If an input or output exception occurs
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        VBox root = new VBox(); // Main container
        GreetingPane greetingPane = new GreetingPane();
        scrabbleScoreCalculator scoreCalculator = new scrabbleScoreCalculator(); // New Scrabble score calculator
                                                                                 // component
        PRESS press = new PRESS();
        // scrabbleScoreCalculator scoreCalculator = new scrabbleScoreCalculator(); //
        // New Scrabble score calculator component

        root.getChildren().addAll(greetingPane, scoreCalculator, press); // Add the greetingPane and the
                                                                         // scrabbleScoreCalculator to the root
                                                                         // container

        scene = new Scene(root, sceneWidth, sceneHeight);

        // Set the Scene to the Stage
        primaryStage.setScene(scene);

        // Set the title of the Stage
        primaryStage.setTitle("Project 5 JavaFx");

        // Show the Stage
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}