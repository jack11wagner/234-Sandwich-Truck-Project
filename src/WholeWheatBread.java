public class WholeWheatBread extends CondimentDecorator{
    private Sandwich sandwich;

    public WholeWheatBread(Sandwich sandwich){
        this.sandwich = sandwich;
    }

    @Override
    public String getDescription() {
        if (sandwich instanceof CondimentDecorator)
            return sandwich.getDescription() + " Whole Wheat Bread";
        return sandwich.getDescription() + " w/ Whole Wheat Bread";
    }

    @Override
    public double cost() {
        return sandwich.cost() + SimSettings.WHOLE_WHEAT_BREAD_CONDIMENT_COST;
    }
}
