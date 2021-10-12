package my.husco;

/**
 * This class is for the bill info of all services.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class BillInfo {

    String ndType, sdType, pbeType, peType;
    String pbeSize, peSize;
    int ndZone;
    int ndWeight, sdWeight, pbeWeight, peWeight;
    int peThickness;
    int ndQuantity, sdQuantity, pbeQuantity, peQuantity;
    double ndPrice, sdPrice, pbePrice, pePrice;

    /**
     * This is constructor for the service Next-Day Delivery.
     *
     * @param ndType     The package type of Next-Day Delivery.
     * @param ndZone     The zone of Next-Day Delivery.
     * @param ndWeight   The weight of Next-Day Delivery.
     * @param ndQuantity The quantity of Next-Day Delivery.
     * @param ndPrice    The total price of Next-Day Delivery.
     */
    public BillInfo(String ndType, int ndZone, int ndWeight, int ndQuantity, double ndPrice) {
        this.ndType = ndType;
        this.ndZone = ndZone;
        this.ndWeight = ndWeight;
        this.ndQuantity = ndQuantity;
        this.ndPrice = ndPrice;
    }

    /**
     * This is constructor for the service Same-Day Delivery.
     *
     * @param sdType     The package type of Same-Day Delivery.
     * @param sdWeight   The weight of Same-Day Delivery.
     * @param sdQuantity The quantity of Same-Day Delivery.
     * @param sdPrice    The total price of Same-Day Delivery.
     */
    public BillInfo(String sdType, int sdWeight, int sdQuantity, double sdPrice) {
        this.sdType = sdType;
        this.sdWeight = sdWeight;
        this.sdQuantity = sdQuantity;
        this.sdPrice = sdPrice;
    }

    /**
     * This is constructor for the service Prepaid Box and Envelope.
     *
     * @param pbeType     The package type of Prepaid Box and Envelope.
     * @param pbeSize     The size of Prepaid Box and Envelope.
     * @param pbeWeight   The weight of Prepaid Box and Envelope.
     * @param pbeQuantity The quantity of Prepaid Box and Envelope.
     * @param pbePrice    The total price of Prepaid Box and Envelope.
     */
    public BillInfo(String pbeType, String pbeSize, int pbeWeight, int pbeQuantity, double pbePrice) {
        this.pbeType = pbeType;
        this.pbeSize = pbeSize;
        this.pbeWeight = pbeWeight;
        this.pbeQuantity = pbeQuantity;
        this.pbePrice = pbePrice;
    }

    /**
     * This is constructor for the service Pos Ekspres.
     *
     * @param peType      The package type of Pos Ekspres.
     * @param peSize      The size of Pos Ekspres.
     * @param peWeight    The weight of Pos Ekspres.
     * @param peThickness The thickness of Pos Ekspres.
     * @param peQuantity  The quantity of Pos Ekspres.
     * @param pePrice     The total price of Pos Ekspres.
     */
    public BillInfo(String peType, String peSize, int peWeight, int peThickness, int peQuantity, double pePrice) {
        this.peType = peType;
        this.peSize = peSize;
        this.peWeight = peWeight;
        this.peThickness = peThickness;
        this.peQuantity = peQuantity;
        this.pePrice = pePrice;
    }

}
