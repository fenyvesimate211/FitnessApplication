package controller;

import application.Main;
import com.google.inject.Guice;
import com.google.inject.Injector;
import dao.FitnessDao;
import database.PersistenceModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.FitnessModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class FitnessController {
    Main change = new Main();
    @FXML
    private TableColumn<FitnessModel, Double> duration;
    @FXML
    private TableColumn<FitnessModel, Double> distance;
    @FXML
    private TableColumn<FitnessModel, Double> calorie;
    @FXML
    private TableColumn<FitnessModel, Integer> steps;
    @FXML
    private TableColumn<FitnessModel, String> date;
    @FXML
    private TableView<FitnessModel> table_view;

    @FXML
    private TextField durationTextField;
    @FXML
    private TextField distanceTextField;
    @FXML
    private TextField calorieTextField;
    @FXML
    private TextField stepsTextField;
    FitnessModel fitnessModel = new FitnessModel();
    Injector injector = Guice.createInjector(new PersistenceModule("mysql"));
    FitnessDao fitnessDao = injector.getInstance(FitnessDao.class);
    Logger logger = LogManager.getLogger();

    /**
     * When fxml is loaded, it will initialize tableview from database.
     */
    @FXML
    void initialize() {
        final ObservableList<FitnessModel> fitnessModels = FXCollections.observableArrayList(fitnessDao.findAll());
        table_view.setItems(fitnessModels);
        initColumn();
    }

    /**
     * Initialize columns for tableview.
     */
    private void initColumn() {
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        distance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        calorie.setCellValueFactory(new PropertyValueFactory<>("calorie"));
        steps.setCellValueFactory(new PropertyValueFactory<>("steps"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    /**
     * When Bmi button is pressed, it will load bmi.fxml.
     * @throws IOException if fxml file can"t be found
     */
    public void Bmi() throws IOException {
        change.changeScene("/fxml/bmi.fxml");
    }

    /**
     * When Add activity button is pressed, it will try to parse fields from TextFields.
     * If fields are given in range it will write to database.
     * If field is not in range it will write a message to the user.
     */
    public void Add_activity() {
        try {
            FitnessModel fitnessModel = new FitnessModel(
                    Double.parseDouble(durationTextField.getText()),
                    Double.parseDouble(distanceTextField.getText()),
                    Double.parseDouble(calorieTextField.getText()),
                    Integer.parseInt(stepsTextField.getText()),
                    LocalDate.now());
            if (Objects.equals(fitnessModel.calculate(), "Calculated Successfully")){
                Injector injector = Guice.createInjector(new PersistenceModule("mysql"));
                FitnessDao fitnessDao = injector.getInstance(FitnessDao.class);
                fitnessDao.persist(fitnessModel);
                logger.info(fitnessModel.calculate());
                deleteFields();
                initialize();
            } else {
                fitnessModel.calculate();
                deleteFields();
                logger.error(fitnessModel.calculate());
            }
        } catch (NumberFormatException numberFormatException) {
            logger.error(numberFormatException);
        }
    }

    /**
     * Delete fields texts
     */
    public void deleteFields() {
        durationTextField.setText("");
        distanceTextField.setText("");
        calorieTextField.setText("");
        stepsTextField.setText("");
    }

    /**
     * If there is a selected field in table view it will open a dialog window for deletion.
     * If yes is pressed, it will delete from the database.
     */
    @FXML
    public void Delete() {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Do you really want to delete?", "Delete", dialogButton);
        if(dialogResult == 0) {
            try {
                logger.info(table_view.getSelectionModel().getSelectedItem().getId() + " id deleted");
                fitnessModel.setId(table_view.getSelectionModel().getSelectedItem().getId());
                fitnessDao.remove(fitnessDao.find(fitnessModel.getId()).get());
                initialize();
            } catch (NullPointerException nullPointerException) {
                logger.error(nullPointerException);
            }
        } else {
            logger.info("Not delete is pressed");
        }
    }
}
