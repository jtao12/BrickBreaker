package ui;
import model.Game;

import javax.swing.*;
import java.awt.*;



/*
 * Represents the panel in which the scoreboard is displayed.
 */
@SuppressWarnings("serial")
public class Score_Panel extends JPanel {
    private static final String BRICKS_TEXT = "Bricks Remaining: ";
    private static final String SCORE_TEXT = "Score: ";
    private static final int LBL_WIDTH = 700;
    private static final int LBL_HEIGHT = 400;
    private Game game;
    private JLabel bricksLbl;
    private JLabel scoreLbl;

    // Constructs a score panel
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public Score_Panel(Game g) {
        game = g;
        setBackground(new Color(54, 54, 54));
        bricksLbl = new JLabel(BRICKS_TEXT + game.getNumBricksLeft());
        bricksLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        //bricksLbl.setFont(new Font("Calibri", Font.BOLD, 14));
        bricksLbl.setForeground(Color.white);
        scoreLbl = new JLabel(SCORE_TEXT );
        scoreLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(bricksLbl);
        add(Box.createHorizontalStrut(10));
        add(scoreLbl);
    }

    // Updates the score panel
    // modifies: this
    // effects:  updates number of invaders shot and number of missiles
    //           remaining to reflect current state of game
    public void update() {
        bricksLbl.setText(BRICKS_TEXT + game.getNumBricksLeft());
        scoreLbl.setText(SCORE_TEXT );
        repaint();
    }
}

