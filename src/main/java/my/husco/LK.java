package my.husco;

/**
 * This class is for manipulating LK.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class LK extends PosEkspres {

    private String size;
    private int weight;
    private int thickness;
    private double price;

    /**
     * This constructor is for class LK.
     *
     * @param peType      One of the type of pos ekspres is LK.
     * @param peSize      The size of LK.
     * @param peWeight    The weight of LK.
     * @param peThickness The thickness of LK.
     * @param peQuantity  The quantity of LK.
     * @param pePrice     The total price of pos ekspres.
     */
    public LK(String peType, String peSize, int peWeight, int peThickness, int peQuantity, double pePrice) {
        super(peType, peSize, peWeight, peThickness, peQuantity, pePrice);
    }

    /**
     * Gets the size of LK.
     *
     * @return The size of LK.
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the weight of LK.
     *
     * @return The weight of LK.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the thickness of LK.
     *
     * @return The thickness of LK.
     */
    public int getThickness() {
        return thickness;
    }

    /**
     * Gets the total price of LK.
     *
     * @return The total price of LK (Pos Ekspres).
     */
    public double getPrice() {
        setPrice();
        return price;
    }

    /**
     * Sets the total price of LK.
     */
    public void setPrice() {
        this.price = price();
    }

    /**
     * This method is for calculating the total price of LK.
     *
     * @return The total price of LK (Pos Ekspres).
     */
    private double price() {
        size = "340mm x 250mm";
        weight = 1000;
        thickness = 25;
        price = 7.42 * getPeQuantity();
        return price;
    }

}
