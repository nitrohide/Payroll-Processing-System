/**
 * The Employee class is the class for an Employee object which are what make up the list of elements within the Company class.
 * @author Anuraj Dubey, Chenghao Lin
 */
public class Employee {

    private Profile profile;
    private double payment;

    /**
     * This is a constructor for the Employee class.
     * @param name This is the name of an employee.
     * @param department This is the department an employee works in.
     * @param dateHired The date an employee was hired.
     */
    public Employee(Profile profile){
        this.profile = profile;
    }

    /**
     * This is a getter method to get the Profile object of the Employee.
     * @return Profile object.
     */
    public Profile getProfile(){
        return profile;
    }


    /**
     * Setter method to set the payment value.
     * @param payment The payment value.
     */
    public void setPayment(double payment) {
        this.payment = payment;
    }

    /**
     * Getter method for the payment value set.
     */
    public double getPayment(){
        return payment;
    }

    /**
     * Method that calculates the payment for each Employee.
     */
    public void calculatePayment() { }  //to be inherited by Fulltime/Parttime/Management


    /**
     * This is a method that takes an Employee object and amends it to a string format for printing.
     * @return The string formatted Employee.
     */
    @Override
    public String toString() {
        String payment = String.format("%,.2f", this.getPayment());
        return profile.toString() + "::Payment $" + payment + "::";
    }

    /**
     * This method checks if an Employee object is equal to another Employee object.
     * @return True if equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Employee){
            Employee otherEmployee = (Employee) obj;
            if (!otherEmployee.profile.equals(this.profile)){
                return false;
            }
            if (!(otherEmployee.payment == this.payment)){
                return false;
            }

        }
        return true;
    }
}
