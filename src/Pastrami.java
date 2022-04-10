/**
 * Author: Jackson Wagner
 * Sandwiches should be instantiated before any Condiments are attached
 */

public class Pastrami extends Sandwich {

    public Pastrami(){
        /**
         * Generic Pastrami Sandwich Object
         */
        description = "Pastrami Sandwich";
    }
    public void prepare() {
        /**
         * Modeling of the Making of the Sandwich by printing the status of the sandwich
         */
        System.out.println("Making " + this.getDescription());
    }

    @Override
    public double cost() {
        /**
         * @returns the cost of the sandwich
         */
        return SimSettings.PASTRAMI_SANDWICH_COST;
    }

}
