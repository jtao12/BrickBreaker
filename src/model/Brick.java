package model;

import java.awt.*;

/**
 * Created by justi on 8/28/2017.
 */
public class Brick extends Sprite {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 10;
    public static final int X_OFFSET =  Game.WIDTH / 2;
    public static final int Y_OFFSET = 50;

    public Brick(){
        super(WIDTH,HEIGHT,X_OFFSET,Y_OFFSET);
    }

    public void draw(Graphics g){
        return;
    }

    public void move(){
        return;
    }

}
