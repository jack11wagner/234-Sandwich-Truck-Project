public class Pastrami extends Sandwich {

    public Pastrami(){
        description = "Pastrami Sandwich";
    }
    @Override
    public double cost() {
        return SimSettings.PASTRAMI_SANDWICH_COST;
    }
}
