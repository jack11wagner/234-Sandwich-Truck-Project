import java.util.ArrayList;

public class OrderList {
    private ArrayList<Order> orderList;
    public OrderList(){
        orderList = new ArrayList<>();
    }

    public void addOrder(Order e)
    {
        orderList.add(e);
    }

    @Override
    public String toString() {
        String result ="(";
        for(Order e:orderList){
            result+= e.getFullOrderDetails()+", ";
        }
        result+=")";
        return result;
    }
}
