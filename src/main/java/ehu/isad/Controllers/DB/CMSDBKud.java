package ehu.isad.Controllers.DB;

import ehu.isad.Model.Eskaneoa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CMSDBKud {

    private String[] cmsak = new String[4];

    private static final CMSDBKud instance = new CMSDBKud();

    public static CMSDBKud getInstance() {
        return instance;
    }

    private CMSDBKud() {
        cmsak[0] = "WordPress%";
        cmsak[1] = "Joomla%";
        cmsak[2] = "phpMyAdmin%";
        cmsak[3] = "Drupal%";
    }

    public List<Eskaneoa> eskaneoInfoLortu() {
        List<Eskaneoa> emaitza = new ArrayList<>();
        DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
        String cmsMota = " AND s.string LIKE \""+cmsak[0]+"\" OR s.string LIKE \""+cmsak[1]
                +"\" OR s.string LIKE \""+cmsak[2]+"\" OR s.string LIKE \""+cmsak[3]+"\"";
        String query = "select s.string,s.version,t.target from scans s, targets t " +
                "where s.target_id = t.target_id"+cmsMota+" GROUP BY(t.target_id)";
        ResultSet rs = dbkud.execSQL(query);

        try {
            while (rs.next()) {

                String cms = rs.getString("string");
                String url = rs.getString("target");
                String version = rs.getString("version");

                Eskaneoa eskaneo = new Eskaneoa(url,cms,version);
                emaitza.add(eskaneo);
            }
        }catch (SQLException e){
            System.err.println(e);
        }


        return emaitza;
    }
}
