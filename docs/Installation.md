# Installation Guide

## Helm Install of Driver

Prior to installing the driver, it may be necessary to:
 - configure the source for NS Packages. See [Configuring NS Package Location](ConfiguringNSPackageLocation.md)
 - configure the Kafka host and create mandatory topics. See [Configuring Kafka](ConfiguringKafka.md)
 - configure a secret containing trusted client certificates. See [Configuring Certificates](ConfiguringCertificates.md)


Download the Helm chart for the required version of the NFVO Driver. Run the following command to install the Helm chart with the default values:

```bash
helm install sol005-lifecycle-driver sol005-lifecycle-driver-<version>.tgz
```
**NOTES**:
 Before installing the driver, add a secret for icr.io by editing secrets through the following OpenShift console:

```bash
https://console-openshift-console.apps.DEV-CLUSTER.cp.fyre.ibm.com/k8s/ns/openshift-config/secrets/pull-secret/edit
```
username: iamapikey
password: < API key generated through IBM cloud account https://cloud.ibm.com/iam/apikeys >

## Onboarding Driver into LM

Use lmctl for onboard the driver into LM. For full details on how to install or use lmctl, refer to its documentation.

Certificate used by sol005 driver can be obtained from the secret sol005-lifecycle-driver-tls. This certificate needs to be used while onboarding NFVO driver. Use the following command to obtain sol005 certificate.
```bash
oc get secret sol005-lifecycle-driver-tls -o 'go-template={{index .data "tls.crt"}}' | base64 -d > sol005-lifecycle-tls.pem
```

The following command will onboard the NFVO Driver into CP4NA environment called 'dev01':

```bash
lmctl resourcedriver add --type sol005 --url http://sol005-lifecycle-driver:8296 dev01 --certificate sol005-lifecycle-tls.pem
```


## Create route for sol005

A route need to be created to access NFVO driver externally. In this example we are using reencrypt route.

Create certificates for the route.

```bash
openssl req -newkey rsa:2048 -keyout route-tls.key -x509 -days 365 -out route-tls.crt -nodes
```
**NOTES**: Use Common Name as ```sol005-lifecycle-driver.apps.<cluster-name>.<domain-name>``` while creating certificates.

Get CA certificate from secret.

```bash
oc get secret sol005-lifecycle-driver-tls -o 'go-template={{index .data "ca.crt"}}' | base64 -d > sol005-ca.crt
```

Create route.

```bash
oc create route reencrypt --service=sol005-lifecycle-driver --cert=route-tls.crt --key=route-tls.key --dest-ca-cert=sol005-ca.crt --hostname=sol005-lifecycle-driver.apps.<cluster-name>.<domain-name>
```


**NOTES**:
- The above example assumes lmctl has been configured with an environment called 'dev01'. Replace this environment name accordingly
- If this configuration doesn't include the password for the environment, one will be prompted for
