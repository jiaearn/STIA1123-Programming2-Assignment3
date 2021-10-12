package my.husco;

/**
 * This class is for manipulating EnvelopeS.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class EnvelopeS extends PrepaidBoxEnvelope {

    private String size;
    private int weight;
    private double price;

    /**
     * This constructor is for class EnvelopeS.
     *
     * @param pbeType     One of the package type is EnvelopeS.
     * @param pbeSize     The size of EnvelopeS.
     * @param pbeWeight   The weight of EnvelopeS.
     * @param pbeQuantity The quantity of EnvelopeS.
     * @param pbePrice    The total price of EnvelopeS.
     */
    public EnvelopeS(String pbeType, String pbeSize, int pbeWeight, int pbeQuantity, double pbePrice) {
        super(pbeType, pbeSize, pbeWeight, pbeQuantity, pbePrice);
    }

    /**
     * Gets the size of EnvelopeS.
     *
     * @return The size of EnvelopeS.
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the weight of EnvelopeS.
     *
     * @return The weight of EnvelopeS.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the total price of EnvelopeS.
     *
     * @return The total price of EnvelopeS.
     */
    public double getPrice() {
        setPrice();
        return price;
    }

    /**
     * Sets the total price of EnvelopeS.
     */
    public void setPrice() {
        this.price = gram2Price();
    }

    /**
     * This method is for calculating the total price of EnvelopeS.
     *
     * @return The total price of EnvelopeS.
     */
    public double gram2Price() {
        size = "280mm x 200mm";
        weight = 500;
        price = 7.31 * getPbeQuantity();
        return price;
    }

}
