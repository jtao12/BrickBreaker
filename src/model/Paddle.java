package model;

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
    protected static final int PADDLE_SPEED = 10;
    protected static final Color COLOR = new Color(250, 250, 250);

    public Paddle(){
        super(X_OFFSET,Y_OFFSET, WIDTH, HEIGHT);
    }

    protected void handleBoundary_x() {
        if (x < 0)
            x = 0;
        else if (x > Game.WIDTH)
            x  = Game.WIDTH;
    }

    public void move(){
        x += PADDLE_SPEED;

    }

    public void moveLeft() {
        for (int i = 0; i < PADDLE_SPEED; i++) {
            x--;
        }
    }


    public void moveRight(){
        x = x + PADDLE_SPEED;
      //  handleBoundary_x();
    }

    public void draw(Graphics g){
        g.setColor(COLOR);
        g.fillRect(x, y, WIDTH, HEIGHT);
        return;
    }


}
