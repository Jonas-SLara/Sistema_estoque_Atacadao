# Base com JDK 21
FROM eclipse-temurin:21-alpine

# Variáveis de ambiente
ENV WILDFLY_VERSION 30.0.0.Final
ENV WILDFLY_HOME /opt/wildfly

# Instalar dependências: curl, unzip, bash
RUN apk update && apk add --no-cache curl unzip bash

# Baixar e instalar WildFly
RUN curl -L https://github.com/wildfly/wildfly/releases/download/${WILDFLY_VERSION}/wildfly-${WILDFLY_VERSION}.zip -o wildfly.zip && \
    unzip wildfly.zip -d /opt && \
    mv /opt/wildfly-${WILDFLY_VERSION} $WILDFLY_HOME && \
    rm wildfly.zip

# Copiar o .war para o WildFly apenas se já tiver o .war na raiz do projeto(fase de produção)
# COPY teste.war $WILDFLY_HOME/standalone/deployments/

# Expõe a porta do WildFly
EXPOSE 8080

# Comando para iniciar o servidor
CMD ["sh", "-c", "$WILDFLY_HOME/bin/standalone.sh -b 0.0.0.0"]
