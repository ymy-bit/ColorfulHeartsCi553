package com.example.colorfulheartsci553.menu;

import com.example.colorfulheartsci553.menu.screens.*;
import com.example.colorfulheartsci553.utils.enums.MenuState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class MenuView {

    private Scene scene;

    public int width;
    public int height;

    public Color backgroundColor = Color.BLACK;


    public StackPane primaryPane;

    public MenuController controller;
    public MenuModel model;


    //different panes for different menu screens
    MenuState menuState;
    public MainMenu mainMenu;
    public PlayMenu playMenu;

    public StackPane mainMenuPane;
    public StackPane playMenuPane;


    public MenuView(int w, int h) {
        width = w;
        height = h;
    }


    public void start() {

        primaryPane = new StackPane();
        primaryPane.getStyleClass().add("pane");

        setUpMenus();



        switchPane(mainMenuPane);
        menuState = MenuState.MainMenu;





        scene = new Scene(primaryPane,  width, height);

        scene.setFill(backgroundColor);

        scene.getStylesheets().add(getClass().getResource("/com/example/colorfulheartsci553/style.css").toExternalForm());
    }

    public void setUpMenus(){
        mainMenu  = new MainMenu();
        playMenu = new PlayMenu();

        mainMenuPane = mainMenu.getPane();
        playMenuPane = playMenu.getPane();

        mainMenu.setOnPlayClick(this::handleOnPlayClicked);
        mainMenu.setOnQuitClick(this::handleOnQuitClicked);
        playMenu.setOnStartClicked(this::handleOnStartClicked);
        playMenu.setOnBackClicked(this::handleOnBackClicked);

        primaryPane.getChildren().add(mainMenuPane);
        primaryPane.getChildren().add(playMenuPane);
    }


    public Scene getScene() {
        return scene;
    }

    public void switchPane(Pane pane){
        for (Node node : primaryPane.getChildren()) {
            node.setVisible(false);
        }
        pane.setVisible(true);
    }

    public void handleOnStartClicked(ActionEvent actionEvent) {
        controller.startGameClicked(playMenu.getName());
    }

    public void handleOnPlayClicked(ActionEvent actionEvent) {
        controller.playClicked();
    }

    public void handleOnBackClicked(ActionEvent actionEvent) {
        controller.backClicked();
    }

    public void handleOnQuitClicked(ActionEvent event) {
        System.exit(0);
    }




}