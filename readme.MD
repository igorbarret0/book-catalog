<h1 align="center">
  MICROSERVIÇOS DE CONVERSÃO DE MOEDA PARA CATÁLOGO DE LIVROS
</h1>

Uma aplicação modular com dois microserviços: um para cadastro de livros e outro para conversão de preços em diferentes moedas. Ao cadastrar um livro, o preço inicial é em dólares  e 
é convertido dinamicamente para a moeda desejada usando uma API externa. O código inclui um proxy para acessar a API de câmbio, atualizando as informações do livro com ambiente e nova moeda.

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [MySQL](https://dev.mysql.com/downloads/)
- [Eureka Server](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html)
- [OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)

## Práticas adotadas

- DRY
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Comunicação via API
- Desenvolvimento Independente

## Como Executar

- Clonar repositório git
```
git clone git@github.com:igorbarret0/book-catalog.git
```

- Construir o projeto:
```
./mvnw clean package
```


A API poderá ser acessada em [localhost:8100](http://localhost:8080).

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/)

- Salvar livro
```
$ POST http://localhost:8100/book-service

[
  {
    "author": "George R. R. Martin",
    "launchDate": "05/06/2013",
    "price": 26.80,
    "title": "A guerra dos tronos"
  }
]
```

- Obter preço na moeda desejada
```
$ GET http://localhost:8100/person/{id}/{currency}
$ GET http://localhost:8100/person/1/BRL

[
  {
    "author": "George R. R. Martin",
    "launchDate": "05/06/2013",
    "price": 140.16,
    "title": "A guerra dos tronos"
  }
]
```


