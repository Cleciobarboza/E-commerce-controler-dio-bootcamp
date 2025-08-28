# 🛍️ Storefront - Serviço de Produtos

## Sumário
- [Visão Geral do Serviço](#visão-geral-do-serviço)
- [Endpoints da API](#endpoints-da-api)
- [Modelos de Dados](#modelos-de-dados)
- [Componentes Chave](#componentes-chave)
- [Configurações](#configurações)

---

## Visão Geral do Serviço
O **Storefront** é um microsserviço desenvolvido em **Spring Boot** responsável pela **gestão de produtos** em uma plataforma de e-commerce.  

Ele gerencia informações como disponibilidade e preço, além de se comunicar com o serviço externo **Warehouse** para buscar detalhes de produtos e processar compras.  
Também consome mensagens de uma fila RabbitMQ para **atualizar a disponibilidade** dos produtos em tempo real.

---

## Endpoints da API

| Endpoint                | Método HTTP | Descrição                                                                | Corpo da Requisição       | Corpo da Resposta                  |
|--------------------------|-------------|--------------------------------------------------------------------------|---------------------------|-------------------------------------|
| `/products`             | **POST**    | Cria um novo produto e retorna seus detalhes.                            | `ProductSaveRequest`      | `ProductSavedResponse`              |
| `/products/{id}/purchase` | **POST**  | Inicia o processo de compra de um produto específico.                     | Nenhum                   | Nenhum (**204 No Content**)         |
| `/products`             | **GET**     | Retorna a lista de todos os produtos ativos (disponíveis), ordenados por nome. | Nenhum                   | `List<ProductAvailableResponse>`    |
| `/products/{id}`        | **GET**     | Retorna informações detalhadas de um produto específico, incluindo preço. | Nenhum                   | `ProductDetailResponse`             |

---

## Modelos de Dados

### `ProductSaveRequest`
> Usado para criar um novo produto.  
**Campos**:
- `id: UUID` → Identificador único do produto.  
- `name: String` → Nome do produto.  

---

### `ProductSavedResponse`
> Retornado após um produto ser salvo com sucesso.  
**Campos**:
- `id: UUID`  
- `name: String`  
- `active: Boolean` → Indica se o produto está ativo.  

---

### `ProductAvailableResponse`
> Representa um produto listado como disponível.  
**Campos**:
- `id: UUID`  
- `name: String`  

---

### `ProductDetailResponse`
> Fornece informações detalhadas sobre um produto.  
**Campos**:
- `id: UUID`  
- `name: String`  
- `price: BigDecimal` → Preço do produto.  

---

## Componentes Chave

### `ProductService`
- Contém a lógica de negócio central para a gestão de produtos.  
- Lida com:
  - Gravação de produtos.  
  - Alteração de disponibilidade.  
  - Obtenção de detalhes.  
- Interage com:
  - **ProductRepository** → Banco de dados.  
  - **WarehouseClient** → Comunicação com serviço externo.  

---

### `ProductChangeAvailabilityConsumer`
- Listener RabbitMQ que **consome mensagens** para atualizar a disponibilidade dos produtos.  
- Escuta a fila definida em `${spring.rabbitmq.queue.product-change-availability}`.  
- Recebe mensagens do tipo `StockStatusMessage` para ativar/desativar produtos.  

---

### `WarehouseClientConfig`
- Configura o cliente HTTP para comunicação com o serviço **Warehouse**.  
- Baseado na propriedade `${warehouse.base-path}`.  

---

### `AMQPConfig` e `OpenAPIConfig`
- **AMQPConfig** → Configuração RabbitMQ + conversão de mensagens (`Jackson2JsonMessageConverter`).  
- **OpenAPIConfig** → Configuração da documentação com **OpenAPI 3.0** (Swagger UI).  

---

## Configurações
O serviço depende das seguintes propriedades:  

```properties
spring.rabbitmq.queue.product-change-availability=product-change-availability-queue
warehouse.base-path=http://localhost:8080

