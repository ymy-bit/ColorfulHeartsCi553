package com.example.colorfulheartsci553.game;

import com.example.colorfulheartsci553.game.enums.Input;
import javafx.scene.input.KeyEvent;

public class Controller {

    View view;
    Model model;

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
            case T:
                model.setPlayerInput(Input.T);
                break;
            case P:
                model.setPlayerInput(Input.P);
                break;
            case G:
                model.setPlayerInput(Input.G);
                break;
            case ENTER:
                model.setPlayerInput(Input.ENTER);
                break;
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
