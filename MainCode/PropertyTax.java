/**
 * The type Property tax.
 */
public class PropertyTax {
    private double dueTax;
    private double[] overdueTax;
    private double totalOverdueTax;
    private double totalTax;
    private int yearsDue;

    /**
     * Instantiates a new Property tax.
     *
     * @param p The property
     */
    public PropertyTax(Property p) {
        TaxCalculator calc = new TaxCalculator(p.getMarketValue(), p.getLocation(), p.getPrincipal(), p.getLastTaxPayment());
        dueTax = calc.getDueTax();
        overdueTax = calc.getOverdueTax();
        totalOverdueTax = calc.getTotalOverdueTax();
        totalTax = calc.getTotalTax();
        yearsDue = calc.getOverdueCheck() + 1;
    }

    /**
     * Gets due tax.
     *
     * @return The due tax.
     */
    public double getDueTax() {
        return dueTax;
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
     * Gets total overdue tax.
     *
     * @return The total overdue tax.
     */
    public double getTotalOverdueTax() {
        return totalOverdueTax;
    }

    /**
     * Gets total tax.
     *
     * @return The total tax.
     */
    public double getTotalTax() {
        return totalTax;
    }

    /**
     * Gets total amount of years that tax is owed for.
     *
     * @return the total amount of years that tax is owed for.
     */
    public int getYearsDue() {
        return yearsDue;
    }
}
