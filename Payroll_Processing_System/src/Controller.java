/**
 *  The Controller class controls the back-end functionality of all interface interactions a user has
 *  while using the GUI system.
 * @author Anuraj Dubey, Chenghao Lin
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.event.ActionEvent;
import javafx.event.Event;

import java.io.File;


public class Controller {
    Company Company = new Company();

    private boolean validSalary = false;
    private boolean validName = false;
    private boolean validHours = false;
    private boolean validRate = false;

    final static int FIRST_TOKEN = 1;
    final static int SECOND_TOKEN = 2;
    final static int THIRD_TOKEN = 3;
    final static int FOURTH_TOKEN = 4;
    final static int FIFTH_TOKEN = 5;
    final static int SIXTH_TOKEN = 6;
    final static int NOT_FOUND = -1;
    final static int DOES_NOT_EXIST = 0;
    final static int MANAGER = 1;
    final static int DEPARTMENT_HEAD = 2;
    final static int DIRECTOR= 3;


    @FXML
    private RadioButton csButton;

    @FXML
    private ToggleGroup departmentGroup;

    @FXML
    private RadioButton itButton;

    @FXML
    private RadioButton eceButton;

    @FXML
    private DatePicker dateInput;

    @FXML
    private RadioButton fulltimeButton;

    @FXML
    private ToggleGroup employeeGroup;

    @FXML
    private RadioButton parttimeButton;

    @FXML
    private RadioButton managementButton;

    @FXML
    private TextField salaryInput;

    @FXML
    private TextField hoursInput;

    @FXML
    private TextField rateInput;

    @FXML
    private RadioButton managerButton;

    @FXML
    private ToggleGroup roleGroup;

    @FXML
    private RadioButton deptHeadButton;

    @FXML
    private RadioButton directorButton;

    @FXML
    private TextField nameInput;

    @FXML
    private Button clearButton;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button setHoursButton;

    @FXML
    private TextArea textArea;

    /**
     * This method takes care of importing a file that holds different employees.
     * @param event the interaction with the button.
     */
    @FXML
    void importFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Source File for the Import");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file

    }

    
    /**
     * This method is responsible for reading from a source file that is inputted.
     * @param sourcefile the file to be read from.
     */
    void readFromFile(File sourcefile) {
        try {

            Scanner s = new Scanner(sourcefile).useDelimiter(",");
            int token = 1;
            String employeeType = null;
            String employeeName = null;
            String employeeDep = null;
            String date = null;
            String payment = null;
            String role = null;
            Date dateHired = null;

            while (s.hasNext()) {  //FIX magic numbers
                if (token == FIRST_TOKEN) {
                    employeeType = s.next();
                }
                if (token == SECOND_TOKEN) {
                    employeeName = s.next();
                }
                if (token == THIRD_TOKEN) {
                    employeeDep = s.next();
                }
                if (token == FOURTH_TOKEN) {
                    date = s.next();
                    dateHired = new Date(date);
                }
                if (token == FIFTH_TOKEN) {
                    payment = s.next();
                }
                if (token == SIXTH_TOKEN ){
                    role = s.next();
                }
                token++;
            }
            Profile p = new Profile(employeeName, employeeDep, dateHired);
            Employee e = new Employee(p);
            if (employeeType.equals("P")) {
                e = addParttime(p);
            }
            else if (employeeType.equals("F")) {
                e = addFulltime(p);
            }
            else if (employeeType.equals("M")) {
                e = addManagement(p);
            }
            isValid(e);

            }

         catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method exports a file with data from the GUI.
     * @param event the interaction with the button.
     */
    @FXML
    void exportFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
    }
    
    /**
     * This method writes a a file, that is to be exported to the user's computer.
     * @param targetFile a file to write to.
     */
    void  writeToFile(File targetFile){
        try {

            FileWriter file = new FileWriter(targetFile, true);
            BufferedWriter out = new BufferedWriter(file);
            out.write(textArea + "\n");
            out.close();
            file.close();
            //BufferedWriter.flush();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method takes care of the payment calculation to display on the GUI.
     * @param event the interaction with the button.
     */
    @FXML
    void calculatePayments(ActionEvent event) {
        if (Company.getNumEmployee() == DOES_NOT_EXIST) {
            textArea.appendText("Employee database is empty.\n");
        }
        else {
            Company.processPayments();
            textArea.appendText("Calculation of employee payments is done.\n");
        }
    }

    /**
     * This method takes care of printing the employees in the database onto the GUI.
     * @param event the interaction with the button.
     */
    @FXML
    void print(ActionEvent event) {
        if (Company.getNumEmployee() == DOES_NOT_EXIST) {
            textArea.appendText("Employee database is empty.\n");
        }
        else {
            textArea.appendText("--Printing earning statements for all employees--\n");
            textArea.appendText(Company.print());
        }
    }

    /**
     * This method takes care of printing the employees in the database by department onto the GUI.
     * @param event the interaction with the button.
     */
    @FXML
    void printDept(ActionEvent event) {
        if (Company.getNumEmployee() == DOES_NOT_EXIST) {
            textArea.appendText("Employee database is empty.\n");
        }
        else {
            textArea.appendText("--Printing earning statements by department--\n");
            textArea.appendText(Company.printByDepartment());
        }
    }

    /**
     * This method takes care of printing the employees in the database by date hired onto the GUI.
     * @param event the interaction with the button.
     */
    @FXML
    void printDate(ActionEvent event) {
        if (Company.getNumEmployee() == DOES_NOT_EXIST) {
            textArea.appendText("Employee database is empty.\n");
        }
        else {
            textArea.appendText("--Printing earning statements by date hired--\n");
            textArea.appendText(Company.printByDate());
        }
    }

    /**
     * This method takes care of establishing if an employee is a part time employee in the GUI.
     * @param event the interaction with the button.
     */
    @FXML
    void isParttime(ActionEvent event) {
        managerButton.setDisable(true);
        deptHeadButton.setDisable(true);
        directorButton.setDisable(true);
        managerButton.setSelected(false);
        addButton.setDisable(false);
        removeButton.setDisable(false);
        deptHeadButton.setSelected(false);
        directorButton.setSelected(false);
        rateInput.setDisable(false);
        hoursInput.setDisable(false);
        setHoursButton.setDisable(false);
        salaryInput.setDisable(true);
        salaryInput.clear();
    }

    /**
     * This method takes care of establishing if an employee a full time employee in the GUI.
     * @param event the interaction with the button.
     */
    @FXML
    void isFulltime(ActionEvent event) {
        rateInput.setDisable(true);
        rateInput.clear();
        hoursInput.setDisable(true);
        hoursInput.clear();
        addButton.setDisable(false);
        removeButton.setDisable(false);
        setHoursButton.setDisable(true);
        salaryInput.setDisable(false);
        managerButton.setDisable(true);
        deptHeadButton.setDisable(true);
        directorButton.setDisable(true);
        managerButton.setSelected(false);
        deptHeadButton.setSelected(false);
        directorButton.setSelected(false);
    }

    /**
     * This method takes care of establishing if an employee is a full time employee in the GUI.
     * @param event the interaction with the button.
     */
    @FXML
    void isManagement(ActionEvent event) {
        rateInput.setDisable(true);
        rateInput.clear();
        hoursInput.setDisable(true);
        hoursInput.clear();
        addButton.setDisable(false);
        removeButton.setDisable(false);
        setHoursButton.setDisable(true);
        salaryInput.setDisable(false);
        managerButton.setSelected(true);
        managerButton.setDisable(false);
        deptHeadButton.setDisable(false);
        directorButton.setDisable(false);
    }

    /**
     * This method takes care of setting the hours based on user-GUI interaction.
     * @param event the interaction with the button.
     */
    @FXML
    void setHours(KeyEvent event) {

        /* catch (NumberFormatException e) {
            textArea.appendText("Non numeric data has been entered.\n"); */
    }

    /**
     * This method takes care of setting the hours based on user-GUI interaction with the button.
     * @param event the interaction with the button.
     */
    @FXML
    void setHoursButton(ActionEvent event) {

    }

    /**
     * This method takes care of finalizing the addition of anew employee into the GUI database.
     * @param event the interaction with the button.
     */
    @FXML
    void addEmployee(ActionEvent event) {
        try {
            Employee employee = null;
            Profile Profile = createProfile();
            if (Profile == null) {
                return;
            }
            RadioButton radioEmployee = (RadioButton) employeeGroup.getSelectedToggle();
            String employeeType = radioEmployee.getText();
            if (employeeType.equals("Part Time")) {
                employee = addParttime(Profile);
            }
            else if (employeeType.equals("Full Time")) {
                employee = addFulltime(Profile);
            }
            else if (employeeType.equals("Management")) {
                employee = addManagement(Profile);
            }
            isValid(employee);
        }
        catch (NullPointerException e) {
            textArea.appendText( "Missing arguments!\n");
        }
    }

    /**
     * This method takes care of creating a new profile for the added employee in the GUI.
     * @return returns a newly created employee profile.
     */
    @FXML
    private Profile createProfile() {
        try {
            String name = nameInput.getText();
            RadioButton radioDept = (RadioButton) departmentGroup.getSelectedToggle();
            String departmentCode = radioDept.getText();
            String day = String.valueOf(dateInput.getValue().getDayOfMonth());
            String month = String.valueOf(dateInput.getValue().getMonthValue());
            String year = String.valueOf(dateInput.getValue().getYear());
            String date = month + "/" + day + "/" + year;
            Date dateHired = new Date(date);
            if (!dateHired.isValid()) {
                textArea.appendText(dateHired.toString() + " is not a valid date \n");
                return null;
            }
            return new Profile(name, departmentCode, dateHired);
        }
        catch (NullPointerException e) {
            textArea.appendText("Missing arguments!\n");
        }
        return null;
    }

    /**
     * This method adds a parttime employee to the GUI database.
     * @param Profile the profile of the part time employee.
     * @return a new part time employee.
     */
    @FXML
    private Employee addParttime(Profile Profile) {
        try {
            double hourlyRate = Double.parseDouble(rateInput.getText());
            if (hourlyRate < 0) {
                textArea.appendText("Pay rate cannot be negative!\n");
                return null;
            }
            return new Parttime(Profile, hourlyRate);
        }
        catch (NumberFormatException e) {
            textArea.appendText("Non numeric data has been entered.\n");
        }
        catch (NullPointerException e) {
            textArea.appendText( "Input a hourly rate.\n");
        }
        return null;
    }

    /**
     * This method takes care of adding a full time employee into the GUI.
     * @param Profile the profile of the employee to be added.
     * @return a new full time employee.
     */
    @FXML
    private Employee addFulltime(Profile Profile) {
        try {
            double salary = Double.parseDouble(salaryInput.getText());
            if (salary < 0) {
                textArea.appendText("Salary cannot be negative!\n");
                return null;
            }
            return new Fulltime(Profile, salary);
        }
        catch (NumberFormatException e) {
            textArea.appendText("Non numeric data has been entered.\n");
        }
        catch (NullPointerException e) {
            textArea.appendText( "Input an annual salary.\n");
        }
        return null;
    }

    /**
     *  This method takes care of adding a full time employee into the GUI.
     * @param profile the profile of the employee to be added.
     * @return a new manager object.
     */
    @FXML
    private Employee addManagement(Profile profile) {
        try {
            int managementCode = getManagementCode();
            if (managementCode == NOT_FOUND) {
                return null;
            }
            double salary = Double.parseDouble(salaryInput.getText());

            if (salary < 0) {
                textArea.appendText("Salary cannot be negative!\n");
                return null;
            }
            return new Management(profile, salary, managementCode);
        }
        catch (NumberFormatException e) {
            textArea.appendText("Non numeric data has been entered.\n");
        }
        catch (NullPointerException e) {
            textArea.appendText( "Input an annual salary.\n");
        }
        return null;
    }

    /**
     * This method gets the code for a manager's role.
     * @return the manager code.
     */
    @FXML
    private int getManagementCode() {
        try {
            RadioButton radioRole = (RadioButton) roleGroup.getSelectedToggle();
            String managementRole = radioRole.getText();
            if (managementRole.equals("Manager")) {
                return MANAGER;
            } else if (managementRole.equals("Department Head")) {
                return DEPARTMENT_HEAD;
            }
            else if (managementRole.equals("Director")) {
                return DIRECTOR;
            }
        }
        catch (NullPointerException e) {
            textArea.appendText("Select a management role.\n");
        }
        return NOT_FOUND;
    }

    /**
     * This method checks if an employee is indeed a valid one in the GUI.
     * @param employee the employee to be checked.
     */
    @FXML
    void isValid(Employee employee) {
        if (employee == null) {
            textArea.appendText("Unable to add employee.\n");
        } else if (!Company.add(employee)) {
            textArea.appendText("Employee is already in the list.\n");
        } else {
            textArea.appendText("Employee added.\n");
        }
    }

    /**
     * This method removes an employee from the GUI database.
     * @param event the interaction with the button.
     */
    @FXML
    void removeEmployee(ActionEvent event) {
        Employee employee;
        Profile profile = createProfile();
        if (profile == null) {
            return;
        }
        employee = new Employee(profile);
        if (!Company.remove(employee)) {
            textArea.appendText("Employee does not exist.\n");
        } else {
            textArea.appendText("Employee removed.\n");
        }
    }

    /**
     * This method simply clears all data shown in the text area.
     * @param event the interaction with the button.
     */
    @FXML
    void clear(ActionEvent event) {
        textArea.clear();
        salaryInput.clear();
        nameInput.clear();
        hoursInput.clear();
        rateInput.clear();
        dateInput.setValue(null);
        validSalary= false;
        validName= false;
        validHours= false;
        validRate= false;

    }
}
