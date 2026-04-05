package com.example.colorfulheartsci553.game;

import javafx.stage.Stage;

public class GameMain {


    public void start(Stage window, int W, int H)  {


        GameView view = new GameView(W, H);
        GameModel model = new GameModel(W, H);
        GameController controller = new GameController();

        view.controller = controller;
        view.model = model;

        controller.view = view;
        controller.model = model;

        model.controller = controller;
        model.view = view;

        model.initialize();
        view.start(window);

    }

}
