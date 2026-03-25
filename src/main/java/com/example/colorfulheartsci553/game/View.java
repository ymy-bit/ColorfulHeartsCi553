package com.example.colorfulheartsci553.game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class View  {

    public int width;
    public int height;

    public Color backgroundColor = Color.BLACK;

    public Pane pane;
    public Canvas canvas;

    public Controller controller;
    public Model model;


    public GameObject heart;

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

        scene.setOnKeyPressed(this::handleOnKeyPressed);
        scene.setOnKeyReleased(this::handleOnKeyReleased);

        window.setScene(scene);
        window.show();
    }

    public void handleOnKeyPressed(KeyEvent event){
        controller.userInputDetected(event);
    }
    public void handleOnKeyReleased(KeyEvent event){
        controller.userInputReleased(event);
    }

    public void drawScreen(){
        synchronized (model) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(backgroundColor);
            gc.fillRect(0, 0, width, height);

            //place the following line in a function that would take all the game objects from model and puts them in view to draw
            heart = model.heart;
            drawGameObject(gc, heart);
        }
    }

    public void drawGameObject(GraphicsContext gc, GameObject go){
        gc.drawImage(go.image, go.topX, go.topY, go.width, go.height);
    }

}
