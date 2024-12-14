package gameObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Basket extends GameObject {
    private Image basketImage;

    public Basket(int x, int y, int width, int height) {
        super(x, y, width, height);
        // Load the basket image
        try {
            basketImage = ImageIO.read(new File("assets/images/keranjang.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(int mouseX, int panelWidth) {
        this.x = Math.max(0, Math.min(mouseX - width / 2, panelWidth - width));
    }

    @Override
    public void update() {
        // No additional logic for the basket
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(basketImage, x, y, width, height, null); // Draw basket image
    }
}
