import processing.core.PApplet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Game extends PApplet {
    ArrayList<Brick> brickList = new ArrayList<>();
    Ball b;
    Paddle p;
    Brick br;
    int highScore;

    public void settings() {
        size(800, 800);

    }

    public void setup() {
        highScore = Integer.parseInt(readFile("data/highScore.txt").trim());
        b = new Ball(400, 400, -4, -2, color(255, 0, 0), highScore);
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
        fill(0, 0, 255);
        text("High Score: " + highScore, 10, 520);
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

        try {
            b.draw(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ;

        if(brickList.isEmpty()) {
            background(0);
            fill(255, 0, 0);
            textSize(100);
            text("You win!", 215, 400);
            if(80 > highScore) {
                try {
                    writeDataToFile("data/highScore.txt", "80");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
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

    public static void writeDataToFile(String filePath, String data) throws IOException {
        try (FileWriter f = new FileWriter(filePath);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter writer = new PrintWriter(b);) {


            writer.println(data);


        } catch (IOException error) {
            System.err.println("There was a problem writing to the file: " + filePath);
            error.printStackTrace();
        }
    }

    private static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {

            String line = br.readLine();
            while ( line != null) {
                sb.append(line).append(System.getProperty("line.separator"));
                line = br.readLine();
            }

        } catch (Exception errorObj) {
            System.err.println("Couldn't read file: " + filePath);
            errorObj.printStackTrace();
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
