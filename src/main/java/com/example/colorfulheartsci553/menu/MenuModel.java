package com.example.colorfulheartsci553.menu;

import com.example.colorfulheartsci553.utils.file_manager.SaveFile;

public class MenuModel {

    MenuView view;
    MenuController controller;

    //width and height of window
    int width;
    int height;

    SaveFile saveFile;

    SwitchToGameListener switchToGame;

    public MenuModel(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void initialize(SaveFile saveFile) {
        this.saveFile = saveFile;
    }

    public void startGame(String name){
        name = name.replace("," , ".");
        if (name.isEmpty()) {
            saveFile.setName("no name");
        } else{
            saveFile.setName(name);
        }

        switchToGame.onSwitchToGame(saveFile);

    }

    public void playClicked(){
        view.switchPane(view.playMenuPane);
    }

    public void backClicked(){
        view.switchPane(view.mainMenuPane);
    }

    interface SwitchToGameListener {
        void onSwitchToGame(SaveFile saveFile);
    }

    public void setOnSwitchToGame(SwitchToGameListener switchToGame){
        this.switchToGame = switchToGame;
    }


}
