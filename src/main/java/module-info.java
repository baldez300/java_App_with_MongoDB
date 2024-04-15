module org.example.java_app_with_mongodb {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.java_app_with_mongodb to javafx.fxml;
    exports org.example.java_app_with_mongodb;
}