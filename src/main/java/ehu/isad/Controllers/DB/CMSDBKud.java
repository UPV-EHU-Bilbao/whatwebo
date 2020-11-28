package ehu.isad.Controllers.DB;

import ehu.isad.Model.Eskaneoa;

import java.util.List;

public class CMSDBKud {

    private static final CMSDBKud instance = new CMSDBKud();

    public static CMSDBKud getInstance() {
        return instance;
    }

    private CMSDBKud() {
    }

    public List<Eskaneoa> eskaneoInfoLortu() {
        //TODO
        return null;
    }
}
