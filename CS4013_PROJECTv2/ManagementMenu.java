import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The type Management menu.
 */
public class ManagementMenu {
    private Scanner in;
    private static ArrayList<Owner> owners;

    /**
     * Instantiates a new Management menu.
     *
     * @param pm the pm
     */
    public ManagementMenu(PropertyMenu pm) {
        in = new Scanner(System.in);
        owners = pm.getOwners();
    }

    /**
     * Runs the management menu.
     */
    public void run() {
        boolean stop = false;

        if(owners.isEmpty()) {
            System.out.println("Error: No property data to report. Returning...\n");
            stop = true;
        }

        while(!stop) {
            System.out.println("(D)ata - (O)verdue - (S)tatistics - (C)hanges - (R)eturn");
            String input = in.nextLine().toUpperCase();

            if (input.equals("D")) {
                boolean cont = true;

                System.out.println("Select owner:");
                Owner o = getOwnerChoice(owners);

                while (cont) {
                    System.out.println("View specific (P)roperty data or view (A)ll data?");
                    input = in.nextLine();

                    if (input.toUpperCase().equals("P")) {
                        Property p = null;

                        System.out.println("Select Property:");
                        p = getPropertyChoice(o.getListOfProperties().getProperties());

                        o.getPayments().viewPaymentsByProperty(p);

                        cont = false;
                    } else if (input.toUpperCase().equals("A")) {
                        o.getPayments().viewAllPayments();

                        cont = false;
                    } else {
                        System.out.println("Incorrect input. Select P or A.");
                    }
                }
            } else if(input.equals("O")) {
                boolean cont = true;
                int numberOfYears = 1;
                int year = 0;

                for (int i = 0; i < owners.size(); i++) {
                    ArrayList<Property> propertyList = owners.get(i).getListOfProperties().getProperties();

                    for (int j = 0; j < propertyList.size(); j++) {
                        if (numberOfYears < propertyList.get(j).getYearsDue()) {
                            numberOfYears = propertyList.get(j).getYearsDue();
                        }
                    }
                }

                while (cont) {
                    System.out.print("Select year: ");

                    for (int i = numberOfYears; i > 0; i--) {
                        System.out.print(LocalDate.now().getYear() - (i - 1));

                        if ((i - 1) != 0) {
                            System.out.print(" / ");
                        } else {
                            System.out.println();
                        }
                    }

                    year = in.nextInt();
                    in.nextLine();

                    for (int i = 0; i < numberOfYears; i++) {
                        if (year == (LocalDate.now().getYear() - (numberOfYears - 1)) + i) {
                            year = i;
                            cont = false;
                            break;
                        }
                    }

                    if(year > numberOfYears) {
                        System.out.println("Incorrect input: Select one of the listed years.");
                    }
                }

                cont = true;

                while (cont) {
                    System.out.println("Select properties based on routing key? Y / N");
                    input = in.nextLine();

                    if (input.toUpperCase().equals("Y")) {
                        String routingKey = getRoutingKey();
                        ArrayList<Property> propertyList = findPropertiesWithRK(routingKey);

                        for (int i = 0; i < propertyList.size(); i++) {
                            Property p = propertyList.get(i);

                            if (year <= p.getYearsDue()) {
                                System.out.println(propertyList.get(i).getPostcode() + ": " + propertyList.get(i).getOverdueTax(year));
                            }
                        }

                        cont = false;
                    } else if (input.toUpperCase().equals("N")) {
                        for (int i = 0; i < owners.size(); i++) {
                            ArrayList<Property> list = owners.get(i).getListOfProperties().getProperties();

                            for (int j = 0; j < list.size(); j++) {
                                Property p = list.get(j);

                                if (year <= p.getYearsDue()) {
                                    System.out.println(list.get(j).getPostcode() + ": " + list.get(j).getOverdueTax(year));
                                }
                            }
                        }

                        cont = false;
                    } else {
                        System.out.println("Incorrect Input: Select Y or N.");
                    }
                }
            } else if (input.equals("S")) {
                double average = 0;
                double count = 0;
                double total = 0;
                int propertyTaxPaid = 0;
                double percentagePaid = 0.0;

                String routingKey = getRoutingKey();
                ArrayList<Property> propertyList = findPropertiesWithRK(routingKey);

                if (propertyList.isEmpty()) {
                    System.out.println("Property with routing key not found. Returning...");
                    break;
                }

                for (int i = 0; i < propertyList.size(); i++) {
                    total += propertyList.get(i).getTaxPaid();
                    count++;

                    if(propertyList.get(i).getTotalTax() == 0) {
                        propertyTaxPaid++;
                    }
                }

                average = total / count;
                percentagePaid = (propertyTaxPaid / count) * 100;

                System.out.println("Total Tax Paid: " + total + "\n" + "Average Tax Paid: " + average + "\n" +
                                    "Number of Property Taxes Paid: " + propertyTaxPaid + " (" + percentagePaid
                                    + "% of property taxes paid)" + "\n");
            } else if(input.equals("C")) {
                boolean cont = true;
                Property p = null;

                System.out.println("Select owner:");
                Owner o = getOwnerChoice(owners);

                while (cont) {
                    System.out.println("Select Property:");
                    p = getPropertyChoice(o.getListOfProperties().getProperties());

                    cont = false;
                }

                double marketValue = p.getMarketValue();
                String location = p.getLocation();
                boolean principal = p.getPrincipal();
                LocalDate lastTaxPayment = p.getLastTaxPayment();

                TaxCalculator tc = new TaxCalculator(marketValue, location, principal, lastTaxPayment);

                System.out.println("Fixed Cost: 100");
                System.out.println("Market Tax: " + tc.getMarketRate());
                System.out.println("Location Tax: " + tc.getLocationRate());
                System.out.println("PPR Charge: " + tc.checkPPR());
                System.out.println("Unpaid Tax Penalty: " + tc.getUnpaidPenalty());
                System.out.println("Total Tax: " + tc.getDueTax() + "\n");

                cont = true;

                while(cont) {
                    System.out.println("Select what tax/charge to change: (F)ixed / (M)arket / " +
                                        "(L)ocation / (P)PR / (U)npaid");
                    input = in.nextLine();
                    boolean check = false;
                    double percent = 0;
                    double oldValue;
                    double newValue = 0;

                    if(input.toUpperCase().equals("F")) {
                        while (!check) {
                            System.out.println("Enter new fixed cost:");
                            newValue = in.nextDouble();
                            in.nextLine();

                            if (newValue > 0) {
                                check = true;
                            } else {
                                System.out.println("Incorrect input: Enter a value greater than 0.");
                            }
                        }

                        System.out.println("Old Fixed Cost: 100");
                        System.out.println("New Fixed Cost: " + newValue);
                        System.out.println("Old Total: " + tc.getDueTax());
                        oldValue = tc.getDueTax();

                        if (100 < newValue) {
                            tc.setDueTax(tc.getDueTax() + (newValue - 100));
                            percent = ((tc.getDueTax() - oldValue) / oldValue) * 100;

                            System.out.println("New Total: " + tc.getDueTax());
                            System.out.println("Percentage Increase: " + round(percent) + "%");
                        } else if (100 > newValue) {
                            tc.setDueTax(tc.getDueTax() - (100 - newValue));
                            percent = Math.abs(((tc.getDueTax() - oldValue) / oldValue) * 100);

                            System.out.println("New Total: " + tc.getDueTax());
                            System.out.println("Percentage Decrease: " + round(percent) + "%");
                        }

                        cont = false;
                    } else if(input.toUpperCase().equals("M")) {
                        double rate = 0;

                        while (!check) {
                            System.out.println("Enter new market value tax (e.g. 0.08%):");
                            input = in.nextLine();

                            if(!Pattern.matches("^((100)|(\\d{1,2}(.\\d*)?))%$", input)) {   // Checks for percentage
                                System.out.println("Incorrect input: Enter a percentage value.");
                                continue;
                            }

                            rate = Double.parseDouble(input.substring(0, (input.indexOf("%"))));

                            if (rate >= 0 && rate <= 100) {
                                newValue = tc.getMarketValue() * (rate / 100);
                                check = true;
                            } else {
                                System.out.println("Incorrect input: Enter a percentage value between 0 and 100.");
                            }
                        }

                        System.out.println("Old Market Tax: " + tc.getMarketRate());
                        System.out.println("New Market Tax: " + newValue + " (" + rate + "%)");
                        System.out.println("Old Total: " + tc.getDueTax());
                        oldValue = tc.getDueTax();

                        double oldMarketTax = Double.parseDouble(tc.getMarketRate().substring(1, tc.getMarketRate().indexOf(" ")));

                        if (oldMarketTax < newValue) {
                            tc.setDueTax(tc.getDueTax() + (newValue - oldMarketTax));
                            percent = ((tc.getDueTax() - oldValue) / oldValue) * 100;

                            System.out.println("New Total: " + tc.getDueTax());
                            System.out.println("Percentage Increase: " + round(percent) + "%");
                        } else if (oldMarketTax > newValue) {
                            tc.setDueTax(tc.getDueTax() - (oldMarketTax - newValue));
                            percent = Math.abs(((tc.getDueTax() - oldValue) / oldValue) * 100);

                            System.out.println("New Total: " + tc.getDueTax());
                            System.out.println("Percentage Decrease: " + round(percent) + "%");
                        }

                        cont = false;

                    } else if(input.toUpperCase().equals("L")) {
                        while (!check) {
                            System.out.println("Enter new location category tax:");
                            newValue = in.nextDouble();
                            in.nextLine();

                            if (newValue > 0) {
                                check = true;
                            } else {
                                System.out.println("Incorrect input: Enter a value greater than 0.");
                            }
                        }

                        System.out.println("Old Location Tax: " + tc.getLocationRate());
                        System.out.println("New Location Tax: " + newValue);
                        System.out.println("Old Total: " + tc.getDueTax());
                        oldValue = tc.getDueTax();

                        double oldLocationTax = Double.parseDouble(tc.getLocationRate().substring(1));

                        if (oldLocationTax < newValue) {
                            tc.setDueTax(tc.getDueTax() + (newValue - oldLocationTax));
                            percent = ((tc.getDueTax() - oldValue) / oldValue) * 100;

                            System.out.println("New Total: " + tc.getDueTax());
                            System.out.println("Percentage Increase: " + round(percent) + "%");
                        } else if (oldLocationTax > newValue) {
                            tc.setDueTax(tc.getDueTax() - (oldLocationTax - newValue));
                            percent = Math.abs(((tc.getDueTax() - oldValue) / oldValue) * 100);

                            System.out.println("New Total: " + tc.getDueTax());
                            System.out.println("Percentage Decrease: " + round(percent) + "%");
                        }

                        cont = false;

                    } else if(input.toUpperCase().equals("P")) {
                        while (!check) {
                            System.out.println("Enter new PPR charge:");
                            newValue = in.nextDouble();
                            in.nextLine();

                            if (newValue > 0) {
                                check = true;
                            } else {
                                System.out.println("Incorrect input: Enter a value greater than 0.");
                            }
                        }

                        System.out.println("Old PPR Charge: " + tc.checkPPR());
                        System.out.println("New PPR charge: " + newValue);
                        System.out.println("Old Total: " + tc.getDueTax());
                        oldValue = tc.getDueTax();

                        double oldPPRCharge = Double.parseDouble(tc.checkPPR().substring(1));

                        if (oldPPRCharge < newValue) {
                            tc.setDueTax(tc.getDueTax() + (newValue - oldPPRCharge));
                            percent = ((tc.getDueTax() - oldValue) / oldValue) * 100;

                            System.out.println("New Total: " + tc.getDueTax());
                            System.out.println("Percentage Increase: " + round(percent) + "%");
                        } else if (oldPPRCharge > newValue) {
                            tc.setDueTax(tc.getDueTax() - (oldPPRCharge - newValue));
                            percent = Math.abs(((tc.getDueTax() - oldValue) / oldValue) * 100);

                            System.out.println("New Total: " + tc.getDueTax());
                            System.out.println("Percentage Decrease: " + round(percent) + "%");
                        }

                        cont = false;

                    } else if(input.toUpperCase().equals("U")) {
                        double rate = 0;

                        while (!check) {
                            System.out.println("Enter new unpaid tax penalty (e.g. 15.5%):");
                            input = in.nextLine();

                            if(!Pattern.matches("^((100)|(\\d{1,2}(.\\d*)?))%$", input)) {   // Checks for percentage
                                System.out.println("Incorrect input: Enter a percentage value.");
                                continue;
                            }

                            rate = Double.parseDouble(input.substring(0, (input.indexOf("%"))));

                            if (rate >= 0 && rate <= 100) {
                                newValue = (tc.getOverdueTax(0) * (1 + (rate / 100)) - tc.getOverdueTax(0));
                                check = true;
                            } else {
                                System.out.println("Incorrect input: Enter a percentage value between 0 and 100.");
                            }
                        }

                        System.out.println("Old Unpaid Tax Penalty: " + tc.getUnpaidPenalty());
                        System.out.println("New Unpaid Tax Penalty: " + newValue + " (" + rate + "%)");
                        System.out.println("Old Total: " + tc.getDueTax());
                        oldValue = tc.getDueTax();

                        double oldUnpaidPenalty = Double.parseDouble(tc.getUnpaidPenalty().substring(1, tc.getUnpaidPenalty().indexOf(" ")));

                        if (oldUnpaidPenalty < newValue) {
                            tc.setDueTax(tc.getDueTax() + (newValue - oldUnpaidPenalty));
                            percent = ((tc.getDueTax() - oldValue) / oldValue) * 100;

                            System.out.println("New Total: " + tc.getDueTax());
                            System.out.println("Percentage Increase: " + round(percent) + "%");
                        } else if (oldUnpaidPenalty > newValue) {
                            tc.setDueTax(tc.getDueTax() - (oldUnpaidPenalty - newValue));
                            percent = Math.abs(((tc.getDueTax() - oldValue) / oldValue) * 100);

                            System.out.println("New Total: " + tc.getDueTax());
                            System.out.println("Percentage Decrease: " + round(percent) + "%");
                        }

                        cont = false;

                    } else {
                        System.out.println("Incorrect input: Select F, M, L, P or U");
                    }
                }
            } else if(input.equals("R")) {
                System.out.println("Returning to User Mode.");

                stop = true;
            }
        }
    }

