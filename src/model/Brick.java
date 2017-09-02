package model;

import java.awt.*;

/**
 * Created by justi on 8/28/2017.
 */


// TODO; do we want to change the color of the brick upon collision

public class Brick extends Sprite {

//private static final int WIDTH=50;
//private static final int HEIGHT=10;
private boolean is_inplay;
private Color colour_i_am;

public Brick(int X_OFFSET,int Y_OFFSET, int WIDTH, int HEIGHT, Color color){
        super(X_OFFSET,Y_OFFSET, WIDTH, HEIGHT);
        colour_i_am = color;
        is_inplay = true; // seet default status to in_play
        }

public void draw(Graphics g){
        g.setColor(this.colour_i_am);
        g.fillRect(x,y,width,height);
        }

public void move() throws invalidMove{
        throw new invalidMove("Brick's Can't Move");
        }



}
