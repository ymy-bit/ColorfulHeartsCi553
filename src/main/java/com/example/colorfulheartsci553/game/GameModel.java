package com.example.colorfulheartsci553.game;

import com.example.colorfulheartsci553.utils.enums.GameState;
import com.example.colorfulheartsci553.utils.enums.Input;
import com.example.colorfulheartsci553.game.prefabs.Pellet;
import com.example.colorfulheartsci553.game.prefabs.redHeart;
import com.example.colorfulheartsci553.utils.file_manager.FileManager;
import com.example.colorfulheartsci553.utils.file_manager.SaveFile;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class GameModel {

    GameView view;
    GameController controller;


    returnToListener returnToMenu;

    //width and height of window
    int width;
    int height;
    SaveFile saveFile;

    //checks the state of the game, might change for an enum for other states such as paused or others
    public GameState gameState;

    //set holding player inputs for better player controls
    Set<Input> playerInput = new HashSet<>();
    ArrayList<GameObject> gameObjects;

    //game objects
    public GameObject heart;
    public int score;
    public int scoreTimer;
    public int pelletDirCounter;

    //timer for spawner, could be changed to its own class.
    int timer;

    public GameModel(int w, int h, SaveFile saveFile){
        this.width = w;
        this.height = h;
        this.saveFile = saveFile;
    }

    public void initialize(){
        gameState = GameState.RUNNING;
        System.out.println("Game State : " + gameState);
        gameRunningView();

        heart = new redHeart(width/2, height/2);
        gameObjects = new ArrayList<>(50);
        timer = -120;
        scoreTimer = -120;
        score = 0;
        pelletDirCounter = 0;


        Thread gameThread = new Thread(this::gameLoop);
        gameThread.setDaemon(true);
        gameThread.start();
    }
    
    


    public void gameLoop(){
        while(gameState != GameState.OVER){
            try {
                if(gameState == GameState.RUNNING){
                    playerMovement();
                    scoreHandler();
                    spawnPellet();
                    pelletMovement();
                    checkIntersection();
                    cleanUp();
                    updateView();
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println("Game Interrupted");
            }
        }

        Platform.runLater(view::gameOver);

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
            if (gameObject.topX > width ||  gameObject.topX < -20){
                gameObject.doCollision();
            }
        }
    }

    public synchronized void spawnPellet(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int orientation =  0;
        int speed = 2;
        if (pelletDirCounter > 5){
            orientation = random.nextInt(0, 2);
            speed = 4;
        }
        timer++;
        if (timer == 80){

            for(int i = 0; i < 5; i++){
                if (orientation == 0){
                    Pellet pellet = new Pellet(-10, random.nextInt(0, height));
                    pellet.orientation = 1;
                    pellet.speed = speed;
                    gameObjects.add(pellet);
                } else if (orientation == 1){
                    Pellet pellet = new Pellet(width + 10, random.nextInt(0, height));
                    pellet.orientation = -1;
                    pellet.speed = speed;
                    gameObjects.add(pellet);

                }

            }
            pelletDirCounter++;
            timer = 0;
        }

    }

    public synchronized void checkIntersection(){
        for (GameObject gameObject : gameObjects){
            if (heart.intersects(gameObject)){
                gameObject.doCollision();
                gameOver();
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
    public synchronized void gameRunningView(){
        Platform.runLater(view::gameRunning);
    }

    public synchronized void pauseUnpauseGame(){
        if (gameState == GameState.RUNNING){
            gameState = GameState.PAUSED;
            Platform.runLater(view::gamePaused);
        } else if  (gameState == GameState.PAUSED){
            gameState = GameState.RUNNING;
            Platform.runLater(view::gameRunning);
        }
    }

    public synchronized void gameOver(){
        FileManager fileManager = new FileManager();
        try {
            saveFile.setScore(score);
            fileManager.writeToFile(saveFile);
        } catch (IOException e){
            view.onSaveFailed();
        }

        gameState = GameState.OVER;
    }

    //Interface with a function to be called when returning to mainMenu
    interface returnToListener {
        void returnToMenu();
    }

    public void setOnReturnToMenu(returnToListener returnToMenu){
        this.returnToMenu = returnToMenu;
    }

    public synchronized void setPlayerInput(Input key){
        if (key == Input.Q) {
            System.out.println("Q pressed");
            returnToMenu.returnToMenu();
            return;
        }
        if (key == Input.P) {
            System.out.println("P pressed");
            pauseUnpauseGame();
            return;
        }
        if (key == Input.ENTER && gameState == GameState.OVER){
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
