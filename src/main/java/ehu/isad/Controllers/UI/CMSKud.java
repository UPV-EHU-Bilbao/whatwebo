package ehu.isad.Controllers.UI;

import ehu.isad.Controllers.DB.CMSDBKud;
import ehu.isad.Model.Eskaneoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ComboBox<?> comboZerbitzua;

    @FXML
    private Button btnUrl;

    public CMSKud() {
        System.out.println("cms inst");
    }

    private void hasieratuTaula(){
//        Eskaneoa esk=new Eskaneoa("uwu.com","owo","4.4");//proba
//        esk.setLastUpdate(new DatePicker());//proba
        List<Eskaneoa> eskanList= CMSDBKud.getInstance().eskaneoInfoLortu();
        //eskanList.add(esk);//proba
        eskaneoak = FXCollections.observableArrayList(eskanList);

        tCMS.setEditable(true);
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        cURL.setCellValueFactory(new PropertyValueFactory<>("url"));
        cCMS.setCellValueFactory(new PropertyValueFactory<>("cms"));
        cVersion.setCellValueFactory(new PropertyValueFactory<>("version"));
        cLastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));



        tCMS.setItems(eskaneoak);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hasieratuTaula();
    }
}