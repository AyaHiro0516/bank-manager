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

    public boolean isEmpty() {
        return !isChooseSA && !isChooseCA && !isChooseLSA && !isChooseLCA;
    }

    public boolean getIsChooseSA() {
        return isChooseSA;
    }

    public ConditionBean setIsChooseSA(boolean isChooseSA) {
        this.isChooseSA = isChooseSA;
        return this;
    }

    public boolean getIsChooseCA() {
        return isChooseCA;
    }

    public ConditionBean setIsChooseCA(boolean isChooseCA) {
        this.isChooseCA = isChooseCA;
        return this;
    }

    public boolean getIsChooseLSA() {
        return isChooseLSA;
    }

    public ConditionBean setIsChooseLSA(boolean isChooseLSA) {
        this.isChooseLSA = isChooseLSA;
        return this;
    }

    public boolean getIsChooseLCA() {
        return isChooseLCA;
    }

    public ConditionBean setIsChooseLCA(boolean isChooseLCA) {
        this.isChooseLCA = isChooseLCA;
        return this;
    }

}