    private Owner getOwnerChoice(ArrayList<Owner> options) {
        if(options.size() == 0) {
            return null;
        }
        while (true)
        {
            char c = 'A';
            for (int i = 0; i < options.size(); i++) {
                System.out.println(c + ") " + options.get(i));
                c++;
            }
            String input = in.nextLine();
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < options.size())
                return options.get(n);
        }
    }

    private Property getPropertyChoice(ArrayList<Property> options) {
        if(options.size() == 0) {
            return null;
        }
        while (true)
        {
            char c = 'A';
            for (Property choice : options)
            {
                System.out.println(c + ") " + choice);
                c++;
            }
            String input = in.nextLine();
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < options.size())
                return options.get(n);
        }
    }

    private String getRoutingKey() {
        boolean cont = true;
        String key = null;

        while(cont) {
            System.out.println("Enter Routing Key (e.g. A65):");
            key = in.nextLine();

            key.toUpperCase();

            if(Pattern.matches("[A-Z][0-9]{2}", key)) {
                cont = false;
            } else if(Pattern.matches("D6W", key)) { // exception to postcode format
                cont = false;
            } else {
                System.out.println("Error: Input does not match routing key format. Please try again.\n");
            }
        }

        return key;
    }

    private ArrayList<Property> findPropertiesWithRK(String routingKey) {
        ArrayList<Property> list = new ArrayList<Property>();

        for(int i = 0; i < owners.size(); i++) {
            ArrayList<Property> propertyList = owners.get(i).getListOfProperties().getProperties();

            for(int j = 0; j < propertyList.size(); j++) {
                if(propertyList.get(j).getRoutingKey().equals(routingKey)) {
                    list.add(propertyList.get(j));
                }
            }
        }

        return list;
    }

    private double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}