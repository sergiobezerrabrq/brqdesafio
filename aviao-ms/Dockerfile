FROM openjdk:11
VOLUME /tmp
EXPOSE 9998
ADD ./target/aviao-ms-0.0.1-SNAPSHOT.jar aviao-ms.jar
ENTRYPOINT ["java","-jar","/aviao-ms.jar"]