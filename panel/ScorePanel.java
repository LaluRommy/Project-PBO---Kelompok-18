package panel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.awt.event.*;
import model.Player;
import database.Database;;

public class ScorePanel extends JPanel {
    private Image backgroundImage;
    private JFrame frameInduk;
    private Database database = new Database();
    private ArrayList<Player> player;

    public ScorePanel(JFrame frame) {
        frameInduk = frame;
        try {
            backgroundImage = ImageIO.read(new File("assets/images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        JLabel header = new JLabel("Leaderboard", JLabel.CENTER);
        header.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        header.setForeground(Color.BLACK);
        add(header, BorderLayout.NORTH);
        
        JPanel isi = new JPanel();
        isi.setLayout(new BoxLayout(isi, BoxLayout.Y_AXIS));
        isi.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(isi);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);

        try {
            player = database.getPlayers(); // Mengambil data player dari database
            sorting(player); // melakukan sorting dari score tertinggi hingga terkecil
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Menampilkan setiap data player
        for(Player i : player){
            JPanel playerPanel = new JPanel(new BorderLayout());
            playerPanel.setOpaque(false);

            // Nama Player
            JLabel nameLabel = new JLabel(i.getNama());
            nameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            nameLabel.setForeground(Color.BLACK);
            nameLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
            playerPanel.add(nameLabel, BorderLayout.WEST);

            // Score Player
            JLabel scoreLabel = new JLabel(String.valueOf(i.getScore()), JLabel.RIGHT);
            scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            scoreLabel.setForeground(Color.BLACK);
            scoreLabel.setBorder(new EmptyBorder(0, 0, 0, 20));
            playerPanel.add(scoreLabel, BorderLayout.EAST);

            isi.add(playerPanel);
        }

        JLabel menu = new JLabel("Back to Main Menu", JLabel.CENTER);
        menu.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
        menu.setForeground(Color.BLACK);

        menu.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                JFrame frame = new JFrame();
                frameInduk.dispose();
                new Menu(frame);
            }
        });
        add(menu, BorderLayout.SOUTH);

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
    }

    // Method Sorting (Bubble Sort)
    public void sorting(ArrayList<Player> players){
        for(int i = 0; i < players.size(); i++){
            for(int j = i + 1; j < players.size(); j++){
                if(players.get(i).getScore() < players.get(j).getScore()){
                    Player temp;
                    temp = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, temp);
                }
            }
        }
    }
}
