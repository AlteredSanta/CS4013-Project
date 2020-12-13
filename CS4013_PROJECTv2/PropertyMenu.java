import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The type Property menu.
 */
public class PropertyMenu {
    private Scanner in;
    private File ownersDir;
    private File userDir;
    private File userInfo;
    private Owner currentUser;
    private static ArrayList<Owner> owners = new ArrayList<Owner>();

    /**
     * Instantiates a new Property menu.
     */
    public PropertyMenu() {
        in = new Scanner(System.in);

        ownersDir = new File("owners");

        if(!ownersDir.exists()) {
            ownersDir.mkdir();
        }
    }

    /**
     * Runs the command line interface for the program.
     */
    public void run() {
        boolean exit = false;

        while(!exit) {
            boolean stop = false;

            if(ownersDir.list().length == 0) {
                System.out.println("No owners detected.");
                registerNewUser();
                stop = true;
            }

            while(!stop) {
                int count = 0;

                System.out.println("Select User:");

                File[] listOfFiles = ownersDir.listFiles();

                for(int i = 0; i < listOfFiles.length; i++) {
                   String existingOwner = listOfFiles[i].getName().replaceAll("_", " ");
                   userDir = listOfFiles[i];

                   Owner o = new Owner(existingOwner, userDir);

                   boolean ownerExists = false;
                   for(int j = 0; j < owners.size(); j++) {
                       if(o.getName().equals(owners.get(j).getName())) {
                           ownerExists = true;
                           break;
                       }
                   }

                   if(!ownerExists) {
                       String line = "";
                       String cvsSplitBy = ",";

                       try {
                           BufferedReader br = new BufferedReader(new FileReader(listOfFiles[i] + "/PropertyInfo.csv"));
                           BufferedReader br2 = new BufferedReader(new FileReader(listOfFiles[i] + "/PaymentsFrom"
                                                                   + existingOwner.replaceAll(" ", "") + ".csv"));

                           br.readLine();
                           br2.readLine();

                           while((line = br.readLine()) != null) {
                               String[] data = line.split(cvsSplitBy);

                               String address = data[0];
                               String postcode = data[1];
                               Double marketValue = Double.parseDouble(data[2]);
                               String location = data[3];
                               Boolean principal = Boolean.parseBoolean(data[4]);
                               String dateOfLastTaxPayment = data[5];
                               o.registerProperty(address, postcode, marketValue, location, principal, dateOfLastTaxPayment);
                           }

                           ArrayList<Property> propertyList = o.getListOfProperties().getProperties();
                           double[] dueList = new double[propertyList.size()];
                           double[] overdueList = new double[propertyList.size()];
                           double[] totalList = new double[propertyList.size()];

                           for(int j = 0; j < propertyList.size(); j++) {
                               dueList[j] = -1;
                               overdueList[j] = -1;
                               totalList[j] = -1;
                           }

                           while((line = br2.readLine()) != null) {
                               if(line.length() > 0) {
                                   String[] data = line.split(cvsSplitBy);

                                   String postcode = data[0];
                                   double due = Double.parseDouble(data[5]);
                                   double overdue = Double.parseDouble(data[6]);
                                   double total = Double.parseDouble(data[7]);

                                   for(int j = 0; j < propertyList.size(); j++) {
                                       Property p = propertyList.get(j);

                                       if(p.getPostcode().equals(postcode)) {
                                           dueList[j] = due;
                                           overdueList[j] = overdue;
                                           totalList[j] = total;
                                       }
                                   }
                               }
                           }

                           for(int j = 0; j < propertyList.size(); j++) {
                               if(totalList[j] == 0) {
                                   propertyList.get(j).payTotalTax();
                               } else if(dueList[j] == 0) {
                                   propertyList.get(j).payDueTax();
                               } else if(overdueList[j] == 0) {
                                   propertyList.get(j).payTotalOverdueTax();
                               }
                           }

                           br.close();
                           br2.close();
                       } catch (IOException e) {
                           System.out.println("Error: Failed to get property info.");
                       }

                       owners.add(o);
                   }

                   count++;
               }

               char c = 'A';
               for(int i = 0; i < owners.size(); i++) {
                   String username = owners.get(i).getName();
                   username = username.replaceAll("_", " ");

                   System.out.println(c + ") " + username);
                   c++;
               }

               System.out.println("(Type \"reg\" to register a new user or \"man\" to enter management mode.)");
               String input = in.nextLine();

               if(input.toLowerCase().equals("reg")) {
                   boolean check = registerNewUser();

                   if(check) {
                       break;
                   } else {
                       continue;
                   }
               } else if(input.toLowerCase().equals("man")) {
                   System.out.println("Entering Management Mode...");
                   ManagementMenu menu = new ManagementMenu(this);
                   menu.run();
                   continue;
               }

               int n = input.toUpperCase().charAt(0) - 'A';
               if (0 <= n && n < listOfFiles.length) {
                   userDir = listOfFiles[n];
                   currentUser = owners.get(n);

                   stop = true;
               } else {
                   System.out.println("Incorrect input: Select one of listed users.");
               }
            }

            stop = false;

            while(!stop) {
                System.out.println("(R)egister - (P)ay - (V)iew - (Q)uit");
                String input = in.nextLine().toUpperCase();

                if(input.toUpperCase().equals("R")) {
                    System.out.println("Address:");
                    String address = in.nextLine();

                    if(address.contains(",")) {
                        address = address.replaceAll(",", ".");
                    }

                    String postcode = postcodeCheck();

                    Double marketValue = getMarketValue();

                    String location = getLocationCategory();

                    Boolean principal = checkIfPPR();

                    String dateOfLastTaxPayment = getDateOfPayment();

                    boolean check = currentUser.registerProperty(address, postcode, marketValue, location, principal, dateOfLastTaxPayment);

                    if(!check) {
                        continue;
                    }

                    try {
                        FileWriter writer = new FileWriter(userDir + "/PropertyInfo.csv", true);

                        writer.append(address);
                        writer.append(',');
                        writer.append(postcode);
                        writer.append(',');
                        writer.append(Double.toString(marketValue));
                        writer.append(',');
                        writer.append(location);
                        writer.append(',');
                        writer.append(Boolean.toString(principal));
                        writer.append(',');
                        writer.append(dateOfLastTaxPayment);
                        writer.append('\n');

                        writer.flush();
                        writer.close();
                    } catch(IOException e) {
                        System.out.println("Failed to create user file. Exiting.");
                        System.exit(-1);
                    }
                } else if(input.toUpperCase().equals("P")) {
                    if(currentUser.getListOfProperties().getProperties().isEmpty()) {
                        System.out.println("No properties registered.");
                        continue;
                    }

                    System.out.println("Select a property:");
                    Property p = getPropertyChoice(currentUser.getListOfProperties().getProperties());

                    System.out.println("Due Tax: " + p.getDueTax());
                    System.out.println("Overdue Tax: " + p.getTotalOverdueTax());
                    System.out.println("Total Tax: " + p.getTotalTax() + "\n");
                    System.out.println("Select what you would like to pay: (D)ue / (O)verdue / (T)otal");

                    boolean cont = true;

                    while(cont) {
                        input = in.nextLine();

                        double oldDue = p.getDueTax();
                        double oldOverdue = p.getTotalOverdueTax();
                        double oldTotal = p.getTotalTax();
                        double payment = 0;
                        double newDue = 0;
                        double newOverdue = 0;
                        double newTotal = 0;

                        if(input.toUpperCase().equals("D")) {
                            payment = p.getDueTax();
                            boolean check = p.payDueTax();
                            newDue = p.getDueTax();
                            newOverdue = p.getTotalOverdueTax();
                            newTotal = p.getTotalTax();

                            if(check) {
                                currentUser.getPayments().addPayment(p.getPostcode(), oldDue, oldOverdue, oldTotal,
                                                                     payment, newDue, newOverdue, newTotal);
                            }

                            cont = false;
                        } else if(input.toUpperCase().equals("O")) {
                            payment = p.getTotalOverdueTax();
                            boolean check = p.payTotalOverdueTax();
                            newDue = p.getDueTax();
                            newOverdue = p.getTotalOverdueTax();
                            newTotal = p.getTotalTax();

                            if(check) {
                                currentUser.getPayments().addPayment(p.getPostcode(), oldDue, oldOverdue, oldTotal,
                                                                     payment, newDue, newOverdue, newTotal);
                            }

                            cont = false;
                        } else if(input.toUpperCase().equals("T")) {
                            payment = p.getTotalTax();
                            boolean check = p.payTotalTax();
                            newDue = p.getDueTax();
                            newOverdue = p.getTotalOverdueTax();
                            newTotal = p.getTotalTax();

                            if(check) {
                                currentUser.getPayments().addPayment(p.getPostcode(), oldDue, oldOverdue, oldTotal,
                                                                     payment, newDue, newOverdue, newTotal);
                            }

                            cont = false;
                        } else {
                            System.out.println("Incorrect input. Select one of three options D / O / T");
                        }
                    }
                } else if(input.toUpperCase().equals("V")) {
                    ArrayList<Property> listOfProperties = currentUser.getListOfProperties().getProperties();
                    Property p = null;

                    if(listOfProperties.isEmpty()) {
                        System.out.println("No properties registered.");
                        continue;
                    } else if(listOfProperties.size() == 1) {
                        p = listOfProperties.get(0);

                        viewProperty(p);
                    } else {
                        System.out.println("Select a property:");
                        p = getPropertyChoice(listOfProperties);

                        viewProperty(p);
                    }
                } else if(input.toUpperCase().equals("Q")) {
                    System.out.println("(Q)uit program or (C)hange user?");
                    input = in.nextLine();

                    if(input.toUpperCase().equals("Q")) {
                        System.out.println("Quitting program...");
                        stop = true;
                        exit = true;
                    } else if(input.toUpperCase().equals("C")) {
                        System.out.println("Changing user...\n");
                        stop = true;
                    } else {
                        System.out.println("Unknown input: Quitting program...");
                        stop = true;
                        exit = true;
                    }
                }
            }
        }
    }

