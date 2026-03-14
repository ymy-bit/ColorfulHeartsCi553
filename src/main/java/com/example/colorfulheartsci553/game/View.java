package com.example.colorfulheartsci553.game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;



public class View  {

    public int width;
    public int height;

    public Pane pane;
    public Canvas canvas;

    public Controller controller;
    public Model model;

    public Circle circle;

    int circleMoveSpeed = 5;

    public View(int w, int h){
        width = w;
        height = h;
    }



    public void start(Stage window)
    {
        pane = new Pane();

        canvas = new Canvas(width, height);
        pane.getChildren().add(canvas);

        Scene scene = new Scene(pane);

        circle = new Circle((double) width/2, (double) height /2, 20, Color.RED);

        pane.getChildren().add(circle);



        scene.setOnKeyPressed(this::handleOnKeyPressed);
        scene.setOnKeyReleased(this::handleOnKeyReleased);

        window.setScene(scene);
        window.show();
    }

    public void handleOnKeyPressed(KeyEvent event){
        circle.setCenterX(circle.getCenterX() + circleMoveSpeed);
    }
    public void handleOnKeyReleased(KeyEvent event){
        System.out.println(event.getCode());
    }

}
