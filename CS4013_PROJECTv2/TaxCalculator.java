import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * The type Tax calculator.
 */
public class TaxCalculator {
    private double marketValue;
    private String location;
    private boolean principal;
    private LocalDate lastTaxPayment;
    private int overdueCheck;

    private double dueTax;
    private double[] overdueTax;
    private double totalOverdueTax;
    private double totalTax;

    /**
     * Instantiates a new Tax calculator.
     *
     * @param marketValue          The property's market value.
     * @param location             The property's location category.
     * @param principal            Checks if property is a principal private residence.
     * @param lastTaxPayment       The date of the property's last tax payment.
     */
    public TaxCalculator(double marketValue, String location, boolean principal, LocalDate lastTaxPayment) {
        this.marketValue = marketValue;
        this.location = location;
        this.principal = principal;
        this.lastTaxPayment = lastTaxPayment;

        LocalDate dueDate = LocalDate.of((LocalDate.now().getYear() + 1), 1, 1);

        overdueCheck = dueDate.getYear() - (lastTaxPayment.getYear() + 1);

        dueTax = round(calculateTax(overdueCheck));
        calculateOverdueTax();
        calculateTotalTax();
    }

    /**
     * Gets due tax.
     *
     * @return the due tax
     */
    public double getDueTax() {
        return dueTax;
    }

    /**
     * Sets due tax.
     *
     * @param dueTax the due tax
     */
    public void setDueTax(double dueTax) {
        this.dueTax = dueTax;
    }

    /**
     * Gets overdue tax.
     *
     * @return The array of overdue tax.
     */
    public double[] getOverdueTax() {
        return overdueTax;
    }

    /**
     * Gets overdue tax owed on the year specified.
     *
     * @param year the year
     * @return the overdue tax owed on the year specified.
     */
    public double getOverdueTax(int year) {
        return overdueTax[year];
    }

    /**
     * Gets total overdue tax.
     *
     * @return the total overdue tax
     */
    public double getTotalOverdueTax() {
        return totalOverdueTax;
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
     * Gets overdue check (how many years are overdue).
     *
     * @return the overdue check
     */
    public int getOverdueCheck() {
        return overdueCheck;
    }

    /**
     * Gets market value of the property.
     *
     * @return the market value of the property.
     */
    public double getMarketValue() {
        return marketValue;
    }

    /**
     * Gets market rate.
     *
     * @return the market rate
     */
    public String getMarketRate() {
        if(marketValue < 150000) {
            return "0.00 (0%)";
        } else if(marketValue >= 150000 && marketValue <= 400000) {
            return round(marketValue * 0.0001) + " (0.01%)";
        } else if(marketValue >= 400001 && marketValue <= 650000) {
            return round(marketValue * 0.0002) + " (0.02%)";
        } else {
            return round(marketValue * 0.0004) + " (0.04%)";
        }
    }

    /**
     * Gets location rate.
     *
     * @return the location rate
     */
    public String getLocationRate() {
        if (location.equals("City")) {
            return "100";
        } else if (location.equals("Large town")) {
            return "80";
        } else if (location.equals("Small town")) {
            return "60";
        } else if (location.equals("Village")) {
            return "50";
        } else  {
            return "25";
        }
    }

    /**
     * Check if property is a principal private residence.
     *
     * @return PPR charge if true, nothing if false
     */
    public String checkPPR() {
        if(principal) {
            return "100";
        } else {
            return "0";
        }
    }

    /**
     * Gets unpaid property tax penalty.
     *
     * @return the unpaid property tax penalty
     */
    public String getUnpaidPenalty() {
        if(overdueCheck != 0) {
            double additionalTax = round((overdueTax[0] * Math.pow((1 + 0.07), overdueCheck)) - overdueTax[0]);

            return additionalTax + " (" + round((Math.pow((1 + 0.07), overdueCheck) - 1) * 100) + "%)";
        } else {
            return "0";
        }
    }

    private double calculateTax(int overdueCheck) {
        double tax = 100.0;

        if(principal) {
            tax += 100.0;
        }

        if(marketValue >= 150000 && marketValue <= 400000) {
            tax += marketValue * 0.0001;
        } else if(marketValue >= 400001 && marketValue <= 650000) {
            tax += marketValue * 0.0002;
        } else if(marketValue > 650000) {
            tax += marketValue * 0.0004;
        }

        switch(location) {
            case "City":
                tax += 100.0;
                break;

            case "Large town":
                tax += 80.0;
                break;

            case "Small town":
                tax += 60.0;
                break;

            case "Village":
                tax += 50.0;
                break;

            case "Countryside":
                tax += 25.0;
                break;
        }

        if(overdueCheck != 0) {
            tax *= Math.pow((1 + 0.07), overdueCheck);
        }

        return tax;
    }

    private void calculateOverdueTax() {
        overdueTax = new double[overdueCheck];

        for(int i = 0; i < overdueCheck; i++) {
            overdueTax[i] = round(calculateTax(i));
        }
    }

    private void calculateTotalTax() {
        for(int i = 0; i < overdueCheck; i++) {
            totalOverdueTax += overdueTax[i];
        }

        totalTax = round(dueTax + totalOverdueTax);
    }

    private double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
