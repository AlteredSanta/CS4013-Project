import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The type Owner.
 */
public class Owner {
    private String name;
    private Properties listOfProperties;
    private Payments ownerPayments;

    /**
     * Instantiates a new Owner.
     *
     * @param name    Name of the owner.
     * @param userDir The user directory.
     */
    public Owner(String name, File userDir) {
        this.name = name;
        listOfProperties = new Properties();
        ownerPayments = new Payments(name, userDir);
    }

    /**
     * Register property boolean.
     *
     * @param address              The address of the property.
     * @param postcode             The postcode of the property.
     * @param marketValue          The property's market value.
     * @param location             The property's location category.
     * @param principal            Checks if property is a principal private residence.
     * @param dateOfLastTaxPayment The date of the property's last tax payment.
     * @return If property successfully registered, returns true. Else, returns false.
     */
    public boolean registerProperty(String address, String postcode, Double marketValue, String location, Boolean principal, String dateOfLastTaxPayment) {
        ArrayList<Property> p = listOfProperties.getProperties();

        for(int i = 0; i < p.size(); i++) {
            if(address.equals(p.get(i).getAddress()) || postcode.equals(p.get(i).getPostcode())) {
                System.out.println("Error: Property already exists.");
                return false;
            }
        }

        if(address == null || postcode == null || marketValue == null || location == null || principal == null) {
            System.out.println("Error: Detected unrecorded value(s).");
            return false;
        } else if(marketValue < 0) {
            System.out.println("Error: Negative value(s) detected.");
            return false;
        } else {
            LocalDate lastTaxPayment = LocalDate.parse(dateOfLastTaxPayment);

            if(principal) {
                int size = p.size();

                for(int i = 0; i < size; i++) {
                    if(p.get(i).getPrincipal()) {
                        System.out.println("Error: Principal Private Residence already exists.");
                        return false;
                    }
                }
            }

            Property newProperty = new Property(address, postcode, marketValue, location, principal, lastTaxPayment);
            listOfProperties.addProperty(newProperty);
        }
        return true;
    }

    /**
     * Gets the owner's name.
     *
     * @return the owner's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets list of properties registered by the owner.
     *
     * @return the list of properties registered by the owner
     */
    public Properties getListOfProperties() {
        return listOfProperties;
    }

    /**
     * Gets payments made by the owner.
     *
     * @return the payments made by the owner
     */
    public Payments getPayments() {
        return ownerPayments;
    }

    @Override
    public String toString() {
        return name;
    }
}
