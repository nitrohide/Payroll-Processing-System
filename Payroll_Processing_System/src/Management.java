/**
 * The Management class inherits from Full-time, and is a class to represent an object for a Manager.
 * @author Anuraj Dubey, Chenghao Lin
 */
public class Management extends Fulltime{
    private int role;
    private double compensation;
    private String role_name;
    final static int MANAGER = 1;
    final static double MANAGER_COMPENSATION = 5000;
    final static int DEPARTMENT_HEAD = 2;
    final static double DEPARTMENT_HEAD_COMPENSATION = 9500;
    final static int DIRECTOR= 3;
    final static double DIRECTOR_COMPENSATION = 12000;

    /**
     * Constructor for Management object.
     * @param name Name of Manager.
     * @param department Department of Manager.
     * @param dateHired Date Manager was hired.
     * @param yearlySalary Salary of Manager.
     * @param role Role of Manager.
     */
    public Management(Profile Profile, double yearlySalary, int role){
        super(Profile, yearlySalary);
        this.role = role;

        if (this.role == MANAGER){
            this.role_name = "Manager";
            compensation = MANAGER_COMPENSATION/PAYPERIODS;
        }
        else if (this.role == DEPARTMENT_HEAD){
            this.role_name = "Department Head";
            compensation = DEPARTMENT_HEAD_COMPENSATION/PAYPERIODS;
        }
        else if (this.role == DIRECTOR){
            this.role_name = "Director";
            compensation = DIRECTOR_COMPENSATION/PAYPERIODS;
        }
        super.setCompensation(compensation);
    }

    /**
     * Method that calculates payment due for each Manager.
     */
    @Override
    public void calculatePayment() {
        super.calculatePayment();
    }

    /**
     * This is a method that takes a Management object and amends it to a string format for printing.
     * @return The string formatted Management object.
     */
    @Override
    public String toString() {
        String formattedCompensation = String.format("%,.2f", this.compensation);
        return super.toString() + "::" + this.role_name + " Compensation $"
                + formattedCompensation;
    }
    /**
     * This method checks if a Management object is equal to another one.
     * @return True if equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Management){
            Management otherManagement = (Management) obj;
            if (!(otherManagement.role == this.role)){
                return false;
            }
            if (!(otherManagement.role_name == this.role_name)) {
                return false;
            }

        }
        return true;
    }
}
