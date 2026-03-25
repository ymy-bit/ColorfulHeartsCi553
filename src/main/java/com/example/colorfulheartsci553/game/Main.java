package com.example.colorfulheartsci553.game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage window)  {
        int W = 640;
        int H = 400;

        View view = new View(W, H);
        Model model = new Model(W, H);
        Controller controller = new Controller();

        view.controller = controller;
        view.model = model;

        controller.view = view;
        controller.model = model;

        model.controller = controller;
        model.view = view;

        model.initialize();
        view.start(window);

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
