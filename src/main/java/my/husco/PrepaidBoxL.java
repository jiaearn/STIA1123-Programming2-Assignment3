package my.husco;

/**
 * This class is for manipulating PrepaidBoxL.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class PrepaidBoxL extends PrepaidBoxEnvelope {

    private String size;
    private int weight;
    private double price;

    /**
     * This constructor is for class PrepaidBoxL.
     *
     * @param pbeType     One of the package type is PrepaidBoxL.
     * @param pbeSize     The size of PrepaidBoxL.
     * @param pbeWeight   The weight of PrepaidBoxL.
     * @param pbeQuantity The quantity of PrepaidBoxL.
     * @param pbePrice    The total price of PrepaidBoxL
     */
    public PrepaidBoxL(String pbeType, String pbeSize, int pbeWeight, int pbeQuantity, double pbePrice) {
        super(pbeType, pbeSize, pbeWeight, pbeQuantity, pbePrice);
    }

    /**
     * Gets the size of PrepaidBoxL.
     *
     * @return The size of PrepaidBoxL.
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the weight of PrepaidBoxL.
     *
     * @return The weight of PrepaidBoxL.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the total price of PrepaidBoxL.
     *
     * @return The total price of PrepaidBoxL.
     */
    public double getPrice() {
        setPrice();
        return price;
    }

    /**
     * Sets the total price of PrepaidBoxL.
     */
    public void setPrice() {
        this.price = gram5Price();
    }

    /**
     * This method is for calculating the total price of PrepaidBoxL.
     *
     * @return The total price of PrepaidBoxL.
     */
    public double gram5Price() {
        size = "380mm x 320mm x 200mm";
        weight = 10000;
        price = 31.80 * getPbeQuantity();
        return price;
    }

}
