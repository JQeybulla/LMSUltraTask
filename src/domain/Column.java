package domain;

public class Column {
    private String key;
    private String value;
    //private Reader bir;

    private String name;
    private String model;
    private long width;
    private boolean isHidden;
    private String align;
    private boolean frozen;
    protected int status;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        if ( value != null ){
            return value;
        }else {
            return  "";
        }

    }

    public void setValue(String value) {
        this.value = value;
    }


    public boolean getFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getWidth() {
        return width;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
