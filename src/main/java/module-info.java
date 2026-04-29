module com.pluralsight {
    requires javafx.controls;
    requires javafx.fxml;

    // This allows JavaFX to access your classes
    opens com.pluralsight.model to javafx.graphics, javafx.fxml;

    exports com.pluralsight.model;
}
