package com.example.colorfulheartsci553.game;

import com.example.colorfulheartsci553.Launcher;
import com.example.colorfulheartsci553.utils.file_manager.SaveFile;

public class GameScreenManager {

    public GameView view;

    public void start(int W, int H, SaveFile saveFile, Launcher launcher)  {


        view = new GameView(W, H);
        GameModel model = new GameModel(W, H, saveFile);
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
