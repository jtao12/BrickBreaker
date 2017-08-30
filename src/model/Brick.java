package model;

import java.awt.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * Created by justi on 8/28/2017.
 */
public class Brick extends Sprite {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 10;

    public Brick(int X_OFFSET, int Y_OFFSET){
        super(WIDTH,HEIGHT,X_OFFSET,Y_OFFSET);
    }
    // TODO ; allocate list of colours for bricks

    public void draw(Graphics g){
        

    }


    public void move(){
        return;
    }

}
