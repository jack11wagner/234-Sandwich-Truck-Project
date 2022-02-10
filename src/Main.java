public class Main {

    public static void main(String[] args) {
    OrderList ol = new OrderList();
    for(int i =0;i<15;i++){
        ol.addOrder(new Order("2022-01-01"));
    }
    System.out.println(ol);

    }
}
