package ehu.isad.Controllers.UI;

import ehu.isad.Utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class WebKudTest {

    WebKud web;
    Properties prp;
    String dbmysqlpath;
    File f;

    @BeforeEach
    void setUp() {
        web=new WebKud();
        prp= Utils.lortuEzarpenak();
        dbmysqlpath=System.getProperty("user.home") +
                System.getProperty("file.separator") + ".whatwebfx" +
                System.getProperty("file.separator")+prp.getProperty("dbmysqlpath");
        f=new File(dbmysqlpath);
    }

    @Test
    void ezabatuFitx() throws IOException {
        f.mkdir();
        web.ezabatuFitx();
        assertFalse(f.exists());
    }
}