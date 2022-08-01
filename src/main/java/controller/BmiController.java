package controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.BmiModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class BmiController {
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private Label bmi;
    Main change = new Main();
    private static final Logger logger = LogManager.getLogger();

    /**
     * When calculate button is pressed read TextFields and parse it for bmiModel.
     * Throw exception if it can't parse it, because of invalid number format.
     */
    @FXML
    public void Calculate() {
        try {
            BmiModel bmiModel = new BmiModel(
                    Double.parseDouble(weightTextField.getText()),
                    Double.parseDouble(heightTextField.getText()));
            bmi.setText(bmiModel.calculate());
        } catch (NumberFormatException numberFormatException) {
            bmi.setText("Invalid number format");
            logger.error(numberFormatException);
        }
    }

    /**
     * When Back button is pressed, it will load fitness.fxml.
     * @throws IOException if fxml file can"t be found
     */
    @FXML
    public void Back() throws IOException {
        change.changeScene("/fxml/fitness.fxml");
    }
}
