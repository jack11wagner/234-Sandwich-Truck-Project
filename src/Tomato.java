public class Tomato extends CondimentDecorator{
    private Sandwich sandwich;

    public Tomato(Sandwich sandwich){
        this.sandwich = sandwich;
    }

    @Override
    public String getDescription() {
        if (sandwich instanceof CondimentDecorator)
            return sandwich.getDescription() + " Tomato";
        return sandwich.getDescription() + " w/ Tomato";
    }

    @Override
    public double cost() {
        return sandwich.cost() + SimSettings.TOMATO_CONDIMENT_COST;
    }
}
