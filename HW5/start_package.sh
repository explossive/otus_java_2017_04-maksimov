#!/usr/bin/env bash

mvn clean package
java -jar target/L5.1.jar "package" "ru.otus.main"