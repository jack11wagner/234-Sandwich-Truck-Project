public abstract class NavigationStrategy {
    private Truck truck;

    /**
     * This method is only necessary to give the Strategy the truck, which cannot be done at instantiation time
     * @param truck, given truck so it can control its movements
     */
    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public void moveToDestination() {}
}
