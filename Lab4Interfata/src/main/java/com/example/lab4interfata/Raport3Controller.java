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
import java.util.Map;

public class Raport3Controller {

    @FXML
    private TableView<Map.Entry<Integer, String>> TorturiTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, String>, Integer> idColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, String>, String> typeColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, String>, Integer> orderCountColumn;

    private final ServiceComanda serviceComanda;

    public Raport3Controller() throws RepositoryException, ParseException {
        serviceComanda = new ServiceComanda(new SQLComandaRepository());
    }

    @FXML
    public void initialize() {
        // Get the most ordered cakes data
        Map<Integer, String> torturiMap = serviceComanda.getMostOrderedCakes();

        // Convert the map entries to an ObservableList for TableView
        ObservableList<Map.Entry<Integer, String>> cakeOrderList = FXCollections.observableArrayList(torturiMap.entrySet());

        // Set items to the TableView
        TorturiTableView.setItems(cakeOrderList);

        // Set cell value factories
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getKey()).asObject());
        typeColumn.setCellValueFactory(cellData -> {
            String value = cellData.getValue().getValue();
            String type = value.split(", ")[0]; // Extract the cake type
            return new SimpleStringProperty(type);
        });
        orderCountColumn.setCellValueFactory(cellData -> {
            String value = cellData.getValue().getValue();
            int orderCount = Integer.parseInt(value.split(": ")[1]); // Extract the order count
            return new SimpleIntegerProperty(orderCount).asObject();
        });
    }


    @FXML
    public void onActionClose() {
        Stage stage = (Stage) TorturiTableView.getScene().getWindow();
        stage.close();  // Close the current stage (window)
    }
}
