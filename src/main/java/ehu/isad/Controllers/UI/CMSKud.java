package ehu.isad.Controllers.UI;

import ehu.isad.Controllers.DB.CMSDBKud;
import ehu.isad.Model.Eskaneoa;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
    private TableColumn<Eskaneoa, DatePicker> cLastUpdate;

    @FXML
    private TextField textFilter;

    @FXML
    private ComboBox<String> comboZerbitzua;

    @FXML
    private Button btnUrl;

    public CMSKud() {
        System.out.println("cms inst");
    }

    private SortedList<Eskaneoa> filtroa(){
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Eskaneoa> filteredData = new FilteredList<>(eskaneoak, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        textFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(eskan -> {
                // Hutsik badago, denak erakutsi
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Konparatu url bakoitza textFilter-ekin
                String lowerCaseFilter = newValue.toLowerCase();

                if (eskan.getUrl().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filtroa bat egin url-arekin
                }
                return false; // Ez du bat egiten
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Eskaneoa> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tCMS.comparatorProperty());

        return sortedData;
    }

    @FXML
    void onCommit(ActionEvent event) {
        // Editagarria egin
        cLastUpdate.setOnEditCommit(
                t -> {
                    Eskaneoa eskaneoa=t.getTableView().getItems().get(t.getTablePosition().getRow());
                    eskaneoa.setLastUpdate(t.getNewValue());
                    CMSDBKud.getInstance().dataEguneratu(eskaneoa.getUrl(),eskaneoa.getLastUpdate());
                });

    }


    public void hasieratuTaula() {
//        Eskaneoa esk=new Eskaneoa("uwu.com","owo","4.4");//proba
//        esk.setLastUpdate(new DatePicker());//proba
        List<Eskaneoa> eskanList = CMSDBKud.getInstance().eskaneoInfoLortu();
        //eskanList.add(esk);//proba
        eskaneoak = FXCollections.observableArrayList(eskanList);

        tCMS.setEditable(true);
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        cURL.setCellValueFactory(new PropertyValueFactory<>("url"));
        cCMS.setCellValueFactory(new PropertyValueFactory<>("cms"));
        cVersion.setCellValueFactory(new PropertyValueFactory<>("version"));
        cLastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));

        //DATEPICKER

        //FILTROA
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Eskaneoa> filteredData = new FilteredList<>(eskaneoak, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        comboZerbitzua.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
            filteredData.setPredicate(eskaneo -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (eskaneo.getCms().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (eskaneo.getCms().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Eskaneoa> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tCMS.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tCMS.setItems(sortedData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboZerbitzua.getItems().add("WordPress");
        comboZerbitzua.getItems().add("Joomla");
        comboZerbitzua.getItems().add("phpMyAdmin");
        comboZerbitzua.getItems().add("Drupal");
        comboZerbitzua.getItems().add("");

        comboZerbitzua.setEditable(true);
        hasieratuTaula();
//        cLastUpdate.setOnEditCommit(
//                t -> {
//                    Eskaneoa eskaneoa=t.getTableView().getItems().get(t.getTablePosition().getRow());
//                    eskaneoa.setLastUpdate(t.getNewValue());
//                    CMSDBKud.getInstance().dataEguneratu(eskaneoa.getUrl(),eskaneoa.getLastUpdate());
//                });
//
//        this.cLastUpdate.getTableView().getItems().get().getLastUpdate().setOnShowing(event -> {
//            final TableView<T> tableView = getTableView();
//            tableView.getSelectionModel().select(getTableRow().getIndex());
//            tableView.edit(tableView.getSelectionModel().getSelectedIndex(), column);
//        });
//
//        this.colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
//            if(isEditing()) {
//                commitEdit(newValue);
//            }
//        });



    }
}