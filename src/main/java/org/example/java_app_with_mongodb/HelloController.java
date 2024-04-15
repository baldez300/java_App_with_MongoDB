package org.example.java_app_with_mongodb;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField IDField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField cityField;

    public static class EnvLoader {
        public static Properties load() {
            Properties props = new Properties();
            try (FileInputStream fis = new FileInputStream(".env")) {
                props.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return props;
        }
    }

    Properties env = EnvLoader.load();
    String mongodbURI = env.getProperty("MONGODB_URI");

    @FXML
    public void onAddButtonClick(ActionEvent actionEvent) {
        try (MongoClient mongoClient = MongoClients.create(mongodbURI)) {
            MongoDatabase database = mongoClient.getDatabase("classroom");
            MongoCollection<Document> collection = database.getCollection("students");

            double id = Double.parseDouble(IDField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String city = cityField.getText();

            Document document = new Document()
                    .append("id", id)
                    .append("name", name)
                    .append("age", age)
                    .append("city", city);

            collection.insertOne(document);
            showAlert("Student added successfully!", Alert.AlertType.INFORMATION);
            clearFields();
        } catch (Exception ex) {
            showAlert("Error occurred: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String s, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    private void clearFields() {
        IDField.setText("");
        nameField.setText("");
        ageField.setText("");
        cityField.setText("");
    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void onReadButtonClick(ActionEvent actionEvent) {
        try (MongoClient mongoClient = MongoClients.create(mongodbURI)) {
            MongoDatabase database = mongoClient.getDatabase("classroom");
            MongoCollection<Document> collection = database.getCollection("students");

            // double id = Double.parseDouble(IDField.getText());
            //Document query = new Document("id", id);
            String name = nameField.getText();
            Document query = new Document("name", name);
            Document student = collection.find(query).first();

            if (student != null) {
                String message = String.format("Found Student: %s with ID: %.0f and Age: %d from %s",
                        student.getString("name"), student.getDouble("id"),
                        student.getInteger("age"), student.getString("city"));

                // Display the student data in a JavaFX dialog
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Student Information");
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
            } else {
                // If student not found, display an error dialog
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Student not found!");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            // If an error occurs, display an error dialog
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error occurred: " + ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void onUpdateButtonClick() {
        try (MongoClient mongoClient = MongoClients.create(mongodbURI)) {

            MongoDatabase database = mongoClient.getDatabase("classroom");
            MongoCollection<Document> collection = database.getCollection("students");

            double id = Double.parseDouble(IDField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String city = cityField.getText();

            Document query = new Document("id", id);
            Document update = new Document("$set", new Document("name", name).append("age", age).append("city", city));

            if (collection.updateOne(query, update).getModifiedCount() == 0) {
                throw new Exception("Student not found!");
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Successful");
            alert.setHeaderText(null);
            alert.setContentText("Student updated successfully!");
            alert.showAndWait();
        } catch (Exception ex) {
            // If an error occurs, display an error dialog
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error occurred: " + ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void onDeleteButtonClick() {
        try (MongoClient mongoClient = MongoClients.create(mongodbURI)) {

            MongoDatabase database = mongoClient.getDatabase("classroom");
            MongoCollection<Document> collection = database.getCollection("students");

            double id = Double.parseDouble(IDField.getText());
            Document query = new Document("id", id);

            if (collection.deleteOne(query).getDeletedCount() == 0) {
                throw new Exception("Student not found!");
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Successful");
            alert.setHeaderText(null);
            alert.setContentText("Student deleted successfully!");
            alert.showAndWait();
        } catch (Exception ex) {
            // If an error occurs, display an error dialog
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error occurred: " + ex.getMessage());
            alert.showAndWait();
        }
    }

}
