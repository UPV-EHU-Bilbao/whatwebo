package ehu.isad.Controllers.DB;

import ehu.isad.Model.Target;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServerDBKud {

    private static final ServerDBKud instance = new ServerDBKud();

    public static ServerDBKud getInstance() {
        return instance;
    }

    private ServerDBKud() {
    }

    public List<Target> targetLortu(){

        List<Target> emaitza = new ArrayList<>();
        DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();

        String query = "select target_id,target,status from targets";
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
