public class WhiteBread extends CondimentDecorator{
    private Sandwich sandwich;

    public WhiteBread(Sandwich sandwich){
        this.sandwich = sandwich;
    }

    @Override
    public String getDescription() {
        if (sandwich instanceof CondimentDecorator)
            return sandwich.getDescription() + " White Bread";
        return sandwich.getDescription() + " w/ White Bread";
    }

    @Override
    public double cost() {
        return sandwich.cost() + SimSettings.WHITE_BREAD_CONDIMENT_COST;
    }
}

