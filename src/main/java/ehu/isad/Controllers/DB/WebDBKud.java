package ehu.isad.Controllers.DB;

import java.io.*;

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

        }
    }
}
