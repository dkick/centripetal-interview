#!/bin/sh

set -e

poly test
clojure -T:build uberjar :project rest-api
docker build -t otx-rest-api .
