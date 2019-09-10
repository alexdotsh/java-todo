## Use container

DOCKER_IMAGE_NAME="jdk8-gradle-environment:0.0.1"
PROJECT_DIRECTORY_NAME="app"

build
```
docker build -t "$DOCKER_IMAGE_NAME" .
```

use
```
docker run --rm -v "$PWD/gradle_distributions/.gradle":/root/.gradle -v "$PWD/$PROJECT_DIRECTORY_NAME":/app "$DOCKER_IMAGE_NAME" gradlew <gradle-wrapper-task>
```
