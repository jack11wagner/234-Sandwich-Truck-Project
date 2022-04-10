import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class DistanceComparer {

    private OrderList ol;


    public DistanceComparer(OrderList ol) {
        this.ol = ol;
    }

    public AbstractMap<String, Integer> getStrategyTimes() {
        /**
         * Finds the quickest combination of strategies to complete all orders and returns them in order of quickest to slowest
         * The key is a string contains the indexes of the order strat and nav strat separated by a space,
         * and the value is the sum of the distances of the nav instructions
         */
        HashMap<String, Integer> quickestStrategies = new HashMap<>();
        NavigationStrategy navStrat;
        OrderStrategy orderStrat;
        for (int i = 0; i < SimSettings.allOrderStrategies.length; i++) {
            for (int j = 0; j < SimSettings.allNavStrategies.length; j++) {
                orderStrat = SimSettings.allOrderStrategies[i];
                orderStrat.setOrderList(ol);
                orderStrat.createOrderQueue();
                navStrat = SimSettings.allNavStrategies[j];

                ArrayList<int[]> orderDestinationsInOrder = new ArrayList<>();
                AddressConverter addConverter = new AddressConverter();
                Order nextOrder;
                String address;
                // populate orderDestinationsInOrder list according to the OrderStrategy
                while ((nextOrder = orderStrat.getNextOrder()) != null) { // fetch next order, null means empty
                    String[] splittedOrder = splitOrder(nextOrder.toString()); // split the order into the address and food order
                    address = splittedOrder[0];
                    //foodOrder = splittedOrder[1]; // food order commented out until future
                    orderDestinationsInOrder.add(addConverter.convert(address)); // convert the address into a pair of coordinates and append
                }

                Collection<int[]> navInstructions = navStrat.calculateNavInstructions(-1, new int[]{SimSettings.INITIAL_TRUCK_X, SimSettings.INITIAL_TRUCK_Y}, orderDestinationsInOrder);
                int distance = sumDistances(navInstructions);
                quickestStrategies.put(i + " " + j, distance / SimSettings.ROAD_SPACING); // one block 1 mile, so road spacing = 1 mile
            }
        }
        return quickestStrategies;
    }

    private int sumDistances(Collection<int[]> navInstructions) {
        int sum = 0;
        for (int[] ints : navInstructions) {
            if (ints[1] != -1)
                sum += ints[1];
        }
        return sum;
    }

    private String[] splitOrder(String order) {
        /**
         * spilts the order into individual strings for the address and food order, given in the whole order string,
         * so that the address and food order can be used separately
         * @param: String order - the whole string order that contains the time, address, and food order
         * @return: an array of two strings corresponding to the address string and the food order string
         */
        String[] splitOrderArray;
        String address;
        String foodOrder;
        splitOrderArray = order.split(",");
        address =  splitOrderArray[1];
        foodOrder = splitOrderArray[2];
        return new String[]{address, foodOrder};
    }
}
