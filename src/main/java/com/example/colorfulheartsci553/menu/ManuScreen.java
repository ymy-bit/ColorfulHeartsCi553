package com.example.colorfulheartsci553.menu;

import javafx.stage.Stage;

public class ManuScreen {

    public void setMenu(Stage window, int W, int H){
        MenuView view = new MenuView(W, H);
        MenuController controller = new MenuController();
        MenuModel model = new MenuModel(W, H);

        view.model = model;
        view.controller = controller;

        model.controller = controller;
        model.view = view;

        controller.view = view;
        controller.model = model;

        model.initialize();
        view.start(window);

    }

}
