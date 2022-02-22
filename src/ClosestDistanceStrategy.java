//import java.util.*;
//
//public class ClosestDistanceStrategy extends Strategy{
//    private Queue<Order> orderQueue;
//    private ArrayList<Order> orderList;
//
//
//    public ClosestDistanceStrategy(OrderList orderList) {
//        this.orderQueue = new LinkedList<>();
//        this.orderList = orderList.getOrderList();
//    }
//
//    private Order getClosestOrder()
//    {
//        AddressConverter ac = new AddressConverter();
////        int[] truck_coords = {truckx, trucky};
//
//        double minDist = Integer.MAX_VALUE;
//        Order minOrder =orderList.get(0);
//        for(Order o: orderList){
//            double dist = getDistance(truck_coords, ac.convert(o.getOrderAddress()));
//            if (dist<minDist && !orderQueue.contains(o)) {
//                minDist = dist;
//                minOrder = o;
//            }
//        }
//        truckx = ac.convert(minOrder.getOrderAddress())[0];
//        trucky = ac.convert(minOrder.getOrderAddress())[1];
//        return minOrder;
//
//    }
//
//    public void createOrderQueue()
//    {
//        for(int i=0 ;i< orderList.size();i++){
//            Order closest = getClosestOrder();
//            orderQueue.add(closest);
//        }
//    }
//
//
//
//     public Order getNextOrder()
//     {
//        return orderQueue.poll();
//     }
//
//
//
//
//    @Override
//    public double getDistance(int[] location1, int[] location2) {
//        int x1, y1, x2, y2;
//        x1 = location1[0];
//        y1 = location1[1];
//        x2 = location2[0];
//        y2 = location2[1];
//
//        return (Math.abs(x1 - x2) + Math.abs(y1 - y2));
//    }
//
//    public int getQueueSize() {
//        return orderQueue.size();
//    }
//}
