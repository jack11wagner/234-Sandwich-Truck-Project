/**
 * Author: Jackson Wagner
 * Abstract Sandwich Class
 */
public abstract class Sandwich {

    String description = "Default Sandwich";


    public String getDescription()
    {
        /**
         * Parent Class Description Class
         */
        return description;
    }

    public void prepare() {
    }

    public abstract double cost();

}
