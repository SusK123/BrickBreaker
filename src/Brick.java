import processing.core.PApplet;

public class Brick {
    private int x, y, color;

    public Brick(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(PApplet game) {
        game.fill(color);
        game.rect(x, y, 100, 20);
    }
}
