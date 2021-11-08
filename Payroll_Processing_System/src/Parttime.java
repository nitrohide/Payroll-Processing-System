/**
 * This is a class to create a Part-time Employee. It inherits from the Employee class.
 * @author Anuraj Dubey, Chenghao Lin
 */
public class Parttime extends Employee{
    final int PAY_PERIOD_MAX = 80;
    final double OVERTIME_RATE = 1.5;

    private int hoursWorked;
    private double hourlyRate;

    /**
     * Constructor to create a parttime object.
     * @param name The name of the parttimer.
     * @param department Department of the parttimer
     * @param dateHired The date hired.
     * @param hourlyRate The hourly wage of the parttimer.
     */
    public Parttime(Profile Profile, double hourlyRate){
        super(Profile);
        this.hourlyRate = hourlyRate;
    }

    /**
     * Method that calculates payment due for each Part-timer.
     */
    @Override
    public void calculatePayment() {
        double payment;
        if (this.hoursWorked <= PAY_PERIOD_MAX) {  //if hoursworked did not exceed normal hours
            payment = this.hourlyRate * this.hoursWorked;
        }
        else {
            int overtimeHours = this.hoursWorked - PAY_PERIOD_MAX;
            payment = (this.hourlyRate * PAY_PERIOD_MAX) + (overtimeHours * OVERTIME_RATE * this.hourlyRate);
        }
        super.setPayment(payment);
    }


    /**
     * This is a method that takes a Parttime object and amends it to a string format for printing.
     * @return The string formatted Parttime object.
     */
    @Override
    public String toString() {
        String formattedRate = String.format("%,.2f", this.hourlyRate);
        return super.toString() + "PART TIME::Hourly Rate $" + formattedRate + "::"
                + "Hours worked this period: " + this.hoursWorked;
    }

    /**
     * This method checks if a PartTime object is equal to another one.
     * @return True if equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Employee){
            Parttime otherParttime = (Parttime) obj;
            if (!(otherParttime.hoursWorked == this.hoursWorked)){
                return false;
            }
            if (!(otherParttime.hourlyRate == this.hourlyRate)) {
                return false;
            }

        }
        return true;
    }

    /**
     * Setter method to set the hours worked for a parttimer.
     */
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    /**
     * Getter method to get the number of hours worked.
     * @return The hours worked during the pay period.
     */
    public int getHoursWorked() {
        return hoursWorked;
    }

}
