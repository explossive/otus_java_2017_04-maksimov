#!/usr/bin/env bash

mvn clean package

rm -rf jvm_*.out
rm -rf ./logs/*
rm -rf ./dumps/*

if [ ! -d "./logs" ]; then
    mkdir "./logs"
fi
if [ ! -d "./dumps" ]; then
    mkdir "./dumps"
fi
for f in "-XX:+UseSerialGC" "-XX:+UseParallelGC" "-XX:+UseParallelOldGC" "-XX:+UseParNewGC -XX:+UseConcMarkSweepGC" "-XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark" "-XX:+UseG1GC"
do
    GC=$f
    DATE=$(date +"%Y%m%d_%H:%M")
    JVM_OUT="jvm_$DATE.out"
    echo "Current GC mode: "$GC
    java -agentlib:jdwp=transport=dt_socket,address=14025,server=y,suspend=n -Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m $GC  -verbose:gc -Xloggc:./logs/gc_pid_%p.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./dumps/ -XX:OnOutOfMemoryError="kill -3 %p" -jar target/L4.1.jar > $JVM_OUT
done