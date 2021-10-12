package my.husco;

/**
 * This class is for manipulating LG.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class LG extends PosEkspres {

    private String size;
    private int weight;
    private int thickness;
    private double price;

    /**
     * This constructor is for class LG.
     *
     * @param peType      One of the type of pos ekspres is LG.
     * @param peSize      The size of LG.
     * @param peWeight    The weight of LG.
     * @param peThickness The thickness of LG.
     * @param peQuantity  The quantity of LG.
     * @param pePrice     The total price of pos ekspres.
     */
    public LG(String peType, String peSize, int peWeight, int peThickness, int peQuantity, double pePrice) {
        super(peType, peSize, peWeight, peThickness, peQuantity, pePrice);
    }

    /**
     * Gets the size of LG.
     *
     * @return The size of LG.
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the weight of LG.
     *
     * @return The weight of LG.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the thickness of LG.
     *
     * @return The thickness of LG.
     */
    public int getThickness() {
        return thickness;
    }

    /**
     * Gets the total price of LG.
     *
     * @return The total price of LG (Pos Ekspres).
     */
    public double getPrice() {
        setPrice();
        return price;
    }

    /**
     * Sets the total price of LG.
     */
    public void setPrice() {
        this.price = price();
    }

    /**
     * This method is for calculating the total price of LG.
     *
     * @return The total price of LG (Pos Ekspres).
     */
    private double price() {
        size = "220mm x 110mm";
        weight = 100;
        thickness = 3;
        price = 3.18 * getPeQuantity();
        return price;
    }

}