    /**
     * Gets owners.
     *
     * @return The list of owners registered.
     */
    public ArrayList<Owner> getOwners() {
        return owners;
    }


    private boolean registerNewUser() {
        System.out.println("Enter name (First name + Last name, e.g. John Johnson):");
        String user = in.nextLine();

        for(int i = 0; i < owners.size(); i++) {
            if(user.equals(owners.get(i).getName())) {
                System.out.println("Error: Owner already exists.\n");
                return false;
            }
        }

        user = user.replaceAll(" ", "_");
        userDir = new File(ownersDir + "/" + user);
        userDir.mkdir();

        userInfo = new File(userDir + "/PropertyInfo.csv");
        File paymentInfo = new File(userDir + "/PaymentsFrom" + user.replaceAll("_", "") + ".csv");

        try {
            userInfo.createNewFile();
            paymentInfo.createNewFile();

            FileWriter userWriter = new FileWriter(userDir + "/PropertyInfo.csv", true);
            FileWriter paymentWriter = new FileWriter(userDir + "/PaymentsFrom" + user.replaceAll("_", "") + ".csv", true);

            userWriter.append("Address");
            userWriter.append(',');
            userWriter.append("Postcode");
            userWriter.append(',');
            userWriter.append("Market Value");
            userWriter.append(',');
            userWriter.append("Location");
            userWriter.append(',');
            userWriter.append("Principal");
            userWriter.append(',');
            userWriter.append("Last Tax Payment");
            userWriter.append('\n');

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

            userWriter.flush();
            userWriter.close();
        } catch(IOException e) {
            System.out.println("Failed to create user file. Exiting.");
            System.exit(-1);
        }

        currentUser = new Owner(user.replaceAll("_", " "), userDir);
        owners.add(currentUser);

        return true;
    }

