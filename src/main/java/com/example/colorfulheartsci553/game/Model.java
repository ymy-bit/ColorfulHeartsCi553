package com.example.colorfulheartsci553.game;

import javafx.application.Platform;
import javafx.scene.image.Image;

import java.util.HashSet;
import java.util.Set;

public class Model {

    View view;
    Controller controller;

    //width and height of window
    int width;
    int height;

    //checks the state of the game, might change for an enum for other states such as paused or others
    public boolean gameRunning;

    //set holding player inputs for better player controls
    Set<String> playerInput = new HashSet<String>();

    //game objects
    public GameObject heart;

    //player stats
    int speed = 3   ;

    public Model(int w, int h){
        this.width = w;
        this.height = h;
    }

    public void initialize(){
        gameRunning = true;
        // create game object, change later to accomidate different soul types
        Image playerImage = new Image(getClass().getResource("/com/example/colorfulheartsci553/images/Soul.png").toExternalForm());
        heart = new GameObject(width/2, height/2, 30, 30, playerImage);

        Thread thread = new Thread(this::gameLoop);
        thread.setDaemon(true);
        thread.start();
    }


    public void gameLoop(){
        while(gameRunning){
            try {
                playerMovement();
                updateView();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Game Interrupted");
            }
        }

        System.out.println("Game stopped");
    }

    public synchronized void playerMovement(){
        if (playerInput.contains("UP") && (heart.topY > 0)){
            heart.topY -= speed;
        }
        if (playerInput.contains("DOWN") && (heart.topY < height - heart.height)){
            heart.topY += speed;
        }
        if (playerInput.contains("LEFT") && (heart.topX > 0)){
            heart.topX -= speed;
        }
        if (playerInput.contains("RIGHT") && (heart.topX < width-heart.width)){
            heart.topX += speed;
        }
    }

    public synchronized void updateView(){
        Platform.runLater(view::drawScreen);
    }


    public void gameShutDown(){
        gameRunning = false;
    }

    public void setPlayerInput(String key){
        if (key.equals("T")){
            gameShutDown();
            return;
        }
        playerInput.add(key);
    }

    public void removePlayerInput(String key){
        playerInput.remove(key);
    }
}
