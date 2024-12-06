import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class Fruit extends GameObject {
    private final Image[] fruitImages = new Image[4]; // Array for fruit images
    private final Image image;
    private int score = 0;

    public Fruit(int x, int y, int width, int height) {
        super(x, y, width, height);
        // Load images for fruits
        try {
            fruitImages[0] = ImageIO.read(new File("assets/images/apel.png"));
            fruitImages[1] = ImageIO.read(new File("assets/images/semangka.png"));
            fruitImages[2] = ImageIO.read(new File("assets/images/pisang.png"));
            fruitImages[3] = ImageIO.read(new File("assets/images/dynamite.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Randomly select a fruit image
        this.image = fruitImages[(int) (Math.random() * fruitImages.length)];
    }

    @Override
    public void update() {
        this.y += 5; // Buah turun ke bawah
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null); // Draw fruit image
    }

    public int getScore() {
        if (image == fruitImages[0]) {
            score = 3;
        } else if (image == fruitImages[1]) {
            score = 8;
        } else if (image == fruitImages[2]) {
            score = 5;
        }
        return score;
    }

    public boolean hitDynamite(){
        if (image == fruitImages[3]) {
            return true;
        } else 
            return false;
    }
}
