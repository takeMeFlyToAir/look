#!/bin/bash

echo "[INFO] try close $1"

for pid in `ps -ef|grep $1|grep -v grep|grep -v jetty_start|awk '{print $2}'`
do
    echo $pid
    kill -9 $pid
done

echo "[INFO] Use maven jetty-plugin run the project."

if [ ! -d "/logs/look" ]; then
  mkdir /logs/look
fi


nohup mvn jetty:run-war -Djetty.port=8097  > /logs/look/output.log 2>&1 &

echo "[INFO] project started."

