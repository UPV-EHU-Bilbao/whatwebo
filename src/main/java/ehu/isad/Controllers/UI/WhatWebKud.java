package ehu.isad.Controllers.UI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import ehu.isad.WhatWeb;
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

import java.net.URL;
import java.util.ResourceBundle;

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

    }


    @FXML
    void onClick(MouseEvent event) {
        System.exit(0);
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
