package com.example.lab4interfata;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import repository.RepositoryException;
import repository.SQLTortRepository;
import service.ServiceTort;
import domain.Tort;

import java.util.List;

public class ViewTorturiController {

    @FXML
    private TableView<Tort> TortTableView;  // TableView to display the cakes

    @FXML
    private TableColumn<Tort, Integer> idColumn;  // Column for the tort id

    @FXML
    private TableColumn<Tort, String> typeColumn;  // Column for the tort type

    private ServiceTort tortService;

    public ViewTorturiController() throws RepositoryException {
        // Initialize the service class
        tortService = new ServiceTort(new SQLTortRepository());
    }

    // This method is called to populate the TableView with all cakes
    @FXML
    public void initialize() {
        // Fetch all the cakes from the service layer
        List<Tort> torturi = (List<Tort>) tortService.getAlltortuti();

        // Create an ObservableList from the list of torturi
        ObservableList<Tort> torturiObservableList = FXCollections.observableArrayList(torturi);

        // Set the items of the TableView
        TortTableView.setItems(torturiObservableList);

        // Set the cell value factories for the columns
        idColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getId()).asObject()
        );
        typeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getType())
        );
    }

    // This method is called when the "Inchide" button is pressed to close the window
    @FXML
    public void onActionClose() {
        Stage stage = (Stage) TortTableView.getScene().getWindow();
        stage.close();  // Close the current stage (window)
    }
}
