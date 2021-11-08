/**
 * Class for full-time employees which inherits from its parent class, Employee.
 * @author Anuraj Dubey, Chenghao Lin
 */
public class Fulltime extends Employee{
    private double yearlySalary;
    public static final int PAYPERIODS = 26;
    double compensation;

    /**
     * Constructor to create a Fulltime object.
     * @param name Name of employee.
     * @param department Department of employee.
     * @param dateHired Date employee was hired.
     * @param yearlySalary Salary of employee.
     */
    public Fulltime(Profile Profile, double yearlySalary){
        super(Profile);
        this.yearlySalary = yearlySalary;
        this.compensation = 0;
    }
    public void setCompensation(double compensation){
        this.compensation = compensation;
    }
    public double getCompensation(){
        return this.compensation;
    }

    /**
     * Method that calculated payment due for each Full-time employee.
     */
    @Override
    public void calculatePayment() {
        double payment = (this.yearlySalary / PAYPERIODS) + compensation ;
        super.setPayment(payment);
    }

    /**
     * This is a method that takes a Full-time object and amends it to a string format for printing.
     * @return The string formatted Full-Time object.
     */
    @Override
    public String toString() {
        String formattedSalary = String.format("%,.2f", this.yearlySalary);
        return super.toString() + "FULL TIME::Annual Salary $" + formattedSalary;
    }

    /**
     * This method checks if a Fulltime object is equal to another one.
     * @return True if equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Employee){
            Fulltime otherFulltime = (Fulltime) obj;
            if (!(otherFulltime.yearlySalary == this.yearlySalary)){
                return false;
            }
        }
        return true;
    }

    /**
     * Getter method to get the salary of the Full-time worker.
     * @return
     */
    public double getSalary(){
        return yearlySalary;
    }
}
