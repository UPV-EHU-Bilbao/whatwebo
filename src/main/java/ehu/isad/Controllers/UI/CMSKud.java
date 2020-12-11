package ehu.isad.Controllers.UI;

import ehu.isad.Controllers.DB.CMSDBKud;
import ehu.isad.Model.DatePickerCell;
import ehu.isad.Model.Eskaneoa;
import ehu.isad.WhatWeb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CMSKud implements Initializable {

    private ObservableList<Eskaneoa> eskaneoak;

    @FXML
    private GridPane pnCMS;

    @FXML
    private TableView<Eskaneoa> tCMS;

    @FXML
    private TableColumn<Eskaneoa, String> cURL;

    @FXML
    private TableColumn<Eskaneoa, String> cCMS;

    @FXML
    private TableColumn<Eskaneoa, String> cVersion;

    @FXML
    private TableColumn<Eskaneoa, LocalDate> cLastUpdate;

    @FXML
    private TextField textFilter;

    @FXML
    private ComboBox<String> comboZerbitzua;

    @FXML
    private Button btnUrl;

    private WhatWeb main;


    public CMSKud() {

    }

    public void setMain(WhatWeb pMain){
        this.main=pMain;

    }

    private SortedList<Eskaneoa> filtroa(){
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Eskaneoa> filteredData = new FilteredList<>(eskaneoak, p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        //testua
        textFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(eskan -> {
                String comboValue = comboZerbitzua.getValue().toLowerCase();
                // Hutsik badago, denak erakutsi
                if ((newValue == null || newValue.isEmpty()) && comboValue=="") {
                    return true;
                }

                // Konparatu url bakoitza textFilter-ekin
                String lowerCaseFilter = newValue.toLowerCase();

                if (eskan.getUrl().toLowerCase().contains(lowerCaseFilter) &&
                eskan.getCms().toLowerCase().contains(comboValue)) {
                    return true; // Filtroa bat egin url-arekin
                }
                return false; // Ez du bat egiten
            });
        });
        //combo
        comboZerbitzua.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
            filteredData.setPredicate(eskaneo -> {
                String textValue = textFilter.getText().toLowerCase();
                // If filter text is empty, display all persons.
                if ((newValue == null || newValue.isEmpty()) && textValue=="") {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (eskaneo.getCms().toLowerCase().contains(lowerCaseFilter)&&
                eskaneo.getUrl().toLowerCase().contains(textValue)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Eskaneoa> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tCMS.comparatorProperty());

        return sortedData;
    }


    @FXML
    void onaAdd(ActionEvent event) {
        main.getWhatWebKud().webAurrera();
    }


    public void hasieratuTaula() {

        comboZerbitzua.getSelectionModel().selectFirst();
        textFilter.clear();
        List<Eskaneoa> eskanList = CMSDBKud.getInstance().eskaneoInfoLortu();
        eskaneoak = FXCollections.observableArrayList(eskanList);

        tCMS.setEditable(true);
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        cURL.setCellValueFactory(new PropertyValueFactory<>("url"));
        cCMS.setCellValueFactory(new PropertyValueFactory<>("cms"));
        cVersion.setCellValueFactory(new PropertyValueFactory<>("version"));
        cLastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));

        //DATEPICKER
        cLastUpdate.setCellFactory(new Callback<TableColumn<Eskaneoa, LocalDate>, TableCell<Eskaneoa, LocalDate>>() {
            @Override
            public TableCell<Eskaneoa, LocalDate> call(TableColumn<Eskaneoa, LocalDate> param) {
                DatePickerCell datePick = new DatePickerCell(eskaneoak);
                return datePick;
            }
        });
        //FILTROA
        SortedList<Eskaneoa> sortedData = filtroa();
        tCMS.setItems(sortedData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboZerbitzua.getItems().add("");
        comboZerbitzua.getItems().add("WordPress");
        comboZerbitzua.getItems().add("Joomla!");
        comboZerbitzua.getItems().add("phpMyAdmin");
        comboZerbitzua.getItems().add("Drupal");
        comboZerbitzua.getSelectionModel().selectFirst();
        comboZerbitzua.setEditable(true);

        hasieratuTaula();

    }
}