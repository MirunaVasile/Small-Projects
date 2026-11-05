package com.example.lab4interfata;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import repository.RepositoryException;
import repository.SQLTortRepository;
import service.ServiceTort;

public class DeleteTortController {
    @FXML
    private TextField IdTortTextField;

    @FXML
    private Label Mesaj;

    private ServiceTort tortService;

    public DeleteTortController() throws RepositoryException {
        tortService = new ServiceTort(new SQLTortRepository());
    }

    @FXML
    public void onActionStergeTort() {
        try {
            int id = Integer.parseInt(IdTortTextField.getText());
            tortService.deleteTort(id);
            Mesaj.setText("Tortul a fost sters!");
        } catch (Exception e) {
            Mesaj.setText("Eroare: " + e.getMessage());
        }
    }
}
