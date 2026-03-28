package com.example.colorfulheartsci553.game.Prefabs;

import com.example.colorfulheartsci553.game.GameObject;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * <p>Basic heart which can move around freely around the screen</p>
 */

public class redHeart  extends GameObject {


    private int size = 20;

    Image sprite = new Image(getClass().getResource("/com/example/colorfulheartsci553/images/Soul.png").toExternalForm());

    /**
     * <p>Constructor for a game object, will change in future versions to be empty for override. Params follow
     * parameter for creating shapes using JavaFX</p>
     *
     * @param topX   where the player spawns in the X coordinate
     * @param topY   where teh player spawns in the Y coordinate
     */

    public redHeart(int topX, int topY) {

        this.topX = topX - size/2;
        this.topY = topY - size/2;
        this.width = size;
        this.height = size;
        this.image = sprite;
        this.collisionBox = new Rectangle(topX, topY, size, size);
    }

    @Override
    public void doCollision() {

    }
}
