package ehu.isad.Controllers.UI;

import ehu.isad.Controllers.DB.CMSDBKud;
import ehu.isad.Controllers.DB.WebDBKud;
import ehu.isad.Utils.Utils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

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
        LocalDate now= LocalDate.now();
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

                try {
                    txertatu();
                    ezabatuFitx();
                    CMSDBKud.getInstance().dataEguneratu(this.textURL.getText(),now);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

        });
        taskThread.start();

    }


    public List<String> allProcesses() {
        List<String> processes = new LinkedList<String>();
        try {
            String line;
            Process p = null;
            Properties prp= Utils.lortuEzarpenak();
            String dbmysqlpath=System.getProperty("user.home") +
                    System.getProperty("file.separator") + ".whatwebfx" +
                    System.getProperty("file.separator")+prp.getProperty("dbmysqlpath");

            if(System.getProperty("os.name").toLowerCase().contains("win")) {
                dbmysqlpath=dbmysqlpath.replace("\\","/");
                dbmysqlpath=dbmysqlpath.replace("C:/","/mnt/c/");
                p = Runtime.getRuntime().exec
                        (System.getenv("windir") +"\\system32\\"+"" +
                                "wsl whatweb --color=never --log-sql="+ dbmysqlpath + " " + this.textURL.getText());
            } else {
                p = Runtime.getRuntime().exec("whatweb --color=never --log-sql="+ dbmysqlpath + " " + this.textURL.getText());
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

    void txertatu() throws IOException {

        Properties prp= Utils.lortuEzarpenak();
        String dbmysqlpath=System.getProperty("user.home") +
                System.getProperty("file.separator") + ".whatwebfx" +
                System.getProperty("file.separator")+prp.getProperty("dbmysqlpath");
        File f=new File(dbmysqlpath);
        BufferedReader bf = new BufferedReader(new FileReader(f));
        WebDBKud.getInstance().sartuSQLite(bf);
        bf.close();

    }

    void ezabatuFitx() throws IOException {
        Properties prp= Utils.lortuEzarpenak();
        String dbpath=System.getProperty("user.home") +
                System.getProperty("file.separator") + ".whatwebfx" +
                System.getProperty("file.separator")+prp.getProperty("dbmysqlpath");
        File f=new File(dbpath);
        f.delete();
    }

    public WebKud() {

    }
}
