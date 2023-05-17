docker buildx create --name mybuilder
docker buildx use mybuilder
docker buildx build --platform linux/amd64,linux/arm64,linux/arm/v7,windows/amd64 -t piotrdamrych/hello:latest --push .
docker run piotrdamrych/hello:latest
