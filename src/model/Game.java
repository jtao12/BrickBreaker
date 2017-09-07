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

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 5;
    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 5;
    /** Separation between bricks */
    private static final int BRICK_SEP = 5;
    /** Width of a brick */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
    /** Height of a brick */
    private static final int BRICK_HEIGHT = 30;
    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

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
//    private void initializeBricks() {
//        for (int i = 0; i < 5; i++){
//            Brick aBrick = new Brick(20 * i + i * 50,100,COLOR);
//            sprites.add(aBrick);
//        }
//    }

    private void initializeBricks() {
        for( int row = 0; row < 5; row++ ) {

            for (int column = 0; column < 6; column++) {

				/* To get the x coordinate for the starting width:
				 * 	start at the center width,
				 * 	subtract half of the bricks (width) in the row,
				 * 	subtract half of the separations (width) between the bricks in the row,
				 * now you're at where the first brick should be,
				 * so for the starting point of the next bricks in the column, you need to:
				 * 	add a brick width
				 * 	add a separation width
				 */

            int	x = WIDTH/2 - (NBRICKS_PER_ROW*BRICK_WIDTH)/2 - ((NBRICKS_PER_ROW-1)*BRICK_SEP)/2 + column*BRICK_WIDTH + column*BRICK_SEP;

				/* To get the y coordinate of the starting height:
				 * 	start at the given length from the top for the first row,
				 * 	then add a brick height and a brick separation for each of the following rows
				 */

            int	y = HEIGHT/2 + row*BRICK_HEIGHT + row*BRICK_SEP;

            Brick aBrick = new Brick( x, y, BRICK_WIDTH, BRICK_HEIGHT, COLOR);
            sprites.add(aBrick);


        }
    }
}
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


    public void keyReleased(int keyCode){
        paddle.resetSpeed();
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


    private void movePaddle(){paddle.move();}



    // Sets / resets the game
    // modifies: this
    // effects:  resets number of missiles in play and number of invaders destroyed;
    //           game is not over
    private void reset() {
        isGameOver = false;
        numBricksLeft = 15;

    }

    public void update() {
       moveBall();
        movePaddle();
       checkCollisions();



    }

    // Is game over?
    // Effects: returns true if game is over, false otherwise
    public boolean isOver() {
        if (ball.getX() < 0){
            isGameOver = true;
        }
        return isGameOver;
    }

    public int getNumBricksLeft() {
        return numBricksLeft;
    }

    // Checks for collisions between an brick and a ball
    // modifies: this
    // effects:  removes any brick that has been hit with a ball
    //           and removes corresponding brick from play
    private void checkCollisions() {
        ball.handleBoundary();
        List<Sprite> toBeRemoved = new ArrayList<Sprite>();
        for (Sprite next : sprites) {
            if (ball.collidedWith(paddle)){
                ball.changeVelocity(ball.getX_velocity(), -Math.abs(ball.getY_velocity()));
            }
            if (next instanceof Brick) {
                checkBrickHit((Brick) next, toBeRemoved);
            }

        }
        sprites.removeAll(toBeRemoved);

    }






    // Has a given brick been hit by a ball?
    // modifies: this, bricksToRemove
    // effects: if target has been hit by a ball, removes target and ball from play;
    // increments number of invaders destroyed.
    private void checkBrickHit(Brick target, List<Sprite> bricksToRemove) {
        for (Sprite next : sprites) {
            if (next instanceof Ball) {
                if (ball.collidedWith(target)) {
                    bricksToRemove.add(target);
                    ball.changeVelocity(ball.getX_velocity(), -ball.getY_velocity());
                   // bricksToRemove.add(next);
                    //numBricksLeft--;
                }
            }
        }
    }

}
