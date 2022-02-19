import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ClosestDistanceStrategy extends Strategy{
    private Queue<Order> orderQueue;
    private OrderList orderList;
    private Truck truck;
    private int truckx;
    private int trucky;


    public ClosestDistanceStrategy(Truck truck, OrderList orderList) {
        this.orderQueue = new PriorityQueue<>();
        this.orderList = orderList;
        this.truck = new Truck(new Window(), orderList);
        truckx = 5;
        trucky = 5;

    }

    public Order getClosestOrder()
    {
        AddressConverter ac = new AddressConverter();
        Order firstOrder = orderList.getOrderList().get(0);
        int[] truck_coords = {truckx, trucky};
        System.out.println(ac.convert(firstOrder.getFullOrderDetails()));
        double minDist = getDistance(truck_coords, ac.convert(firstOrder.getFullOrderDetails()));
        System.out.println(minDist);
        Order minOrder = firstOrder;
        for(Order o: orderList.getOrderList()){
            System.out.println(o);
            System.out.println(ac.convert(o.getFullOrderDetails()));
            double dist = getDistance(truck_coords, ac.convert(o.getFullOrderDetails()));
            System.out.println(dist);
            if (dist<minDist && !orderQueue.contains(minOrder))
                minDist = dist;
                minOrder = o;
        }
        System.out.println(minDist);
        truckx = ac.convert(minOrder.getFullOrderDetails()).get(0);
        trucky = ac.convert(minOrder.getFullOrderDetails()).get(1);
        return minOrder;

    }

    public void createOrderQueue()
    {
        for(int i=0 ;i< orderList.getNumberOfOrders();i++){
            Order closest = getClosestOrder();
            orderQueue.add(closest);
        }
    }



     public void getNextOrder()
     {
        orderQueue.poll();
     }



    @Override
    public double getDistance(int[] location1, ArrayList<Integer> location2) {
        int x1, y1, x2, y2;
        x1 = location1[0];
        y1 = location1[1];
        x2 = location2.get(0);
        y2 = location2.get(1);

        return (Math.abs(x1 - x2) + Math.abs(y1 - y2));
    }
}
