package my.husco;

/**
 * This class is for calculating the price of cross town.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class CrossPrice extends SameDayDelivery {

    private double price;

    /**
     * This is constructor for class CrossPrice.
     *
     * @param sdType     One of the town type of Same-Day Delivery is Cross town.
     * @param sdWeight   The package weight between 1-1000gm.
     * @param sdQuantity The package quantity.
     * @param sdPrice    The total price of cross town (Same-day Delivery).
     */
    public CrossPrice(String sdType, int sdWeight, int sdQuantity, double sdPrice) {
        super(sdType, sdWeight, sdQuantity, sdPrice);
    }

    /**
     * Gets the total price of cross town.
     *
     * @return The total price of cross town (Same-day Delivery).
     */
    public double getPrice() {
        setPrice();
        return price;
    }

    /**
     * Sets the total price of cross town.
     */
    public void setPrice() {
        this.price = townPrice();
    }

    /**
     * This method is for calculating the total price of cross town.
     *
     * @return The total price of cross town (Same-day Delivery).
     */
    public double townPrice() {
        if (getSdWeight() > 0 && getSdWeight() <= 500) {
            sdPrice = 12.90 * getSdQuantity();
        } else if (getSdWeight() > 500 && getSdWeight() <= 750) {
            sdPrice = 13.90 * getSdQuantity();
        } else if (getSdWeight() > 750 && getSdWeight() <= 1000) {
            sdPrice = 14.90 * getSdQuantity();
        }
        return sdPrice;
    }

}
