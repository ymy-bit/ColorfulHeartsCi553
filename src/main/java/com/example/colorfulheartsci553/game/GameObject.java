

package com.example.colorfulheartsci553.game;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * <p>This is the GameObject SuperClass, it will have all  the variables and methods needed for a base GameObject,
 * be it player, wall, or bullet, for extra methods other subclasses will be created for less general methods needed
 * for one specific object</p>
 * @author Younes Mohamed Younes Bashir, ymy-bit
 * @version 0.1
 */
public class GameObject {

    public int topY ;
    public int topX;
    public int height;
    public int width;
    public Image image; // prototype to specifying that shape of game object, might change for drawing sprites, or any better solution.
    public Rectangle collisionBox; // a rectangle that will represent the collision box for a gameObject


    /**
     * <p>Constructor for a game object, will change in future versions to be empty for override. Params follow
     * parameter for creating shapes using JavaFX</p>
     * @param topX left most point
     * @param topY uppermost point
     * @param width width of shape
     * @param height height of shape
     * @param image sprite for gameObject
     */
    public GameObject(int topX, int topY, int width, int height,  Image image) {
        this.topX = topX;
        this.topY = topY;
        this.width = width;
        this.height = height;
        this.image = image;
        this.collisionBox = new Rectangle(topX, topY, width, height);
    }

    /**
     *
     */
    public void collision(){
        
    }
}