    private String postcodeCheck() {
        String postcode = null;
        boolean cont = true;

        while(cont) {
            System.out.println("Postcode (e.g. A65 F4E2):");
            postcode = in.nextLine();

            postcode = postcode.toUpperCase();

            if(Pattern.matches("[A-Z][0-9]{2}\\s[A-Z0-9]{4}", postcode)) {
                cont = false;
            } else if(Pattern.matches("D6W\\s[A-Z0-9]{4}", postcode)) { // exception to postcode format
                cont = false;
            } else {
                System.out.println("Error: Input does not match postcode format. Please try again.\n");
            }
        }

        return postcode;
    }

    private Double getMarketValue() {
        boolean cont = true;
        Double mV = null;

        while(cont) {
            System.out.println("Market Value (e.g. 50000.0):");
            mV = Double.parseDouble(in.nextLine());

            if(mV > 0) {
                cont = false;
            } else {
                System.out.println("Incorrect input: Enter a value that is more than 0");
            }
        }

        return mV;
    }

    private String getLocationCategory() {
        String category = "";
        boolean cont = true;

        System.out.println("Select Location Category");
        System.out.println("A: City");
        System.out.println("B: Large town");
        System.out.println("C: Small town");
        System.out.println("D: Village");
        System.out.println("E: Countryside");

        while(cont) {
            char input = in.next().charAt(0);
            in.nextLine();
            input = Character.toUpperCase(input);

            switch(input) {
                case 'A':
                    category = "City";
                    cont = false;
                    break;
                case 'B':
                    category = "Large town";
                    cont = false;
                    break;
                case 'C':
                    category = "Small town";
                    cont = false;
                    break;
                case 'D':
                    category = "Village";
                    cont = false;
                    break;
                case 'E':
                    category = "Countryside";
                    cont = false;
                    break;
                default:
                    System.out.println("Incorrect input. Select choices between A - E.");
            }
        }

        return category;
    }

