FROM node:14 as webbuild

COPY modules/ui/ /ui/

WORKDIR /ui/

RUN npm install
RUN npm run build


FROM openjdk:11 as build
WORKDIR /workspace/app

COPY --from=webbuild /ui/dist/ms-teams-approval-ui/ src/main/resources/public/

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src


RUN chmod +x mvnw
RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:11
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

EXPOSE 8080

ENTRYPOINT ["java","-cp","app:app/lib/*","net.fabcelhaft.msteamsapprovalexample.MsTeamsApprovalExampleApplication"]