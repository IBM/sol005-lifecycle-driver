# Dev Env
These docs help you get a full dev environment setup for working on this driver.

## Install Java
You need Java1.8+. Install those according to the instructions for your operating system.

## Build Project
From the root directory run the following command to build the project

```
./mvnw clean install
```

sol005-lifecycle-driverxxx.jar will be created under target folder

Note: you will also need the `helm` and `docker` command line tools to run the build script. `helm` must be initialised (e.g. `helm init --client-only`)