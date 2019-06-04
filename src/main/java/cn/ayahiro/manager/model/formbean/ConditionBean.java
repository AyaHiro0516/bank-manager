package cn.ayahiro.manager.model.formbean;

public class ConditionBean {
    private boolean isChooseSA;
    private boolean isChooseCA;
    private boolean isChooseLSA;
    private boolean isChooseLCA;

    public ConditionBean() {
    }

    public ConditionBean(boolean isChooseSA, boolean isChooseCA, boolean isChooseLSA, boolean isChooseLCA) {
        this.isChooseSA = isChooseSA;
        this.isChooseCA = isChooseCA;
        this.isChooseLSA = isChooseLSA;
        this.isChooseLCA = isChooseLCA;
    }

    public boolean getIsChooseSA() {
        return isChooseSA;
    }

    public void setIsChooseSA(boolean isChooseSA) {
        this.isChooseSA = isChooseSA;
    }

    public boolean getIsChooseCA() {
        return isChooseCA;
    }

    public void setIsChooseCA(boolean isChooseCA) {
        this.isChooseCA = isChooseCA;
    }
    public boolean getIsChooseLSA() {
        return isChooseLSA;
    }

    public void setIsChooseLSA(boolean isChooseLSA) {
        this.isChooseLSA = isChooseLSA;
    }
    public boolean getIsChooseLCA() {
        return isChooseLCA;
    }

    public void setIsChooseLCA(boolean isChooseLCA) {
        this.isChooseLCA = isChooseLCA;
    }

}
