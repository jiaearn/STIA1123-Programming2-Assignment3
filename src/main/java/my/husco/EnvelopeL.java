package my.husco;

/**
 * This class is for manipulating EnvelopeL.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class EnvelopeL extends PrepaidBoxEnvelope {

    private String size;
    private int weight;
    private double price;

    /**
     * This constructor is for class EnvelopeL.
     *
     * @param pbeType     One of the package type is EnvelopeL.
     * @param pbeSize     The size of EnvelopeL.
     * @param pbeWeight   The weight of EnvelopeL.
     * @param pbeQuantity The quantity of EnvelopeL.
     * @param pbePrice    The total price of EnvelopeL.
     */
    public EnvelopeL(String pbeType, String pbeSize, int pbeWeight, int pbeQuantity, double pbePrice) {
        super(pbeType, pbeSize, pbeWeight, pbeQuantity, pbePrice);
    }

    /**
     * Gets the size of EnvelopeL.
     *
     * @return The size of EnvelopeL.
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the weight of EnvelopeL.
     *
     * @return The weight of EnvelopeL.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the total price of EnvelopeL.
     *
     * @return The total price of EnvelopeL.
     */
    public double getPrice() {
        setPrice();
        return price;
    }

    /**
     * Sets the total price of EnvelopeL.
     */
    public void setPrice() {
        this.price = gram1Price();
    }

    /**
     * This method is for calculating the total price of EnvelopeL.
     *
     * @return The total price of EnvelopeL.
     */
    public double gram1Price() {
        size = "380mm x 320mm";
        weight = 1000;
        price = 10.49 * getPbeQuantity();
        return price;
    }

}
