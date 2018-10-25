## Documentação

Documentação de apoio ao estudo e construção da API do FakeBank.

* [O FakeBank](#fakebank)
* [Etapa 1 - Banco de Dados](#etapa-1-banco-de-dados)
* [Etapa 2 - Tabela: Agência](#etapa-2-tabela-agencia)

## Fakebank

O FakeBank é um projeto de Estudos, em que se objetiva aprender a criar estruturas de serviços REST usando tecnologias como Java, Hibernate, JUnit, Spring Boot e Spring Data.

Também é nosso objeto aprender técnicas de programação orientada a objetos, como DDD (Domain Driven Design), alguns Design Patterns, SOLID e construção de testes unitários.

## Etapa 1 - Banco de Dados

O Banco de Dados usado para suportar as tabelas que utilizaremos será o **Microsoft SQL Server**.

Optamos por criar o banco de dados antes da Modelagem Conceitual, para expor problemas reais de bancos de dados com algumas modelagens não muito aderentes às práticas de normalização.

Sendo assim, não teremos Migrations de estrururas definidas no Framework de ORM para o Banco de Dados diretamente.

O nome dado para o Banco de Dados será DB_FAKE_BANK.

Como o objetivo de estudo principal não é o SQL Server, não serão observadas grandes informações acerca da criação do Banco de Dados, como Filegroups, Collations, etc...

```sql
USE MASTER
GO

CREATE DATABASE DB_FAKE_BANK
GO

USE DB_FAKE_BANK
GO
```

## Etapa 2 - Tabela AGENCIA

A primeira tabela de criaremos será a tabela ```DBO.AGENCIA```

Nesta tabela, teremos um campo AUTO-NUMERÁVEL conmo Chave Primária da Relação.

```sql
USE DB_FAKE_BANK
GO

CREATE TABLE DBO.AGENCIA
(
    CD_AGENCIA   INT         IDENTITY(1,1) NOT NULL,
    NR_AGENCIA   INT                       NOT NULL,
    NM_AGENCIA   VARCHAR(80)               NOT NULL,
    NR_CNPJ      CHAR(14)                  NOT NULL
)
GO

ALTER TABLE DBO.AGENCIA
ADD CONSTRAINT PK_AGENCIA
PRIMARY KEY (CD_AGENCIA)
GO

ALTER TABLE DBO.AGENCIA
ADD CONSTRAINT UK_AGENCIA_CNPJ
UNIQUE (NR_CNPJ)
GO
```
