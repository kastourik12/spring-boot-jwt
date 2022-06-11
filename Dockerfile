FROM adoptopenjdk:latest
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY . $APP_HOME