package com.example.colorfulheartsci553;

import com.example.colorfulheartsci553.game.GameMain;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    GameMain gameMain =  new GameMain();

    @Override
    public void start(Stage stage) {
        int W = 640;
        int H = 480;

        stage.setResizable(false);

        gameMain.start(stage,  W, H);
        stage.show();
    }
}
