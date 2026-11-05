package com.example.lab4interfata;

import domain.Tort;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.RepositoryException;
import repository.SQLTortRepository;
import service.ServiceTort;

public class UpdateTortController {

    @FXML
    private TextField IdTortTextField;

    @FXML
    private TextField TipTortTextField;

    @FXML
    private Label Mesaj;

    private ServiceTort tortService;

    public UpdateTortController() throws RepositoryException {
        // Initialize the service class
        tortService = new ServiceTort(new SQLTortRepository());
    }

    // Method to handle updating a tort
    @FXML
    public void onActionUpdateTort() {
        String idText = IdTortTextField.getText();
        String newTip = TipTortTextField.getText();

        if (idText.isEmpty() || newTip.isEmpty()) {
            Mesaj.setText("Fields cannot be empty!");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            tortService.updateTort(id, newTip);
            Mesaj.setText("Tortul a fost modificat!");


        } catch (NumberFormatException e) {
            Mesaj.setText("ID must be a valid number!");
        } catch (Exception e) {
            Mesaj.setText("Error: " + e.getMessage());
        }

        IdTortTextField.clear(); // Clear fields after action
        TipTortTextField.clear();
    }

}
