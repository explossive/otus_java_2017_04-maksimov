#!/usr/bin/env bash

mvn clean package
cp ./target/app.war ~/apps/jetty-distribution-9.4.6.v20170531/webapps/