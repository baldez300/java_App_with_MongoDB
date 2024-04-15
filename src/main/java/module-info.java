module org.example.java_app_with_mongodb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;


    opens org.example.java_app_with_mongodb to javafx.fxml;
    exports org.example.java_app_with_mongodb;
}