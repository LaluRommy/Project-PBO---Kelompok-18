package gameObject;

import java.awt.*;

public abstract class GameObject {
    public int x, y, width, height;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update(); // Memperbarui posisi atau logika

    public abstract void render(Graphics g); // Menggambar objek ke layar
}
