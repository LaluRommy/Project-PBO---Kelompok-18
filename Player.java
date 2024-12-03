public class Player {
    private String nama;
    private int score;

    public Player(String nama, int score){
        this.nama = nama;
        this.score = score;
    }

    public String getNama(){
        return nama;
    }

    public int getScore(){
        return score;
    }
}
