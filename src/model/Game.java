package model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by justi on 8/28/2017.
 */
public class Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final Random RND = new Random(); // TODO: What is this for?????
    private List<Sprite> sprites;
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private boolean isGameOver;
    private int numBricksLeft;   //
    private static final Color COLOR = new Color(128, 50, 20);

    public Game(){
        sprites = new ArrayList<Sprite>();
        initializeSprites();
        reset();

    }

    // Initializes sprites
    // modifies: this
    // effects:  sets up list of sprites bricks, paddle, and ball
    private void initializeSprites() {
        sprites.clear();
        initializeBricks(); // TODO
        paddle = new Paddle();
        ball = new Ball();
        sprites.add(paddle);
        sprites.add(ball);
    }

    // Initializes bricks
    // effects:  sets up list of sprites bricks
    private void initializeBricks() {
        for (int i = 0; i < 3; i++){
            Brick aBrick = new Brick(20 * i + i * 50,100,COLOR);
            sprites.add(aBrick);
        }
    }
    // TODO: Refractor this to controller class
    // Responds to key press codes
    // modifies: this
    // effects:  moves paddle, launches ball at the beginning
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT)
            paddle.moveLeft();
        else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT)
            paddle.moveRight();
        else if (keyCode == KeyEvent.VK_SPACE && !ball.getLaunchStatus())
            ball.launchBall();
        else if (keyCode == KeyEvent.VK_R && isGameOver)
            reset();
        else if (keyCode == KeyEvent.VK_X)
            System.exit(0);
    }

    public void draw(Graphics g){
        for(Sprite next: sprites){
            next.draw(g);
        }
    }


    // moves the sprites
    // modifies: this
    // effects: moves sprites to location at next clock tick
    private void moveBall() {
        ball.move();
    }

    // Sets / resets the game
    // modifies: this
    // effects:  resets number of missiles in play and number of invaders destroyed;
    //           game is not over
    private void reset() {
        isGameOver = false;
        numBricksLeft = 15;

    }

    public void update(){
        ball.move();
        return;
    }

    // Is game over?
    // Effects: returns true if game is over, false otherwise
    public boolean isOver() {
        return isGameOver;
    }

    public int getNumBricksLeft() {
        return numBricksLeft;
    }



}
