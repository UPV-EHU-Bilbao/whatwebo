package ehu.isad.Controllers.DB;

import ehu.isad.Model.Eskaneoa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CMSDBKudTest {

    CMSDBKud cmsdb;
    String[] cmsak;
    Eskaneoa esk;

    @BeforeEach
    void setUp() {
       cmsdb = CMSDBKud.getInstance();
       cmsak = new String[5];
       cmsak[0] = "WordPress";
       cmsak[1] = "Joomla!";
       cmsak[2] = "phpMyAdmin";
       cmsak[3] = "Drupal";
       cmsak[4] = "unknown";
    }

    @Test
    void eskaneoInfoLortu() {
        List<Eskaneoa> list=cmsdb.eskaneoInfoLortu();
        assertNotNull(list);
        for(int i=0;i< list.size();i++) {
            esk = list.get(i);
            assertNotNull(esk.getUrl());
            assertNotNull(esk.getLastUpdate());
            assertNotNull(esk.getCms());
            assertNotNull(esk.getVersion());
            assertTrue(Arrays.asList(cmsak).contains(esk.getCms()));
        }
    }
}