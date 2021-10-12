package my.husco;

/**
 * This class is for manipulating Document.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class Document extends NextDayDelivery {

    private double price;

    /**
     * This is constructor for the class Document.
     *
     * @param ndType     One of the type of Next-Day Delivery is Document.
     * @param ndZone     Zone 1-5 of Document.
     * @param ndWeight   The weight of Document.
     * @param ndQuantity The quantity of Document.
     * @param ndPrice    The total price of Next-Day Delivery.
     */
    public Document(String ndType, int ndZone, int ndWeight, int ndQuantity, double ndPrice) {
        super(ndType, ndZone, ndWeight, ndQuantity, ndPrice);
    }

    /**
     * Gets the total price of Document.
     *
     * @return The total price of Document.
     */
    public double getPrice() {
        setPrice();
        return price;
    }

    /**
     * Sets the total price of Document.
     */
    public void setPrice() {
        this.price = documentPrice();
    }

    /**
     * This method is for showing the first price and surcharge price of each zone.
     *
     * @param zone The first price and surcharge price of document for zone 1-5.
     */
    public void document(int zone) {
        switch (zone) {
            case 1:
                document(4.90, 0.80);
                break;
            case 2:
                document(5.40, 1.00);
                break;
            case 3:
                document(6.90, 1.50);
                break;
            case 4:
                document(7.40, 1.50);
                break;
            case 5:
                document(7.90, 2.00);
                break;
        }
    }

    /**
     * This method is for calculating the total price of document.
     *
     * @param fdPrice The first price of document.
     * @param sdPrice The surcharge price of document.
     */
    public void document(double fdPrice, double sdPrice) {
        int i;
        double totalsdPrice;

        if (getNdWeight() > 0 && getNdWeight() <= 500) {
            totalsdPrice = 0;
        } else {
            if ((getNdWeight() - 500) % 250 == 0) {
                i = ((getNdWeight() - 500) / 250);
                totalsdPrice = i * sdPrice;
            } else {
                i = (getNdWeight() - 500) / 250;
                totalsdPrice = (i + 1) * sdPrice;
            }
        }
        ndPrice = (fdPrice + totalsdPrice) * getNdQuantity();
    }

    /**
     * This method is for calculating the total price of document.
     *
     * @return The total price of document.
     */
    public double documentPrice() {
        document(ndZone);
        return ndPrice;
    }

}
