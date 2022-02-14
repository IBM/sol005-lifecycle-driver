# Docker Images

## Build Docker Image
This requires docker to be installed and running on your local machine.


Navigate to the Docker directory and build the image. Tag with the release version number.

```
1. cp target/sol005-lifecycle-driverxxx.jar to target/docker-ready directory.
```

```
2. cd target/docker-ready directory
```

```
3. docker build -t sol005-lifecycle-driver: <release version number> .
```

Make sure the sol005 lifecycle driver image created successfully.
