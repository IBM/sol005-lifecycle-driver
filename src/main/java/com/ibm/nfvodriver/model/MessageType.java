package com.ibm.nfvodriver.model;

import java.util.Locale;

public enum MessageType {
    REQUEST,
    RESPONSE,
    MESSAGE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
