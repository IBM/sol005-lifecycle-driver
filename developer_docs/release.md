# Releasing the Driver

The following guide details the steps for releasing the SOL005 Lifecycle Driver. This may only be performed by a user with admin rights to this Git repository and the ibmcom docker registry.

## 1. Ensure Milestone

Ensure there is a milestone created for the release at: [https://github.com/IBM/sol005-lifecycle-driver/milestones](https://github.com/IBM/sol005-lifecycle-driver/milestones).

Also ensure all issues going into this release are assigned to this milestone. **Move any issues from unreleased milestones into this release if the code has been merged**

## 2. Merge Develop to Master

Development work is normally carried out on the `develop` branch. Merge this branch to `master`, by creating a PR.

Then perform the release from the `master` branch. This ensures the `master` branch is tagged correctly.

> Note: do NOT delete the `develop` branch

## 3. Build and Release (on master)  

> Note: Make sure to pull-in the latest and correct tag required for the openjdk image locally before preparing the release build.  
> e.g  
> You can find the openjdk image details here: https://github.com/IBM/sol005-lifecycle-driver/blob/master/src/main/resources/docker/Dockerfile#L1-L2  
> `docker pull openjdk:8u302-jre`

Run the following command (the `dev` profile ensures extra log statements are available in the built code):
```
./mvnw clean package -Pdev,docker,helm
```

This should produce 2 artifacts:
- a locally built docker image, e.g. `ibmcom/sol005-lifecycle-driver:0.2.0`
- a helm chart, e.g. `sol005-lifecycle-driver-0.2.0.tgz`

Verify the docker image has been produced by running
```
docker image ls
```

Verify that a helm chart is built in the `target/helm/repo` directory, e.g.
```
ls target/helm/repo
```

## 4. Release artifacts

The Docker image not been pushed by the previous build step so must be done manually, e.g.
```
docker push ibmcom/sol005-lifecycle-driver:0.2.0
```

Complete the following:

- Visit the [releases](https://github.com/IBM/sol005-lifecycle-driver/releases) section of the driver repository
- Click `Draft a new release`
- Input the version the `--version` option earlier as the tag e.g. 0.2.0
- Use the same value for the `Release title` e.g. 0.2.0
- Add release notes in the description of the release. Look at previous releases to see the format. Usually, we will list the issues fixed.
- Attach the Helm chart `tgz` file produced by `mvnw` command in the `target/helm/repo` directory

## 5. Cleanup

- Close the Milestone for this release on [Github](https://github.com/IBM/sol005-lifecycle-driver/milestones)
- Create a new Milestone for next release (if one does not exist).
