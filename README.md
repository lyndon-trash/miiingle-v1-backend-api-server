# Backend: API Server
Resource Server for the Miiingle.NET Web and Mobile App

## Dockerize and Push to Registry
```
iex "$(aws ecr get-login --no-include-email --region=us-east-1)" 
.\gradlew build dockerPush -x test -PdockerRepository="$env:DOCKER_ECR_REPO_URL"/api-server
```