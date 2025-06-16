FROM debian:sid

RUN apt-get update \
 && apt-get install -y curl openjdk-24-jdk rlwrap \
 && curl -L -O https://github.com/clojure/brew-install/releases/latest/download/linux-install.sh \
 && chmod +x linux-install.sh \
 && ./linux-install.sh \
 && rm linux-install.sh \
 && adduser interview

USER interview
WORKDIR /home/interview
COPY --chown=interview projects/rest-api/target/rest-api.jar .