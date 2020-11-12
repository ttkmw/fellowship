FROM openjdk:8-jdk-alpine
ARG JAR_FILE=chat-0.0.1.jar
ADD output/${JAR_FILE} /usr/local/chat-service/
ADD output/run.sh run.sh
RUN chmod +x run.sh
CMD run.sh

