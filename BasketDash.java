import javax.swing.*;

public class BasketDash {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Basket Dash");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GamePanel gamePanel = new GamePanel(frame);
        frame.add(gamePanel);
        frame.setVisible(true);

        gamePanel.startGame();
    }
}
