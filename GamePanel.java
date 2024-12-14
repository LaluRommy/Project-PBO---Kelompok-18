import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements MouseMotionListener, Runnable {
    private JFrame mainFrame = new JFrame();
    private Thread gameThread;
    private boolean running = false;

    private Basket basket;
    private final ArrayList<Fruit> fruits = new ArrayList<>();
    private final Random random = new Random();

    private int score = 0;
    private int highScore = 0;
    private int nyawa = 3;
    private int maxFruit = 1;

    // Load the background image
    private Image backgroundImage;

    // Audio-related fields
    private Clip backgroundMusic;

    public GamePanel(JFrame mainFrame) {
        setDoubleBuffered(true);
        this.mainFrame = mainFrame;
        this.setFocusable(true);
        this.addMouseMotionListener(this);

        // Initialize the basket
        basket = new Basket(0, 450, 120, 100); // Start basket at the top for now

        // Load background image
        try {
            backgroundImage = ImageIO.read(new File("assets/images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load and play background music
        playBackgroundMusic();
    }

    private void playBackgroundMusic() {
        try {
            // Load the music.wav file
            File musicFile = new File("assets/music/backsound.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music indefinitely
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
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

        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        // Draw the basket at the bottom
        basket.render(g);

        // Draw the fruits
        for (Fruit fruit : fruits) {
            fruit.render(g);
        }

        // Display the score with an aesthetic font
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 30);
        g.drawString("High Score: " + highScore, 10, 60);
        g.drawString("Lives: " + nyawa, 10, 90);
    }

    @Override
    public void run() {
        int fps = 30;
        long time = 1000000000 / fps;

        while (running) {
            long starTime = System.nanoTime();
            try {
                updateGameLogic();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            repaint();

            long sleepTime = time - (System.nanoTime() - starTime);
            if(sleepTime > 0){
                try {
                    Thread.sleep(sleepTime / 1000000); // Control the speed of the game
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateGameLogic() throws SQLException {
        // Update the position of the fruits
        for (int i = 0; i < fruits.size(); i++) {
            Fruit fruit = fruits.get(i);
            fruit.update();

            // If the fruit is caught by the basket
            if (fruit.y + fruit.height >= basket.y &&
                    fruit.x + fruit.width > basket.x &&
                    fruit.x < basket.x + basket.width) {

                // Jika yang terkena adalah bom
                if (fruit.hitDynamite()) {
                    nyawa--;
                    gameOver();
                }

                fruits.remove(i);
                score += fruit.getScore();
                if (score > highScore) {
                    highScore = score;
                }
            }
            // If the fruit falls off the screen
            else if (fruit.y > getHeight()) {
                fruits.remove(i);
                gameOver();
                // nyawa--;
            }
        }

        // Add a new fruit randomly
        if (fruits.size() < maxFruit && random.nextInt(20) == 0) {
            int fruitX = random.nextInt(getWidth() - 5);
            fruits.add(new Fruit(fruitX, 0, 60, 60));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Update the basket position based on mouse movement
        basket.move(e.getX(), getWidth());
    }

    // Method to stop the background music
    private void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }

    // Method to stop the game after Game Over
    public void stopGame() {
        running = false;
        stopBackgroundMusic();
        try {
            if (gameThread != null) {
                gameThread.join(); // Wait for the thread to finish
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void gameOver() throws SQLException {
        if (nyawa == 0) {
            try {
                Database database = new Database();
                String nama = JOptionPane.showInputDialog(null, "Game Over!!! \n Masukkan Nama Anda", null);
                if (nama == null || nama.trim().isEmpty()) {
                    nama = "Player";
                }
                    
                Player player = new Player(nama, score);
                database.addPlayer(player);
                mainFrame.getContentPane().removeAll();
                mainFrame.add(new ScorePanel(mainFrame));
                mainFrame.revalidate();
                mainFrame.repaint();
                stopGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
