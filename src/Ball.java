import processing.core.PApplet;

import java.util.ArrayList;

public class Ball {
    private int x, y, xSpeed, ySpeed, color;
    private int score = 0;
    private int size = 10;
    private int gameMode = 0;

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
        if(y+size > 800) {
            ySpeed = -ySpeed;
            gameMode = 1;
        } else if(y-size < 0) {
            ySpeed = -ySpeed;
        } else if(x+size > 800) {
            xSpeed = -xSpeed;
        } else if(x-size < 0) {
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
                score++;
                return true;
            }
        }

        return false;
    }

    public void draw(PApplet game) {
        if(gameMode == 0) {
            game.fill(0, 0, 255);
            game.ellipse(x, y, 20, 20);
            game.text("Score: " + score, 10, 500);
            update();
            bounceOffWall();
        } else if(gameMode == 1) {
            game.background(0);
            game.fill(255, 0, 0);
            game.textSize(100);
            game.text("You lose!", 215, 400);
        }
    }
}
