package edu.guilford;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Controls class is the controller for the application. It handles the logic
 * for the application.
 * It contains the initialize method that is called when the application is
 * started.
 * The initialize method populates the list of images and selects a random image
 * to display.
 * It also sets an initial color for the ColorPicker and initializes the image
 * view with the first image.
 * The updateImageColor method gets the selected color from the ColorPicker and
 * applies it to the image using the Blend effect.
 * The updateImageView method updates the image view with the current image.
 * 
 * @Author Carlos Ramirez, Gahel Arango
 * @Version 3.0
 * 
 */

public class Controls {

    /**
     * The ColorPicker control allows the user to select a color from a predefined
     * set of colors or from a custom color palette.
     * The user can select a color by clicking on the color picker button and
     * choosing a color from the color palette.
     * The selected color is displayed in the color picker button.
     */
    @FXML
    private ColorPicker colorPicker;

    /**
     * The ImageView control is used to display an image in a JavaFX application.
     * It can be used to display an image from a file, URL, or input stream.
     */
    @FXML
    private ImageView imageView;

    /**
     * The List of images to be displayed in the application.
     */
    private final List<Image> images = new ArrayList<>();

    /**
     * The index of the current image in the list of images.
     */
    private int currentIndex = 0;

    /**
     * The initialize method is called when the application is started.
     * It populates the list of images and selects a random image to display.
     * It also sets an initial color for the ColorPicker and initializes the image
     * view with the first image.
     */
    public void initialize() {
        // Populate the list of images
        images.add(new Image(getClass().getResourceAsStream("cloud_covered.jpg")));
        images.add(new Image(getClass().getResourceAsStream("gmod_error.jpg")));
        images.add(new Image(getClass().getResourceAsStream("spider_logo_1.jpg")));
        images.add(new Image(getClass().getResourceAsStream("nfs_u2.jpg")));

        // Select a random image
        Random random = new Random();
        currentIndex = random.nextInt(images.size());

        // Set an initial color for the ColorPicker
        colorPicker.setOnAction(event -> updateImageColor());

        // Initialize the image view with the first image
        updateImageView();
    }

    /**
     * Updates the color of the image using the selected color from the ColorPicker.
     */
    private void updateImageColor() {
        // Get selected color from ColorPicker
        Color color = colorPicker.getValue();

        // Apply color to the image using Blend effect
        ColorInput colorInput = new ColorInput(0, 0, imageView.getImage().getWidth(), imageView.getImage().getHeight(),
                color);
        Blend blend = new Blend(BlendMode.MULTIPLY);
        blend.setTopInput(colorInput);
        imageView.setEffect(blend);
    }

    /**
     * Updates the image view with the current image.
     */
    private void updateImageView() {
        if (!images.isEmpty()) {
            imageView.setImage(images.get(currentIndex));
        }
    }
}
