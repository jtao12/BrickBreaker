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
    private Color[] colors = new Color[5];

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
        initializeBricks();
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
        colors[0] = new Color(0xFF9E25);
        colors[1] = new Color(0xFF9E25);
        colors[2] = new Color(0x548AFF);
        colors[3] = new Color(0xFF686A);
        colors[4] = new Color(0xFF686A);
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

            int	y = HEIGHT/4 + row*BRICK_HEIGHT + row*BRICK_SEP;

            Brick aBrick = new Brick( x, y, BRICK_WIDTH, BRICK_HEIGHT, colors[row]);
            sprites.add(aBrick);
        }
    }
}
    // Responds to key press codes
    // modifies: this
    // effects:  moves paddle, launches ball at the beginning
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT) {
            paddle.moveLeft();
            if (ball.getLaunchStatus() == false)
                ball.moveLeft();
        }
        else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT) {
            paddle.moveRight();
            if (ball.getLaunchStatus() == false)
                ball.moveRight();
        }
        else if (keyCode == KeyEvent.VK_SPACE && !ball.getLaunchStatus())
            ball.launchBall();
        else if (keyCode == KeyEvent.VK_R && isGameOver)
            reset();
        else if (keyCode == KeyEvent.VK_X)
            System.exit(0);
    }

    public void keyReleased(){
        paddle.resetSpeed();
        if (ball.getLaunchStatus() == false)
            ball.resetSpeed();
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

    private void movePaddle(){
        paddle.move();
    }


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
        isgameover();
    }

    // Is game over?
    // Effects: returns true if game is over, false otherwise
    public boolean isOver() {
        return isGameOver;
    }

    // Checks how many bricks are left
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
        if (ball.collidedWith(paddle)){
            boundary_paddlecollision();
        }
        for (Sprite next : sprites) {
            if (next instanceof Brick) {
                checkBrickHit((Brick) next, toBeRemoved);
            }

        }
        sprites.removeAll(toBeRemoved);
    }

    // Randomly changes the speed of the ball depending where it collides
    // with the paddle

    private void boundary_paddlecollision(){
        int ball_x = ball.getX();
        int paddle_3rds = paddle.getWidth() / 3;
        if (ball_x <= paddle_3rds + paddle.getX()){
            ball.changeVelocity((RND.nextInt(4) - 5), -Math.abs(ball.getY_velocity()));
        }
        else if (ball_x <= paddle_3rds * 2 + paddle.getX()){
            ball.changeVelocity((RND.nextInt(2)-1), -Math.abs(ball.getY_velocity()));
        }
        else {
            ball.changeVelocity((RND.nextInt(4) + 1) , -Math.abs(ball.getY_velocity()));

        }
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
                    numBricksLeft--;
                }
            }
        }
    }

    public void isgameover(){
  /*      if (numBricksLeft == 0)
            return; // TODO: something ror winning
            */
        if (ball.getY() >= HEIGHT){
            isGameOver = true;
        }
        if (isGameOver){
            initializeSprites();
        }
    }

}
