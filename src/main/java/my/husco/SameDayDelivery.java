package my.husco;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is for manipulating Same-Day Delivery.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class SameDayDelivery {

    String sdType;
    int sdWeight;
    int sdQuantity;
    double sdPrice;

    static ArrayList<BillInfo> billInfo = new ArrayList<>();

    /**
     * This is constructor for SameDayDelivery.
     */
    public SameDayDelivery() {

    }

    /**
     * Gets the weight of Same-Day Delivery.
     *
     * @return The weight of Same-Day Delivery.
     */
    public int getSdWeight() {
        return sdWeight;
    }

    /**
     * Gets the quantity of Same-Day Delivery.
     *
     * @return The quantity of Same-Day Delivery.
     */
    public int getSdQuantity() {
        return sdQuantity;
    }

    /**
     * This is constructor for class SameDayDelivery.
     *
     * @param sdType     Either local town or cross town.
     * @param sdWeight   The package weight between 1-1000gm of Same-Day Delivery.
     * @param sdQuantity The package quantity of Same-Day Delivery.
     * @param sdPrice    The total price of Same-Day Delivery.
     */
    public SameDayDelivery(String sdType, int sdWeight, int sdQuantity, double sdPrice) {
        this.sdType = sdType;
        this.sdWeight = sdWeight;
        this.sdQuantity = sdQuantity;
        this.sdPrice = sdPrice;
    }

    /**
     * This method is for manipulating the string.
     *
     * @return All the data of Same-Day Delivery.
     */
    public String toString() {
        String dataSave;
        if (sdType.equals("Local Town")) {
            LocalPrice localPrice = new LocalPrice(sdType, sdWeight, sdQuantity, sdPrice);
            sdPrice = localPrice.getPrice();
        } else {
            CrossPrice crossPrice = new CrossPrice(sdType, sdWeight, sdQuantity, sdPrice);
            sdPrice = crossPrice.getPrice();
        }
        billInfo.add(new BillInfo(sdType, sdWeight, sdQuantity, sdPrice));
        CalculateTotalPrice calculateTotalPrice = new CalculateTotalPrice();
        addData();
        dataSave = "\nType : " + sdType
                + "\n" + "Weight : " + sdWeight + " (gm)"
                + "\n" + "Quantity : " + sdQuantity
                + "\n" + "Price : RM " + String.format("%.2f", sdPrice)
                + "\n\n" + "Total Price for Same Day Delivery : RM " + String.format("%.2f", calculateTotalPrice.getSdTotalPrice())
        ;
        return dataSave;
    }

    /**
     * This method is for writing data into file.
     */
    public void addData() {
        try (FileWriter sameDay = new FileWriter("Same-Day Delivery.txt")) {
            StringBuilder sb = new StringBuilder();
            for (BillInfo bI : SameDayDelivery.billInfo) {
                String price = String.format("%.2f", bI.sdPrice);
                sb
                        .append(bI.sdType).append(",")
                        .append(bI.sdWeight).append(",")
                        .append(bI.sdQuantity).append(",")
                        .append(price).append("\r\n");
            }
            sameDay.write(sb.toString());
            sameDay.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
