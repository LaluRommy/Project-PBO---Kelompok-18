import java.awt.*;

class Fruit extends GameObject {
    private final Color[] fruitColors = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.MAGENTA
    };
    private final Color color;

    public Fruit(int x, int y, int width, int height) {
        super(x, y, width, height);
        // Pilih warna buah secara acak
        this.color = fruitColors[(int) (Math.random() * fruitColors.length)];
    }

    @Override
    public void update() {
        this.y += 5; // Buah turun ke bawah
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height); // Buah berbentuk oval
    }
}
