/**
 * Author: Jackson Wagner
 * Sandwiches should be instantiated before any Condiments are attached
 */
public class Ham extends Sandwich{
    public Ham(){
        /**
         * Generic Ham Sandwich Object
         */
        description = "Ham Sandwich";
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
        return SimSettings.HAM_SANDWICH_COST;
    }
}
