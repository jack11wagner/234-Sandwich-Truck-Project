import java.sql.Timestamp;
import java.util.Comparator;

public class TimeComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        // Currently sorted in Ascending Order to change sort order simply change the returns of each conditional to its additive inverse (-1 to 1) vice versa
        if (o1.getOrderTimestamp().compareTo(o2.getOrderTimestamp())>0)
            // if First Timestamp is after the Second Timestamp
            return 1;
        else if (o1.getOrderTimestamp().compareTo(o2.getOrderTimestamp())<0)
            // if First Timestamp is before the Second Timestamp
            return -1;
        else return 0;
    }
}
