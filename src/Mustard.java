public class Mustard extends CondimentDecorator{
    private Sandwich sandwich;
    public Mustard(Sandwich sandwich){
        this.sandwich = sandwich;
    }
    @Override
    public String getDescription() {
        if (sandwich instanceof CondimentDecorator)
            return sandwich.getDescription() + " Mustard";
        return sandwich.getDescription() + " w/ Mustard";
    }

    @Override
    public double cost() {
        return sandwich.cost() + SimSettings.MUSTARD_CONDIMENT_COST;
    }
}
