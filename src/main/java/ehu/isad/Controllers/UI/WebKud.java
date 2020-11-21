package ehu.isad.Controllers.UI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class WebKud implements Initializable {

    @FXML
    private GridPane pnWhatWeb;

    @FXML
    private TextField textURL;

    @FXML
    private TextArea textAreaLog;

    @FXML
    private Button btnScan;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onClickScan(ActionEvent event) {
        textAreaLog.setWrapText(true);
        textAreaLog.setText("Kargatzen. Itxaron, mesedez....");


        Thread taskThread = new Thread(() -> {

            String newLine = System.getProperty("line.separator");
            final StringBuilder emaitza = new StringBuilder();
            allProcesses().forEach(line -> {
                emaitza.append(line + newLine);
            });

            Platform.runLater(() -> {
                textAreaLog.setText(emaitza.toString());

                // txertatu();

            });

        });
        taskThread.start();

    }


    public List<String> allProcesses() {
        List<String> processes = new LinkedList<String>();
        try {
            String line;
            Process p = null;
            if(System.getProperty("os.name").toLowerCase().contains("win")) {
                p = Runtime.getRuntime().exec
                        (System.getenv("windir") +"\\system32\\"+"" +
                                "wsl whatweb --color=never "+this.textURL.getText());
            } else {
                p = Runtime.getRuntime().exec("whatweb --color=never "+this.textURL.getText());
            }
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                processes.add(line);
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }

        return processes;
    }
    public WebKud() {
        System.out.println("web inst");
    }
}
