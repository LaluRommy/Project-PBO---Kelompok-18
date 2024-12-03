import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class ScorePanel extends JPanel{
    private final GradientPaint backgroundGradient = new GradientPaint(0, 0, Color.CYAN, 0, 500, Color.PINK);

    public ScorePanel(){
        setVisible(true);
    }

    

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        // Latar belakang gradasi
        g2d.setPaint(backgroundGradient);
    }   
}
