public class Player {
    String name;
    PlayingPiece playingPiece;

    public Player(String name, PlayingPiece piece) {
        this.name = name;
        this.playingPiece = piece;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayingPiece getPiece() {
        return playingPiece;
    }

    public void setPiece(PlayingPiece piece) {
        this.playingPiece = piece;
    }
}
