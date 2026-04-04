package com.example.colorfulheartsci553.game;

import com.example.colorfulheartsci553.game.enums.Input;
import com.example.colorfulheartsci553.game.prefabs.Pellet;
import com.example.colorfulheartsci553.game.prefabs.redHeart;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Model {

    View view;
    Controller controller;

    //width and height of window
    int width;
    int height;

    //checks the state of the game, might change for an enum for other states such as paused or others
    public boolean gameRunning;
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

    public Model(int w, int h){
        this.width = w;
        this.height = h;
    }

    public void initialize(){
        gameRunning = true;

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
        while(gameRunning){
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
        /*
        if (!spawnPellets){
            return;
        }
         */
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
        System.out.println(gameObjects.size());
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


    public void gameShutDown(){
        System.out.println("Game shut down");
        gameRunning = false;
    }

    public void setPlayerInput(Input key){
        switch (key) {
            case Input.T:
                System.out.println("T pressed");
                gameShutDown();
                return;

            case Input.P:
                System.out.println("P pressed");
                spawnPellets = !spawnPellets;
                return;

            case Input.G:
                System.out.println("G pressed");
                System.gc();
                return;

        }
        if (key == Input.ENTER && !gameRunning){
            gameObjects.clear();
            gameObjects.trimToSize();
            System.gc();
            initialize();
            return;
        }
        playerInput.add(key);

    }

    public void removePlayerInput(Input key) {
        playerInput.remove(key);
    }
}
