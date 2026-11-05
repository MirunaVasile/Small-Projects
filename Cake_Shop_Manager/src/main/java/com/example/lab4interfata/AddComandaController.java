package com.example.lab4interfata;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import domain.Comanda;
import domain.Tort;
import repository.RepositoryException;
import repository.SQLComandaRepository;
import repository.SQLTortRepository;
import service.ServiceComanda;
import service.ServiceTort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddComandaController {

    @FXML
    private TextField idComandaTextField;
    @FXML
    private DatePicker dataDatePicker;
    @FXML
    private ListView<Tort> tortListView;  // ListView for torturi selection

    private ServiceComanda serviceComanda;
    private ServiceTort serviceTort;

    public AddComandaController() throws RepositoryException, ParseException {
        serviceTort = new ServiceTort(new SQLTortRepository());
        serviceComanda = new ServiceComanda(new SQLComandaRepository());
    }

    // Initialize the ListView with torturi data
    @FXML
    public void initialize() {
        // Load torturi data into the ListView (this assumes that ServiceTort provides all torturi)
        tortListView.getItems().setAll(serviceTort.getAlltortuti());
        tortListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);  // Enable multiple selection
    }

    @FXML
    public void onActionAdaugaComanda() throws ParseException, RepositoryException {

        try {
            int comandaId = Integer.parseInt(idComandaTextField.getText());

            // Get the selected date directly from the DatePicker (LocalDate)
            java.time.LocalDate localDate = dataDatePicker.getValue();

            // Convert LocalDate to Date
            if (localDate != null) {
                Date data = java.sql.Date.valueOf(localDate);  // Convert LocalDate to Date

                // Get the selected torturi
                List<Tort> selectedTorturi = tortListView.getSelectionModel().getSelectedItems();

                // Save the comanda (you can use the ServiceComanda to save it to the database)
                serviceComanda.addComanda(comandaId, data, selectedTorturi);

                // Show confirmation message
                showAlert("Succes", "Comanda a fost adăugată cu succes!");
            } else {
                showAlert("Eroare", "Te rugăm să selectezi o dată validă.");
            }

        } catch (Exception e) {
            showAlert("Eroare", e.getMessage());
        }
    }

    // Utility method to show alert dialogs
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void onActionAnuleaza() {
        // Close the window if the user cancels
        Stage stage = (Stage) idComandaTextField.getScene().getWindow();
        stage.close();
    }
}
