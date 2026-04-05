package com.example.colorfulheartsci553.game.prefabs;

import com.example.colorfulheartsci553.enums.Input;
import com.example.colorfulheartsci553.game.GameObject;
import javafx.scene.image.Image;

public class Pellet extends GameObject {

    int size = 20;

    private static final Image pSprite = new Image(Pellet.class.getResource("/com/example/colorfulheartsci553/images/pellet.png").toExternalForm());

    int speed = 2;
    int orientation = 1; //1 to go right, -1 to go left, it is an int to it can be used in the calculations instead.

    boolean destroyed = false;

    public Pellet(int topX, int topY) {
        this.topX = topX - size/2;
        this.topY = topY - size/2;
        this.height = size;
        this.width = size;
        this.sprite = pSprite;
    }

    @Override
    public void doCollision() {
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void move(Input direction) {
        topX += speed * orientation;
    }


}
