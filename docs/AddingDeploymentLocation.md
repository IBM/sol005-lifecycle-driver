# Adding a SOL005-compliant NFVO as a Deployment Location in CP4NA

The deployment location for the target NFVO can be added in the CP4NA UI, supplying the following information for infrastructure properties.

###### Example of JSON structure for Deployment Location
```jsonc
{
    "nfvoServerUrl": "http://nfvo-test-harness:8297",
    # Authentication details
    "authenticationType": "BASIC",
    "username": "xxx",
    "password": "yyy"
}
```

##### Examples of Authentication Details

###### Basic Authentication

```jsonc
{
    "authenticationType": "BASIC",
    "username": "xxx",
    "password": "yyy"
}
```

###### OAuth2 Authentication

```jsonc
{
    "authenticationType": "OAUTH2",
    "accessTokenUri": "https://nfvo:port/oauth2/token",
    "client_id": "",
    "client_secret": "",
    "grant_type": "",                                   # Optional
    "scope": ""                                         # Optional
}
```

###### Cookie-based Session Authentication

```jsonc
{
    "authenticationType": "COOKIE",
    "authenticationUrl": "",
    "username": "xxx",
    "password": "yyy",
    "usernameTokenName": "",                # Optional (default: IDToken1)
    "passwordTokenName": ""                 # Optional (default: IDToken2)
}
```

## Subscribing to NsLcmOpOcc notifications from the NFVO

In order for the driver to receive lifecycle notifications from the NFVO, the following command should be run.

**NOTES**:
- It is important that the command is run from a location where the NFVO can be reached
- The address (for the driver) used in the content of the message below should be considered from the point of view of the NFVO itself (in cases where addresses or ports may be subject to NAT or proxying) 

```bash
curl -X POST \
  https://nfvo-address:port/nslcm/v1/subscriptions \
  -H 'Content-Type: application/json' \
  -d '{
    "callbackUri" : "http://sol005-lifecycle-driver:8296/nslcm/v1/notifications",
    "filter" : {
      "notificationTypes" : [ "NsLcmOperationOccurrenceNotification" ],
      "operationStates" : [ "COMPLETED", "FAILED", "FAILED_TEMP", "ROLLED_BACK" ]
    }
}'
```

- For authentication based request we can use the following command, with specific username and password.

```bash
curl -X POST \
  https://nfvo-address:port/nslcm/v1/subscriptions \
  -H 'Content-Type: application/json' \
  -d '{
    "callbackUri" : "http://sol005-lifecycle-driver:8296/nslcm/v1/notifications",
    "filter" : {
      "notificationTypes" : [ "NsLcmOperationOccurrenceNotification" ],
      "operationStates" : [ "COMPLETED", "FAILED", "FAILED_TEMP", "ROLLED_BACK" ]
    },"authentication" : { "authType" : "BASIC", "paramsBasic" : { "username" : "XXXX", "password" : "XXXX" }}}' -u "user:password"
}'
```
