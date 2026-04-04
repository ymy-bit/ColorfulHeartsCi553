package com.example.colorfulheartsci553.game;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class View  {

    public int width;
    public int height;

    public Color backgroundColor = Color.BLACK;

    public Pane pane;
    public Canvas canvas;

    public Controller controller;
    public Model model;

    public Label infoText;

    public GameObject heart;
    ArrayList<GameObject> gameObjects = new ArrayList<>();
    public int score = 0;


    public View(int w, int h){
        width = w;
        height = h;
    }



    public void start(Stage window)
    {
        window.setResizable(false);
        pane = new Pane();

        canvas = new Canvas(width, height);
        pane.getChildren().add(canvas);

        infoText = new Label("SCORE: " + score);
        pane.getChildren().add(infoText);

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/com/example/colorfulheartsci553/style.css").toExternalForm());

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

            retrieveObjects();
            drawGameObject(gc, heart);
            for(GameObject gameObject : gameObjects){
                drawGameObject(gc, gameObject);
            }
            infoText.setText("Score: " + score);
        }
    }

    public void drawGameObject(GraphicsContext gc, GameObject go){

        gc.drawImage(go.sprite, go.topX, go.topY, go.sprite.getWidth(), go.sprite.getHeight());
    }

    public void retrieveObjects(){
        heart = model.heart;
        gameObjects = model.gameObjects;
        score = model.score;
    }

}
