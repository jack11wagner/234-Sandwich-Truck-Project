/**
 * Author: Nikolas Kovacs
 * This is an abstract class that is meant to be inherited from in order to implement different navigation modes
 */
public abstract class NavigationStrategy {
//    private Truck truck;
//
//    /**
//     * This method is only necessary to give the Strategy the truck, which cannot be done at instantiation time
//     * Truck MUST call this method in its constructor
//     * @param truck, given truck so it can control its movements
//     */
//    public void setTruck(Truck truck) {
//        this.truck = truck;
//    }

    public abstract void calculateNavInstructions(int[] coords);
}
