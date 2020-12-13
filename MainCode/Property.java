import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * The type Property.
 */
public class Property {
    private String address;
    private String postcode;
    private double marketValue;
    private String location;
    private boolean principal;
    private LocalDate lastTaxPayment;

    private double dueTax;
    private double[] overdueTax;
    private double totalOverdueTax;
    private double totalTax;
    private int yearsDue;
    private double taxPaid;

    /**
     * Instantiates a new Property.
     *
     * @param address              The address of the property.
     * @param postcode             The postcode of the property.
     * @param marketValue          The property's market value.
     * @param location             The property's location category.
     * @param principal            Checks if property is a principal private residence.
     * @param lastTaxPayment       The date of the property's last tax payment.
     */
    public Property(String address, String postcode, double marketValue, String location, boolean principal, LocalDate lastTaxPayment) {
        this.address = address;
        this.postcode = postcode;
        this.marketValue = marketValue;
        this.location = location;
        this.principal = principal;
        this.lastTaxPayment = lastTaxPayment;

        PropertyTax currentTax = new PropertyTax(this);
        dueTax = currentTax.getDueTax();
        overdueTax = currentTax.getOverdueTax();
        totalOverdueTax = currentTax.getTotalOverdueTax();
        totalTax = currentTax.getTotalTax();
        yearsDue = currentTax.getYearsDue();
    }

    /**
     * Gets the address of the property.
     *
     * @return the address of the property.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the postcode of the property.
     *
     * @return the postcode of the property.
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Gets routing key.
     *
     * @return the routing key.
     */
    public String getRoutingKey() {
        return postcode.substring(0, 3);
    }

    /**
     * Gets the property's market value.
     *
     * @return the market value of the property.
     */
    public double getMarketValue() {
        return marketValue;
    }

    /**
     * Gets the property's location category.
     *
     * @return the location category.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Checks if property is a principal private residence.
     *
     * @return the principal private residence.
     */
    public boolean getPrincipal() {
        return principal;
    }

    /**
     * Gets due tax.
     *
     * @return the due tax.
     */
    public double getDueTax() {
        return dueTax;
    }

    /**
     * Pay due tax.
     *
     * @return true if tax paid successfully, false if otherwise.
     */
    public boolean payDueTax() {
        if(dueTax > 0) {
            totalTax = round(totalTax - dueTax);
            taxPaid = round(taxPaid + dueTax);
            dueTax = 0;

            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets overdue tax owed on the year specified.
     *
     * @param year the year
     * @return the overdue tax owed on the year specified.
     */
    public double getOverdueTax(int year) {
        double total = 0.0;

        for(int i = 0; i < year; i++) {
            total += overdueTax[year];
        }

        return round(total);
    }

    /**
     * Gets total overdue tax.
     *
     * @return the total overdue tax.
     */
    public double getTotalOverdueTax() {
        return totalOverdueTax;
    }

    /**
     * Pay total overdue tax.
     *
     * @return true if tax paid successfully, false if otherwise.
     */
    public boolean payTotalOverdueTax() {
        if(totalOverdueTax > 0) {
            totalTax = round(totalTax - totalOverdueTax);
            taxPaid = round(taxPaid + totalOverdueTax);

            for(int i = 0; i < overdueTax.length; i++) {
                overdueTax[i] = 0;
            }

            totalOverdueTax = 0;

            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets total tax.
     *
     * @return the total tax
     */
    public double getTotalTax() {
        return totalTax;
    }

    /**
     * Pay total tax.
     *
     * @return true if tax paid successfully, false if otherwise.
     */
    public boolean payTotalTax() {
        if(totalTax > 0) {
            taxPaid = round(taxPaid + totalTax);
            dueTax = 0;

            for(int i = 0; i < overdueTax.length; i++) {
                overdueTax[i] = 0;
            }

            totalOverdueTax = 0;
            totalTax = 0;

            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets last tax payment.
     *
     * @return the last tax payment
     */
    public LocalDate getLastTaxPayment() {
        return lastTaxPayment;
    }

    /**
     * Gets total amount of years that tax is owed for.
     *
     * @return the total amount of years that tax is owed for.
     */
    public int getYearsDue() {
        return yearsDue;
    }

    /**
     * Gets the amount of tax paid.
     *
     * @return the amount of tax paid
     */
    public double getTaxPaid() {
        return round(taxPaid);
    }

    @Override
    public String toString() {
        String PPR = "no";
        String propString = "Address: " + address + "\n" + "Postcode: " + postcode + "\n" +
                "Market Value: " + marketValue + "\n" + "Location: " + location + "\n";

        if(principal) {
            PPR = "yes";
        }

        propString += "Principal Private Residence: " + PPR + "\n";

        return propString;
    }

    /**
     * Show balance statement of the specified year.
     *
     * @param year the year
     * @return the balance statement of the specified year.
     */
    public String showTax(int year) {
        if(year + 1 == yearsDue) {
            return showTotalTax();
        }

        double due = overdueTax[year];
        double overdue = 0.0;
        double total = 0.0;

        for(int i = 0; i < year; i++) {
            overdue += overdueTax[i];
        }

        total = due + overdue;

        return "Due Tax: " + round(due) + "\nOverdue Tax: " + round(overdue) + "\nTotal Tax: " + round(total) + "\n";
    }

    /**
     * Show total balance statement.
     *
     * @return the total balance statement.
     */
    public String showTotalTax() {
        double due = dueTax;
        double overdue = 0.0;
        double total = 0.0;

        for(int i = 0; i < yearsDue - 1; i++) {
            overdue += overdueTax[i];
        }

        total = due + overdue;

        return "Due Tax: " + round(due) + "\nOverdue Tax: " + round(overdue) + "\nTotal Tax: " + round(total) + "\n";
    }

    private double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
