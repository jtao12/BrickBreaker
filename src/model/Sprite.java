package model;
import java.awt.*;

/*
 * Represents a sprite
 */
public abstract class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;


    //Constructs a sprite
    //Effects: sprite is at the specified location with given width and height.
    public Sprite(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Moves the sprite
    // modifies: this
    // effects:  sprite has been moved
    public abstract void move() throws invalidMove;

    // Draws the sprite
    // modifies: g
    // effects: draws the sprite on the Graphics object g
    public abstract void draw(Graphics g);

    // Constrains sprite so that it doesn't travel off sides of screen
    // modifies: this
    // effects: sprite is constrained to remain within horizontal boundaries of game
    protected void handleBoundary_x() {
//        if (x < 0)
//            x = 0;
//        else if (x > Game.WIDTH)
//            x = Game.WIDTH;
    }

    // Has invader collided with another sprite?
    // Effects: returns true if this Invader has collided with other Sprite; false otherwise
    public boolean collidedWith(Sprite other) {
        Rectangle thisBoundingRect = new Rectangle(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        Rectangle otherBoundingRect = new Rectangle(other.getX() - other.getWidth() / 2, other.getY() - other.getHeight() / 2,
                other.getWidth(), other.getHeight());
        return thisBoundingRect.intersects(otherBoundingRect);
    }

}

