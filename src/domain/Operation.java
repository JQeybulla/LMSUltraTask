package domain;


import java.io.Serializable;

public class Operation implements Serializable {

    private long id;
    private String name;
    private ModuleOperation moduleOperation;
    private long group;


    public ModuleOperation getModuleOperation() {
        return moduleOperation;
    }

    public void setModuleOperation(ModuleOperation moduleOperation) {
        this.moduleOperation = moduleOperation;
    }

    public long getGroup() {
        return group;
    }

    public void setGroup(long group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}