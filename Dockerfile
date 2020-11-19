FROM openjdk:8-jdk-alpine
ARG JAR_FILE=chat-0.0.1.jar
ADD build/libs/${JAR_FILE} /usr/local/chat-service/
ADD ./run.sh /run.sh
RUN chmod +x /run.sh
CMD /run.sh

