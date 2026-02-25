package it.learning.actions;

import org.apache.struts2.ActionSupport;

public class SalutoAction extends ActionSupport {

    private String messaggio;

    @Override
    public String execute() {
        messaggio = "Struts 7 è configurato correttamente!";
        return SUCCESS;
    }

    public String getMessaggio() {
        return messaggio;
    }
}
