package model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*****************************************************************
 * BrickBreaker v.1.0                                            *
 * Authors: Justin Tao, Lauren Bentley                           *
 * Acknowledgements: SpaceInvader game from UBC CPSC210 lecture  *
 * Updates: Sep. 8, 2017                                         *
 * TODOs: bug when sometimes paddle will not move right for a    *
 *        milisecond when ball is in play                        *
 *****************************************************************/

public class Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final Random RND = new Random();
    private List<Sprite> sprites;
    private Paddle paddle;
    private Ball ball;
    private int numBricksLeft;
    private int score;
    private static final int SCORE_MULTIPLIER = 100;
    private boolean isGameOver;
    private boolean isGameWon;

    // Number of columns of bricks
    private static final int NBRICK_COLS = 5;
    // Number of rows of bricks
    private static final int NBRICK_ROWS = 5;
    // Separation between bricks
    private static final int BRICK_SEP = 5;
    // Width of a brick
    private static final int BRICK_WIDTH = (WIDTH - (NBRICK_COLS - 1) * BRICK_SEP) / NBRICK_COLS;
    // Height of a brick
    private static final int BRICK_HEIGHT = 30;
    // Color array of brick colors
    private Color[] colors = new Color[5];

    // Game constructor
    public Game(){
        sprites = new ArrayList<Sprite>();
        initializeSprites();
        isGameWon = false;
        isGameOver = false;
        reset();
    }

    // Initializes sprites
    // modifies: this
    // effects:  sets up list of sprites, bricks, paddle, and ball
    private void initializeSprites() {
        sprites.clear();
        initializeBricks();
        numBricksLeft = NBRICK_ROWS * NBRICK_COLS;
        score = 0;
        paddle = new Paddle();
        ball = new Ball();
        sprites.add(paddle);
        sprites.add(ball);
    }

    // Initializes bricks
    // effects:  sets up list of sprites bricks
    private void initializeBricks() {
        colors[0] = new Color(0xFF9E25);
        colors[1] = new Color(0xFF9E25);
        colors[2] = new Color(0x548AFF);
        colors[3] = new Color(0xFF686A);
        colors[4] = new Color(0xFF686A);
        for( int row = 0; row < NBRICK_ROWS; row++) {
            for (int column = 0; column < NBRICK_COLS; column++) {

			    // To get the x coordinate for the starting width:
				// 	start at half the center width,
				// 	subtract half of the bricks (width) in the row,
				// 	subtract half of the separations (width) between the bricks in the row,
				//  now you're at where the first brick should be,
				//  so for the starting point of the next bricks in the column, you need to:
				// 	add a brick width
				// 	add a separation width

                int	x = WIDTH/2 - (NBRICK_COLS * BRICK_WIDTH) / 2 - ((NBRICK_COLS - 1) * BRICK_SEP) / 2
                        + column * BRICK_WIDTH + column * BRICK_SEP;

				// To get the y coordinate of the starting height:
				// 	start at the given length from the top for the first row,
				// 	then add a brick height and a brick separation for each of the following rows

                int	y = HEIGHT / 4 + row * BRICK_HEIGHT + row * BRICK_SEP;

                // Adds brick with the color corresponding to its row, and a score
                // higher bricks are worth more points
                Brick aBrick = new Brick( x, y, BRICK_WIDTH, BRICK_HEIGHT, colors[row], SCORE_MULTIPLIER * Math.abs(NBRICK_ROWS - row));
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
            // moves ball with paddle if not yet launched
            if (ball.getLaunchStatus() == false)
                ball.moveLeft();
        }
        else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT) {
            paddle.moveRight();
            // moves ball with paddle if not yet launched
            if (ball.getLaunchStatus() == false)
                ball.moveRight();
        }
        else if (keyCode == KeyEvent.VK_SPACE && !ball.getLaunchStatus())
            ball.launchBall();
        else if (keyCode == KeyEvent.VK_R && (isGameOver || isGameWon))
            reset();
        else if (keyCode == KeyEvent.VK_X)
            System.exit(0);
    }

    // Responds to key release codes
    // modifies: this
    // effects: resets paddle and unlaunched ball speeds for smoother animation
    public void keyReleased(){
        paddle.resetSpeed();
        if (ball.getLaunchStatus() == false)
            ball.resetSpeed();
    }

    // Draws the sprite
    public void draw(Graphics g){
        for(Sprite next: sprites){
            next.draw(g);
        }
    }

    // Moves the ball
    // modifies: this
    // effects: moves ball to location at next clock tick
    private void moveBall() {
        ball.move();
    }

    // Moves the paddle
    // modifies: this
    // effects: moves paddle to location at next clock tick
    private void movePaddle(){
        paddle.move();
    }

    // Sets / resets the game
    // modifies: this
    // effects:  resets number of bricks left;
    //           game is not over nor won;
    private void reset() {
        initializeSprites();
        isGameOver = false;
        isGameWon = false;
    }

    // Things to update with each clock tick
    public void update() {
        moveBall();
        movePaddle();
        checkCollisions();
        isGameOver();
    }

    // Returns bricks left in play
    public int getNumBricksLeft() {
        return numBricksLeft;
    }

    // Returns score so far
    public int getScore() {
        return score;
    }

    // Is game over?
    // effects: returns true if game is over, false otherwise
    public boolean getIsGameOver() {
        return isGameOver;
    }

    // Is game won?
    // effects: returns true if game is won, false otherwise
    public boolean getIsGameWon(){
        return isGameWon;
    }

    // Checks if game is over
    // effects: game won when no bricks left, game over when ball reaches
    // bottom of screen
    public void isGameOver(){
        if (numBricksLeft == 0){
            isGameWon = true;

            // pauses the ball where it is after game is won
            ball.changeVelocity(0,0);
        }

        if (ball.getY() >= HEIGHT){
            isGameOver = true;
        }
    }

    // Checks for collisions between an brick and a ball
    // modifies: this
    // effects:  removes any brick that has been hit with a ball
    //           and removes corresponding brick from play
    private void checkCollisions() {
        ball.handleBoundary();
        List<Sprite> toBeRemoved = new ArrayList<Sprite>();
        if (ball.collidedWith(paddle)){
            paddleCollision();
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
    private void paddleCollision(){
        int ball_x = ball.getX();
        int paddleFifths = paddle.getWidth() / 5;
        if (ball_x <= paddleFifths + paddle.getX()){
            ball.changeVelocity((RND.nextInt(Ball.BALL_SPEED - 1) - Ball.BALL_SPEED), -Math.abs(ball.getY_velocity()));
        }
        else if (ball_x <= paddleFifths * 2 + paddle.getX()){
            ball.changeVelocity((5.0 - Ball.BALL_SPEED), -Math.abs(ball.getY_velocity()));
        }
        else if (ball_x <= paddleFifths * 3 + paddle.getX()){
            ball.changeVelocity((RND.nextInt(2)-1), -Math.abs(ball.getY_velocity()));
        }
        else if (ball_x <= paddleFifths * 4 + paddle.getX()){
            ball.changeVelocity(RND.nextInt(5) + 3, -Math.abs(ball.getY_velocity()));
        }
        else {
            ball.changeVelocity((RND.nextInt(Ball.BALL_SPEED - 1) + 1), -Math.abs(ball.getY_velocity()));
        }
    }

    // Has a given brick been hit by a ball?
    // modifies: this, bricksToRemove
    // effects: if target has been hit by a ball, removes target and ball from play;
    // decrements number of bricks left.
    private void checkBrickHit(Brick target, List<Sprite> bricksToRemove) {
        for (Sprite next : sprites) {
            if (next instanceof Ball) {
                if (ball.collidedWith(target)) {
                    bricksToRemove.add(target);
                    ball.changeVelocity(ball.getX_velocity(), -ball.getY_velocity());
                    numBricksLeft--;
                    score += target.brickScore;
                }
            }
        }
    }
}
