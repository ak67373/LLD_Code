import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //initialize board
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the board size");
        int size = sc.nextInt();
        Board board = new Board(size);

        System.out.println("Enter the total snake and its from to");
        int snakeNo = sc.nextInt();

        while(snakeNo-- >0){

        }

        // add snakes and ladders
        board.addSnake(90, 70);
        board.addSnake(80, 50);
        board.addLadder(50, 79);
        board.addLadder(60, 70);

        //Players
        List<Player> players = Arrays.asList(new Player("Ashu"), new Player("Abhi"));
        //Dice
        Dice dice = new Dice(2, 6);
        //Game
        Game game = new Game(players, dice, board, true);
        game.startGame();

    }
}