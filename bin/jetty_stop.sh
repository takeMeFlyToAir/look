#!/bin/bash

echo "[INFO] try close $1"

for pid in `ps -ef|grep $1|grep -v grep|grep -v jetty_stop|awk '{print $2}'`
do
    echo $pid
    kill -9 $pid
done