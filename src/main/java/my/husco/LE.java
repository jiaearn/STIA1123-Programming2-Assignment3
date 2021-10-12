package my.husco;

/**
 * This class is for manipulating LE.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class LE extends PosEkspres {

    private String size;
    private int weight;
    private int thickness;
    private double price;

    /**
     * This constructor is for class LE.
     *
     * @param peType      One of the type of pos ekspres is LE.
     * @param peSize      The size of LE.
     * @param peWeight    The weight of LE.
     * @param peThickness The thickness of LE.
     * @param peQuantity  The quantity of LE.
     * @param pePrice     The total price of pos ekspres.
     */
    public LE(String peType, String peSize, int peWeight, int peThickness, int peQuantity, double pePrice) {
        super(peType, peSize, peWeight, peThickness, peQuantity, pePrice);
    }

    /**
     * Gets the size of LE.
     *
     * @return The size of LE.
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the weight of LE.
     *
     * @return The weight of LE.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the thickness of LE.
     *
     * @return The thickness of LE.
     */
    public int getThickness() {
        return thickness;
    }

    /**
     * Gets the total price of LE.
     *
     * @return The total price of LE (Pos Ekspres).
     */
    public double getPrice() {
        setPrice();
        return price;
    }

    /**
     * Sets the total price of LE.
     */
    public void setPrice() {
        this.price = price();
    }

    /**
     * This method is for calculating the total price of LE.
     *
     * @return The total price of LE (Pos Ekspres).
     */
    private double price() {
        size = "229mm x 162mm";
        weight = 250;
        thickness = 5;
        price = 3.71 * getPeQuantity();
        return price;
    }

}
