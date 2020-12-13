import java.io.*;

/**
 * The type Payments.
 */
public class Payments {
    private File paymentFile;

    /**
     * Instantiates a new payment file for an owner.
     *
     * @param name    The name of the owner.
     * @param userDir the user directory.
     */
    public Payments(String name, File userDir) {
        paymentFile = new File(userDir + "/PaymentsFrom" + name.replaceAll(" ", "") + ".csv");

        if(!paymentFile.exists()) {
            try {
                paymentFile.createNewFile();

                FileWriter paymentWriter = new FileWriter(userDir + "/PaymentsFrom" + name.replaceAll(" ", "") + ".csv", true);

                paymentWriter.append("Postcode");
                paymentWriter.append(',');
                paymentWriter.append("Old Due");
                paymentWriter.append(',');
                paymentWriter.append("Old Overdue");
                paymentWriter.append(',');
                paymentWriter.append("Old Total");
                paymentWriter.append(',');
                paymentWriter.append("Payment");
                paymentWriter.append(',');
                paymentWriter.append("New Due");
                paymentWriter.append(',');
                paymentWriter.append("New Overdue");
                paymentWriter.append(',');
                paymentWriter.append("New Total");
                paymentWriter.append('\n');

                paymentWriter.flush();
                paymentWriter.close();
            } catch(IOException e) {
                System.out.println("Error: Failed to create new payment file.");
            }
        }
    }

    /**
     * Add payment.
     *
     * @param postcode   The postcode of the property.
     * @param oldDue     The old due tax.
     * @param oldOverdue The old overdue tax.
     * @param oldTotal   The old total tax.
     * @param payment    The payment.
     * @param newDue     The new due tax.
     * @param newOverdue The new overdue tax.
     * @param newTotal   The new total tax.
     */
    public void addPayment(String postcode, double oldDue, double oldOverdue, double oldTotal,
                           double payment, double newDue, double newOverdue, double newTotal) {
        try {
            FileWriter writer = new FileWriter(paymentFile, true);

            writer.append(postcode);
            writer.append(',');
            writer.append(Double.toString(oldDue));
            writer.append(',');
            writer.append(Double.toString(oldOverdue));
            writer.append(',');
            writer.append(Double.toString(oldTotal));
            writer.append(',');
            writer.append(Double.toString(payment));
            writer.append(',');
            writer.append(Double.toString(newDue));
            writer.append(',');
            writer.append(Double.toString(newOverdue));
            writer.append(',');
            writer.append(Double.toString(newTotal));
            writer.append('\n');

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to add payment.");
        }
    }

    /**
     * View payments by property.
     *
     * @param p The property used to view payments with.
     */
    public void viewPaymentsByProperty(Property p) {
        String postcode = p.getPostcode();
        String line = "";
        String cvsSplitBy = ",";

        try {
            BufferedReader br = new BufferedReader(new FileReader(paymentFile));
            int count = 0;

            br.readLine();

            while((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);

                if(data[0].contains(postcode)) {
                    System.out.println("Postcode: " + data[0] + "\nOld Due: " + data[1] + "\nOld Overdue: " + data[2] +
                            "\nOld Total: " + data[3] + "\nPayment: " + data[4] + "\nNew Due: " + data[5] +
                            "\nNew Overdue: " + data[6] + "\nNew Total: " + data[7] +  "\n");
                    count++;
                }
            }

            br.close();

            if(count == 0) {
                System.out.println("No payments made.");
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to view payments.");
        }
    }

    /**
     * View all payments.
     */
    public void viewAllPayments() {
        String line = "";
        String cvsSplitBy = ",";

        try {
            BufferedReader br = new BufferedReader(new FileReader(paymentFile));
            int count = 0;

            br.readLine();

            while((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);

                System.out.println("Postcode: " + data[0] + "\nOld Due: " + data[1] + "\nOld Overdue: " + data[2] +
                        "\nOld Total: " + data[3] + "\nPayment: " + data[4] + "\nNew Due: " + data[5] +
                        "\nNew Overdue: " + data[6] + "\nNew Total: " + data[7] +  "\n");
                count++;
            }

            br.close();

            if(count == 0) {
                System.out.println("No payments made.");
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to view payments.");
        }
    }
}
