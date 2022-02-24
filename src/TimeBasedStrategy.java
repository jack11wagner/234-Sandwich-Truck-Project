import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class TimeBasedStrategy extends Strategy{
    private final PriorityQueue<Order> orderQueue;
    private final ArrayList<Order> orderList;

    public TimeBasedStrategy(OrderList orderList){
        orderQueue = new PriorityQueue<>(new TimeComparator());
        this.orderList = orderList.getOrderList();


    }
    public PriorityQueue<Order> getOrderQueue()
    {
        return orderQueue;
    }

    @Override
    public double getDistance(int[] location1, int[] location2) {
        return 0;
    }

    @Override
    public void createOrderQueue() {
        orderQueue.addAll(orderList);
    }

    @Override
    public Order getNextOrder() {
        return orderQueue.poll();
    }
}
