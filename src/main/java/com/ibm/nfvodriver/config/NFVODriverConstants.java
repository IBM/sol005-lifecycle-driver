package com.ibm.nfvodriver.config;

import java.util.EnumSet;

import org.etsi.sol005.lifecyclemanagement.LcmOperationStateType;

public abstract class NFVODriverConstants {

    public static final String NFVO_SERVER_URL = "nfvoServerUrl";
    public static final String AUTHENTICATION_TYPE = "authenticationType";
    public static final String AUTHENTICATION_USERNAME = "username";
    public static final String AUTHENTICATION_PASSWORD = "password";
    public static final String AUTHENTICATION_URL = "authenticationUrl";
    public static final String AUTHENTICATION_USERNAME_TOKEN_NAME = "usernameTokenName";
    public static final String AUTHENTICATION_PASSWORD_TOKEN_NAME = "passwordTokenName";
    public static final String AUTHENTICATION_CLIENT_ID = "client_id";
    public static final String AUTHENTICATION_CLIENT_SECRET = "client_secret";
    public static final String AUTHENTICATION_GRANT_TYPE = "grant_type";
    public static final String AUTHENTICATION_SCOPE = "scope";
    public static final String AUTHENTICATION_ACCESS_TOKEN_URI = "accessTokenUri";

    public static final EnumSet<LcmOperationStateType> COMPLETED_OPERATIONAL_STATES = EnumSet.of(LcmOperationStateType.COMPLETED, LcmOperationStateType.FAILED, LcmOperationStateType.ROLLED_BACK);

}
