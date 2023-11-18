//Heba Sayed
//Project 3 CIS 296
//HASHController.java

package HashProj;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class HASHController implements Initializable {   
      
   @FXML
    private Button fileButton;

    @FXML
    private ListView listViewer;
    
    private HashMap<String, Integer> keywordCountMap = new HashMap<>();
    
    @FXML
void getFile(ActionEvent event) {
    //Clear previous content
    listViewer.getItems().clear();
    keywordCountMap.clear();

    FileChooser fc = new FileChooser();

    //extension filter for Java files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Java Files (*.java)", "*.java");
    fc.getExtensionFilters().add(extFilter);
    
    
    //choose from files
    File selectedFile = fc.showOpenDialog(null);

    if (selectedFile != null) {
        try {
            //Use Scanner to read the contents of the file
            Scanner scanner = new Scanner(selectedFile);
            StringBuilder content = new StringBuilder();

            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }

            // Now 'content' contains the entire content of the file
            System.out.println("File Content:\n" + content.toString());

            //Count occurrences of keywords and update the HashMap
            countKeywordOccurrences(content.toString());

            // Display keyword occurrences (above zero) in the ListView
            displayKeywordOccurrences();

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("File is not valid");
    }
}

// Helper method to count occurrences of keywords in the content
private void countKeywordOccurrences(String content) {
    String[] keywords = {"abstract", "assert", "boolean",
    "break", "byte", "case", "catch", "char", "class",
    "const", "continue", "default", "do", "double", "else",
    "enum", "extends", "final", "finally", "float", "for", 
    "goto", "if", "implements", "import", "instanceof", "int",
    "interface", "long", "native", "new", "package", "private",
    "protected", "public", "return", "short", "static", "strictfp",
    "super", "switch", "synchronized", "this", "throw", "throws",
    "transient", "try", "void", "volatile", "while", /* Add all keywords */};

    for (String keyword : keywords) {
        int count = countOccurrences(content, keyword);
        
        // Only update the map with entries having non-zero counts
        if (count > 0) {
            keywordCountMap.put(keyword, count);
        }
    }
}

// Helper method to count occurrences of a specific keyword in the content
private int countOccurrences(String content, String keyword) {
    // Use regex to find non-word characters before and after the keyword
    String pattern = "\\b" + keyword + "\\b";
    java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
    java.util.regex.Matcher m = p.matcher(content);

    int count = 0;
    while (m.find()) {
        count++;
    }

    return count;
}
// Helper method to display keyword occurrences in the ListView
private void displayKeywordOccurrences() {
    ObservableList<String> items = FXCollections.observableArrayList();
    
    for (String keyword : keywordCountMap.keySet()) {
        int count = keywordCountMap.get(keyword);
        items.add(keyword + ": " + count);
    }
    
    listViewer.setItems(items);
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  
}
}

