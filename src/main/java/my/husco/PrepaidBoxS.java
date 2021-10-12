package my.husco;

/**
 * This class is for manipulating PrepaidBoxS.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class PrepaidBoxS extends PrepaidBoxEnvelope {

    private String size;
    private int weight;
    private double price;

    /**
     * This constructor is for class PrepaidBoxS.
     *
     * @param pbeType     One of the package type is PrepaidBoxS.
     * @param pbeSize     The size of PrepaidBoxS.
     * @param pbeWeight   The weight of PrepaidBoxS.
     * @param pbeQuantity The quantity of PrepaidBoxS.
     * @param pbePrice    The total price of PrepaidBoxS.
     */
    public PrepaidBoxS(String pbeType, String pbeSize, int pbeWeight, int pbeQuantity, double pbePrice) {
        super(pbeType, pbeSize, pbeWeight, pbeQuantity, pbePrice);
    }

    /**
     * Gets the size of PrepaidBoxS.
     *
     * @return The size of PrepaidBoxS.
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the weight of PrepaidBoxS.
     *
     * @return The weight of PrepaidBoxS.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the total price of PrepaidBoxS.
     *
     * @return The total price of PrepaidBoxS.
     */
    public double getPrice() {
        setPrice();
        return price;
    }

    /**
     * Sets the total price of PrepaidBoxS.
     */
    public void setPrice() {
        this.price = gram3Price();
    }

    /**
     * This method is for calculating the total price of PrepaidBoxS.
     *
     * @return The total price of PrepaidBoxS.
     */
    public double gram3Price() {
        size = "340mm x 250mm x 80mm";
        weight = 2000;
        price = 13.78 * getPbeQuantity();
        return price;
    }

}
