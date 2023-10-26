FROM openjdk:11
VOLUME /tpm
EXPOSE  2010
ADD target/app-horario-personal-0.0.1-SNAPSHOT.jar app-horario-personal.jar
ENTRYPOINT ["java", "-jar", "/app-horario-personal.jar"]