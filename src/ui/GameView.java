package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

/** Represents the game view **/
public class GameView extends JPanel {
    private static final String YOU_LOSE = "You Lost";
    private static final String RESTART = "R to replay";
    private static final String YOU_WIN = "You win!";
    private Game game;

    // Make a Game_View
    public GameView(Game g){
        setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT));
        setBackground(Color.BLACK);
        this.game = g;
    }

    // Paints components, and draws game over / game won messages
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawGame(g);
        if (game.getIsGameOver()){
            gameOver(g);
        }

        if (game.getIsGameWon()){
            gameWin(g);
        }
    }

    // Draws the game
    private void drawGame(Graphics g){
        game.draw(g);
        return;
    }

    // Game Over text overlay
    // effects: draws game over text when lost
    private void gameOver(Graphics g){
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Calibri", Font.BOLD, 28));
        FontMetrics fm = g.getFontMetrics();
        g.drawString(YOU_LOSE, Game.WIDTH /2 - fm.stringWidth(YOU_LOSE) / 2, Game.HEIGHT / 2);
        g.drawString(RESTART, Game.WIDTH /2 - fm.stringWidth(RESTART) / 2, Game.HEIGHT / 2 + 50);
    }

    // Game Win text overlay
    // effects: draws game win text when won
    private void gameWin(Graphics g){
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Calibri", Font.BOLD, 28));
        FontMetrics fm = g.getFontMetrics();
        g.drawString(RESTART, Game.WIDTH /2 - fm.stringWidth(RESTART) / 2, Game.HEIGHT / 2 + 50);
        g.drawString(YOU_WIN, Game.WIDTH /2 - fm.stringWidth(YOU_WIN) / 2, Game.HEIGHT / 2);
    }
}
