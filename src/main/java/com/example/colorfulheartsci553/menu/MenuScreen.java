package com.example.colorfulheartsci553.menu;


import javafx.scene.layout.StackPane;

/**
 * <P>An abstract class which provides the pane of a menu screen and provides methods to setting actions.</P>
 */
public abstract class MenuScreen {
    public StackPane screen;

    public MenuScreen(){}

    public StackPane getPane(){
        return screen;
    }
}
