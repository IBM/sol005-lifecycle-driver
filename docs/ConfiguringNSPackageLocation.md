# Configuring a package repository for retrieving NS Packages

The NFVO driver can be configured with a location from which it can retrieve NS packages. This can be configured via the Helm values file by setting the following property during the Helm install.

**Currently, only Sonatype Nexus repositories are supported**

It is assumed that each NS package will be stored as two files:
- `{nsPackageId}.pkgInfo` - A JSON representation of the NsPkgInfo for the package
- `{nsPackageId}.zip` - A zip file containing the full NS package

These file should be present in the same directory on the Nexus repository (configurable below).

###### Example of values passed to Helm chart during install
```yaml
app:
  config:
    packageRepositoryUrl: "http://package-server:8081"
    repositoryName: nfvo-packages
    nexusGroupName: path/to/files
```

**NOTES**:
- the `packageRepositoryUrl` must reference the Sonatype Nexus instance
  - **IMPORTANT: This is different to previous versions**
- the `repositoryName` should match a repository within Nexus (should be of type "raw")
- the `nexusGroupName` property should match the base path where the files reside in the Nexus repository

### Authentication

If required, authentication details for communicating with Nexus can be supplied as follows (during the Helm install)
```yaml
app:
  config:
    authenticationProperties:
      type: BASIC
      username: jack
      password: jack
```

