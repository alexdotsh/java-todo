## Build image

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
