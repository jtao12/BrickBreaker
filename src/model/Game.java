package model;

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
    public static final Random RND = new Random();

    private List<Sprite> sprites;
    private Paddle paddle;
    private Ball ball;
    private boolean isGameOver;
    private int numBricksLeft;

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
        initalizeBricks();
        paddle = new Paddle();
        ball = new Ball();
        sprites.add(paddle);
        sprites.add(ball);
    }

    // Initializes sprites
    // modifies: this
    // effects:  sets up list of sprites bricks, paddle, and ball
    private void initializeBricks() {

    }

    // Responds to key press codes
    // modifies: this
    // effects:  moves paddle, fires ball at the beginning
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT)
            paddle.moveleft();
        else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT)
            paddle.moveright();
        else if (keyCode == KeyEvent.VK_SPACE)
            launchBall();
        else if (keyCode == KeyEvent.VK_R && isGameOver)
            reset();
        else if (keyCode == KeyEvent.VK_X)
            System.exit(0);
    }

}
