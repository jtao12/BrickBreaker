package model;

import java.awt.*;

/** Represents a Paddle **/
public class Paddle extends Sprite {
    protected static final int PADDLE_WIDTH = 90;
    protected static final int PADDLE_HEIGHT = 10;
    protected static final int PADDLE_X_OFFSET =  Game.WIDTH / 2 - PADDLE_WIDTH / 2 ;
    protected static final int PADDLE_Y_OFFSET = Game.HEIGHT - 50;
    // Paddle color
    protected static final Color PADDLE_COLOR = new Color(250, 250, 250);
    // How fast the paddle moves
    protected static final int PADDLE_SPEED = 7;
    protected int PADDLE_VELOCITY = 0;


    public Paddle(){
        super(PADDLE_X_OFFSET, PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    // Keeps paddle in bounds of screen
    // effects: stops paddle from moving off screen
    protected void handleBoundaryPaddle() {
        if (x < 0)
            x = 0;
        else if (x > Game.WIDTH - PADDLE_WIDTH)
            x = Game.WIDTH - PADDLE_WIDTH;
    }

    // Moves the paddle
    // modifies: this
    // effects: moves paddle to location at next clock tick
    public void move(){
        x += PADDLE_VELOCITY;
        handleBoundaryPaddle();
    }

    // Moves the paddle left
    // modifies: this
    // effects: paddle has been moved
    public void moveLeft() {
        PADDLE_VELOCITY = -PADDLE_SPEED;
    }

    // Moves the paddle right
    // modifies: this
    // effects: paddle has been moved
    public void moveRight(){
        PADDLE_VELOCITY = PADDLE_SPEED;
    }

    // Resets the paddle speed to 0
    // modifies: this
    // effects: paddle has stopped
    public void resetSpeed(){
        PADDLE_VELOCITY = 0;
    }

    // Draws the paddle, and sets the color
    public void draw(Graphics g){
        g.setColor(PADDLE_COLOR);
        g.fillRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        return;
    }

    // Get bounding rectangle of the top side of the paddle
    // Since we only care if the ball hits the top of the paddle
    public Rectangle getBounds(){
        Rectangle bounds = new Rectangle(x,y,width,1);
        return bounds;
    }

}
