package my.husco;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is for manipulating Prepaid Box and Envelope.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class PrepaidBoxEnvelope {

    String pbeType, pbeSize;
    int pbeWeight;
    int pbeQuantity;
    double pbePrice;

    static ArrayList<BillInfo> billInfo = new ArrayList<>();

    /**
     * This is constructor for Prepaid Box and Envelope.
     */
    public PrepaidBoxEnvelope() {

    }

    /**
     * Gets the quantity of Prepaid Box and Envelope.
     *
     * @return The quantity of Prepaid Box and Envelope.
     */
    public int getPbeQuantity() {
        return pbeQuantity;
    }

    /**
     * This is constructor for class PrepaidBoxEnvelope.
     *
     * @param pbeType     The package type of Prepaid Box and Envelope.
     * @param pbeSize     The package size of Prepaid Box and Envelope.
     * @param pbeWeight   The package weight of Prepaid Box and Envelope.
     * @param pbeQuantity The package quantity of Prepaid Box and Envelope.
     * @param pbePrice    The total price of Prepaid Box and Envelope.
     */
    public PrepaidBoxEnvelope(String pbeType, String pbeSize, int pbeWeight, int pbeQuantity, double pbePrice) {
        this.pbeType = pbeType;
        this.pbeSize = pbeSize;
        this.pbeWeight = pbeWeight;
        this.pbeQuantity = pbeQuantity;
        this.pbePrice = pbePrice;
    }

    /**
     * This method is for manipulating the string.
     *
     * @return All the data of Prepaid Box and Envelope.
     */
    public String toString() {
        String dataSave;
        switch (pbeType) {
            case "Envelope S (ES)":
                EnvelopeS envelopeS = new EnvelopeS(pbeType, pbeSize, pbeWeight, pbeQuantity, pbePrice);
                pbePrice = envelopeS.getPrice();
                pbeSize = envelopeS.getSize();
                pbeWeight = envelopeS.getWeight();
                break;
            case "Envelope L (EL)":
                EnvelopeL envelopeL = new EnvelopeL(pbeType, pbeSize, pbeWeight, pbeQuantity, pbePrice);
                pbePrice = envelopeL.getPrice();
                pbeSize = envelopeL.getSize();
                pbeWeight = envelopeL.getWeight();
                break;
            case "Prepaid Box S (PBS)":
                PrepaidBoxS prepaidBoxS = new PrepaidBoxS(pbeType, pbeSize, pbeWeight, pbeQuantity, pbePrice);
                pbePrice = prepaidBoxS.getPrice();
                pbeSize = prepaidBoxS.getSize();
                pbeWeight = prepaidBoxS.getWeight();
                break;
            case "Prepaid Box M (PBM)":
                PrepaidBoxM prepaidBoxM = new PrepaidBoxM(pbeType, pbeSize, pbeWeight, pbeQuantity, pbePrice);
                pbePrice = prepaidBoxM.getPrice();
                pbeSize = prepaidBoxM.getSize();
                pbeWeight = prepaidBoxM.getWeight();
                break;
            default:
                PrepaidBoxL prepaidBoxL = new PrepaidBoxL(pbeType, pbeSize, pbeWeight, pbeQuantity, pbePrice);
                pbePrice = prepaidBoxL.getPrice();
                pbeSize = prepaidBoxL.getSize();
                pbeWeight = prepaidBoxL.getWeight();
                break;
        }
        billInfo.add(new BillInfo(pbeType, pbeSize, pbeWeight, pbeQuantity, pbePrice));
        addData();
        CalculateTotalPrice calculateTotalPrice = new CalculateTotalPrice();
        dataSave = "\nType : " + pbeType
                + "\n" + "Size : " + pbeSize
                + "\n" + "Max Weight : " + pbeWeight + " (gm)"
                + "\n" + "Quantity : " + pbeQuantity
                + "\n" + "Price : RM " + String.format("%.2f", pbePrice)
                + "\n\n" + "Total Price for Prepaid Box & Envelope : RM " + String.format("%.2f", calculateTotalPrice.getPbeTotalPrice())
        ;
        return dataSave;
    }

    /**
     * This method is for writing data into file.
     */
    public void addData() {
        try (FileWriter pbe = new FileWriter("Prepaid Box & Envelope.txt")) {
            StringBuilder sb = new StringBuilder();
            for (BillInfo bI : PrepaidBoxEnvelope.billInfo) {
                String price = String.format("%.2f", bI.pbePrice);
                sb
                        .append(bI.pbeType).append(",")
                        .append(bI.pbeSize).append(",")
                        .append(bI.pbeWeight).append(",")
                        .append(bI.pbeQuantity).append(",")
                        .append(price).append("\r\n");
            }
            pbe.write(sb.toString());
            pbe.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
