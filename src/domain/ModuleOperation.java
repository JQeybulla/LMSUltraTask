package domain;

import java.io.Serializable;


public class ModuleOperation implements Serializable {

    private long id;
    private int buttonLevel;
    private String name;
    private String buttonHint;
    private String buttonSpan;
    private String buttonClick;
    private String buttonDiv;
    private String buttonId;
    private String buttonBackImage;
    private Size size;
    private Long hintRes;
    private Long nameRes;
    private String appActions;
    private long menuIdForAct;
    private long operTypeForModule;

    public String getDialogActions() {
        return dialogActions;
    }

    public void setDialogActions(String dialogActions) {
        this.dialogActions = dialogActions;
    }

    private String dialogActions;

    public long getOperTypeForModule() {
        return operTypeForModule;
    }

    public void setOperTypeForModule(long operTypeForModule) {
        this.operTypeForModule = operTypeForModule;
    }

    public String getAppActions() {
        return appActions;
    }

    public void setAppActions(String appActions) {
        this.appActions = appActions;
    }

    public long getMenuIdForAct() {
        return menuIdForAct;
    }

    public void setMenuIdForAct(long menuIdForAct) {
        this.menuIdForAct = menuIdForAct;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getHintRes() {
        return hintRes;
    }

    public void setHintRes(Long hintRes) {
        this.hintRes = hintRes;
    }

    public Long getNameRes() {
        return nameRes;
    }

    public void setNameRes(Long nameRes) {
        this.nameRes = nameRes;
    }

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    public String getButtonBackImage() {
        return buttonBackImage;
    }

    public void setButtonBackImage(String buttonBackImage) {
        this.buttonBackImage = buttonBackImage;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getButtonHint() {
        return buttonHint;
    }

    public void setButtonHint(String buttonHint) {
        this.buttonHint = buttonHint;
    }

    public String getButtonClick() {
        return buttonClick;
    }

    public void setButtonClick(String buttonClick) {
        this.buttonClick = buttonClick;
    }

    public String getButtonSpan() {
        return buttonSpan;
    }

    public void setButtonSpan(String buttonSpan) {
        this.buttonSpan = buttonSpan;
    }

    public int getButtonLevel() {
        return buttonLevel;
    }

    public void setButtonLevel(int buttonLevel) {
        this.buttonLevel = buttonLevel;
    }

    public String getButtonDiv() {
        return buttonDiv;
    }

    public void setButtonDiv(String buttonDiv) {
        this.buttonDiv = buttonDiv;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}