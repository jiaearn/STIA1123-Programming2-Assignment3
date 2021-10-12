package my.husco;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is for manipulating Next-Day Delivery.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class NextDayDelivery {

    String ndType;
    int ndZone;
    int ndWeight;
    int ndQuantity;
    double ndPrice;

    static ArrayList<BillInfo> billInfo = new ArrayList<>();

    /**
     * This is constructor for NextDayDelivery.
     */
    public NextDayDelivery() {

    }

    /**
     * Gets the weight of Next-Day Delivery.
     *
     * @return The weight of Next-Day Delivery.
     */
    public int getNdWeight() {
        return ndWeight;
    }

    /**
     * Gets the quantity of Next-Day Delivery.
     *
     * @return The quantity of Next-Day Delivery.
     */
    public int getNdQuantity() {
        return ndQuantity;
    }

    /**
     * This is constructor for class NextDayDelivery.
     *
     * @param ndType     Either document or parcel.
     * @param ndZone     The package zone 1-5.
     * @param ndWeight   The weight of package.
     * @param ndQuantity The package quantity.
     * @param ndPrice    The total price of Next-Day Delivery.
     */
    public NextDayDelivery(String ndType, int ndZone, int ndWeight, int ndQuantity, double ndPrice) {
        this.ndType = ndType;
        this.ndZone = ndZone;
        this.ndWeight = ndWeight;
        this.ndQuantity = ndQuantity;
        this.ndPrice = ndPrice;
    }

    /**
     * This method is for manipulating the string.
     *
     * @return All the data of Next-Day Delivery.
     */
    public String toString() {
        String dataSave;
        if (ndType.equals("Document")) {
            Document document = new Document(ndType, ndZone, ndWeight, ndQuantity, ndPrice);
            ndPrice = document.getPrice();
        } else {
            Parcel parcel = new Parcel(ndType, ndZone, ndWeight, ndQuantity, ndPrice);
            ndPrice = parcel.getPrice();
        }
        billInfo.add(new BillInfo(ndType, ndZone, ndWeight, ndQuantity, ndPrice));
        addData();
        CalculateTotalPrice calculateTotalPrice = new CalculateTotalPrice();
        dataSave = "\nType : " + ndType
                + "\n" + "Zone : " + ndZone
                + "\n" + "Weight : " + ndWeight + " (gm)"
                + "\n" + "Quantity : " + ndQuantity
                + "\n" + "Price : RM " + String.format("%.2f", ndPrice)
                + "\n\n" + "Total Price for Next Day Delivery : RM " + String.format("%.2f", calculateTotalPrice.getNdTotalPrice())
        ;
        return dataSave;
    }

    /**
     * This method is for writing data into file.
     */
    public void addData() {
        try (FileWriter nextDay = new FileWriter("Next-Day Delivery.txt")) {
            StringBuilder sb = new StringBuilder();
            for (BillInfo bI : NextDayDelivery.billInfo) {
                String price = String.format("%.2f", bI.ndPrice);
                sb
                        .append(bI.ndType).append(",")
                        .append(bI.ndZone).append(",")
                        .append(bI.ndWeight).append(",")
                        .append(bI.ndQuantity).append(",")
                        .append(price)
                        .append("\r\n");
            }
            nextDay.write(sb.toString());
            nextDay.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
