web: java -Xms340m -Xmx340m -Xss512k -XX:+UseCompressedOops -XX:MaxMetaspaceSize=128m -Dswarm.ds.name=PostgreSQLDS -Dswarm.ds.connection.url=$JDBC_DATABASE_URL -Dswarm.ds.username=$JDBC_DATABASE_USERNAME -Dswarm.ds.password=$JDBC_DATABASE_PASSWORD -Djavamelody.datasources=java:jboss/datasources/PostgreSQLDS -Dswarm.mail.smtp.host=$MAIL_SERVER_SMTP_HOST -Dswarm.mail.smtp.port=$MAIL_SERVER_SMTP_PORT -Dswarm.mail.mail-sessions.famivac.smtp-server.username=$MAIL_SERVER_SMTP_USERNAME -Dswarm.mail.mail-sessions.famivac.smtp-server.password=$MAIL_SERVER_SMTP_PASSWORD -Dswarm.mail.mail-sessions.famivac.smtp-server.ssl=true -Dswarm.http.port=$PORT -jar gestionnaire-web/target/gestionnaire-web-*-swarm.jar
