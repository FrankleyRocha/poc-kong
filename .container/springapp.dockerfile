FROM ubuntu:24.04

# Instalar dependências
RUN apt update && apt install -y openjdk-17-jdk maven

# Criar diretório de trabalho
WORKDIR /build

# Copiar arquivos do projeto
COPY springapp .

# Compilar a aplicação
RUN mvn clean package -DskipTests

# Copia a aplicação e limpa
RUN mkdir /app
RUN cp target/*.jar /app/poc-kong-springapp.jar
RUN rm -rf /build

WORKDIR /app

# Executar a aplicação
CMD ["java", "-jar", "poc-kong-springapp.jar"]