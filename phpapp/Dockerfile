# Usa a imagem base do Ubuntu 24.04
FROM ubuntu:24.04

# Instala pacotes essenciais e PHP com Apache
RUN apt-get update && apt-get install -y \
    apache2 \
    php \
    libapache2-mod-php \
    php-cli \
    php-mbstring \
    php-xml \
    php-curl \
    php-zip \
    unzip \
    curl \
    && apt-get clean

# Habilita o módulo PHP no Apache
RUN a2enmod php8.3

# Define o diretório raiz do Apache
WORKDIR /var/www/html

# Expõe a porta do Apache
EXPOSE 80

# Inicializa o Apache
CMD ["apachectl", "-D", "FOREGROUND"]
