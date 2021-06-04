package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Departments {
    private static final ObservableList<String> Departments = FXCollections.observableArrayList("Testing", "Development", "Management", "Marketing", "Customer Support", "Human Resources");

    public static ObservableList<String> getDepartments() {
        return Departments;
    }
}
