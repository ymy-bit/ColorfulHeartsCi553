package com.example.colorfulheartsci553.game;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class View {

    public int width;
    public int height;

    public Pane pane;
    public Canvas canvas;

    public Controller controller;
    public Model model;

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


        window.setScene(scene);
        window.show();
    }
}
