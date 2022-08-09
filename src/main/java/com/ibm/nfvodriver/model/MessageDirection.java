package com.ibm.nfvodriver.model;

import java.util.Locale;

public enum MessageDirection {

    RECEIVED,
    SENT;
    @Override
    public String toString(){
       return this.name().toLowerCase();
    }
}
