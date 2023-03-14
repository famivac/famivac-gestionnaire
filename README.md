Famivac - Gestionnaire
---

# Tech Stack

- Java 11
- Jakarta EE 8 (https://jakarta.ee/release/8/)
- Wildfly 24 (https://docs.wildfly.org/23/Bootable_Guide.html)

# Local Development

- Start docker-compose: `docker-compose up`

Startup line

```shell
POSTGRESQL_SERVICE_HOST=localhost POSTGRESQL_PASSWORD=gestionnaire POSTGRESQL_DATABASE=gestionnaire POSTGRESQL_SERVICE_PORT=54322 POSTGRESQL_USER=gestionnaire java -Djavamelody.datasources=javamelody.datasources -jar target/gestionnaire-2.0.0-microbundle.jar --port 8080 --noHazelcast
```

# Deploy

```shell
eval "$(ssh-agent -s)"
chmod 600 deploy/deploy.key
ssh-add deploy/deploy.key
ssh-keyscan ns377672.ip-37-59-60.eu >> ~/.ssh/known_hosts
git remote add deploy dokku@ns377672.ip-37-59-60.eu:famivac-gestionnaire
git config --global push.default simple
git push deploy master:master
```

# Infrastructure - Dokku

## Configure backups

- `dokku postgres:backup-auth famivac-gestionnaire-db-prod AWS_ACCESS_KEY AWS_SECRET_KEY eu-west-3`
- cron : `0 0 * * * dokku postgres:backup famivac-gestionnaire-db-prod famivac-gestionnaire-db-prod`