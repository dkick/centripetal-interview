# centripetal-interview

[Clojure Homework Question](./doc/Centripetal-ClojureCodingQuestion.pdf)

## Bonus Points: Use polylith

Polylith is something that I meant to get around to using some day but
I hadn't made the time yet. This seems like a good opportunity.

> If you read any articles or take any tips from any site, tutorial,
> or blog, **cite them**. Put them in a Readme.md or references.txt file
> to show what research you did."

Polylith has some 
[great documentation](https://polylith.gitbook.io/polylith). 
And some excellent, very relevant examples:

* https://github.com/furkan3ayraktar/clojure-polylith-realworld-example-app
* https://github.com/seancorfield/usermanager-example

## Indicators of Compromise (IOC)s and Indicators and IDs

See 
[ai.centripetal.alien-vault-otx-json.interface](./components/alien-vault-otx-json/src/ai/centripetal/alien_vault_otx_json/interface.clj)
for a discussion of implementation choices related to these aspects

## Usage

```
dkick@Damiens-MacBook-Pro centripetal-interview % ./build.sh 
Projects to run tests from: rest-api

SLF4J(I): Connected with provider of type [org.slf4j.nop.NOPServiceProvider]
Running tests for the rest-api project using test runner: Polylith built-in clojure.test runner...
Running tests from the rest-api project, including 2 bricks: alien-vault-otx-json, rest-api
Reflection warning, meander/util/epsilon.cljc:758:24 - reference to field val can't be resolved.

Testing ai.centripetal.alien-vault-otx-json.core-test

Ran 8 tests containing 20 assertions.
0 failures, 0 errors.

Test results: 20 passes, 0 failures, 0 errors.

Testing ai.centripetal.alien-vault-otx-json.interface-test

Ran 3 tests containing 3 assertions.
0 failures, 0 errors.

Test results: 3 passes, 0 failures, 0 errors.
SLF4J(W): No SLF4J providers were found.
SLF4J(W): Defaulting to no-operation (NOP) logger implementation
SLF4J(W): See https://www.slf4j.org/codes.html#noProviders for further details.
WARNING: update-keys already refers to: #'clojure.core/update-keys in namespace: io.aviso.exception, being replaced by: #'io.aviso.exception/update-keys
WARNING: abs already refers to: #'clojure.core/abs in namespace: taoensso.encore, being replaced by: #'taoensso.encore/abs

Testing ai.centripetal.rest-api.core-test

Ran 1 tests containing 1 assertions.
0 failures, 0 errors.

Test results: 1 passes, 0 failures, 0 errors.

Execution time: 3 seconds

Compiling ai.centripetal.rest-api.core...
Reflection warning, meander/util/epsilon.cljc:758:24 - reference to field val can't be resolved.
SLF4J(W): No SLF4J providers were found.
SLF4J(W): Defaulting to no-operation (NOP) logger implementation
SLF4J(W): See https://www.slf4j.org/codes.html#noProviders for further details.
WARNING: update-keys already refers to: #'clojure.core/update-keys in namespace: io.aviso.exception, being replaced by: #'io.aviso.exception/update-keys
WARNING: abs already refers to: #'clojure.core/abs in namespace: taoensso.encore, being replaced by: #'taoensso.encore/abs
Building uberjar target/rest-api.jar...
Uberjar is built.
[+] Building 1.4s (9/9) FINISHED                                                                                                    docker:desktop-linux
 => [internal] load build definition from Dockerfile                                                                                                0.0s
 => => transferring dockerfile: 435B                                                                                                                0.0s
 => [internal] load metadata for docker.io/library/debian:sid                                                                                       0.8s
 => [internal] load .dockerignore                                                                                                                   0.0s
 => => transferring context: 2B                                                                                                                     0.0s
 => [1/4] FROM docker.io/library/debian:sid@sha256:eb835ce18611a0b66cf4b9c6a239752d8498bb528585c01a7b7141aed1097ea7                                 0.0s
 => => resolve docker.io/library/debian:sid@sha256:eb835ce18611a0b66cf4b9c6a239752d8498bb528585c01a7b7141aed1097ea7                                 0.0s
 => [internal] load build context                                                                                                                   0.1s
 => => transferring context: 20.94MB                                                                                                                0.1s
 => CACHED [2/4] RUN apt-get update  && apt-get install -y curl openjdk-24-jdk rlwrap  && curl -L -O https://github.com/clojure/brew-install/relea  0.0s
 => CACHED [3/4] WORKDIR /home/interview                                                                                                            0.0s
 => [4/4] COPY --chown=interview projects/rest-api/target/rest-api.jar .                                                                            0.0s
 => exporting to image                                                                                                                              0.5s
 => => exporting layers                                                                                                                             0.4s
 => => exporting manifest sha256:c2876de5e951705362634b19fc20948eb55931442c89be766072958053d60ee4                                                   0.0s
 => => exporting config sha256:7fc0404970aca8571a849c063eca1035320a5864c1d52ed65a72601d7fe77119                                                     0.0s
 => => exporting attestation manifest sha256:53ac5373a120df3df1a0e7df220d6376f4e9de87be8a18ba4219eb3b5da7c941                                       0.0s
 => => exporting manifest list sha256:8f375e2bc595ebc3027f7828cd43072a944f23cac1949e5fbdc6f87bda84c0c5                                              0.0s
 => => naming to docker.io/library/otx-rest-api:latest                                                                                              0.0s
 => => unpacking to docker.io/library/otx-rest-api:latest                                                                                           0.1s
dkick@Damiens-MacBook-Pro centripetal-interview % java -jar projects/rest-api/target/rest-api.jar 
Starting up on port 8080
SLF4J(W): No SLF4J providers were found.
SLF4J(W): Defaulting to no-operation (NOP) logger implementation
SLF4J(W): See https://www.slf4j.org/codes.html#noProviders for further details.


```
