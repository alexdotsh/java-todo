## Build environment

Set environment variables

`DOCKER_IMAGE_NAME="jdk8-gradle-environment:0.0.1"`

`PROJECT_DIRECTORY="app"`

`PROJECT_NAME="todo"`

build
```bash
docker build --rm -t "$DOCKER_IMAGE_NAME" -f Dockerfile.build .
```

use
```bash
docker run --rm -v "$PWD/$PROJECT_DIRECTORY/$PROJECT_NAME":/home/gradle "$DOCKER_IMAGE_NAME" gradle --no-daemon build
```

#### Deployable environment

Set environment variables

`DOCKER_APP_NAME="jdk8-app:0.0.1"`

`JAR_FILE="../app/todo/build/libs/todo-0.0.1-SNAPSHOT.jar"`

```terminal
docker build --rm -t "$DOCKER_APP_NAME" --build-arg JAR_FILE="$JAR_FILE" -f Dockerfile .
```

```terminal
docker run --rm -p 8080:8080 jdk8-app:0.0.1
```
