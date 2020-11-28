package ehu.isad.Model;

import javafx.scene.control.DatePicker;

public class Eskaneoa {

    public String url;
    public String cms;
    public String version;
    public DatePicker lastUpdate;

    public Eskaneoa(String pUrl, String pCms, String pVersion){
        url=pUrl;
        cms=pCms;
        version=pVersion;
        //lastUpdate=?;
    }

    public void setLastUpdate(DatePicker lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUrl() {
        return url;
    }

    public String getCms() {
        return cms;
    }

    public String getVersion() {
        return version;
    }

    public DatePicker getLastUpdate() {
        return lastUpdate;
    }

}
