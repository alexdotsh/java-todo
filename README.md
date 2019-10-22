## Todo app in Spring using Docker

### How to: Building the Docker images

#### Build environment - Step 1
Docker image is used for building a *Gradle build* environment. After a successful build, a *build* directory will be created in the same project folder with the build results and a final `.jar` file (To be used in the step 2).

Set environment variables
```bash
DOCKER_IMAGE_NAME="jdk8-gradle-environment:0.0.1"

PROJECT_DIRECTORY="$PWD"
```

Build Gradle Docker image
```bash
docker build --rm -t "$DOCKER_IMAGE_NAME" -f ./docker/Dockerfile.build ./docker/.
```

Run Gradle Docker container
```bash
docker run --rm -v "$PROJECT_DIRECTORY":/home/gradle "$DOCKER_IMAGE_NAME" gradle --no-daemon build
```

#### Deployable environment - Step 2

This Docker image is used to copy the `libs/*-0.0.1-SNAPSHOT.jar` file (from step 1) into the image and be able launch/run it from within the container.

Set environment variables(Please set variables from Step 1 too)

```bash
APP_NAME="jdk8-app:0.0.1"

JAR_FILE="build/libs/todo-0.0.1-SNAPSHOT.jar"
```

Build App Docker image with `*.jar` file (From step 1)
```bash
docker build --rm -t "$APP_NAME" --build-arg JAR_FILE="$JAR_FILE" -f ./docker/Dockerfile .
```

Run App Docker container with `*.jar` file (From step 1) mounted
```bash
docker run --rm -p 8080:8080 -v "$JAR_FILE:/app.jar" $APP_NAME
```

Access at:

`localhost:8080`
