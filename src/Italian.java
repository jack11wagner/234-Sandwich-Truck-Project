public class Italian extends Sandwich{
    public Italian(){
        description = "Italian Sandwich";
    }
    @Override
    public double cost() {
        return SimSettings.ITALIAN_SANDWICH_COST;
    }
}
