import javax.swing.*;

public class BasketDash {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Basket Dash");
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        gamePanel.startGame();
    }
}
