package com.example.colorfulheartsci553.game.prefabs;

import com.example.colorfulheartsci553.enums.Input;
import com.example.colorfulheartsci553.game.GameObject;
import javafx.scene.image.Image;

/**
 * <p>Basic heart which can move around freely around the screen</p>
 */

public class redHeart  extends GameObject {


    private final int size = 30;

    private final Image playerSprite = new Image(getClass().getResource("/com/example/colorfulheartsci553/images/red_heart.png").toExternalForm());

    public int speed = 5;

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
        this.sprite = playerSprite;

    }

    @Override
    public void doCollision() {

    }

    @Override
    public void move(Input direction) {
        if (direction == Input.UP) {
            topY -= speed;
        }
        if (direction == Input.DOWN){
            topY += speed;
        }
        if (direction == Input.LEFT){
            topX -= speed;
        }
        if (direction == Input.RIGHT){
            topX += speed;
        }
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
