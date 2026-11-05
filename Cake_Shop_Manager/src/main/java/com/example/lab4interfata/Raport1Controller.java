package com.example.lab4interfata;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import repository.RepositoryException;
import repository.SQLComandaRepository;
import service.ServiceComanda;

import java.text.ParseException;
import java.util.*;

public class Raport1Controller {

    @FXML
    private TableView<DailySales> DailySalesTableView;  // TableView to display daily sales

    @FXML
    private TableColumn<DailySales, String> dateColumn;  // Column for the date

    @FXML
    private TableColumn<DailySales, Integer> salesColumn;  // Column for the number of cakes sold

    private ServiceComanda comandaService;

    public Raport1Controller() throws RepositoryException, ParseException {
        // Initialize the service class
        comandaService = new ServiceComanda(new SQLComandaRepository());
    }

    // This method is called to populate the TableView with the sales per day
    @FXML
    public void initialize() {
        // Fetch the torturi sold per day
        Map<Date, Integer> torturiOrderedPerDay = comandaService.getTorturiOrderedPerDay();

        // Convert the map to a list of DailySales objects
        List<DailySales> dailySalesList = new ArrayList<>();
        for (Map.Entry<Date, Integer> entry : torturiOrderedPerDay.entrySet()) {
            dailySalesList.add(new DailySales(entry.getKey().toString(), entry.getValue()));
        }

        // Sort the list in descending order based on the number of cakes sold
        dailySalesList.sort((a, b) -> Integer.compare(b.getSales(), a.getSales()));

        // Convert the list to an ObservableList for the TableView
        ObservableList<DailySales> dailySalesObservableList = FXCollections.observableArrayList(dailySalesList);

        // Set the items of the TableView
        DailySalesTableView.setItems(dailySalesObservableList);

        // Set the cell value factories for the columns
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
        salesColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSales()).asObject());
    }

    // This method is called when the "Inchide" button is pressed to close the window
    @FXML
    public void onActionClose() {
        Stage stage = (Stage) DailySalesTableView.getScene().getWindow();
        stage.close();  // Close the current stage (window)
    }

    // Helper class to hold date and sales data
    public static class DailySales {
        private final String date;
        private final int sales;

        public DailySales(String date, int sales) {
            this.date = date;
            this.sales = sales;
        }

        public String getDate() {
            return date;
        }

        public int getSales() {
            return sales;
        }
    }
}
