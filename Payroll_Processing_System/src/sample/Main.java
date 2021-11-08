/**
 *  The Main class is responsible for kick-starting the GUI and all it's internal processes.
 * @author Anuraj Dubey, Chenghao Lin
 */


package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    
    /**
     * This method creates the actual GUI and displays it on-screen.
     * @param primaryStage The Stage object required to create the GUI.
     */
    @Override
    public void start(Stage primaryStage){
        try {
            BorderPane root = FXMLLoader.load(getClass().getResource("view.fxml"));
            Scene scene = new Scene(root, 600, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Payroll Processing");
            primaryStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * The main method launches the program.
     * @param args the given arguments to start the program.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
