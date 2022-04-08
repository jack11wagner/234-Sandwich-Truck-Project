/**
 * Author: Jackson Wagner
 * Condiments attach to existing Sandwich instances
 */
public class Lettuce extends CondimentDecorator{
    private Sandwich sandwich;
    private String condimentType;
    public Lettuce(Sandwich sandwich){
        /**
         * Generic Lettuce Condiment Object - this class attaches to Sandwich instances using the
         * Decorator design pattern
         */
        this.sandwich = sandwich;
        this.condimentType = "Lettuce";
    }
    @Override
    public void prepare(){
        /**
         * Modeling the adding of a condiment by printing out the status of
         * the condiments
         */
        sandwich.prepare();
        System.out.println("Adding " + condimentType);
    }

    @Override
    public String getDescription() {
        /**
         * This simply adds onto the Sandwich objects description
         * I added a condition that if a condiment has already been linked to a sandwich we
         * don't add a w/ to the description
         */
        if (sandwich instanceof CondimentDecorator)
            return sandwich.getDescription() + " "+ condimentType;
        return sandwich.getDescription() + " w/ " + condimentType;
    }

    public double cost() {
        /**
         * Returns the running cost of the sandwich as well as any previously added Condiments
         */
        return sandwich.cost() + SimSettings.LETTUCE_CONDIMENT_COST;
    }
}
