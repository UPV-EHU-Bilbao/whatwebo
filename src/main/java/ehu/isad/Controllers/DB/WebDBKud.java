package ehu.isad.Controllers.DB;

import ehu.isad.Model.Target;
import ehu.isad.Utils.Utils;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WebDBKud {

    private static final WebDBKud instance = new WebDBKud();

    public static WebDBKud getInstance() {
        return instance;
    }

    private WebDBKud() {
    }

    public void sartuSQLite(BufferedReader bf) throws IOException {

        String query;
        DBKudeatzaile dbKudeatzaile=DBKudeatzaile.getInstantzia();
        String line;

        while((line=bf.readLine())!=null){
            line=line.replace("IGNORE","OR IGNORE");
            query= line;
            dbKudeatzaile.execSQL(query);

            // if line contains ikasten.io
            //    update targets set azkenEguneraketa=now()
        }
    }

    public List<Target> targetLortu(){

        List<Target> emaitza = new ArrayList<>();
        DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();

        String query = "select * from targets";
        ResultSet rs = dbkud.execSQL(query);

        try {
            while (rs.next()) {

                int id = rs.getInt("target_id");
                String target = rs.getString("target");
                int status = rs.getInt("status");

                Target targeta = new Target(id,target, status);
                emaitza.add(targeta);
            }
        }catch (SQLException e){
            System.err.println(e);
        }


        return emaitza;
    }
}
