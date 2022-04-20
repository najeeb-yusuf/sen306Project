public class Relationship {
    private String sid;
    private String ct;

    public Relationship(String sid, String ct){
        this.sid = sid;
        this.ct = ct;
    }

    public String getCt() {
        return ct;
    }

    public String getSid() {
        return sid;
    }
}
