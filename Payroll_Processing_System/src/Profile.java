/**
 * The Profile class is responsible for keeping track of unique attributes of an employee. These attributes are their name, department
 * and the date they were hired.
 * @author Anuraj Dubey, Chenghao Lin
 */
public class Profile {
    private String name; //employee’s name in the form 'lastname,firstname'
    private String department; //department code: CS, ECE, IT
    private Date dateHired;


    /**
     * This is a constructor to create a Profile object.
     * @param name This is the name of the employee.
     * @param department This is the department the employee works in.
     * @param dateHired This is the date the employee was hired.
     */
    public Profile(String name, String department, Date dateHired){
        this.name = name;
        this.department = department;
        this.dateHired = dateHired;
    }

    /**
     * Getter method to get the department of an employee
     * @return The department.
     */
    public String getDepartment(){
        return department;
    }
    /**
     * Getter method to get the date hired.
     * @return The date hired.
     */
    public Date getDateHired(){
        return dateHired;
    }

    /**
     * This is a method that takes a Profile object and amends it to a string format for printing.
     * @return The string formatted Profile.
     */
    @Override
    public String toString() {
        return this.name + "::" + this.department + "::" + this.dateHired.toString();
    }

    /**
     * This method checks if a Profile object is equal to another Profile object.
     * @return True if equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {  //compare name, department and dateHired

        if (obj instanceof Profile){
            Profile otherProfile = (Profile) obj;
            if (!otherProfile.name.equals(this.name)){
                return false;
            }
            if (!otherProfile.department.equals(this.department)){
                return false;
            }
            if (otherProfile.dateHired.compareTo(this.dateHired) != 0){
                return false;
            }
        }
        return true;
    }
}
