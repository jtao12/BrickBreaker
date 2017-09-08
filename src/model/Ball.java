package model;
import java.awt.*;
import java.util.Random;

public class Ball extends Sprite {
    private double x_velocity;
    private double y_velocity;
    private boolean is_launched;
    private final static int DIAMETER = 10;
    private final static int X_OFFSET = (Game.WIDTH / 2) - DIAMETER / 2 ;
    private final static int Y_OFFSET = Paddle.Y_OFFSET - Paddle.HEIGHT - (DIAMETER / 2) ;
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
    public void move(){      // TODO: changes x and y and then we need to redraw the ball...I think
//        double rnd_temp = RND.nextDouble() * 3;
//        x += x_velocity * rnd_temp;
        x += x_velocity;
        y += y_velocity;

    }

    public void moveLeft() {
        x_velocity = -Paddle.PADDLE_VELOCITY;
    }

    public void resetSpeed(){
        x_velocity = 0;
    }

    public void moveRight(){
        x_velocity = Paddle.PADDLE_VELOCITY;
    }

    public void handleBoundary() {
        // this makes sures the ball moves witht the paddle if the ball has not yet been
        // launched
        if (getLaunchStatus() == false){
            if (x <= Paddle.WIDTH /2 - DIAMETER / 2){
                x = Paddle.WIDTH /2 - DIAMETER / 2;
            }
            else if (x >= Game.WIDTH -  Paddle.WIDTH /2 - DIAMETER / 2) {
                x = Game.WIDTH - Paddle.WIDTH / 2 - DIAMETER / 2;
            }
        }
        if (y <= 0) {
            if (x_velocity == 0)
                changeVelocity(x_velocity + 1, -y_velocity);
            else
                changeVelocity(x_velocity, -y_velocity);

        }

        if ( x <= 0 || x >= 800) {
            changeVelocity(-x_velocity, y_velocity);
        }


    }


    public void draw(Graphics g){
        g.setColor(COLOR);
        g.fillOval(x, y, DIAMETER, DIAMETER);
        g.drawOval(x, y, DIAMETER, DIAMETER);
    }

    @Override
    public Rectangle getBounds(){
        Rectangle bounds = new Rectangle(x,y,DIAMETER, DIAMETER);
        return bounds;
    }


}
