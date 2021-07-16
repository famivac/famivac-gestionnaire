# Famivac - Gestionnaire

-

TravisCI : [![Build Status](https://travis-ci.com/famivac/famivac-gestionnaire.svg?branch=master)](https://travis-ci.com/famivac/famivac-gestionnaire)
-
Coveralls : [![Coverage Status](https://coveralls.io/repos/paoesco/famivac-gestionnaire/badge.svg?branch=master&service=github)](https://coveralls.io/github/paoesco/famivac-gestionnaire?branch=master)

## Contribute

- Java 16
- Jakarta EE 8 (https://jakarta.ee/release/8/)
- Wildfly (https://docs.wildfly.org/23/Bootable_Guide.html)
- Create postgresql database with user `gestionnaire`, password `gestionnaire`,
  database `gestionnaire`
- See Startup section for any environment variables

## Startup

Environment variables :

- POSTGRESQL_SERVICE_HOST=localhost
- POSTGRESQL_PASSWORD=gestionnaire
- POSTGRESQL_DATABASE=gestionnaire
- POSTGRESQL_SERVICE_PORT=5432
- POSTGRESQL_USER=gestionnaire

Startup line

java -Djavamelody.datasources=javamelody.datasources -Djboss.http.port=5000 -jar
gestionnaire-web/target/gestionnaire-web-2.0.0-bootable.jar

## Dokku

- `dokku postgres:backup-auth famivac-gestionnaire-db-prod AWS_ACCESS_KEY AWS_SECRET_KEY eu-west-3`
- cron : `0 0 * * * dokku postgres:backup famivac-gestionnaire-db-prod famivac-gestionnaire-db-prod`