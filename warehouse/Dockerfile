# Imagem Gradle com JDK 21
FROM gradle:8.7-jdk21

# Instala bash (caso necessário)
RUN apt-get update && apt-get install -y bash --no-install-recommends && rm -rf /var/lib/apt/lists/*

# Define diretório de instalação
ENV INSTALL_PATH=/storefront
RUN mkdir -p $INSTALL_PATH
WORKDIR $INSTALL_PATH

# Copia os arquivos do projeto
COPY . .

# Comando para rodar a aplicação em dev
CMD ["gradle", "bootRun"]
