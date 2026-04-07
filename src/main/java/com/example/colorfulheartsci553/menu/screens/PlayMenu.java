package com.example.colorfulheartsci553.menu.screens;

import com.example.colorfulheartsci553.menu.MenuScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class PlayMenu extends MenuScreen {


    TextField name;
    Button start;
    Button back;

    public PlayMenu(){
        this.screen = new StackPane();

        VBox box = new VBox();
        box.setSpacing(10);


        Label nameLabel = new Label("Enter your name.");


        name = new TextField();
        name.getStyleClass().add("textField");
        name.setPromptText("Enter Name");

        name.setMaxSize(100, 30);

        start = new Button("Start");
        start.getStyleClass().add("CHButton");

        back = new Button("Back");
        back.getStyleClass().add("CHButton");

        box.getChildren().addAll(nameLabel,name,start,back);

        screen.getChildren().add(box);
        box.setAlignment(Pos.CENTER);
        screen.setVisible(false);
    }

    public void setOnStartClicked(EventHandler<ActionEvent> e){
        start.setOnAction(e);
    }

    public void setOnBackClicked(EventHandler<ActionEvent> e){
        back.setOnAction(e);
    }

    public String getName(){
        return name.getText();
    }
}
