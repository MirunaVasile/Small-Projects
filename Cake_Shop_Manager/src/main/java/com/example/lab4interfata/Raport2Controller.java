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
import repository.SQLComandaRepository;
import service.ServiceComanda;

import java.text.ParseException;
import java.time.Month;
import java.util.*;

public class Raport2Controller {

    @FXML
    private TableView<MonthSales> MonthlySalesTableView;  // TableView to display the monthly sales

    @FXML
    private TableColumn<MonthSales, String> monthColumn;  // Column for the month

    @FXML
    private TableColumn<MonthSales, Integer> salesColumn;  // Column for the number of cakes sold

    private ServiceComanda comandaService;

    public Raport2Controller() throws RepositoryException, ParseException {
        // Initialize the service class
        comandaService = new ServiceComanda(new SQLComandaRepository());
    }

    // This method is called to populate the TableView with the sales per month
    @FXML
    public void initialize() {
        // Fetch the torturi sold per month
        Map<Month, Integer> torturiSoldPerMonth = comandaService.getTorturiSoldPerMonth();

        // Convert the map to a list of MonthSales objects
        List<MonthSales> monthSalesList = new ArrayList<>();
        for (Map.Entry<Month, Integer> entry : torturiSoldPerMonth.entrySet()) {
            monthSalesList.add(new MonthSales(entry.getKey().toString(), entry.getValue()));
        }

        // Sort the list in descending order based on the number of cakes sold
        monthSalesList.sort((a, b) -> Integer.compare(b.getSales(), a.getSales()));

        // Convert the list to an ObservableList for the TableView
        ObservableList<MonthSales> monthSalesObservableList = FXCollections.observableArrayList(monthSalesList);

        // Set the items of the TableView
        MonthlySalesTableView.setItems(monthSalesObservableList);

        // Set the cell value factories for the columns
        monthColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMonth()));
        salesColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSales()).asObject());
    }

    // This method is called when the "Inchide" button is pressed to close the window
    @FXML
    public void onActionClose() {
        Stage stage = (Stage) MonthlySalesTableView.getScene().getWindow();
        stage.close();  // Close the current stage (window)
    }

    // Helper class to hold month and sales data
    public static class MonthSales {
        private final String month;
        private final int sales;

        public MonthSales(String month, int sales) {
            this.month = month;
            this.sales = sales;
        }

        public String getMonth() {
            return month;
        }

        public int getSales() {
            return sales;
        }
    }
}
