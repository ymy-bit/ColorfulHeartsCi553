package com.example.colorfulheartsci553.game;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class GameView {

    private Scene scene;

    public int width;
    public int height;

    public Color backgroundColor = Color.BLACK;
    public Color gameOverlayColor = new Color(0, 0, 0, 0.3);

    //public Pane pane;
    public StackPane pane;
    public Canvas canvas;

    public GameController controller;
    public GameModel model;

    public Label infoText;
    public Label quitText;
    public Label gameOverText;
    public Label pausedText;
    public Label pauseText;
    public Label unpauseText;
    public Label retryText;
    public Label saveFailedText;

    public GameObject heart;
    ArrayList<GameObject> gameObjects = new ArrayList<>();
    public int score = 0;


    public GameView(int w, int h){
        width = w;
        height = h;
    }



    public void start()
    {
        pane = new StackPane();

        canvas = new Canvas(width, height);
        pane.getChildren().add(canvas);

        infoText = new Label("SCORE: " + score);
        pane.getChildren().add(infoText);

        quitText = new Label("Press Q to return to menu");
        StackPane.setAlignment(quitText, Pos.BOTTOM_CENTER);
        pane.getChildren().add(quitText);

        setExtraText();

        scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/com/example/colorfulheartsci553/style.css").toExternalForm());
        scene.setFill(backgroundColor);

        scene.setOnKeyPressed(this::handleOnKeyPressed);
        scene.setOnKeyReleased(this::handleOnKeyReleased);

    }

    public Scene getScene(){
        return scene;
    }

    private void setExtraText(){
        gameOverText = new Label("GAME OVER");
        gameOverText.setId("bigText");
        gameOverText.setAlignment(Pos.CENTER);
        gameOverText.setTranslateY(-50);

        retryText = new Label("Press ENTER to retry");

        retryText.setAlignment(Pos.CENTER);
        retryText.setTranslateY(+50);

        pausedText = new Label("PAUSED");
        pausedText.setId("bigText");
        StackPane.setAlignment(pausedText, Pos.CENTER);
        pausedText.setTranslateY(-50);

        unpauseText = new Label("Press P to unpause");
        StackPane.setAlignment(unpauseText, Pos.CENTER);
        unpauseText.setTranslateY(+50);

        pauseText = new Label("Press P to pause");
        StackPane.setAlignment(pauseText, Pos.TOP_RIGHT);

        saveFailedText = new Label("Failed to save your score.");
        saveFailedText.setId("saveFailedText");
        StackPane.setAlignment(saveFailedText, Pos.CENTER);


        pane.getChildren().addAll(gameOverText,retryText,pausedText,unpauseText, pauseText);

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

    public void gameRunning(){
        StackPane.setAlignment(infoText, Pos.TOP_LEFT);
        pauseText.setVisible(true);
        gameOverText.setVisible(false);
        retryText.setVisible(false);
        pausedText.setVisible(false);
        unpauseText.setVisible(false);
    }

    public void gameOver(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(gameOverlayColor);
        gc.fillRect(0, 0, width, height);

        StackPane.setAlignment(infoText, Pos.CENTER);

        pauseText.setVisible(false);
        gameOverText.setVisible(true);
        retryText.setVisible(true);
    }

    public void gamePaused(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(gameOverlayColor);
        gc.fillRect(0, 0, width, height);

        StackPane.setAlignment(infoText, Pos.CENTER);
        pauseText.setVisible(false);
        pausedText.setVisible(true);
        unpauseText.setVisible(true);
    }

    public void onSaveFailed(){
        saveFailedText.setVisible(true);
    }

}
