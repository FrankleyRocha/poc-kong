FROM ubuntu:24.04

# Instala Apache, PHP e dependências
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
    nano \
    curl && \
    apt-get clean

# Habilita módulos necessários do Apache
RUN a2enmod php8.3 && \
    a2enmod rewrite

# Copia o virtualhost customizado
COPY apache-vhost.conf /etc/apache2/sites-available/000-default.conf

# Define o diretório de trabalho
WORKDIR /var/www/html

# Expõe a porta padrão do Apache
EXPOSE 80

# Inicia o Apache
CMD ["apachectl", "-D", "FOREGROUND"]
