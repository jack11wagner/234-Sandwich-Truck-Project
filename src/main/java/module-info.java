module com.example.sandwichtruckproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sandwichtruckproject to javafx.fxml;
    exports com.example.sandwichtruckproject;
}