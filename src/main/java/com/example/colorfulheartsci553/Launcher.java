package com.example.colorfulheartsci553;

import com.example.colorfulheartsci553.game.GameScreenManager;
import com.example.colorfulheartsci553.menu.ManuScreenManager;
import com.example.colorfulheartsci553.utils.file_manager.SaveFile;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    private Stage primaryStage;
    GameScreenManager gameScreen =  new GameScreenManager();
    ManuScreenManager menuScreen = new ManuScreenManager();

    int width = 640;
    int height = 480;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        stage.setResizable(false);
        stage.setTitle("Colorful Hearts");

        menuScene();
        stage.show();
    }

    public void gameScene(SaveFile saveFile){
        gameScreen.start(width,height,saveFile, this);
        primaryStage.setScene(gameScreen.view.getScene());
    }

    public void menuScene(){
        menuScreen.setMenu(width,height, this);
        primaryStage.setScene(menuScreen.view.getScene());
    }





}
