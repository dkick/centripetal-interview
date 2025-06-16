#!/bin/sh

clojure -T:build uberjar :project rest-api
docker build -t otx-rest-api .
