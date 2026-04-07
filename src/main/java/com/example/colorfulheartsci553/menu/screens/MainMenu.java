package com.example.colorfulheartsci553.menu.screens;

import com.example.colorfulheartsci553.menu.MenuScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class MainMenu extends MenuScreen {


    private final Button play;
    private final Button quit;

    public MainMenu(){
        this.screen = new StackPane();

        screen.getStyleClass().add("pane");

        VBox scores = new VBox();
        StackPane.setAlignment(scores, Pos.TOP_LEFT);

        Label scoresText = new Label("Leader Board");
        scoresText.getStyleClass().add("scoresText");

        scores.getChildren().add(scoresText);
        scores.getStyleClass().add("pane");

        scores.setTranslateY(+140);


        VBox vbox = new VBox();
        vbox.getStyleClass().add("pane");

        vbox.setSpacing(10);

        Label title = new Label("Colorful Hearts");
        title.setId("titleText");

        play = new Button("Play");
        play.getStyleClass().add("CHButton");

        quit  = new Button("Quit");
        quit.getStyleClass().add("CHButton");


        vbox.getChildren().addAll(title, play, quit);
        vbox.setAlignment(Pos.TOP_CENTER);
        screen.getChildren().addAll(scores, vbox);

        screen.setVisible(false);
    }

    public void setOnPlayClick(EventHandler<ActionEvent> e){
        play.setOnAction(e);
    }

    public void setOnQuitClick(EventHandler<ActionEvent> e){
        quit.setOnAction(e);
    }


}
