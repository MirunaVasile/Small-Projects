package com.example.lab4interfata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import repository.RepositoryException;
import repository.SQLComandaRepository;
import service.ServiceComanda;
import service.ServiceTort;

import java.io.IOException;
import java.text.ParseException;

public class HelloController {


    @FXML
    private DialogPane Mesaj;


    // Open the "Add Tort" window when menu item is clicked
    @FXML
    public void onActionAdaugaTort(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-tort.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Adauga Tort");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Mesaj.setContentText("Eroare la deschiderea ferestrei!");
        }
    }

    // Open the "Delete Tort" window when menu item is clicked
    @FXML
    public void onActionStergeTort(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("delete-tort.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Sterge Tort");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Mesaj.setContentText("Eroare la deschiderea ferestrei!");
        }
    }

    // Open the "View Torturi" window when menu item is clicked
    @FXML
    public void onActionAfiseazaTorturi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view-torturi.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Vizualizare Torturi");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Mesaj.setContentText("Eroare la deschiderea ferestrei!");
        }
    }

    @FXML
    public void onActionUpdateTort(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("update-tort.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Modifica Tort");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Mesaj.setContentText("Eroare la deschiderea ferestrei!");
        }
    }

    // Open the "Add Comanda" window when menu item is clicked
    @FXML
    public void onActionAdaugaComanda(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-comanda.fxml"));
            Parent root = loader.load(); // Load the FXML file

            Stage stage = new Stage(); // Create a new window for the "Add Comanda" screen
            stage.setTitle("Adaugă Comanda");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Mesaj.setContentText("Eroare la deschiderea ferestrei de adaugare comanda: " + e.getMessage());
        }
    }

    @FXML
    public void onActionAfiseazaComenzi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view-comenzi.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Vizualizare Comenzi");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Mesaj.setContentText("Eroare la deschiderea ferestrei!");
        }
    }

    @FXML
    public void onActionStergeComanda(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("delete-comanda.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Sterge Comanda");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Mesaj.setContentText("Eroare la deschiderea ferestrei!");
        }
    }

    @FXML
    public void onActionModificaComanda(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("update-comanda.fxml"));
            Parent root = loader.load(); // Load the FXML file

            Stage stage = new Stage(); // Create a new window for the "Add Comanda" screen
            stage.setTitle("Modifica Comanda");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Mesaj.setContentText("Eroare la deschiderea ferestrei de modificare comanda: " + e.getMessage());
        }
    }

    @FXML
    public void onActionRaport1(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("raport1.fxml"));
            Parent root = loader.load(); // Load the FXML file

            Stage stage = new Stage(); // Create a new window for the "Add Comanda" screen
            stage.setTitle("Raport 1");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Mesaj.setContentText("Eroare la deschiderea ferestrei: " + e.getMessage());
        }
    }

    @FXML
    private void onActionRaport2() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("raport2.fxml"));
            Parent root = loader.load(); // Load the FXML file

            Stage stage = new Stage(); // Create a new window for the "Add Comanda" screen
            stage.setTitle("Raport 2");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Mesaj.setContentText("Eroare la deschiderea ferestrei: " + e.getMessage());
        }
    }

    @FXML
    private void onActionRaport3() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("raport3.fxml"));
            Parent root = loader.load(); // Load the FXML file

            Stage stage = new Stage(); // Create a new window for the "Add Comanda" screen
            stage.setTitle("Raport 3");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Mesaj.setContentText("Eroare la deschiderea ferestrei: " + e.getMessage());
        }
    }



}
