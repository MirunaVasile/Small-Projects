package com.example.lab4interfata;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import repository.RepositoryException;
import repository.SQLTortRepository;
import service.ServiceTort;

public class AddTortController {

    @FXML
    private TextField IdTortTextField;

    @FXML
    private TextField TipTortTextField;

    @FXML
    private Label Mesaj;

    private ServiceTort tortService;

    public AddTortController() throws RepositoryException {
        tortService = new ServiceTort(new SQLTortRepository());
    }

    @FXML
    public void onActionAdaugaTort() {
        try {
            int id = Integer.parseInt(IdTortTextField.getText());
            String tip = TipTortTextField.getText();
            if (id<=0 || tip.isEmpty()) {
                // Display error message if input is invalid
                Mesaj.setText("Eroare, Toate campurile trebuie completate cu date valide.");
                return;
            }
            tortService.addTort(id, tip);
            Mesaj.setText("Tortul a fost adaugat!");
        } catch (Exception e) {
            Mesaj.setText("Eroare: " + e.getMessage());
        }
    }
}
