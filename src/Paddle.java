import processing.core.PApplet;

public class Paddle {
    private int x, y, xSpeed, ySpeed, color;

    public Paddle(int x, int y, int xs, int ys, int color) {
        this.x = x;
        this.y = y;
        this.xSpeed = xs;
        this.ySpeed = ys;
        this.color = color;
    }

    public void draw(PApplet game) {
        game.fill(color);
        game.rect(x, y, 100, 10);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void updateLeftX() {
        x -= xSpeed;
    }

    public void updateRightX() {
        x += xSpeed;
    }
}
