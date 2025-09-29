package org.study.SpringStarter.util.constants;

public enum Privillages {

    RESET_ANY_USER_PASSWORD(1l, "RESET_ANY_USER_PASSWORD"),
    ACCESS_ADMIN_PANEL(2l, "ACCESS_ADMIN_PANEL");

    private long id;
    private String privillage;

    private Privillages(long id, String privillage) {
        this.id = id;
        this.privillage = privillage;
    }

    public long getId() {
        return id;
    }

    public String getPrivillage() {
        return privillage;
    }

}