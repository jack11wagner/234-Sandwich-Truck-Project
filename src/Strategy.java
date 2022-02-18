public abstract class Strategy {


    private int getDistance(Integer[] location1, Integer[] location2) {
        int x1, y1, x2, y2;
        x1 = location1[0];
        y1 = location1[1];
        x2 = location2[0];
        y2 = location2[1];

        return (Math.abs(x1 - x2) + Math.abs(y1 - y2));

    }
}
