package ehu.isad.Controllers.DB;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    private String mugituDBLortu(String dbpath) throws IOException {
        String path=System.getProperty("user.home") +
                System.getProperty("file.separator") + ".whatwebfx" + System.getProperty("file.separator");
        File f=new File(path);
        File dbFile=new File(dbpath);
        if(!f.exists()){
            f.mkdir();
        }
        f=new File(path+System.getProperty("file.separator")+dbpath);
        f.delete();
        f.mkdir();
        Files.copy(dbFile.toPath(),f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return dbpath;
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
        try {
            dbpath=this.mugituDBLortu(dbpath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.conOpen(dbpath);

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
