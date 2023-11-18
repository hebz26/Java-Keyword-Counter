//Heba Sayed
//Project 3 CIS 296
//HashProj.java

package HashProj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HashProj extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("HashFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Project 3 - Hashing");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
        
    }
   
}
