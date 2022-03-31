public class Lettuce extends CondimentDecorator{
    Sandwich sandwich;
    public Lettuce(Sandwich sandwich){
        this.sandwich = sandwich;
    }

    @Override
    public String getDescription() {
        if (sandwich instanceof CondimentDecorator)
            return sandwich.getDescription() + " Lettuce";
        return sandwich.getDescription() + " w/ Lettuce";
    }

    @Override
    public double cost() {
        return sandwich.cost() + SimSettings.LETTUCE_CONDIMENT_COST;
    }
}
