package ehu.isad.Controllers.UI;

import ehu.isad.Controllers.DB.ServerDBKud;
import ehu.isad.Controllers.DB.WebDBKud;
import ehu.isad.Model.Target;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ServerKud {

    @FXML
    private GridPane pnServer;

    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnEguneratu;

    @FXML
    void onClick(ActionEvent event) {

        this.txtAreaAktualizatu();

    }

    private void txtAreaAktualizatu(){

        List<Target> targets = ServerDBKud.getInstance().targetLortu();

        String newLine = System.getProperty("line.separator");
        final StringBuilder emaitza = new StringBuilder();
        String eskaera = "???: Ezezaguna";
        int status;
        for (Target line : targets) {
            status=line.getStatus();
            if (status == 200) {
                eskaera = 200 + ": SUCCESSFUL REQUEST";
            }else if (status == 301){
                eskaera = 301 + ": MOVED PERMANENTLY";
            }else if (status == 400){
                eskaera= 400 + ": BAD REQUEST";
            }else if (status == 403){
                eskaera = 403 + ": FORBIDDEN";
            }else if (status == 404) {
                eskaera = 404 + ": NOT FOUND";
            }
            emaitza.append(line.getTarget() + "  -  " + eskaera + newLine);
        }

        txtArea.setText(emaitza.toString());

    }

    public ServerKud() {
        System.out.println("server inst");
    }

}
