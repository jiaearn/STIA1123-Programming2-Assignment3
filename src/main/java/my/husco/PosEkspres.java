package my.husco;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is for manipulating Pos Ekspres.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class PosEkspres {

    String peType, peSize;
    int peWeight;
    int peThickness;
    int peQuantity;
    double pePrice;

    static ArrayList<BillInfo> billInfo = new ArrayList<>();

    /**
     * This is constructor for PosEkspres.
     */
    public PosEkspres() {

    }

    /**
     * Gets the quantity of Pos Ekspres.
     *
     * @return tThe quantity of Pos Ekspres.
     */
    public int getPeQuantity() {
        return peQuantity;
    }

    /**
     * This is constructor for class PosEkspres.
     *
     * @param peType      The package type of Pos Ekspres.
     * @param peSize      The package size of Pos Ekspres.
     * @param peWeight    The package weight of Pos Ekspres.
     * @param peThickness The package thickness of Pos Ekspres.
     * @param peQuantity  The package quantity of Pos Ekspres.
     * @param pePrice     The total price of Pos Ekspres.
     */
    public PosEkspres(String peType, String peSize, int peWeight, int peThickness, int peQuantity, double pePrice) {
        this.peType = peType;
        this.peSize = peSize;
        this.peWeight = peWeight;
        this.peThickness = peThickness;
        this.peQuantity = peQuantity;
        this.pePrice = pePrice;
    }

    /**
     * This method is for manipulating the string.
     *
     * @return All the data of Pos Ekspres.
     */
    public String toString() {
        String dataSave;
        switch (peType) {
            case "LG":
                LG lg = new LG(peType, peSize, peWeight, peThickness, peQuantity, pePrice);
                pePrice = lg.getPrice();
                peSize = lg.getSize();
                peWeight = lg.getWeight();
                peThickness = lg.getThickness();
                break;
            case "LE(C5)":
                LE le = new LE(peType, peSize, peWeight, peThickness, peQuantity, pePrice);
                pePrice = le.getPrice();
                peSize = le.getSize();
                peWeight = le.getWeight();
                peThickness = le.getThickness();
                break;
            case "LD(B4)":
                LD ld = new LD(peType, peSize, peWeight, peThickness, peQuantity, pePrice);
                pePrice = ld.getPrice();
                peSize = ld.getSize();
                peWeight = ld.getWeight();
                peThickness = ld.getThickness();
                break;
            default:
                LK lk = new LK(peType, peSize, peWeight, peThickness, peQuantity, pePrice);
                pePrice = lk.getPrice();
                peSize = lk.getSize();
                peWeight = lk.getWeight();
                peThickness = lk.getThickness();
                break;
        }
        billInfo.add(new BillInfo(peType, peSize, peWeight, peThickness, peQuantity, pePrice));
        CalculateTotalPrice calculateTotalPrice = new CalculateTotalPrice();
        addData();
        dataSave = "\nType : " + peType
                + "\n" + "Size : " + peSize
                + "\n" + "Max Weight : " + peWeight + " (gm)"
                + "\n" + "Max Thickness : " + peThickness + " (mm)"
                + "\n" + "Quantity : " + peQuantity
                + "\n" + "Price : RM " + String.format("%.2f", pePrice)
                + "\n\n" + "Total Price for Pos Ekspres RM " + String.format("%.2f", calculateTotalPrice.getPeTotalPrice())
        ;
        return dataSave;
    }

    /**
     * This method is for writing data into file.
     */
    public void addData() {
        try (FileWriter pe = new FileWriter("Pos Ekspres.txt")) {
            StringBuilder sb = new StringBuilder();
            for (BillInfo bI : PosEkspres.billInfo) {
                String price = String.format("%.2f", bI.pePrice);
                sb
                        .append(bI.peType).append(",")
                        .append(bI.peSize).append(",")
                        .append(bI.peWeight).append(",")
                        .append(bI.peThickness).append(",")
                        .append(bI.peQuantity).append(",")
                        .append(price).append("\r\n");
            }
            pe.write(sb.toString());
            pe.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
