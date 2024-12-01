import java.awt.*;

class Basket extends GameObject {
    private final Color borderColor = Color.DARK_GRAY;
    private final Color basketColor = new Color(100, 149, 237); // Warna biru estetis

    public Basket(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void move(int mouseX, int panelWidth) {
        this.x = Math.max(0, Math.min(mouseX - width / 2, panelWidth - width));
    }

    @Override
    public void update() {
        // Tidak diperlukan logika tambahan untuk keranjang
    }

    @Override
    public void render(Graphics g) {
        g.setColor(borderColor);
        g.fillRect(x - 5, y - 5, width + 10, height + 10); // Border keranjang
        g.setColor(basketColor);
        g.fillRect(x, y, width, height);
    }
}
