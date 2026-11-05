package com.example.lab4interfata;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import repository.RepositoryException;
import repository.SQLComandaRepository;
import repository.SQLTortRepository;
import service.ServiceComanda;
import service.ServiceTort;

import java.text.ParseException;

public class DeleteComandaController {
    @FXML
    private TextField IdComandaTextField;

    @FXML
    private Label Mesaj;

    private ServiceComanda comandaService;

    public DeleteComandaController() throws RepositoryException, ParseException {
        comandaService = new ServiceComanda(new SQLComandaRepository());
    }

    @FXML
    public void onActionStergeComanda() {
        try {
            int id = Integer.parseInt(IdComandaTextField.getText());
            comandaService.deleteComanda(id);
            Mesaj.setText("Comanda a fost stearsa!");
        } catch (Exception e) {
            Mesaj.setText("Eroare: " + e.getMessage());
        }
    }
}
