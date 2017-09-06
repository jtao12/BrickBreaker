package ui;


import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/*
 * Represents the main window in which the space invaders
 * game is played
 */
@SuppressWarnings("serial")

// Let this be our controller
public class BrickBreaker extends JFrame {

    private static final int INTERVAL = 10;
    private Game game;
    private Game_View game_view;
    private Timer t;

    // Constructs main window
    // effects: sets up window in which Space Invaders game will be played
    public BrickBreaker() {
        super("Brick Breaker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        game = new Game();
        game_view = new Game_View(game);
        add(game_view);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
        t.start();
    }

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.update();
                game_view.repaint();

            }
        }

        );
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e){
            game.keyReleased(e.getKeyCode());
        }
    }

    // Play the game
    public static void main(String[] args) {
        new BrickBreaker();
    }
}
