import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
    OrderList ol = new OrderList();
    for(int i =0;i<15;i++){
        ol.addOrder(new Order("2022-01-01"));
    }
    ol.writeOrdersToFile();
    Window simWindow = new Window();
    }
}
