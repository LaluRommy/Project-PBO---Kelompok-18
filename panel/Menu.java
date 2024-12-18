package panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JFrame frame;

    // Initialize the GUI
    public Menu(JFrame newFrame) {
        frame = newFrame;
        frame = new JFrame("FruitBasket Dash");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null); // Center window
        frame.setLayout(new BorderLayout());

        // Panel for the background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                ImageIcon startBg = new ImageIcon(
                        new ImageIcon("assets/images/Menubackground.png")
                                .getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH));
                g.drawImage(startBg.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setOpaque(false); // Make panel transparent for the background image

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make this panel transparent as well
        buttonPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering

        // Start button with image
        ImageIcon startBt = new ImageIcon(
                new ImageIcon("assets/images/play.png")
                        .getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH));
        JButton startButton_1 = new JButton(startBt);
        startButton_1.setFocusable(false);
        startButton_1.setOpaque(false);
        startButton_1.setContentAreaFilled(false);
        startButton_1.setBorderPainted(false); // Remove border around the button
        // startButton_1.setPreferredSize(new Dimension(200, 300));
        startButton_1.setBorder(new EmptyBorder(320, 0, 0, 0));

        // Leaderboard button with image
        ImageIcon startBt_2 = new ImageIcon(
                new ImageIcon("assets/images/leaderboard.png")
                        .getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH));
        JButton leaderboardButoon = new JButton(startBt_2);
        leaderboardButoon.setFocusable(false);
        leaderboardButoon.setOpaque(false);
        leaderboardButoon.setContentAreaFilled(false);
        leaderboardButoon.setBorderPainted(false); // Remove border around the button
        // leaderboardButoon.setPreferredSize(new Dimension(200, 400));
        leaderboardButoon.setBorder(new EmptyBorder(0, 0, 100, 0));

        // Add action listener to the start button
        startButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to start panel when start button is clicked
                initStartPanel();
            }
        });

        // Add action listener to the leaderboard button
        leaderboardButoon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show leaderboard when leaderboard button is clicked
                showLeaderboard();
            }
        });

        // Create GridBagConstraints to center the buttons
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER; // Center the buttons
        buttonPanel.add(startButton_1, gbc); // Add the start button to the panel
        gbc.gridy = 1; // Move leaderboard button to the next line
        buttonPanel.add(leaderboardButoon, gbc); // Add the leaderboard button to the panel

        // Add the button panel to the background panel
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the background panel to the frame
        frame.add(backgroundPanel);

        frame.setVisible(true);
    }

    // This method will initialize the start panel when the button is clicked
    private void initStartPanel() {
        frame.dispose();

        // Now, launch the game (BasketDash)
        launchGame();
    }

    // Method to start the game (BasketDash)
    private void launchGame() {
        // Create an instance of BasketDash and start the game
        JFrame gameFrame = new JFrame("Basket Dash");
        gameFrame.setSize(1000, 600);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);

        GamePanel gamePanel = new GamePanel(gameFrame); // Assuming GamePanel class is available
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);

        gamePanel.startGame(); // Start the game
    }

    // Method to display the leaderboard
    private void showLeaderboard() {
        frame.getContentPane().removeAll();
        frame.add(new ScorePanel(frame));
        frame.revalidate();
        frame.repaint();
    }
}
