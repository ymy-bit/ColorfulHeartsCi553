package com.example.colorfulheartsci553.game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args)
    {

    }

    @Override
    public void start(Stage window) throws Exception {
        int H = 400;
        int W = 300;

        View view = new View(W, H);

        view.start(window);

    }
}
