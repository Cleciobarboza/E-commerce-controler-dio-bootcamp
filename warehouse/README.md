# 📦 Warehouse - Módulo de Estoque

## Sumário
- [Visão Geral](#visão-geral)
- [Arquitetura](#arquitetura)
- [Tecnologias](#tecnologias)
- [Configuração](#configuração)
- [Documentação da API (Swagger/OpenAPI)](#documentação-da-api-swaggeropenapi)
- [Execução da Aplicação](#execução-da-aplicação)

---

## Visão Geral
O **Warehouse** é o projeto desenvolvido no bootcamp que representa o **módulo de estoque de um e-commerce**.  
A aplicação é responsável por gerenciar produtos e seus respectivos estoques.  
Ela se integra com outros microsserviços, como o **storefront (vitrine)**, garantindo que as informações de produtos estejam sempre sincronizadas.

---

## Arquitetura
A aplicação segue o padrão **microsserviços** e utiliza **Spring Boot** para construção de APIs RESTful.

- **API RESTful** → Gestão de produtos e estoque.  
- **JPA/Hibernate** → Persistência de dados.  
- **RabbitMQ** → Comunicação assíncrona com outros serviços, garantindo consistência dos dados.  
- **MapStruct** → Conversão entre DTOs e entidades (reduz verbosidade).  
- **Swagger/OpenAPI** → Documentação interativa da API.  

---

## Tecnologias
- **Java 21**  
- **Spring Boot 3.5.5**  
- **Gradle**  
- **Lombok**  
- **MapStruct 1.6.3**  
- **springdoc-openapi-starter-webmvc-ui 2.8.4**  
- **H2 Database (em memória)**  
- **RabbitMQ**  

---

## Configuração
As configurações estão em `src/main/resources/application.properties`.

### RabbitMQ
```properties
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

spring.rabbitmq.queue.product-change-availability=product-change-availability-queue
spring.rabbitmq.exchange.product-change-availability=product-change-availability-exchange
spring.rabbitmq.routing-key.product-change-availability=product-change-availability-routing-key
```

### API do Storefront
```properties
warehouse.base-path=http://localhost:8080
```

### Banco de Dados H2
```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:warehouse-db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```

---

## Documentação da API (Swagger/OpenAPI)
Após executar a aplicação, a documentação interativa estará disponível em:  
👉 [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

Formato JSON da API:  
👉 [http://localhost:8081/v3/api-docs](http://localhost:8081/v3/api-docs)

---

## Execução da Aplicação

### Pré-requisitos
- **Java 21**  
- **Docker Desktop** (para rodar o RabbitMQ)  

### 1. Iniciar o RabbitMQ
```bash
docker run -it --rm --name rabbitmq     -p 5672:5672 -p 15672:15672     rabbitmq:3.13-management
```

### 2. Compilar e Rodar a Aplicação
```bash
./gradlew bootRun
```

A aplicação estará disponível em:  
👉 [http://localhost:8081](http://localhost:8081)  

---