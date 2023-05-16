package domain;

public class Base {
    // Adini deyish Base
    protected long id;
    protected int active;
    protected int status;
    private static String info;

    public static String getInfo() {
        return info;
    }

    public static void setInfo(String info) {
        Base.info = info;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    protected boolean isNew;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

}
