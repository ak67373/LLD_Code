import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private final Queue<Player> players;
    Dice dice;
    Board board;
    Boolean requireExactRollToWin;


    public Game(List<Player> players, Dice dice, Board board, Boolean requireExactRollToWin) {
        this.players = new LinkedList<>(players);
        this.dice = dice;
        this.board = board;
        this.requireExactRollToWin = requireExactRollToWin;
    }

    public void startGame() {
        System.out.println("Game Started -------");
        while (true) {
            Player player = players.poll();

            assert player != null;
            int currentPosition = player.getPosition();

            System.out.println(player.getName() + " current position is " + currentPosition);
            System.out.println("Dice rolled");

            int nextPosition = board.getNextPosition(currentPosition+ dice.roll());

            if (requireExactRollToWin && nextPosition > board.size) {
                System.out.println("Overshot the end, try again next turn");
            }
            else {
                System.out.println("Player " + player.getName() + " next position is " + nextPosition);
                if(nextPosition == board.size){
                    System.out.println("Player " + player.getName() + " Wins");
                    break;
                }
                player.setPosition(nextPosition);
            }

            players.offer(player);
        }
    }
}
