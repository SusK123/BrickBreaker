import processing.core.PApplet;

import java.util.ArrayList;

public class Game extends PApplet {
    ArrayList<Brick> brickList = new ArrayList<>();
    Ball b;
    Paddle p;
    Brick br;

    public void settings() {
        size(800, 800);

    }

    public void setup() {
        b = new Ball(400, 400, -4, -2, color(255, 0, 0));
        p = new Paddle(400, 600, 15, 2, color(0, 255, 0));

        for (int i = 0; i < 800; i+=100) {
            for (int j = 0; j < 200; j+=20) {
                int red = (int)(Math.random()*255);
                int green = (int)(Math.random()*255);
                int blue = (int)(Math.random()*255);
                br = new Brick(i, j, color(red, green, blue));
                brickList.add(br);
            }
        }
    }

    public void draw() {
        background(204);
        fill(255, 0, 0);
        rect(0, 790, 800, 10);
        b.bounceOffPaddle(p);
        p.draw(this);

        for (int i = 0; i < brickList.size(); i++) {
            brickList.get(i).draw(this);
        }
        for (int i = 0; i < brickList.size(); i++) {
            if(b.bounceOffBrick(brickList.get(i))) {
                brickList.remove(i);
            }
        }

        b.draw(this);

        if(brickList.isEmpty()) {
            background(0);
            fill(255, 0, 0);
            textSize(100);
            text("You win!", 215, 400);
        }
    }

    @Override
    public void keyPressed() {
        if(key == 'a'){
            p.updateLeftX();
        }
        if(key == 'd') {
            p.updateRightX();
        }
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
