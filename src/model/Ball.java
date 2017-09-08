package model;
import java.awt.*;
import java.util.Random;

/** Represents a Ball **/
public class Ball extends Sprite {
    private double x_velocity;
    private double y_velocity;
    private boolean is_launched;
    private final static int DIAMETER = 10;
    private final static int X_OFFSET = (Game.WIDTH / 2) - DIAMETER / 2 ;
    private final static int Y_OFFSET = Paddle.PADDLE_Y_OFFSET - Paddle.PADDLE_HEIGHT - (DIAMETER / 2) ;
    private static final Color COLOR = new Color(65, 150, 188);
    public static final Random RND = new Random();
    private final static int BALL_SPEED = 5;

    public Ball(){
        super(X_OFFSET, Y_OFFSET, DIAMETER, DIAMETER);
        this.x_velocity = 0.0;
        this.y_velocity = 0.0;
        this.is_launched = false;
    }

    public void changeVelocity(double x, double y){
        this.x_velocity = x;
        this.y_velocity = y;
    }

    public void launchBall(){
        changeVelocity(RND.nextDouble()*BALL_SPEED,-BALL_SPEED ); // launch ball and set initial speed to 10 pixels/sec
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

    @Override
    public void move(){
        x += x_velocity;
        y += y_velocity;
    }

    // Moves the ball left
    // modifies: this
    // effects: ball has been moved
    public void moveLeft() {
        x_velocity = -Paddle.PADDLE_SPEED;
    }

    // Moves the ball right
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

    // Resets the ball speed to 0
    // modifies: this
    // effects: ball has stopped
    public void handleBoundary() {
        // this makes sures the ball moves with the paddle if the ball
        // has not yet been launched
        if (getLaunchStatus() == false){
            if (x <= Paddle.PADDLE_WIDTH /2 - DIAMETER / 2){
                x = Paddle.PADDLE_WIDTH /2 - DIAMETER / 2;
            }
            else if (x >= Game.WIDTH -  Paddle.PADDLE_WIDTH /2 - DIAMETER / 2) {
                x = Game.WIDTH - Paddle.PADDLE_WIDTH / 2 - DIAMETER / 2;
            }
        }

        // When
        if (y <= 0) {
            if (x_velocity == 0)
                changeVelocity(x_velocity, -y_velocity);
            else
                changeVelocity(x_velocity, -y_velocity);

        }

        if ( x <= 0 || x >= 800) {
            changeVelocity(-x_velocity, y_velocity);
        }


    }

    // Draws the ball, and sets the color
    public void draw(Graphics g){
        g.setColor(COLOR);
        g.fillOval(x, y, DIAMETER, DIAMETER);
        g.drawOval(x, y, DIAMETER, DIAMETER);
    }

    // Sets the bounding rectangle around the ball for collision detection
    @Override
    public Rectangle getBounds(){
        Rectangle bounds = new Rectangle(x, y, DIAMETER, DIAMETER);
        return bounds;
    }


}
