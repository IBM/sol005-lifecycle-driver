package com.ibm.nfvodriver.security;

import java.net.HttpCookie;

public class CookieContext {

    private HttpCookie sessionCookie;

    public HttpCookie getSessionCookie() {
        return sessionCookie;
    }

    public void setSessionCookie(HttpCookie sessionCookie) {
        this.sessionCookie = sessionCookie;
    }

}
