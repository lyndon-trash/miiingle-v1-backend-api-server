# Backend: API Server
[![Build Status](https://travis-ci.com/miiingle/backend-api-server.svg?branch=master)](https://travis-ci.com/miiingle/backend-api-server)

Resource Server for the Miiingle.NET Web and Mobile App

## Dockerize and Push to Registry
```
iex "$(aws ecr get-login --no-include-email --region=us-east-1)" 
.\gradlew build dockerPush -x test -PdockerRepository="$env:DOCKER_ECR_REPO_URL"/api-server
```

## Common Issues
### kubectl - insufficient auth
##### Issue:
```
Error from server (Forbidden): deployments.extensions "api-server" is forbidden: User "arn:aws:iam::[secure]:user/build_pipeline" cannot get resource "deployments" in API group "extensions" in the namespace "default"
```
##### Solution:
- edit auth
```
kubectl edit -n kube-system configmap/aws-auth
```
- add the pipeline user to kubernetes users
```yaml
  mapUsers: |
    - userarn: arn:aws:iam::<aws_account>:user/<iam username>
      username: pipeline-user
      groups:
        - system:masters
  mapAccounts: |
    - "<aws_account>"
```
- make that user an admin
```
kubectl create clusterrolebinding pipeline-user-cluster-admin-binding --clusterrole=cluster-admin --user=pipeline-user
```
