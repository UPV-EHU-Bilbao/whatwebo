package ehu.isad.Controllers.UI;

import ehu.isad.WhatWeb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.InterruptedIOException;
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
    private TableColumn<?, ?> tbUrl;

    @FXML
    private TextField textFilter;

    @FXML
    private ComboBox<?> comboZerbitzua;

    @FXML
    private Button btnUrl;

    @FXML
    private Label lblCMS;

    @FXML
    private Label lblMotak;

    private WhatWeb main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void handleClicks(ActionEvent event){
        if(event.getSource()== btnCMS){
            lblMotak.setText("WordPress, Joomla, phpMyAdmin, Drupal");
            lblCMS.setText("CMS");
            lblCMS.setBackground(new Background(new BackgroundFill(Color.rgb(63,43,99), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if(event.getSource()== btnServer){
            lblMotak.setText("");
            lblCMS.setText("Server");
            lblCMS.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,99), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if(event.getSource()== btnWhatWeb){
            lblMotak.setText("target, agrssion, http, auth, proxy, plugins, output, logging, performance");
            lblCMS.setText("WhatWeb");
            lblCMS.setBackground(new Background(new BackgroundFill(Color.rgb(63,0,0), CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }

    public void setMainApp(WhatWeb whatWeb) {
        this.main=whatWeb;
    }
}
