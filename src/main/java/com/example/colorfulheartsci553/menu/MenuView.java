package com.example.colorfulheartsci553.menu;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuView {

    public int width;
    public int height;

    public Color backgroundColor = Color.BLACK;

    public Pane pane;

    public MenuController controller;
    public MenuModel model;

    public Label infoText;


    public MenuView(int w, int h) {
        width = w;
        height = h;
    }


    public void start(Stage window) {

        pane = new Pane();

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/com/example/colorfulheartsci553/style.css").toExternalForm());

        scene.setOnKeyPressed(this::handleOnKeyPressed);
        scene.setOnKeyReleased(this::handleOnKeyReleased);

        scene.setFill(backgroundColor);

        window.setScene(scene);

    }

    public void handleOnKeyPressed(KeyEvent event) {
        controller.userInputDetected(event);
    }

    public void handleOnKeyReleased(KeyEvent event) {
        System.out.println("key Released");
    }






}