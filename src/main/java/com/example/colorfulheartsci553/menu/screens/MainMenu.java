package com.example.colorfulheartsci553.menu.screens;

import com.example.colorfulheartsci553.menu.MenuScreen;
import com.example.colorfulheartsci553.utils.file_manager.FileManager;
import com.example.colorfulheartsci553.utils.file_manager.SaveFile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Comparator;


public class MainMenu extends MenuScreen {


    private final Button play;
    private final Button quit;
    VBox scores;

    public MainMenu(){
        this.screen = new StackPane();

        screen.getStyleClass().add("pane");

        scores = new VBox();
        StackPane.setAlignment(scores, Pos.TOP_LEFT);

        Label scoresText = new Label("Score Board");
        scoresText.getStyleClass().add("scoresText");

        scores.getChildren().add(scoresText);
        populateScores();
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

        Label copyright = new Label("""
                This is a fan-game of UNDERTALE.\
                
                This game features original recreations of art from UNDERTALE.\
                
                UNDERTALE © 2015 by Toby Fox, Temmie Chang & Co.\
                
                This project and its creator are not affiliated with Toby fox.""");
        copyright.getStyleClass().add("copyrightText");
        StackPane.setAlignment(copyright, Pos.BOTTOM_CENTER);

        vbox.getChildren().addAll(title, play, quit);
        vbox.setAlignment(Pos.TOP_CENTER);

        screen.getChildren().addAll(scores, vbox, copyright);

        screen.setVisible(false);
    }

    public void setOnPlayClick(EventHandler<ActionEvent> e){
        play.setOnAction(e);
    }

    public void setOnQuitClick(EventHandler<ActionEvent> e){
        quit.setOnAction(e);
    }

    public void populateScores(){
        FileManager fileManager = new FileManager();
        ArrayList<SaveFile> scoresList = fileManager.readFromFile();
        scoresList.sort(Comparator.comparingInt(SaveFile::getScore).reversed());
        if(scoresList.size() > 15){
            scoresList.subList(15, scoresList.size()).clear();
        }
        if(!scoresList.isEmpty()){
            for(SaveFile score : scoresList){
                Label l = new Label(score.getName() + ", " + score.getScore());
                scores.getChildren().add(l);
            }
        } else{
            Label l = new Label("No scores found");
            scores.getChildren().add(l);
        }
    }

}
