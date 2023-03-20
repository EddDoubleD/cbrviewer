# Конвертер валют
Конвертер валют с ЦБРФ 
## How to use
1. Авторизуйтесь 
2. Выберете валюту, произведите конвертацию
## Build and start
app:
```
docker image build . -t cbviewer:1.0.0
docker run -d --name cbviewer -p 8080:8080
```
mongo-db:
```
docker-compose up -d
```