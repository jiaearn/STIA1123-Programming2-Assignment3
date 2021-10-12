package my.husco;

/**
 * This class is for manipulating PrepaidBoxM.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class PrepaidBoxM extends PrepaidBoxEnvelope {

    private String size;
    private int weight;
    private double price;

    /**
     * This constructor is for class PrepaidBoxM.
     *
     * @param pbeType     One of the package type is PrepaidBoxM.
     * @param pbeSize     The size of PrepaidBoxM.
     * @param pbeWeight   The weight of PrepaidBoxM.
     * @param pbeQuantity The quantity of PrepaidBoxM.
     * @param pbePrice    The total price of PrepaidBoxM.
     */
    public PrepaidBoxM(String pbeType, String pbeSize, int pbeWeight, int pbeQuantity, double pbePrice) {
        super(pbeType, pbeSize, pbeWeight, pbeQuantity, pbePrice);
    }

    /**
     * Gets the size of PrepaidBoxM.
     *
     * @return The size of PrepaidBoxM.
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the weight of PrepaidBoxM.
     *
     * @return The weight of PrepaidBoxM.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the total price of PrepaidBoxM.
     *
     * @return The total price of PrepaidBoxM.
     */
    public double getPrice() {
        setPrice();
        return price;
    }

    /**
     * Sets the total price of PrepaidBoxM.
     */
    public void setPrice() {
        this.price = gram4Price();
    }

    /**
     * This method is for calculating the total price of PrepaidBoxM.
     *
     * @return The total price of PrepaidBoxM.
     */
    public double gram4Price() {
        size = "340mm x 250mm x 150mm";
        weight = 5000;
        price = 21.20 * getPbeQuantity();
        return price;
    }

}
