package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** Represents the main window in which the BrickBreaker is played **/
@SuppressWarnings("serial")

// Let this be our controller
public class BrickBreaker extends JFrame {

    private static final int INTERVAL = 10;
    private Game game;
    private GameView game_view;
    private ScorePanel score_panel;
    private Timer t;

    // Constructs main window
    // effects: sets up window in which BrickBreaker will be played
    public BrickBreaker() {
        super("Brick Breaker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        game = new Game();
        game_view = new GameView(game);
        score_panel = new ScorePanel(game);
        add(game_view);
        add(score_panel, BorderLayout.NORTH);
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
                score_panel.update();
            }
        }
        );
    }

    // Centres frame on desktop
    // modifies: this
    // effects: location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // A key handler to respond to key events
    // effects: handles keys pressed or released
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e){
            game.keyReleased();
        }
    }

    // Play the game!
    public static void main(String[] args) {
        new BrickBreaker();
    }
}
