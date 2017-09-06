package model;

import javax.sound.sampled.Line;
import java.awt.*;

/**
 * Created by justi on 8/28/2017.
 */

// TODO
// make game_width
// handle key left, and handle key right in game

public class Paddle extends Sprite {
    protected static final int WIDTH = 50;
    protected static final int HEIGHT = 10;
    protected static final int X_OFFSET =  Game.WIDTH / 2 - WIDTH / 2 ;
    protected static final int Y_OFFSET = Game.HEIGHT - 20;
    protected int PADDLE_SPEED = 0;
    protected int PADDLE_VELOCITY = 7;
    protected static final Color COLOR = new Color(250, 250, 250);

    public Paddle(){
        super(X_OFFSET,Y_OFFSET, WIDTH, HEIGHT);
    }

    protected void handleBoundary_x() {
        if (x < 0)
            x = 0;
        else if (x > Game.WIDTH - WIDTH)
            x  = Game.WIDTH - WIDTH;
    }

    public void move(){
        x += PADDLE_SPEED;
        handleBoundary_x();
    }

    public void moveLeft() {
        PADDLE_SPEED = -PADDLE_VELOCITY;
    }

    public void resetSpeed(){
        PADDLE_SPEED = 0;
    }


    public void moveRight(){
        PADDLE_SPEED = PADDLE_VELOCITY;
      //  handleBoundary_x();
    }

    public void draw(Graphics g){
        g.setColor(COLOR);
        g.fillRect(x, y, WIDTH, HEIGHT);
        return;
    }

    public Rectangle getBounds(){
        Rectangle bounds = new Rectangle(x,y,width,1);
        return bounds;
    }



}
