#!/bin/bash

#set -x

comand="mvn test -DdatabaseUsername=student -DdatabasePassword=student"

#echo "Запуск эмуляторов мобилок ..."
#docker compose up -d
#sleep 120

echo $comand
$comand