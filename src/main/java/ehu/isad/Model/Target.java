package ehu.isad.Model;

public class Target {

    private int id;
    private String target;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Target(int id, String target, int status) {
        this.id = id;
        this.target = target;
        this.status = status;
    }
}
