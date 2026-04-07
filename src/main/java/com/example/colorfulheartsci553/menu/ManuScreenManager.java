package com.example.colorfulheartsci553.menu;


import com.example.colorfulheartsci553.Launcher;
import com.example.colorfulheartsci553.utils.file_manager.SaveFile;

public class ManuScreenManager {

    public MenuView view;

    public void setMenu(int W, int H, Launcher launcher) {

        SaveFile saveFile = new SaveFile();

        view = new MenuView(W, H);
        MenuController controller = new MenuController();
        MenuModel model = new MenuModel(W, H);

        view.model = model;
        view.controller = controller;

        model.controller = controller;
        model.view = view;

        controller.view = view;
        controller.model = model;



        model.initialize(saveFile);
        view.start();

        model.setOnSwitchToGame(launcher::gameScene);

    }

}
