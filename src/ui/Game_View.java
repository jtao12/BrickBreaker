package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Lauren on 2017-09-01.
 */
public class Game_View extends JPanel {
    private static final String you_lose = "Loser";
    private static final String restart = "There is no restart on this game";
    private Game game;

    // Make a Game_view

    public Game_View(Game g){
        setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT));
        setBackground(Color.BLACK);
        this.game = g;
    }

    @Override
    protected void paintComponet(Graphics g){
        super.paintComponent(g);
        drawGame(g); // TODO: make this f(x)
    }


    private void drawGame(Graphics g){
        return;
     //  game.draw(g);
    }










}
