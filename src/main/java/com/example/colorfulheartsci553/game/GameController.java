package com.example.colorfulheartsci553.game;

import com.example.colorfulheartsci553.enums.Input;
import javafx.scene.input.KeyEvent;

public class GameController {

    GameView view;
    GameModel model;

    public void userInputDetected(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                model.setPlayerInput(Input.UP);
                break;
            case DOWN:
                model.setPlayerInput(Input.DOWN);
                break;
            case LEFT:
                model.setPlayerInput(Input.LEFT);
                break;
            case RIGHT:
                model.setPlayerInput(Input.RIGHT);
                break;
            case ENTER:
                model.setPlayerInput(Input.ENTER);
                break;
            case Q:
                model.setPlayerInput(Input.Q);
            default:
                break;
        }
    }

    public void userInputReleased(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                model.removePlayerInput(Input.UP);
                break;
            case DOWN:
                model.removePlayerInput(Input.DOWN);
                break;
            case LEFT:
                model.removePlayerInput(Input.LEFT);
                break;
            case RIGHT:
                model.removePlayerInput(Input.RIGHT);
                break;
            default:
        }
    }


}
