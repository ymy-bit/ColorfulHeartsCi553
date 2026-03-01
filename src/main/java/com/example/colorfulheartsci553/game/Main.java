package com.example.colorfulheartsci553.game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage window) throws Exception {
        int H = 600;
        int W = 500;

        View view = new View(W, H);

        view.start(window);

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
