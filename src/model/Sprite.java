package model;
import java.awt.*;

/** Represents a Sprite **/
public abstract class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    // Constructs a sprite
    // effects: sprite is at the specified location with given width and height.
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
    public abstract void move() throws InvalidMove;

    // Draws the sprite
    // modifies: g
    // effects: draws the sprite on the Graphics object g
    public abstract void draw(Graphics g);

    // Has a Sprite collided with another Sprite?
    // effects: returns true if this Sprite has collided with other Sprite; false otherwise
    public boolean collidedWith(Sprite other) {
        Rectangle thisBoundingRect = this.getBounds();  // This will be the ball
        Rectangle otherBoundingRect = other.getBounds();
        return thisBoundingRect.intersects(otherBoundingRect);
    }

    public abstract Rectangle getBounds();
}

