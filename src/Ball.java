import processing.core.PApplet;

import java.util.ArrayList;

public class Ball {
    private int x, y, xSpeed, ySpeed, color;

    public Ball(int x, int y, int xs, int ys, int color) {
        this.x = x;
        this.y = y;
        this.xSpeed = xs;
        this.ySpeed = ys;
        this.color = color;
    }

    public void update() {
        x += xSpeed;
        y += ySpeed;
    }

    public void bounceOffWall() {
        if(y+10 > 800) {
            ySpeed = -ySpeed;
        } else if(y-10 < 0) {
            ySpeed = -ySpeed;
        } else if(x+10 > 800) {
            xSpeed = -xSpeed;
        } else if(x-10 < 0) {
            xSpeed = -xSpeed;
        }
    }

    public void bounceOffPaddle(Paddle p) {
        if(x+10 >= p.getX() && x-10 <= p.getX()+100) {
            if(y+10 == p.getY() || y-10 == p.getY()+10) {
                ySpeed = -ySpeed;
            }
        }
    }

    public boolean bounceOffBrick(Brick br) {
        if (x+10 >= br.getX() && x-10 <= br.getX()+100) {
            if (y+10 == br.getY() || y-10 == br.getY()+20) {
                ySpeed = -ySpeed;
                return true;
            }
        }

        return false;
    }

    public void draw(PApplet game) {
        game.fill(0, 0, 255);
        game.ellipse(x, y, 20, 20);
        update();
        bounceOffWall();

    }
}
