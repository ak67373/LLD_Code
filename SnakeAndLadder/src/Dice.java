public class Dice {
    private int numberOfDice;
    private int faces;

    public Dice(int numberOfDice, int faces) {
        this.numberOfDice = numberOfDice;
        this.faces = faces;
    }

    public int roll(){
        int total =0 ;
        for(int i=1;i<=numberOfDice;i++){
            total += (int) (Math.random() * faces) + 1;
        }
        return total;
    }
}
