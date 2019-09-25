## Todo project

### Use

DOCKER_APP_NAME="jdk8-app:0.0.1"
JAR_FILE="app/todo/build/libs/todo-0.0.1-SNAPSHOT.jar"

```console
docker build --rm -t "$DOCKER_APP_NAME" --build-arg JAR_FILE="$JAR_FILE" -f docker/Dockerfile .
```

```console
docker run --rm -p 8080:8080 jdk8-app:0.0.1
```

access at

`localhost:8080`
