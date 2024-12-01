import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements MouseMotionListener, Runnable {
    private Thread gameThread;
    private boolean running = false;

    private Basket basket;
    private final ArrayList<Fruit> fruits = new ArrayList<>();
    private final Random random = new Random();

    private int score = 0;
    private int highScore = 0;

    // Warna dan font
    private final Font scoreFont = new Font("Comic Sans MS", Font.BOLD, 20);
    private final Color basketColor = new Color(54, 69, 79);
    private final GradientPaint backgroundGradient = new GradientPaint(
            0, 0, Color.CYAN, 0, 500, Color.PINK);

    public GamePanel() {
        this.setFocusable(true);
        this.addMouseMotionListener(this);

        // Inisialisasi keranjang
        basket = new Basket(200, 450, 100, 20);
    }

    public void startGame() {
        if (gameThread == null) {
            running = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Latar belakang gradasi
        g2d.setPaint(backgroundGradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Gambar keranjang
        basket.render(g2d);

        // Gambar buah
        for (Fruit fruit : fruits) {
            fruit.render(g2d);
        }

        // Tampilkan skor dengan font estetis
        g.setFont(scoreFont);
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 30);
        g.drawString("High Score: " + highScore, 10, 60);
    }

    @Override
    public void run() {
        while (running) {
            updateGameLogic();
            repaint();
            try {
                Thread.sleep(30); // Kontrol kecepatan permainan
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateGameLogic() {
        // Perbarui posisi buah
        for (int i = 0; i < fruits.size(); i++) {
            Fruit fruit = fruits.get(i);
            fruit.update();

            // Jika buah tertangkap oleh keranjang
            if (fruit.y + fruit.height >= basket.y &&
                fruit.x + fruit.width > basket.x &&
                fruit.x < basket.x + basket.width) {
                fruits.remove(i);
                score++;
                if (score > highScore) {
                    highScore = score;
                }
            }
            // Jika buah jatuh melewati layar
            else if (fruit.y > getHeight()) {
                fruits.remove(i);
                score = 0; // Reset skor jika gagal menangkap buah
            }
        }

        // Tambahkan buah baru secara acak
        if (random.nextInt(20) == 0) {
            int fruitX = random.nextInt(getWidth() - 30);
            fruits.add(new Fruit(fruitX, 0, 30, 30));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Tidak digunakan
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Perbarui posisi keranjang berdasarkan pergerakan mouse
        basket.move(e.getX(), getWidth());
    }
}
