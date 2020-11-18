package ehu.isad.Controllers.UI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import ehu.isad.WhatWeb;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class WhatWebKud implements Initializable {

    @FXML
    private Button btnCMS;

    @FXML
    private Button btnServer;

    @FXML
    private Button btnWhatWeb;

    @FXML
    private Pane pnBack;

    @FXML
    private Label lblCMS;

    @FXML
    private Label lblMotak;

    @FXML
    private FontAwesomeIconView btnClose;

    @FXML
    private GridPane pnServer;

    @FXML
    private GridPane pnWhatWeb;

    @FXML
    private TextField textURL;

    @FXML
    private TextArea textAreaLog;

    @FXML
    private Button btnScan;

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


    private WhatWeb main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textAreaLog.setWrapText(true);
    }


    @FXML
    void onClick(MouseEvent event) {
        System.exit(0);
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

    @FXML
    private void handleClicks(ActionEvent event){
        if(event.getSource()== btnCMS){
            lblMotak.setText("WordPress, Joomla, phpMyAdmin, Drupal");
            lblCMS.setText("CMS");
            lblCMS.setBackground(new Background(new BackgroundFill(Color.rgb(63,43,99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnBack.setBackground(new Background(new BackgroundFill(Color.rgb(63,43,99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnCMS.toFront();
        }
        else if(event.getSource()== btnServer){
            lblMotak.setText("");
            lblCMS.setText("Server");
            lblCMS.setBackground(new Background(new BackgroundFill(Color.rgb(204,0,150), CornerRadii.EMPTY, Insets.EMPTY)));
            pnBack.setBackground(new Background(new BackgroundFill(Color.rgb(204,0,150), CornerRadii.EMPTY, Insets.EMPTY)));
            pnServer.toFront();
        }
        else if(event.getSource()== btnWhatWeb){
            lblMotak.setText("target, agrssion, http, auth, proxy, plugins, output, logging, performance");
            lblCMS.setText("WhatWeb");
            lblCMS.setBackground(new Background(new BackgroundFill(Color.rgb(200,0,217), CornerRadii.EMPTY, Insets.EMPTY)));
            pnBack.setBackground(new Background(new BackgroundFill(Color.rgb(200,0,217), CornerRadii.EMPTY, Insets.EMPTY)));
            pnWhatWeb.toFront();
        }

    }

    public void setMainApp(WhatWeb whatWeb) {
        this.main=whatWeb;
    }
}
