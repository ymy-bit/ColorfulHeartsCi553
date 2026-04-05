package com.example.colorfulheartsci553.game;

import com.example.colorfulheartsci553.enums.GameState;
import com.example.colorfulheartsci553.enums.Input;
import com.example.colorfulheartsci553.game.prefabs.Pellet;
import com.example.colorfulheartsci553.game.prefabs.redHeart;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class GameModel {

    GameView view;
    GameController controller;

    //width and height of window
    int width;
    int height;

    //checks the state of the game, might change for an enum for other states such as paused or others
    public GameState gameState;
    public boolean spawnPellets;

    //set holding player inputs for better player controls
    Set<Input> playerInput = new HashSet<>();
    ArrayList<GameObject> gameObjects;

    //game objects
    public GameObject heart;
    public int score;
    public int scoreTimer;

    //timer for spawner, could be changed to its own class.
    int timer;

    public GameModel(int w, int h){
        this.width = w;
        this.height = h;
    }

    public void initialize(){
        gameState = GameState.RUNNING;
        System.out.println("Game State : " + gameState);

        heart = new redHeart(width/2, height/2);
        gameObjects = new ArrayList<>(50);
        timer = -270;
        scoreTimer = -270;
        score = 0;
        spawnPellets = true;

        Thread thread = new Thread(this::gameLoop);
        thread.setDaemon(true);
        thread.start();
    }


    public void gameLoop(){
        while(gameState == GameState.RUNNING){
            try {
                playerMovement();
                scoreHandler();
                spawnPellet();
                pelletMovement();
                checkIntersection();
                cleanUp();
                updateView();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Game Interrupted");
            }
        }

        System.out.println("Game stopped");

        if(gameState == GameState.OVER){
            Platform.runLater(view::gameOver);
        }

    }

    public synchronized void scoreHandler(){
        scoreTimer++;
        if (scoreTimer == 80){
            scoreTimer = 0;
            score++;
        }
    }

    public synchronized void pelletMovement(){
        for (GameObject gameObject : gameObjects){
            gameObject.move(Input.RIGHT);
            if (gameObject.topX > width){
                gameObject.doCollision();
            }
        }
    }

    public synchronized void spawnPellet(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        timer++;
        if (timer == 80){
            for(int i = 0; i < 5; i++){
                Pellet pellet = new Pellet(-10, random.nextInt(0, height));
                gameObjects.add(pellet);

            }
            spawnPellets = false;
            timer = 0;
        }

    }

    public synchronized void checkIntersection(){
        for (GameObject gameObject : gameObjects){
            if (heart.intersects(gameObject)){
                gameObject.doCollision();
                gameShutDown();
            }
        }
    }



    public synchronized void cleanUp(){
        gameObjects.removeIf(GameObject::isDestroyed);
        gameObjects.trimToSize();
    }

    public synchronized void playerMovement(){
        if (playerInput.contains(Input.UP) && (heart.topY > 0)){
            heart.move(Input.UP);
        }
        if (playerInput.contains(Input.DOWN) && (heart.topY < height - heart.height)){
            heart.move(Input.DOWN);
        }
        if (playerInput.contains(Input.LEFT) && (heart.topX > 0)){
            heart.move(Input.LEFT);
        }
        if (playerInput.contains(Input.RIGHT) && (heart.topX < width-heart.width)){
            heart.move(Input.RIGHT);
        }
    }

    public synchronized void updateView(){
        Platform.runLater(view::drawScreen);
    }


    public synchronized void gameShutDown(){
        System.out.println("Game shut down");
        gameState = GameState.OVER;
    }

    public synchronized void setPlayerInput(Input key){
        if (key == Input.Q) {
            System.out.println("Q pressed");
            gameShutDown();
            return;
        }
        if (key == Input.ENTER && gameState != GameState.RUNNING){
            System.out.println("ENTER pressed");
            initialize();
            return;
        }
        playerInput.add(key);

    }

    public synchronized void removePlayerInput(Input key) {
        playerInput.remove(key);
    }
}