    private Boolean checkIfPPR() {
        Boolean check = null;
        boolean cont = true;

        System.out.println("Is this your Principal Private Residence? Y / N");

        while(cont) {
            char input = in.next().charAt(0);
            in.nextLine();
            input = Character.toUpperCase(input);

            switch(input) {
                case 'Y':
                    check = true;
                    cont = false;
                    break;
                case 'N':
                    check = false;
                    cont = false;
                    break;
                default:
                    System.out.println("Incorrect input. Select Y or N.");
            }
        }

        return check;
    }

    private String getDateOfPayment() {
        boolean cont = true;
        String date = null;

        System.out.println("Date of Last Tax Payment (in YYYY-MM-DD format, e.g. 2020-11-30):");

        while(cont) {
            date = in.nextLine();

            if(Pattern.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", date)) {
                cont = false;
            } else {
                System.out.println("Error: Input does not match date format. Please try again.\n");
            }
        }

        return date;
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

    private void viewProperty(Property p) {
        boolean cont = true;
        boolean stop = false;

        while(cont) {
            System.out.println("Select how to view balance: (C)hoose Year / (A)ll Years");
            String input = in.nextLine();

            if(input.toUpperCase().equals("C")) {
                System.out.print("Select year: ");

                for(int i = p.getYearsDue(); i > 0; i--) {
                    System.out.print(LocalDate.now().getYear() - (i - 1));

                    if((i - 1) != 0) {
                        System.out.print(" / ");
                    } else {
                        System.out.println();
                    }
                }

                while(!stop) {
                    int num = in.nextInt();
                    in.nextLine();

                    for(int i = 0; i < p.getYearsDue(); i++) {
                        if(num == (LocalDate.now().getYear() - (p.getYearsDue() - 1)) + i) {
                            num = i;
                            break;
                        }
                    }

                    if(num >= 0 && num < p.getYearsDue()) {
                        System.out.println(p.showTax(num));
                        stop = true;
                    } else {
                        System.out.println("Incorrect input: Select listed years.");
                    }
                }

                cont = false;
            } else if(input.toUpperCase().equals("A")) {
                for(int i = 0; i < p.getYearsDue() - 1; i++) {
                    System.out.println("Year " + (i + 1) + "\n" + p.showTax(i));
                }
                System.out.println("Current Year\n" + p.showTotalTax());

                cont = false;
            } else {
                System.out.println("Incorrect input: Select C or A.");
            }
        }
    }
}
