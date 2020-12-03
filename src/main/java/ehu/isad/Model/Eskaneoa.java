package ehu.isad.Model;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class Eskaneoa {

    private String url;
    private String cms;
    private String version;
    private DatePicker lastUpdate;

    public Eskaneoa(String pUrl, String pCms, String pVersion,String pData){
        url=pUrl;
        cms=pCms;
        version=pVersion;
        lastUpdate= new DatePicker();
        lastUpdate.setValue(LocalDate.parse(pData));
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
