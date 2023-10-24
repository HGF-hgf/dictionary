package com.example.dictionaryy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController implements Initializable{

    @FXML
    private TextField filterField;
    @FXML
    private TableView<Words> tableView;
    @FXML
    private TableColumn<Words, String> word;
    @FXML
    private TableColumn<Words, String> meaning;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("searchpage.fxml")));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gamepage.fxml")));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private final ObservableList<Words> dataList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        word.setCellValueFactory(new PropertyValueFactory<>("word"));
        meaning.setCellValueFactory(new PropertyValueFactory<>("meaning"));

        Words word1 = new Words("Hello", "Chao");
        Words word2 = new Words("Goodbye", "Tam biet");
        Words word3 = new Words("Good morning", "Chao buoi sang");
        Words word4 = new Words("Good afternoon", "Chao buoi chieu");
        Words word5 = new Words("Good evening", "Chao buoi toi");
        Words word6 = new Words("Good night", "Chuc ngu ngon");
        Words word7 = new Words("How are you?", "Ban khoe khong?");
        Words word8 = new Words("I'm fine, thank you", "Toi khoe, cam on ban");
        Words word9 = new Words("What is your name?", "Ban ten gi?");
        Words word10 = new Words("My name is...", "Toi ten la...");

        dataList.addAll(word1, word2, word3, word4, word5, word6, word7, word8, word9, word10);

        FilteredList<Words> filteredData = new FilteredList<>(dataList, b -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(words -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (words.getWord().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return words.getMeaning().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Words> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }
}
