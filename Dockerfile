FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./build/libs/chat-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /usr/local/chat-service/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh

