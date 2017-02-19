
package Elements.example;

import java.io.IOException;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Sample custom control hosting a text field and a button.
 */
public class CustomControl extends VBox {
//    @FXML private TextField textField;

    public CustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("custom_control.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
//
//    public String getText() {
//        return textProperty().get();
//    }
//
//    public void setText(String value) {
//        textProperty().set(value);
//    }
//
//    private StringProperty textProperty() {
//        return textField.textProperty();
//    }
//
    @FXML
    protected void doSomething() {
        System.out.println("The button was clicked!");
    }
}
