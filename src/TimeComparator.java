/*
Author: Jackson Wagner
This Class is a Comparator Class which is used to compare timestamps
Ascending Order
Used in our TimeBasedStrategy orderQueue generation
Edits by:
*/

import java.util.Comparator;

public class TimeComparator implements Comparator<Order> {
    /**
     * This Comparator Class is used in the Time Based Strategy Class
     */

    @Override
    public int compare(Order o1, Order o2) {
        /**
         * Comparator implemented method which compares two timestamp objects
         * Currently sorted in Ascending Order to change sort order simply change the returns of each conditional to its additive inverse (-1 to 1) vice versa
         */
        if (o1.getOrderTimestamp().after(o2.getOrderTimestamp()))
            // if First Timestamp is after the Second Timestamp
            return 1;
        else if (o1.getOrderTimestamp().before(o2.getOrderTimestamp()))
            // if First Timestamp is before the Second Timestamp
            return -1;
        else return 0;
    }
}
