#!/usr/bin/env bash

git pull

docker run --rm \
-v "$PWD":/bot \
-w /bot \
--user $(id -u):$(id -g) \
gradle:4.10-jdk11 gradle build -x generateBotJooqSchemaSource --stacktrace

docker-compose build
docker-compose up



#cd ./docker
#echo "" > ./log.log
#java -jar ./app.jar --spring.config.location=./application.yml |\
# tail -f ./log.log |\
# jq '. | select( .clazz | contains("com.metalheart.bot")) | {m: (.time +": "+ .msg)} | .m'