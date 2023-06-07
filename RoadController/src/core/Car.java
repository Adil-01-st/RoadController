package core;

public class Car {
    public String number;
    public int height;
    public double weight;
    public boolean hasVehicle;
    public boolean isSpecial;

    public String toString() {
        String special = isSpecial ? "Special transport " : "";
        return "\n=========================================\n" +
            special + "Car with number " + number +
            ":\n\tHeight: " + height + " mm\n\tWeight: " + weight + " kg";
    }
}