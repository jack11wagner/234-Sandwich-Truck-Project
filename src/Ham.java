public class Ham extends Sandwich{
    public Ham(){
        description = "Ham Sandwich";
    }
    @Override
    public double cost() {
        return SimSettings.HAM_SANDWICH_COST;
    }
}
