import java.util.ArrayList;

public abstract class Strategy {


    public abstract double getDistance(int[] location1, int[] location2);
    public abstract void createOrderQueue();
    public abstract Order getNextOrder();
}
