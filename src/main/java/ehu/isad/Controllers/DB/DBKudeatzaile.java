package ehu.isad.Controllers.DB;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.Properties;

public class DBKudeatzaile {

    Connection conn = null;

    private void conOpen(String dbpath) {
        try {
            dbpath = System.getProperty("user.home") +
                    System.getProperty("file.separator") + ".whatwebfx" +
                    System.getProperty("file.separator") + dbpath;
            String url = "jdbc:sqlite:"+ dbpath;
            conn = DriverManager.getConnection(url);

            System.out.println("Database connection established");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server " + e);
        }
    }

    private void mugituDBLortu(String dbpath) throws IOException {
        String path=System.getProperty("user.home") +
                System.getProperty("file.separator") + ".whatwebfx" + System.getProperty("file.separator");
        File f=new File(path);
        InputStream isdb=getClass().getResourceAsStream("/whatweb2.sqlite.sql");
        if(!f.exists()){
            f.mkdir();
        }
        String dbpathOsoa=path+dbpath;
        f=new File(dbpathOsoa);
        if(!f.exists()){
            this.conOpen(dbpath);
            f=new File(path+System.getProperty("file.separator")+"whatweb2.sqlite.sql");
            f.mkdir();
            Files.copy(isdb,f.toPath(), StandardCopyOption.REPLACE_EXISTING);
            sortuDB(f,dbpathOsoa);
        }
        else{
            this.conOpen(dbpath);
        }
    }

    private void sortuDB(File f,String dbpath) throws IOException {

        BufferedReader bf = new BufferedReader(new FileReader(f));
        String query;
        String line;

        while((line=bf.readLine())!=null){
            query= line;
            this.execSQL(query);
        }
    }

    private ResultSet query(Statement s, String query) {

        ResultSet rs = null;

        try {
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    // singleton patroia
    private static DBKudeatzaile instantzia = new DBKudeatzaile();

    private DBKudeatzaile() {
        Properties properties = null;
        InputStream in = null;

        try {
            in = this.getClass().getResourceAsStream("/setup.properties");
            properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String dbpath=properties.getProperty("dbpath");
        String dbinstalatzaile=properties.getProperty("dbinstalatzaile");
        try {
            this.mugituDBLortu(dbpath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static DBKudeatzaile getInstantzia() {
        return instantzia;
    }

    public ResultSet execSQL(String query) {
        int count = 0;
        Statement s = null;
        ResultSet rs = null;
        try {
            s = (Statement) conn.createStatement();
            if (query.toLowerCase().indexOf("select") == 0) {
                // select agindu bat
                rs = this.query(s, query);
            } else {
                // update, delete, create agindu bat
                count = s.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
