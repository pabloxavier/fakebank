## Documentação

Documentação de apoio ao estudo e construção da API do FakeBank.

* [O FakeBank](#fakebank)
* [Banco de Dados](#banco-de-dados)

## Fakebank

O FakeBank é um projeto de Estudos, em que se objetiva aprender a criar estruturas de serviços REST usando tecnologias como Java, Hibernate, JUnit, Spring Boot e Spring Data.

Também é nosso objeto aprender técnicas de programação orientada a objetos, como DDD (Domain Driven Design), alguns Design Patterns, SOLID e construção de testes unitários.

## Banco de Dados

O Banco de Dados usado para suportar as tabelas que utilizaremos será o Microsoft SQL Server.

Optamos por criar o banco de dados antes d Modelagem Conceitual, para expor problemas reais de bancos de dados com algumas modelagens não muito aderentes às práticas de normalização.

Sendo assim, não teremos Migrations de estrururas definidas no Framework de ORM para o Banco de Dados diretamente.


```sql
SELECT * FROM TB_CLIENTE
```
