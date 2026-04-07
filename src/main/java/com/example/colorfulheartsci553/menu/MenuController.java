package com.example.colorfulheartsci553.menu;

import javafx.scene.input.KeyEvent;

public class MenuController {

    MenuView view;
    MenuModel model;

    public void startGameClicked(String name){
        model.startGame(name);
    }

    public void playClicked(){
        model.playClicked();
    }

    public void backClicked(){
        model.backClicked();
    }


}
