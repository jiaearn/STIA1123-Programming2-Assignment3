package my.husco;

/**
 * This class is for calculating the total price of all services.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class CalculateTotalPrice {

    double ndTotalPrice;
    double sdTotalPrice;
    double pbeTotalPrice;
    double peTotalPrice;
    double totalPrice;

    /**
     * Gets the total price of all services.
     *
     * @return The total price of all services.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Gets the total price of Next-Day Delivery.
     *
     * @return The total price of Next-Day Delivery.
     */
    public double getNdTotalPrice() {
        return ndTotalPrice;
    }

    /**
     * Gets the total price of Same-Day Delivery.
     *
     * @return The total price of Same-Day Delivery.
     */
    public double getSdTotalPrice() {
        return sdTotalPrice;
    }

    /**
     * Gets the total price of Prepaid Box and Envelope.
     *
     * @return The total price of Prepaid Box and Envelope.
     */
    public double getPbeTotalPrice() {
        return pbeTotalPrice;
    }

    /**
     * Gets the total price of Pos Ekspres.
     *
     * @return The total price of Pos Ekspres.
     */
    public double getPeTotalPrice() {
        return peTotalPrice;
    }

    /**
     * This method is for calculating the total price of all services.
     */
    public CalculateTotalPrice() {
        for (BillInfo bI : NextDayDelivery.billInfo) {
            ndTotalPrice += bI.ndPrice;
        }
        for (BillInfo bI : SameDayDelivery.billInfo) {
            sdTotalPrice += bI.sdPrice;
        }
        for (BillInfo bI : PrepaidBoxEnvelope.billInfo) {
            pbeTotalPrice += bI.pbePrice;
        }
        for (BillInfo bI : PosEkspres.billInfo) {
            peTotalPrice += bI.pePrice;
        }
        totalPrice = ndTotalPrice + sdTotalPrice + pbeTotalPrice + peTotalPrice;
    }

}
