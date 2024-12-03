import java.awt.*;

class Fruit extends GameObject {
    private final Color[] fruitColors = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.MAGENTA
    };
    private final Color color;
    private int score = 0;

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

    public int getScore(){
        if(color == fruitColors[0]){
            score = 3;
        }
        else if(color == fruitColors[1]){
            score = 5;
        }
        else if(color == fruitColors[2]){
            score = 8;
        }
        else if(color == fruitColors[3]){
            score = 11;
        }
        else if(color == fruitColors[4]){
            score = 15;
        }

        return score;
    }
}
