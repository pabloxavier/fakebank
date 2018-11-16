```
gradle clean cleaneclipse build eclipse --refresh-dependencies
```

## Documentação

Documentação de apoio ao estudo e construção da API do FakeBank.

* [O FakeBank](#fakebank)
* [Principais Comandos Git](#principais-comandos-git)
* [Etapa 1 Criando o Banco de Dados](#etapa-1-criando-o-banco-de-dados)
    - [Criando a Tabela DBO.AGENCIA](#criando-a-tabela-agencia)
    - [Criando a Tabela DBO.PESSOA](#criando-a-tabela-pessoa)
    - [Criando a Tabela DBO.CLIENTE](#criando-a-tabela-cliente)
    - [Criando a Tabela DBO.CLIENTE_TELEFONE](#criando-a-tabela-cliente_telefone)
    - [Criando a Tabela DBO.GERENTE](#criando-a-tabela-gerente)
    - [Criando a Tabela DBO.GERENTE_AGENCIA](#criando-a-tabela-gerente_agencia)
    - [Criando a Tabela DBO.DOMINIO](#criando-a-tabela-dominio)
    - [Criando a Tabela DBO.CONTA](#criando-a-tabela-conta)
    - [Criando a Tabela DBO.CONTA_ENCERRADA](#criando-a-tabela-conta_encerrada)
    - [Criando a Tabela DBO.CLIENTE_CONTA](#criando-a-tabela-cliente_conta)
    - [Criando a Tabela DBO.TIPO_MOVIMENTACAO](#criando-a-tabela-tipo_movimentacao)
    - [Criando a Tabela DBO.MOVIMENTACAO](#criando-a-tabela-movimentacao)
* [Etapa 2 Configurando o Gradle](#etapa-2-configurando-o-gradle)
* [Etapa 3 Criando o Projeto no Eclipse](#etapa-3-criando-o-projeto-no-eclipse)
* [Etapa 4 Resolvendo as Dependências com Gradle](#etapa-4-resolvendo-as-dependências-com-gradle)
* [Etapa 5 Estabelecendo os Endpoints](#etapa-5-estabelecendo-os-endpoints)
    - [Endpoints de Agência](#endpoints-de-agência)
    - [Endpoints de Cliente Pessoa Física](#endpoints-de-cliente-pessoa-física)
    - [Endpoints de Cliente Pessoa Jurídica](#endpoints-de-cliente-pessoa-jurídica)
    - [Endpoints de Gerente](#endpoints-de-gerente)
    - [Endpoints de Tipo de Conta](#endpoints-de-tipo-de-conta)
    - [Endpoints de Situação de Conta](#endpoints-de-situação-de-conta)
    - [Endpoints de Motivo de Encerramento](#endpoints-de-motivo-de-encerramento)
    - [Endpoints de Conta](#endpoints-de-conta)
    - [Endpoints de Tipo de Movimentação](#endpoints-de-tipo-de-movimentação)
    - [Endpoints de Movimentação](#endpoints-de-movimentação)

## Fakebank

O FakeBank é um projeto de Estudos, em que se objetiva aprender a criar estruturas de serviços REST usando tecnologias como Java, Hibernate, JUnit, Spring Boot e Spring Data.

Também é nosso objeto aprender técnicas de programação orientada a objetos, como DDD (Domain Driven Design), alguns Design Patterns, SOLID e construção de testes unitários.

## Principais Comandos Git

Antes de qualquer coisa, vamos criar um diretório para ser o nosso Repositório Local.

`cd\`
`mkdir GitHubRepositorios`
`cd GitHubRepositorios`

Para fazer um CLONE do Repositório REMOTO para o Repositório LOCAL.

`git clone https://github.com/pabloxavier/fakebank.git`

Acesse o diretório do projeto.

`cd fakebank`

Para criar um novo Branch e já posicionar o Git nesta Branch Local.

`git checkout -b feature-estrutura-inicial`

Para adicionar TODOS os arquivos novos ao branch.

`git add .`

Para efetivar as alterações na branch local.

`git commit -a -m "Primeira alteração de código-fonte"`
OU
`git commit -am "Primeira alteração de código-fonte"`

Para promover as alterações de branch local para um branch remoto.

`git push origin feature-estrutura-inicial`

Visualizando o status atual do repositório LOCAL.

`git status`

Visualizando as últimas iterações no repositório (na branch posicionada).

`git log`

Para atualizar o seu branch local com a master. (PROCESSO 1)

Provavelmente você já estará posicionado no seu branch.

Se por acaso não estiver.

`git checkout SEU_BRANCH`

Já estando posicionado.

`git rebase origin/master`

Para atualizar o seu branch local com a master. (PROCESSO 2)

Provavelmente você já estará posicionado no seu branch.

Se por acaso não estiver.

`git checkout SEU_BRANCH`

Já estando posicionado.

`git merge origin/master`

`git push origin SEU_BRANCH`

Para atualizar o seu branch local com a master. (PROCESSO 3)

Provavelmente você já estará posicionado no seu branch.

Se por acaso não estiver.

`git checkout SEU_BRANCH`

Já estando posicionado.

`git pull origin master`

`git push origin SEU_BRANCH`

**Importante: Havendo conflitos, será necessário resolvê-los, e então realizar um commit para o seu branch.

Reposicionar na master ou em outra branch.

`git checkout master`

Para zerar possíveis problemas de cache no git.

```
git rm --cached -r .
git reset --hard
git gc
```


## Etapa 1 Criando o Banco de Dados

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

### Criando a Tabela AGENCIA

A primeira tabela de criaremos será a tabela ```DBO.AGENCIA```

Nesta tabela, teremos um campo AUTO-NUMERÁVEL conmo Chave Primária da Relação.

A agência terá basicamente, um Número, um Nome e um CNPJ. Todos obrigatórios.

O CNPJ não poderá estar duplicado na base.

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

### Criando a Tabela PESSOA

Nesta tabela ```DBO.PESSOA```, teremos um campo AUTO-NUMERÁVEL conmo Chave Primária da Relação.

A tabela PESSOA é um tipo de modelagem normalmente encontrado em abstrações de sistemas de cadastro de Clientes, Fornecedores, etc...

A modelagem é propositamente uma provocação.

O campo NR_DOCUMENTO será multifacetado. Ora será um CPF, ora será um CNPJ.

O campo TP_PESSOA determinará se temos uma PESSOA FÍSICA ou uma PESSOA JURÍDICA.

```sql
USE DB_FAKE_BANK
GO

CREATE TABLE DBO.PESSOA
(
    CD_PESSOA     INT         IDENTITY(1,1) NOT NULL,
    NR_DOCUMENTO  VARCHAR(20)               NOT NULL,
    TP_PESSOA     CHAR(1)                   NOT NULL,
    NM_PESSOA     VARCHAR(80)               NOT NULL,
    DT_NASCIMENTO DATE                      NULL,
    DT_ABERTURA   DATE                      NULL
)
GO

ALTER TABLE DBO.PESSOA
ADD CONSTRAINT PK_PESSOA
PRIMARY KEY (CD_PESSOA)
GO

ALTER TABLE DBO.PESSOA
ADD CONSTRAINT CK_PESSOA_TIPO
CHECK (TP_PESSOA IN ('F', 'J'))
GO

ALTER TABLE DBO.PESSOA
ADD CONSTRAINT UK_PESSOA_DOCUMENTO
UNIQUE (TP_PESSOA, NR_DOCUMENTO)
GO
```

### Criando a Tabela CLIENTE

Nesta tabela ```DBO.CLIENTE```, teremos um relacionamento com a tabela PESSOA.

Nela estarão informações complementares de CLIENTE que não estejam na tabela PESSOA.

```sql
USE DB_FAKE_BANK
GO

CREATE TABLE DBO.CLIENTE
(
    CD_CLIENTE           INT          IDENTITY(1,1) NOT NULL,
    CD_PESSOA            INT                        NOT NULL,
    IS_ATIVO             BIT                        NOT NULL,
    DS_ENDERECO_COMPLETO VARCHAR(200)               NULL
)
GO

ALTER TABLE DBO.CLIENTE
ADD CONSTRAINT PK_CLIENTE
PRIMARY KEY (CD_CLIENTE)
GO

ALTER TABLE DBO.CLIENTE
ADD CONSTRAINT FK_CLIENTE_PESSOA
FOREIGN KEY (CD_PESSOA)
REFERENCES DBO.PESSOA (CD_PESSOA)
GO
```

### Criando a Tabela CLIENTE_TELEFONE

Nesta tabela ```DBO.CLIENTE_TELEFONE```, teremos cadastrados todos os telefones de um cliente.

O campo ```CD_TELEFONE``` será incrementado para CADA CLIENTE.

```sql
USE DB_FAKE_BANK
GO

CREATE TABLE DBO.CLIENTE_TELEFONE
(
    CD_CLIENTE       INT       NOT NULL,
    CD_TELEFONE      SMALLINT  NOT NULL,
    NR_PREFIXO       SMALLINT  NOT NULL,
    NR_TELEFONE      INT       NOT NULL,
    CD_TIPO_TELEFONE CHAR(1)   NOT NULL
)
GO

ALTER TABLE DBO.CLIENTE_TELEFONE
ADD CONSTRAINT PK_CLIENTE_TELEFONE
PRIMARY KEY (CD_CLIENTE, CD_TELEFONE)
GO

ALTER TABLE DBO.CLIENTE_TELEFONE
ADD CONSTRAINT FK_CLIENTE_TELEFONE_CLIENTE
FOREIGN KEY (CD_CLIENTE)
REFERENCES DBO.CLIENTE (CD_CLIENTE)
GO

ALTER TABLE DBO.CLIENTE_TELEFONE
ADD CONSTRAINT CK_CLIENTE_TELEFONE_TIPO
CHECK (CD_TIPO_TELEFONE IN ('R', 'C', 'M'))
GO
```

### Criando a Tabela GERENTE

Nesta tabela ```DBO.PESSOA```, teremos um relacionamento com a tabela PESSOA.

Nela estarão informações complementares de GERENTE que não estejam na tabela PESSOA.

```sql
USE DB_FAKE_BANK
GO

CREATE TABLE DBO.GERENTE
(
    CD_GERENTE   INT   IDENTITY(1,1) NOT NULL,
    CD_PESSOA    INT                 NOT NULL,
    IS_ATIVO     CHAR(1)             NOT NULL
)
GO

ALTER TABLE DBO.GERENTE
ADD CONSTRAINT PK_GERENTE
PRIMARY KEY (CD_GERENTE)
GO

ALTER TABLE DBO.GERENTE
ADD CONSTRAINT FK_GERENTE_PESSOA
FOREIGN KEY (CD_PESSOA)
REFERENCES DBO.PESSOA (CD_PESSOA)
GO

ALTER TABLE DBO.GERENTE
ADD CONSTRAINT CK_GERENTE_STATUS
CHECK (IS_ATIVO IN ('S', 'N'))
GO
```

### Criando a Tabela GERENTE_AGENCIA

Nesta tabela ```DBO.GERENTE_AGENCIA```, teremos um relacionamento de N-para-N entre Gerente e Agências.

```sql
USE DB_FAKE_BANK
GO

CREATE TABLE DBO.GERENTE_AGENCIA
(
    CD_GERENTE   INT   NOT NULL,
    CD_AGENCIA   INT   NOT NULL
)
GO

ALTER TABLE DBO.GERENTE_AGENCIA
ADD CONSTRAINT PK_GERENTE_AGENCIA
PRIMARY KEY (CD_GERENTE, CD_AGENCIA)
GO

ALTER TABLE DBO.GERENTE_AGENCIA
ADD CONSTRAINT FK_GERENTE_AGENCIA_GERENTE
FOREIGN KEY (CD_GERENTE)
REFERENCES DBO.GERENTE (CD_GERENTE)
GO

ALTER TABLE DBO.GERENTE_AGENCIA
ADD CONSTRAINT FK_GERENTE_AGENCIA_AGENCIA
FOREIGN KEY (CD_AGENCIA)
REFERENCES DBO.AGENCIA (CD_AGENCIA)
GO
```

### Criando a Tabela DOMINIO

Nesta tabela ```DBO.DOMINIO```, teremos uma modelagem típica em muitos sistemas.

Um TABELÃO com vários dados de domínios, algo como, Código e Descrição.

No modelo inicial, teremos Tipo de Conta, Situação de Conta e Motivo de Encerramento de Conta.

```sql
USE DB_FAKE_BANK
GO

CREATE TABLE DBO.DOMINIO
(
    CD_DOMINIO    INT         IDENTITY(1,1) NOT NULL,   
    TP_DOMINIO    VARCHAR(10)               NOT NULL,
    VL_DOMINIO    VARCHAR(10)               NOT NULL,
    DS_DOMINIO    VARCHAR(200)              NOT NULL
)
GO

ALTER TABLE DBO.DOMINIO
ADD CONSTRAINT PK_DOMINIO
PRIMARY KEY (CD_DOMINIO)
GO

ALTER TABLE DBO.DOMINIO
ADD CONSTRAINT UK_DOMINIO_VALOR
UNIQUE (TP_DOMINIO, VL_DOMINIO)
GO
```

### Criando a Tabela CONTA

Nesta tabela ```DBO.CONTA```, teremos registradas as Contas Correntes, Contas Poupança e Contas Salário.

```sql
USE DB_FAKE_BANK
GO

CREATE TABLE DBO.CONTA
(
    CD_CONTA                  VARCHAR(10)    NOT NULL,
    CD_CLIENTE_PRINCIPAL      INT            NOT NULL,
    DT_ABERTURA               DATE           NOT NULL,
    TP_CONTA                  INT            NOT NULL,
    VL_SALDO                  NUMERIC(10,2)  NOT NULL,
    NR_CNPJ_CONTRATO_SALARIO  CHAR(14)       NULL,
    DD_ANIVERSARIO_POUPANCA   TINYINT        NULL,
    CD_GERENTE                INT            NOT NULL,
    CD_SITUACAO_CONTA         INT            NOT NULL
)
GO

ALTER TABLE DBO.CONTA
ADD CONSTRAINT PK_CONTA
PRIMARY KEY (CD_CONTA)
GO

ALTER TABLE DBO.CONTA
ADD CONSTRAINT FK_CONTA_CLIENTE_PRINCIPAL
FOREIGN KEY (CD_CLIENTE_PRINCIPAL)
REFERENCES DBO.CLIENTE (CD_CLIENTE)
GO

ALTER TABLE DBO.CONTA
ADD CONSTRAINT FK_CONTA_TIPO_CONTA
FOREIGN KEY (TP_CONTA)
REFERENCES DBO.DOMINIO (CD_DOMINIO)
GO

ALTER TABLE DBO.CONTA
ADD CONSTRAINT FK_CONTA_GERENTE
FOREIGN KEY (CD_GERENTE)
REFERENCES DBO.GERENTE (CD_GERENTE)
GO

ALTER TABLE DBO.CONTA
ADD CONSTRAINT FK_CONTA_SITUACAO_CONTA
FOREIGN KEY (CD_SITUACAO_CONTA)
REFERENCES DBO.DOMINIO (CD_DOMINIO)
GO
```

### Criando a Tabela CONTA_ENCERRADA

Nesta tabela ```DBO.CONTA_ENCERRADA```, serão registradas as Contas que foram encerradas, com informações deste encerramento.

```sql
USE DB_FAKE_BANK
GO

CREATE TABLE DBO.CONTA_ENCERRADA
(
    CD_CONTA                VARCHAR(10)   NOT NULL,
    DT_ENCERRAMENTO         DATETIME2     NOT NULL,
    CD_MOTIVO_ENCERRAMENTO  INT           NOT NULL,
    CD_CLIENTE_SOLICITANTE  INT           NOT NULL,
    DS_OBSERVACOES          VARCHAR(2000) NULL
)
GO

ALTER TABLE DBO.CONTA_ENCERRADA
ADD CONSTRAINT PK_CONTA_ENCERRADA
PRIMARY KEY (CD_CONTA)
GO

ALTER TABLE DBO.CONTA_ENCERRADA
ADD CONSTRAINT FK_CONTA_ENCERRADA_CONTA
FOREIGN KEY (CD_CONTA)
REFERENCES DBO.CONTA (CD_CONTA)
GO

ALTER TABLE DBO.CONTA_ENCERRADA
ADD CONSTRAINT FK_CONTA_ENCERRADA_MOTIVO_ENCERRAMENTO
FOREIGN KEY (CD_MOTIVO_ENCERRAMENTO)
REFERENCES DBO.DOMINIO (CD_DOMINIO)
GO

ALTER TABLE DBO.CONTA_ENCERRADA
ADD CONSTRAINT FK_CONTA_ENCERRADA_CLIENTE_SOLICITANTE
FOREIGN KEY (CD_CLIENTE_SOLICITANTE)
REFERENCES DBO.CLIENTE (CD_CLIENTE)
GO
```

### Criando a Tabela CLIENTE_CONTA

Nesta tabela ```DBO.CLIENTE_CONTA```, teremos um relacionamento de N-para-N entre Cliente e Conta.

Com algumas informações adicionais.

```sql
USE DB_FAKE_BANK
GO

CREATE TABLE DBO.CLIENTE_CONTA
(
    CD_CLIENTE   INT         NOT NULL,
    CD_CONTA     VARCHAR(10) NOT NULL,
    DT_REGISTRO  DATE        NOT NULL,
    IS_TITULAR   BIT         NOT NULL
)
GO

ALTER TABLE DBO.CLIENTE_CONTA
ADD CONSTRAINT PK_CLIENTE_CONTA
PRIMARY KEY (CD_CLIENTE, CD_CONTA)
GO

ALTER TABLE DBO.CLIENTE_CONTA
ADD CONSTRAINT FK_CLIENTE_CONTA_CLIENTE
FOREIGN KEY (CD_CLIENTE)
REFERENCES DBO.CLIENTE (CD_CLIENTE)
GO

ALTER TABLE DBO.CLIENTE_CONTA
ADD CONSTRAINT FK_CLIENTE_CONTA_CONTA
FOREIGN KEY (CD_CONTA)
REFERENCES DBO.CONTA (CD_CONTA)
GO
```

### Criando a Tabela TIPO_MOVIMENTACAO

Nesta tabela ```DBO.TIPO_MOVIMENTACAO```, teremos um cadastro básico de Tipos de Movimentação, como Pagamento, Saque, Transferência, etc...

```sql
USE DB_FAKE_BANK
GO

CREATE TABLE DBO.TIPO_MOVIMENTACAO
(
    CD_TIPO_MOVIMENTACAO   SMALLINT    NOT NULL,
    DS_TIPO_MOVIMENTACAO   VARCHAR(80) NOT NULL,
    TP_OPERACAO            CHAR(1)     NOT NULL
)
GO

ALTER TABLE DBO.TIPO_MOVIMENTACAO
ADD CONSTRAINT PK_TIPO_MOVIMENTACAO
PRIMARY KEY (CD_TIPO_MOVIMENTACAO)
GO

ALTER TABLE DBO.TIPO_MOVIMENTACAO
ADD CONSTRAINT CK_TIPO_MOVIMENTACAO_OPERACAO
CHECK (TP_OPERACAO IN ('C', 'D'))
GO
```

### Criando a Tabela MOVIMENTACAO

Nesta tabela ```DBO.MOVIMENTACAO```, teremos registradas todas as movimentações das Contas.

```sql
USE DB_FAKE_BANK
GO

CREATE TABLE DBO.MOVIMENTACAO
(
    CD_MOVIMENTACAO       INT          IDENTITY(1,1) NOT NULL,
    CD_CONTA              VARCHAR(10)                NOT NULL,
    DT_MOVIMENTACAO       DATETIME2                  NOT NULL,
    VL_MOVIMENTACAO       NUMERIC(10,2)              NOT NULL,
    CD_TIPO_MOVIMENTACAO  SMALLINT                   NOT NULL,
    VL_SALDO_ANTERIOR     NUMERIC(10,2)              NOT NULL,
    VL_SALDO_ATUAL        NUMERIC(10,2)              NOT NULL
)
GO

ALTER TABLE DBO.MOVIMENTACAO
ADD CONSTRAINT PK_MOVIMENTACAO
PRIMARY KEY (CD_MOVIMENTACAO)
GO

ALTER TABLE DBO.MOVIMENTACAO
ADD CONSTRAINT FK_MOVIMENTACAO_CONTA
FOREIGN KEY (CD_CONTA)
REFERENCES DBO.CONTA (CD_CONTA)
GO

ALTER TABLE DBO.MOVIMENTACAO
ADD CONSTRAINT FK_MOVIMENTACAO_TIPO_MOVIMENTACAO
FOREIGN KEY (CD_TIPO_MOVIMENTACAO)
REFERENCES DBO.TIPO_MOVIMENTACAO (CD_TIPO_MOVIMENTACAO)
GO
```

## Etapa 2 Configurando o Gradle

Seção destinada a descrever como configurar o Gradle.

## Etapa 3 Criando o Projeto no Eclipse

Seção destinada a descrever como criar o projeto no eclipse.

## Etapa 4 Resolvendo as Dependências com Gradle

Seção destinada a descrever como adicionar as dependências no build.gradle.

## Etapa 5 Estabelecendo os Endpoints

### Convenções de REST

Para requisições GET

| Status Codes |
| ------------- |
| 200 - OK |
| 404 - Not Found |
| 500 - Internal Server Error |

Para requisições POST

| Status Codes |
| ------------- |
| 201 - Created |
| 400 - Bad Request |
| 500 - Internal Server Error |

Para requisições PUT

| Status Codes |
| ------------- |
| 200 - Ok |
| 400 - Bad Request |
| 404 - Not Found |
| 500 - Internal Server Error |

Para requisições DELETE

| Status Codes |
| ------------- |
| 200 - Ok |
| 400 - Bad Request |
| 404 - Not Found |
| 500 - Internal Server Error |


### Endpoints de Agência

Definição dos endpoints a serem construídos para tratamentos de agências.

#### Listagem

Listar todas as agências cadastradas.

Resource: `api.fakebank.com.br/agencias`

Method: `GET`

Response Body: 
```javascript
[
    {
        codigo: 123,
        numero: 1234,
        nome: "Caxias",
        cnpj: "12.345.678-0001/11"
    },
    {
        codigo: 456,
        numero: 4567,
        nome: "Gramado",
        cnpj: "12.345.678-0002/22"
    }
]
```

#### Consulta

Consultar uma única agência pelo Código.

Resource: `api.fakebank.com.br/agencias/{codigo}`

Method: `GET`

Response Body: 
```javascript
{
    codigo: 123,
    numero: 1234,
    nome: "Caxias",
    cnpj: "12.345.678-0001/11"
}
```

#### Inclusão

Incluir uma nova agência.

Ao incluir uma agência, retornar uma variável no Header chamada **location**

Por exemplo, considerando que fora inserida uma nova agência de código 555:

**location = api.fakebank.com.br/agencias/555**


Resource: `api.fakebank.com.br/agencias`

Method: `POST`

Request Body: 
```javascript
{
    numero: 1234,
    nome: "Caxias",
    cnpj: "12.345.678-0001/11"
}
```

Response Body: 
```javascript
{
    codigo: 123,
    numero: 1234,
    nome: "Caxias",
    cnpj: "12.345.678-0001/11"
}
```

#### Edição

Alterar uma agência existente.

Resource: `api.fakebank.com.br/agencias/{codigo}`

Method: `PUT`

Request Body: 
```javascript
{
    numero: 1234,
    nome: "Caxias",
    cnpj: "12.345.678-0001/11"
}
```

Response Body: 
```javascript
{
    codigo: 123,
    numero: 1234,
    nome: "Caxias",
    cnpj: "12.345.678-0001/11"
}
```

#### Exclusão

Excluir uma agência cadastrada.

Resource: `api.fakebank.com.br/agencias/{codigo}`

Method: `DELETE`

### Endpoints de Cliente Pessoa Física

Definição dos endpoints a serem construídos para tratamentos de clientes pessoa física.

### Endpoints de Cliente Pessoa Jurídica

Definição dos endpoints a serem construídos para tratamentos de clientes pessoa jurídica.

### Endpoints de Gerente

Definição dos endpoints a serem construídos para tratamentos de gerentes.

### Endpoints de Tipo de Conta

Definição dos endpoints a serem construídos para tratamentos de tipos de conta.

### Endpoints de Situação de Conta

Definição dos endpoints a serem construídos para tratamentos de situações de conta.

### Endpoints de Motivo de Encerramento

Definição dos endpoints a serem construídos para tratamentos de motivos de encerramento de conta.

### Endpoints de Conta

Definição dos endpoints a serem construídos para tratamentos de contas.

### Endpoints de Tipo de Movimentação

Definição dos endpoints a serem construídos para tratamentos de tipos de movimentação.

### Endpoints de Movimentação

Definição dos endpoints a serem construídos para tratamentos de movimentações.

