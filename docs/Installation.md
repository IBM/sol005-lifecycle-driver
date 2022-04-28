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

## Onboarding Driver into LM

Use lmctl for onboard the driver into LM. For full details on how to install or use lmctl, refer to its documentation.

The following command will onboard the NFVO Driver into CP4NA environment called 'dev01':

```bash
lmctl lifecycledriver add --type sol005 --url http://sol005-lifecycle-driver:8296 dev01
```

**NOTES**:
- The above example assumes lmctl has been configured with an environment called 'dev01'. Replace this environment name accordingly
- If this configuration doesn't include the password for the environment, one will be prompted for
