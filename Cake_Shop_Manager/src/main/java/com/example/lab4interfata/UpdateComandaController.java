package com.example.lab4interfata;

import domain.Tort;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import repository.RepositoryException;
import repository.SQLComandaRepository;
import repository.SQLTortRepository;
import service.ServiceComanda;
import service.ServiceTort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UpdateComandaController {

    @FXML
    private TextField IdComandaTextField;
    @FXML
    private DatePicker dataDatePicker;
    @FXML
    private ListView<Tort> tortListView;  // ListView for torturi selection

    private ServiceComanda serviceComanda;
    private ServiceTort serviceTort;

    @FXML
    private Label Mesaj;


    public UpdateComandaController() throws RepositoryException, ParseException {
        // Initialize the service class
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

    // Method to handle updating a tort
    @FXML
    public void onActionUpdateComanda() throws ParseException, RepositoryException {
        try {

            int comandaId = Integer.parseInt(IdComandaTextField.getText());
            String dataString = dataDatePicker.getValue().toString();
            Date data = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            data = dateFormat.parse(dataString);

            // Get the selected torturi
            List<Tort> selectedTorturi = tortListView.getSelectionModel().getSelectedItems();

            if (comandaId <= 0 || selectedTorturi.isEmpty()) {
                // Display error message if input is invalid
                Mesaj.setText("Eroare, Toate campurile trebuie completate.");
                return;
            }


            // Save the comanda (you can use the ServiceComanda to save it to the database)
            serviceComanda.updateComanda(comandaId, data, selectedTorturi);

            // Show confirmation message
            Mesaj.setText("Succes, Comanda a fost modificata cu succes!");
        } catch (NumberFormatException e) {
            Mesaj.setText("ID must be a valid number!");
        } catch (Exception e) {
            Mesaj.setText("Error: " + e.getMessage());
        }

        IdComandaTextField.clear(); // Clear fields after action

    }

}
