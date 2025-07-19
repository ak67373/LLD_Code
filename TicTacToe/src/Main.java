
public class Main {
    public static void main(String[] args) {
        System.out.println("Game Started ...");

        Game game = new Game(3);
        System.out.println("Game Winner is " + game.startGame());

    }
}