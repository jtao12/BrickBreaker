package model;

import java.awt.*;
import java.util.Random;

/** Represents a Ball **/
public class Ball extends Sprite {
    private double x_velocity;
    private double y_velocity;
    private boolean is_launched;

    public final static int BALL_DIAMETER = 50;
    public final static Color BALL_COLOR = new Color(65, 150, 188);
    public final static int BALL_SPEED = 5;
    private final static int BALL_X_OFFSET = (Game.WIDTH / 2) - BALL_DIAMETER / 2 ;
    private final static int BALL_Y_OFFSET = Paddle.PADDLE_Y_OFFSET - BALL_DIAMETER - 2;
    private final static Random RND = new Random();



    public Ball(){
        super(BALL_X_OFFSET, BALL_Y_OFFSET, BALL_DIAMETER, BALL_DIAMETER);
        this.x_velocity = 0.0;
        this.y_velocity = 0.0;
        this.is_launched = false;
    }

    // Changes the velocity of the ball
    // modifies: this
    // effects: ball's velocity has been changed
    public void changeVelocity(double x, double y){
        this.x_velocity = x;
        this.y_velocity = y;
    }

    // Launches the ball at a random angle
    // modifies: this
    // effects: changes the ball's velocity and sets launch status to true
    public void launchBall(){
        changeVelocity(RND.nextDouble() * BALL_SPEED, -BALL_SPEED);
        is_launched = true;
    }

    public boolean getLaunchStatus(){
        return is_launched;
    }

    public double getX_velocity(){
        return x_velocity;
    }

    public double getY_velocity(){
        return y_velocity;
    }

    // Moves the ball
    // modifies: this
    // effects: moves ball to location at next clock tick
    @Override
    public void move(){
        x += x_velocity;
        y += y_velocity;
    }

    // Moves the ball left, used when ball has not been launched yet
    // modifies: this
    // effects: ball has been moved
    public void moveLeft() {
        x_velocity = -Paddle.PADDLE_SPEED;
    }

    // Moves the ball right, used when ball has not been launched yet
    // modifies: this
    // effects: ball has been moved
    public void moveRight(){
        x_velocity = Paddle.PADDLE_SPEED;
    }

    // Resets the ball speed to 0
    // modifies: this
    // effects: ball has stopped
    public void resetSpeed(){
        x_velocity = 0;
    }

    // Handles boundary collisions of the ball
    // modifies: this
    // effects: ball will bounce after colliding with the wall
    public void handleBoundary() {
        // Makes sures the ball moves with the paddle if the ball
        // has not yet been launched
        if (getLaunchStatus() == false){
            if (x <= Paddle.PADDLE_WIDTH /2 - BALL_DIAMETER / 2){
                x = Paddle.PADDLE_WIDTH /2 - BALL_DIAMETER / 2;
            }
            else if (x >= Game.WIDTH -  Paddle.PADDLE_WIDTH /2 - BALL_DIAMETER / 2) {
                x = Game.WIDTH - Paddle.PADDLE_WIDTH / 2 - BALL_DIAMETER / 2;
            }
        }

        // When ball hits the top of the screen, reverse direction of ball
        if (y <= 0) {
            changeVelocity(x_velocity, -y_velocity);
        }

        // When ball hits either side of the screen, reverse direction of ball
        if (x <= 0 || x >= Game.WIDTH - BALL_DIAMETER) {
            if (x <= 0 && x_velocity == 0)
                changeVelocity(-x_velocity + 1, y_velocity);
            else if (x >= Game.WIDTH - BALL_DIAMETER && x_velocity == 0)
                changeVelocity(-x_velocity - 1, y_velocity);
            else
                changeVelocity(-x_velocity, y_velocity);
        }
    }


    // Draws the ball, and sets the color
    public void draw(Graphics g){
        g.setColor(BALL_COLOR);
        g.fillOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
        g.drawOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
    }

    // Sets the bounding rectangle around the ball for collision detection
    @Override
    public Rectangle getBounds(){
        Rectangle bounds = new Rectangle(x, y, BALL_DIAMETER, BALL_DIAMETER);
        return bounds;
    }


}
