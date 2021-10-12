package my.husco;

/**
 * This class is for manipulating LD.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class LD extends PosEkspres {

    private String size;
    private int weight;
    private int thickness;
    private double price;

    /**
     * This constructor is for class LD.
     *
     * @param peType      One of the type of pos ekspres is LD.
     * @param peSize      The size of LD.
     * @param peWeight    The weight of LD.
     * @param peThickness The thickness of LD.
     * @param peQuantity  The quantity of LD.
     * @param pePrice     The total price of pos ekspres.
     */
    public LD(String peType, String peSize, int peWeight, int peThickness, int peQuantity, double pePrice) {
        super(peType, peSize, peWeight, peThickness, peQuantity, pePrice);
    }

    /**
     * Gets the size of LD.
     *
     * @return The size of LD.
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the weight of LD.
     *
     * @return The weight of LD.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the thickness of LD.
     *
     * @return The thickness of LD.
     */
    public int getThickness() {
        return thickness;
    }

    /**
     * Gets the total price of LD.
     *
     * @return The total price of LD (Pos Ekspres).
     */
    public double getPrice() {
        setPrice();
        return price;
    }

    /**
     * Sets the total price of LD.
     */
    public void setPrice() {
        this.price = price();
    }

    /**
     * This method is for calculating the total price of LD.
     *
     * @return The total price of LD (Pos Ekspres).
     */
    private double price() {
        size = "353mm x 250mm";
        weight = 500;
        thickness = 10;
        price = 4.77 * getPeQuantity();
        return price;
    }

}
