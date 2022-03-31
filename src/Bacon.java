public class Bacon extends CondimentDecorator{
    private Sandwich sandwich;

    public Bacon(Sandwich sandwich){
        this.sandwich = sandwich;
    }

    @Override
    public String getDescription() {
        if (sandwich instanceof CondimentDecorator)
            return sandwich.getDescription() + " Bacon";
        return sandwich.getDescription() + "w/ Bacon";
    }

    @Override
    public double cost() {
        return sandwich.cost() + SimSettings.BACON_CONDIMENT_COST;
    }
}
