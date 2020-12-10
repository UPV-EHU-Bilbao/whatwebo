package ehu.isad.Controllers.DB;

import ehu.isad.Model.Eskaneoa;
import ehu.isad.Model.Target;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServerDBKudTest {

    ServerDBKud serdb;
    Target tar;

    @BeforeEach
    void setUp() {
        serdb=ServerDBKud.getInstance();
    }

    @Test
    void targetLortu() {
        List<Target> list=serdb.targetLortu();
        assertNotNull(list);

        for(int i=0;i< list.size();i++) {
            tar = list.get(i);
            assertNotNull(tar.getTarget());
            assertNotNull(tar.getStatus());
            assertTrue(tar.getStatus()>=100);
            assertTrue(tar.getStatus()<600);
        }
    }
}