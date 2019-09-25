## Todo project

Docker image

Set environment variables
`DOCKER_APP_NAME="jdk8-app:0.0.1"`
`JAR_FILE="app/todo/build/libs/todo-0.0.1-SNAPSHOT.jar"`

```bash
docker build --rm -t "$DOCKER_APP_NAME" --build-arg JAR_FILE="$JAR_FILE" -f docker/Dockerfile .
```

```bash
docker run --rm -p 8080:8080 jdk8-app:0.0.1
```

access at

`localhost:8080`
