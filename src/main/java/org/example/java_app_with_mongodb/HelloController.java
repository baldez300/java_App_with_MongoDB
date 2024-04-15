import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

public class HelloApplication {
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

    private void addStudent() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
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
            JOptionPane.showMessageDialog(null, "Student added successfully!");
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        IDField.setText("");
        nameField.setText("");
        ageField.setText("");
        cityField.setText("");
    }

    public void onAddButtonClick(ActionEvent actionEvent) {
        welcomeText.setText("Welcome to JavaFX Application!");
        addStudent();
    }

    public void onReadButtonClick(ActionEvent actionEvent) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("classroom");
            MongoCollection<Document> collection = database.getCollection("students");

            double id = Double.parseDouble(IDField.getText());
            Document query = new Document("id", id);
            Document student = collection.find(query).first();

            if (student != null) {
                String message = String.format("Found Student: %s with ID: %.0f and Age: %d from %s",
                        student.getString("name"), student.getDouble("id"),
                        student.getInteger("age"), student.getString("city"));
                JOptionPane.showMessageDialog(null, message);
            } else {
                JOptionPane.showMessageDialog(null, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onUpdateButtonClick(ActionEvent actionEvent) {
        // Implement update operation
        JOptionPane.showMessageDialog(null, "Student updated successfully!");
    }

    public void onDeleteButtonClick(ActionEvent actionEvent) {
        // Implement delete operation
        JOptionPane.showMessageDialog(null, "Student deleted successfully!");
    }
}
