package model;
import java.awt.*;
import java.util.Random;

public class Ball extends Sprite {
    private int x_velocity;
    private int y_velocity;
    private boolean is_launched;
    private final static int DIAMETER = 10;
    private final static int X_OFFSET = (Game.WIDTH / 2) - DIAMETER / 2 ;
    private final static int Y_OFFSET = Paddle.Y_OFFSET - Paddle.HEIGHT - (DIAMETER / 2) ;
    private static final Color COLOR = new Color(65, 150, 188);
    public static final Random RND = new Random();
    private final static int BALL_SPEED = 5;


    public Ball(){
        super(X_OFFSET, Y_OFFSET, DIAMETER, DIAMETER);
        this.x_velocity = 0;
        this.y_velocity = 0;
        this.is_launched = false;
    }
    public void changeVelocity(int x, int y){
        this.x_velocity = x;
        this.y_velocity = y;
    }

    public void launchBall(){
        changeVelocity(RND.nextInt(3 - -3) -3,-BALL_SPEED ); // launch ball and set initial speed to 10 pixels/sec
        is_launched = true;
    }

    public boolean getLaunchStatus(){
        return is_launched;
    }


    public int getX_velocity(){
        return x_velocity;
    }

    public int getY_velocity(){
        return y_velocity;
    }

    @Override
    public void move(){      // TODO: changes x and y and then we need to redraw the ball...I think
//        double rnd_temp = RND.nextDouble() * 3;
//        x += x_velocity * rnd_temp;
        x += x_velocity;
        y += y_velocity;

    }

    public void handleBoundary() {
        if (y <= 0 || y >= 600) {
            changeVelocity(x_velocity, -y_velocity);
        }

        if (x <= 0 || x >= 800) {
            changeVelocity(-x_velocity, y_velocity);
        }

    }


    // TODO
    public void draw(Graphics g){
        Color savedCol = g.getColor();
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
