# Backend
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

## JWTAuth
![security schema](https://user-images.githubusercontent.com/29662093/229367713-bd92a631-64ce-47f9-8e33-3a21aeb244a6.png)

## How build and usage
1. Before building the backend, you need to build the front, see [how build frontend](https://github.com/EddDoubleD/cbrviewer/blob/master/front/README.md#build)
2. Images required for assembly:
- [liberica-openjdk-alpine](https://hub.docker.com/r/bellsoft/liberica-openjdk-alpine) 
- [alpine](https://hub.docker.com/_/alpine)

3. Build docker image
```
docker image build . -t cbrviewer:1.0.0
docker run -d --name cbrviewer -p 8080:8080 cbrviewer:1.0.0
```
