package com.prod.treknation;

public class Users {
    private boolean isCanaResidenceProcess;
    private String userStage;
    private String userName;
    private String userEmail;
    private boolean isApplyFromCanada;
    private String applyIndiOrFamily;
    private boolean isCmpltdJourny;
    private String  source;
    private String  version;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isCanaResidenceProcess() {
        return isCanaResidenceProcess;
    }

    public void setCanaResidenceProcess(boolean canaResidenceProcess) {
        isCanaResidenceProcess = canaResidenceProcess;
    }

    public String getUserStage() {
        return userStage;
    }

    public void setUserStage(String userStage) {
        this.userStage = userStage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isApplyFromCanada() {
        return isApplyFromCanada;
    }

    public void setApplyFromCanada(boolean applyFromCanada) {
        isApplyFromCanada = applyFromCanada;
    }

    public String getApplyIndiOrFamily() {
        return applyIndiOrFamily;
    }

    public void setApplyIndiOrFamily(String applyIndiOrFamily) {
        this.applyIndiOrFamily = applyIndiOrFamily;
    }

    public boolean isCmpltdJourny() {
        return isCmpltdJourny;
    }

    public void setCmpltdJourny(boolean cmpltdJourny) {
        isCmpltdJourny = cmpltdJourny;
    }
}
