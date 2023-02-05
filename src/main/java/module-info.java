module com.example.passwordcheck {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.junit.jupiter.api;
    requires java.desktop;


    opens com.example.passwordcheck to javafx.fxml;
    exports com.example.passwordcheck;
}