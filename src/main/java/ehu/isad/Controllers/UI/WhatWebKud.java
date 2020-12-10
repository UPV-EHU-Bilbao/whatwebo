package ehu.isad.Controllers.UI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import ehu.isad.Controllers.DB.CMSDBKud;
import ehu.isad.Model.Eskaneoa;
import ehu.isad.WhatWeb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;



public class WhatWebKud{

    @FXML
    private Pane mainPane;

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
    private AnchorPane serverpane;

    @FXML
    private AnchorPane webpane;

    @FXML
    private AnchorPane cmspane;

    @FXML
    private StackPane stackPane;

    private double xx;

    private double xy;



    private WhatWeb main;

    public void webAurrera(){

            lblMotak.setText("target, agrssion, http, auth, proxy, plugins, output, logging, performance");
            lblCMS.setText("WhatWeb");
            lblCMS.setBackground(new Background(new BackgroundFill(Color.rgb(200,0,217), CornerRadii.EMPTY, Insets.EMPTY)));
            pnBack.setBackground(new Background(new BackgroundFill(Color.rgb(200,0,217), CornerRadii.EMPTY, Insets.EMPTY)));
            webpane.toFront();
        }


    @FXML
    void mousePressed(MouseEvent event) {
        xx=event.getX();
        xy=event.getY();

        }


    @FXML
    void mouseDragged(MouseEvent event) {
        double x =event.getScreenX();
        double y =event.getScreenY();


        main.getStage().setX(x-xx);
        main.getStage().setY(y-xy);

    }



    @FXML
    void handleClicks(ActionEvent event) {
        if(event.getSource()== btnCMS){
            //List<Eskaneoa> scans = CMSDBKud.getInstance().eskaneoInfoLortu();
            main.getCmsKud().hasieratuTaula();
            lblMotak.setText("WordPress, Joomla, phpMyAdmin, Drupal");
            lblCMS.setText("CMS");
            lblCMS.setBackground(new Background(new BackgroundFill(Color.rgb(63,43,99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnBack.setBackground(new Background(new BackgroundFill(Color.rgb(63,43,99), CornerRadii.EMPTY, Insets.EMPTY)));
            cmspane.toFront();
        }
        else if(event.getSource()== btnServer){
            lblMotak.setText("");
            lblCMS.setText("Server");
            lblCMS.setBackground(new Background(new BackgroundFill(Color.rgb(204,0,150), CornerRadii.EMPTY, Insets.EMPTY)));
            pnBack.setBackground(new Background(new BackgroundFill(Color.rgb(204,0,150), CornerRadii.EMPTY, Insets.EMPTY)));
            serverpane.toFront();
        }
        else if(event.getSource()== btnWhatWeb ){
            lblMotak.setText("target, agrssion, http, auth, proxy, plugins, output, logging, performance");
            lblCMS.setText("WhatWeb");
            lblCMS.setBackground(new Background(new BackgroundFill(Color.rgb(200,0,217), CornerRadii.EMPTY, Insets.EMPTY)));
            pnBack.setBackground(new Background(new BackgroundFill(Color.rgb(200,0,217), CornerRadii.EMPTY, Insets.EMPTY)));
            webpane.toFront();
        }

    }

    public void setMainApp(WhatWeb whatWeb) {
        this.main=whatWeb;
    }

    @FXML
    void onClick(MouseEvent event) {
        System.exit(0);
    }

}

