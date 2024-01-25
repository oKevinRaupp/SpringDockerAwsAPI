# SpringDockerAwsAPI

Estudando Docker, AWS e API RESTFul!

## Projeto criado para aprender boas praticas na criação de API's.

- Migration do banco de dados.;
- Utilizado o padrão Value Object;
- Dozer;
- Content negociation em JSON, XML, YML;
- Links Hateoas;
- Swagger para documentação;
- JWT e Spring Security;
- API paginada;
- DockerFile

# Endpoints: 

## Authentication Endpoint
(Você pode gerar um novo token e atualizar no banco de dados rodando a aplicação pela primeira vez, será impresso o token no terminal)

<b>POST</b>

/auth/signin -> Autentica um usuario e retorna um token

<b>PUT</b>

/auth/refresh/{username} -> Atualiza o token de um usuario que já perdeu a autenticação e retorna um token

## People:

<b>GET</b>

/api/person/v1 -> Busca todas as pessoas

/api/person/v1/{id} -> Encontra uma pessoa passando um {id}

<b>POST</b>

/api/person/v1  -> Adiciona uma nova pessoa passando um RequestBody em JSON, XML or YML!

/api/person/v1/test/v2 -> Adiciona uma nova pessoa [v2] passando um RequestBody em JSON, XML or YML!

<b>PATCH</b>

/api/person/v1/enable/{id} -> Habilita uma pessoa passando um {id}

/api/person/v1/disable/{id} -> Desabilita uma pessoa passando um {id}

<b>PUT</b>

/api/person/v1 -> Atualiza uma pessoa, passando em JSON, XML ou YML!

<b>DELETE</b>

/api/person/v1/{id} -> Delete uma pessoa passando um {id}

## Book:

<b>GET</b>

/api/book/v1 -> Busca todos os livros

/api/book/v1/{id} -> Busca um livro passando um {id}

<b>POST</b>

/api/person/v1  -> Adiciona um novo livro passando um RequestBody em JSON, XML ou YML!

<b>PUT</b>

/api/book/v1 -> Atualiza um livro, passando em  JSON, XML or YML!

<b>DELETE</b>

/api/book/v1/{id} -> Deleta um livro passando um {id}

## Math:

<b>GET</b>

/api/math/v1/sum/{num1}/{num2} -> Soma dois números

/api/math/v1/sub/{num1}/{num2} -> Subtrai dois números

/api/math/v1/sqrt/{num1} -> Raiz quadrada do números

/api/math/v1/mult/{num1}/{num2} -> Multiplicação de dois números

/api/math/v1/div/{num1}/{num2} -> Divisção de dois números

/api/math/v1/average/{num1}/{num2} -> Média entre dois números

