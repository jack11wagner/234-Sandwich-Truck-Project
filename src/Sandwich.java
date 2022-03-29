public abstract class Sandwich {
    String description = "Default Sandwich";

    public String getDescription()
    {
        return description;
    }

    public abstract double cost();

}
