package ehu.isad.Controllers.UI;

import ehu.isad.Controllers.DB.ServerDBKud;
import ehu.isad.Model.Target;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;


public class ServerKud {

    @FXML
    private Button btnEguneratu;

    @FXML
    private TabPane serverTP;

    @FXML
    private Tab t200;

    @FXML
    private TextArea txt200;

    @FXML
    private Tab t301;

    @FXML
    private TextArea txt301;

    @FXML
    private Tab t403;

    @FXML
    private TextArea txt403;

    @FXML
    private Tab t404;

    @FXML
    private TextArea txt404;
    @FXML
    void onClick(ActionEvent event) {

        this.tabEguneratu();

    }

    private void tabEguneratu(){

        List<Target> targets = ServerDBKud.getInstance().targetLortu();

        String newLine = System.getProperty("line.separator");
        final StringBuilder emaitza200 = new StringBuilder();
        final StringBuilder emaitza301 = new StringBuilder();
        final StringBuilder emaitza403 = new StringBuilder();
        final StringBuilder emaitza404 = new StringBuilder();
        //Lehenengo bertsio honetan 4 hauek soilik filtratuko dira
        int status;
        for (Target line : targets) {
            status=line.getStatus();
            if (status == 200) {
                emaitza200.append(line.getTarget()+newLine);
            }else if (status == 301){
                emaitza301.append(line.getTarget()+newLine);
            }else if (status == 403){
                emaitza403.append(line.getTarget()+newLine);
            }else if (status == 404) {
                emaitza404.append(line.getTarget()+newLine);
            }
        }
        txt200.setText(emaitza200.toString());
        txt301.setText(emaitza301.toString());
        txt403.setText(emaitza403.toString());
        txt404.setText(emaitza404.toString());
    }

    public ServerKud() {

    }

}
