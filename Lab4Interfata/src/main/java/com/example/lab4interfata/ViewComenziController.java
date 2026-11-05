package com.example.lab4interfata;

import domain.Comanda;
import domain.Tort;
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
import repository.SQLComandaRepository;
import service.ServiceComanda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ViewComenziController {

    @FXML
    private TableView<Comanda> ComandaTableView;  // TableView to display the orders

    @FXML
    private TableColumn<Comanda, Integer> idColumn;  // Column for Comanda ID

    @FXML
    private TableColumn<Comanda, String> dataColumn;  // Column for the order date

    @FXML
    private TableColumn<Comanda, String> tortColumn;  // Column for the torturi list

    private ServiceComanda comandaService;

    public ViewComenziController() throws RepositoryException, ParseException {
        // Initialize the service class
        comandaService = new ServiceComanda(new SQLComandaRepository());
    }

    // This method is called to populate the TableView with all orders
    @FXML
    public void initialize() {
        // Fetch all the orders from the service layer
        List<Comanda> comenzi = (List<Comanda>) comandaService.getAllcomenzi();

        // Create an ObservableList from the list of orders
        ObservableList<Comanda> comenziObservableList = FXCollections.observableArrayList(comenzi);

        // Set the items of the TableView
        ComandaTableView.setItems(comenziObservableList);

        // Set the cell value factories for the columns
        idColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getId()).asObject()
        );

        dataColumn.setCellValueFactory(cellData -> {
            // Format the date to a readable string format
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            return new SimpleStringProperty(sdf.format(cellData.getValue().getData()));
        });

        tortColumn.setCellValueFactory(cellData -> {
            // Convert the list of torturi to a string
            List<Tort> torturi = cellData.getValue().getTort();
            StringBuilder torturiString = new StringBuilder();
            for (Tort tort : torturi) {
                torturiString.append(tort.getType()).append(", ");  // Concatenate all tort types
            }
            // Remove the last comma and space
            if (torturiString.length() > 0) {
                torturiString.setLength(torturiString.length() - 2);
            }
            return new SimpleStringProperty(torturiString.toString());
        });
    }

    // This method is called when the "Inchide" button is pressed to close the window
    @FXML
    public void onActionClose() {
        Stage stage = (Stage) ComandaTableView.getScene().getWindow();
        stage.close();  // Close the current stage (window)
    }
}
