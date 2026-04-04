package com.example.colorfulheartsci553.game.tests;

import com.example.colorfulheartsci553.game.prefabs.Pellet;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameObjectTest {

    @Test
    public void testIntersects(){

        Pellet A = new Pellet(100, 100);
        Pellet B = new Pellet(20, 100);
        Pellet C = new Pellet(90, 100);
        Pellet D = new Pellet(80, 100);

        assertFalse(A.intersects(B));
        assertTrue(A.intersects(C));
        assertTrue(A.intersects(D));
    }
}
