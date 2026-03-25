package com.example.colorfulheartsci553.game;

import javafx.scene.input.KeyEvent;

public class Controller {

    View view;
    Model model;

    public void userInputDetected(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                model.setPlayerInput("UP");
                break;
            case DOWN:
                model.setPlayerInput("DOWN");
                break;
            case LEFT:
                model.setPlayerInput("LEFT");
                break;
            case RIGHT:
                model.setPlayerInput("RIGHT");
                break;
            case T:
                model.setPlayerInput("T");
                break;
            default:
                break;
        }
    }

    public void userInputReleased(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                model.removePlayerInput("UP");
                break;
            case DOWN:
                model.removePlayerInput("DOWN");
                break;
            case LEFT:
                model.removePlayerInput("LEFT");
                break;
            case RIGHT:
                model.removePlayerInput("RIGHT");
                break;
            default:
        }
    }


}
