module com.example.lab4interfata {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;


    opens com.example.lab4interfata to javafx.fxml;
    exports com.example.lab4interfata;
}