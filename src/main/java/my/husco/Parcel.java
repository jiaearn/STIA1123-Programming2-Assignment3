package my.husco;

/**
 * This class is for manipulating Parcel.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class Parcel extends NextDayDelivery {

    private double price;

    /**
     * This is constructor for the class Parcel.
     *
     * @param ndType     One of the type of Next-Day Delivery is Parcel.
     * @param ndZone     Zone 1-5 of Parcel.
     * @param ndWeight   The weight of Parcel.
     * @param ndQuantity The quantity of Parcel.
     * @param ndPrice    The total price of Next-Day Delivery.
     */
    public Parcel(String ndType, int ndZone, int ndWeight, int ndQuantity, double ndPrice) {
        super(ndType, ndZone, ndWeight, ndQuantity, ndPrice);
    }

    /**
     * Gets the total price of Parcel.
     *
     * @return The total price of Parcel.
     */
    public double getPrice() {
        setPrice();
        return price;
    }

    /**
     * Sets the total price of Parcel.
     */
    public void setPrice() {
        this.price = parcelPrice();
    }

    /**
     * This method is for showing the first price and surcharge price of each zone.
     *
     * @param zone The first price and surcharge price of parcel for zone 1-5.
     */
    public void parcel(int zone) {
        switch (zone) {
            case 1:
                parcel(10.50, 0.50);
                break;
            case 2:
                parcel(16.00, 2.00);
                break;
            case 3:
                parcel(21.00, 3.00);
                break;
            case 4:
                parcel(26.00, 3.50);
                break;
            case 5:
                parcel(31.00, 4.00);
                break;
        }
    }

    /**
     * This method is for calculating the total price of parcel.
     *
     * @param fpPrice The first price of parcel.
     * @param spPrice The surcharge price of parcel.
     */
    public void parcel(double fpPrice, double spPrice) {
        int j;
        double totalspPrice;

        if (getNdWeight() > 2000 && getNdWeight() <= 2500) {
            totalspPrice = 0;
        } else {
            if ((getNdWeight() - 2500) % 500 == 0) {
                j = ((getNdWeight() - 2500) / 500);
                totalspPrice = j * spPrice;
            } else {
                j = ((getNdWeight() - 2500) / 500);
                totalspPrice = (j + 1) * spPrice;
            }
        }
        ndPrice = (fpPrice + totalspPrice) * getNdQuantity();
    }

    /**
     * This method is for calculating the total price of parcel.
     *
     * @return The total price of parcel.
     */
    public double parcelPrice() {
        parcel(ndZone);
        return ndPrice;
    }

}
