public class Mayo extends CondimentDecorator{
    Sandwich sandwich;
    public Mayo(Sandwich sandwich){
        this.sandwich = sandwich;
    }

    @Override
    public String getDescription() {
        if (sandwich instanceof CondimentDecorator)
            return sandwich.getDescription() + " Mayo";
        return sandwich.getDescription() + " w/ Mayo";
    }

    @Override
    public double cost() {
        return sandwich.cost() + SimSettings.MAYO_CONDIMENT_COST;
    }
}
