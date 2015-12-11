package com.haresh.beans;

import java.io.Serializable;

public class ErrorBean implements Serializable {

    private String errorMessage = "";
    public String getErrorMessage() {
        return errorMessage;
    }

    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorBean() {
    }

    public ErrorBean(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}



