/**
 * The Company class is responsible for creating and maintaining a container, or array, of type Employee.
 * It holds multiple methods that revolve around maintaining and manipulating this array in order to serve as an effective Company
 * payment management sytem.
 * @author Anuraj Dubey, Chenghao Lin
 */
public class Company {
    private Employee[] emplist;
    private int numEmployee;

    /**
     * This is a constructor to create an initial container list of Employees
     */
    public Company(){//constructor to create empty container for employee list
        emplist = new Employee[BAG_INITIAL_VALUE];
    }

    public static final int BAG_INITIAL_VALUE = 4;
    public static final int BAG_GROW_VALUE = 4;
    public static final int NOT_FOUND = -1;
    public static final int MAXIMUM_HOURS = 100;
    public static final int MINIMUM_HOURS = 0;


    /**
     * This is a method to find a certain employee in the list, given and input of type Employee.
     * @param employee The employee that is trying to be found.
     * @return i, the index of the book being searched for.
     */
    private int find(Employee employee) {
        for (int i = 0; i < numEmployee; i++){
            if (employee.getProfile().equals(emplist[i].getProfile())){
                return i;
            }
        }
        return NOT_FOUND; //employee not found
    }

    /**
     * This is a method to grow the list by a given value of 4, anytime the list gets overfilled by Employees.
     * It creates a new list and copies the old list into this new one.
     */
    private void grow() {

        Employee[] temp = new Employee[emplist.length + BAG_GROW_VALUE]; //temp to hold all books before copying into new bag

        for (int i = 0; i < numEmployee; i++){
            temp[i] = emplist[i];
        }
        emplist = temp;

    }

    /**
     * This method adds a given inputted employee at the end of the list.
     * @param employee The employee being added into the bag
     * @return Returns true if add was successful, and false otherwise.
     */
    public boolean add(Employee employee) {  //check the profile before adding
        if (find(employee) == NOT_FOUND) {
            if (emplist[emplist.length - 1] != null) {
                grow();
            }
            for (int i = 0; i < emplist.length; i++) {
                if (emplist[i] == null) {
                    emplist[i] = employee;
                    numEmployee++;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method removes a given Employee from the list by setting that respective index to null.
     * Additionally, it shifts employees further in the bag to the left following the removal.
     * This readjustment is to make sure there is no empty spot in the middle of the bag.
     * @param employee The employee being removed from the list
     * @return true if the removal was successful, or false if the removal was unsuccessful.
     */
    public boolean remove(Employee employee) {

        int index_to_remove = find(employee);
        if (index_to_remove == NOT_FOUND) {
            return false;
        }

        for ( int j = index_to_remove; j < emplist.length ; j++ ){//traverse through rest of list and shift to left
            if ( j == (emplist.length - 1) ){ //check if traversal reaches last index of list
                emplist[j] = null; //if last index of list, set to null in order to accomodate for removed employee
                numEmployee--;
                return true;
            }
            if ( emplist[j+1] == null){ //make last element in list null to accomodate for removed employee
                emplist[j] = null;
                numEmployee--;
                return true;
            }
            emplist[j] = emplist[j + 1]; //set each element to value of next to preserve order of employees
        }
        return false;

    }

    /**
     * This method takes an employee and searches for the equivalent employee in the list.
     * If the employee is found, AND they are indeed a part-time worker, the hours they worked during the pay period will be updated.
     * @param employee An equivalent employee object which serves to help update the real employee object's hours worked.
     * @return Returns True if the working hours update was successful and false otherwise.
     */
    public boolean setHours(Employee employee) {  //set working hours for a part time
        for (int i = 0; i < emplist.length; i++) {
            if (employee instanceof Parttime) {
                Parttime setEmployee = (Parttime) employee;
                int hoursToSet = setEmployee.getHoursWorked();
                int index = find(employee);
                if (index == NOT_FOUND) {
                    return false;
                }
                Parttime employeeFound = (Parttime)emplist[index];
                employeeFound.setHoursWorked(hoursToSet);
                return true;
            }
        }
        return false;
    }

    /**
     * This method just kickstarts the payment processing by sequentially calling calculatePayment() on every Employee in the list.
     */
    public void processPayments() { //process payments for all employees

        for(int i = 0; i < numEmployee; i++){
            emplist[i].calculatePayment();
        }

    }

    /**
     * This method prints out all employees in the list in a sequence.
     */
    public String print() {  //print earning statements for all employees
        String output = "";
        for(int i = 0; i < emplist.length; i++){
            if (emplist[i]!= null) {
               output += (emplist[i].toString()) + "\n";
            }
        }
        return output;
    }

    /**
     * This method prints out all earning statments grouped by department.
     */
    public String printByDepartment() { //print earning statements by department
        String output = "";
        for( int i = 0; i < numEmployee; i++){
            if (emplist[i].getProfile().getDepartment().equals("CS")){
                output += (emplist[i].toString()) + "\n";
            }
        }

        for( int i = 0; i < numEmployee; i++){
            if (emplist[i].getProfile().getDepartment().equals("ECE")){
                output += (emplist[i].toString()) + "\n";
            }
        }

        for( int i = 0; i < numEmployee; i++){
            if (emplist[i].getProfile().getDepartment().equals("IT")){
                output += (emplist[i].toString()) + "\n";
            }
        }
        return output;
    }

    /**
     * This method prints all earning statements sorted by the date each employee was hired.
     */
    public String printByDate() {  //print earning statements by date hired
        String output = "";
        //Using a simple selection sort algorithm to sort employees by date in ascending order
        for (int i = 0; i < emplist.length-1; i++) {   //keep track of sorted part of bag
            int min_idx = i;
            for (int j = i + 1; j < emplist.length; j++) {
                if ( emplist[j] == null
                        || emplist[min_idx] == null) //if space in bag is empty, ignore and continue
                    continue;
                if (emplist[j].getProfile().getDateHired().getYear()
                        < emplist[min_idx].getProfile().getDateHired().getYear()){ //if year of index j is less than that of min_idx
                    min_idx = j;
                }
                if (emplist[j].getProfile().getDateHired().getYear()
                        == emplist[min_idx].getProfile().getDateHired().getYear()){ //if year of index j is equal to that of min_idx, check month
                    if ( emplist[j].getProfile().getDateHired().getMonth()
                            < emplist[min_idx].getProfile().getDateHired().getMonth()){ //continue to compare month values
                        min_idx = j;
                    }
                    if ( emplist[j].getProfile().getDateHired().getMonth()
                            == emplist[min_idx].getProfile().getDateHired().getMonth()){ //if months are equal, compare day
                        if ( emplist[j].getProfile().getDateHired().getDay()
                                < emplist[min_idx].getProfile().getDateHired().getDay()){ //compare day values
                            min_idx = j;
                        }
                    }
                }
            }
            //swapping the minimum element into the sorted region to complete iteration
            Employee temp = emplist[min_idx];
            emplist[min_idx] = emplist[i];
            emplist[i] = temp;
        }
        output = print(); //print the sorted bag
        return output;

    }

    /**
     * Method to get the number of employees.
     * @return
     */
    public int getNumEmployee(){
        return numEmployee;
    }


}
