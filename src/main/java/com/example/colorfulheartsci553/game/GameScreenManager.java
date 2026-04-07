package com.example.colorfulheartsci553.game;

import com.example.colorfulheartsci553.Launcher;

public class GameScreenManager {

    public GameView view;

    public void start(int W, int H, Launcher launcher)  {


        view = new GameView(W, H);
        GameModel model = new GameModel(W, H);
        GameController controller = new GameController();

        view.controller = controller;
        view.model = model;

        controller.view = view;
        controller.model = model;

        model.controller = controller;
        model.view = view;

        model.initialize();
        view.start();

        model.setOnReturnToMenu(launcher::menuScene);

    }

}
