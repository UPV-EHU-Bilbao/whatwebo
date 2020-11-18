package ehu.isad.Controllers.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CMSKud {

    @FXML
    private GridPane pnCMS;

    @FXML
    private TableColumn<?, ?> tbUrl;

    @FXML
    private TextField textFilter;

    @FXML
    private ComboBox<?> comboZerbitzua;

    @FXML
    private Button btnUrl;

    public CMSKud() {
        System.out.println("cms inst");
    }
}