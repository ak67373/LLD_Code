import java.util.HashMap;
import java.util.Map;

public class Board {
    int size;
    Map<Integer, Integer> snakes;
    Map<Integer, Integer> ladders;

    public Board(int size) {
        this.size = size;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
    }

    public void addSnake(int start, int end) {
        if (start < end) throw new IllegalArgumentException("Invalid Snake");
        snakes.put(start, end);

    }

    public void addLadder(int start, int end) {
        if (start > end) throw new IllegalArgumentException("Invalid Ladder");
        ladders.put(start, end);

    }

    public int getNextPosition(int pos) {
        if (snakes.containsKey(pos)) return snakes.get(pos);
        if (ladders.containsKey(pos)) return ladders.get(pos);
        return pos;
    }

    public int getSize() {
        return size;
    }


}
