package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Lauren on 2017-09-01.
 */
public class Game_View extends JPanel {
    private static final String you_lose = "Loser";
    private static final String restart = "R to replay";
    private Game game;

    // Make a Game_view

    public Game_View(Game g){
        setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT));
        setBackground(Color.BLACK);
        this.game = g;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawGame(g);
        if (game.isOver()){
            gameOver(g);
        }
    }


    private void drawGame(Graphics g){
        game.draw(g);
        return;
    }

    private void gameOver(Graphics g){
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Times New Roman", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        g.drawString(you_lose, Game.WIDTH /2 - fm.stringWidth(you_lose), Game.HEIGHT / 2);
        g.drawString(restart, Game.WIDTH /2 - fm.stringWidth(restart), Game.HEIGHT / 2 + 50);
    }



}
