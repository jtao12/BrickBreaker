package model;

import java.awt.*;


/** Represents a Brick **/
public class Brick extends Sprite {

    private Color brickColor;

    public Brick(int X_OFFSET,int Y_OFFSET, int WIDTH, int HEIGHT, Color COLOR){
        super(X_OFFSET,Y_OFFSET, WIDTH, HEIGHT);
        brickColor = COLOR;
    }

    public void draw(Graphics g){
        g.setColor(this.brickColor);
        g.fillRect(x, y, width, height);
    }

    public void move() throws InvalidMove{
        throw new InvalidMove("Bricks can't move");
    }

    public Rectangle getBounds(){
        Rectangle bounds = new Rectangle(x, y, width, height);
        return bounds;
    }

}
