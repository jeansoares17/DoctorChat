#  DOCTORCHAT

Uma API para o app de controle de consultas.

##  Terminais

- Login
    - [ Login ](#login)
    - [ Login cadastrar ](##cadastrar-usuario)

- Cadastro
    - [ Cadastro ](#cadastrar-usuario)
    - [ Atualizar ](#atualizar-cadastro)

- Agenda
    - [ Agendamento ](#cadastrar-consulta)
    - [ Consultar horarios ](#listar-horarios)

---

###  Cadastrar usuario

`POST` /api/cadastro

**Campos da requisição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
|Nome | string | sim | o nome completo do usuario
|Cpf | string |sim| o cpf do usuario
|Email | string | sim | o email do usuario
|Senha | string | sim | o seha a ser utilizada no login
|Data de nascimento | date |sim | a data de nascimento do usuario
|Cep | string |sim| o cep do usuario
|Logradouro | string |sim| o logradouro do usuario
|Numero | int |sim| o numero da casa usuario
|Bairro | string |sim| o bairro do usuario
|Complemento | string |sim| o complemento da rua do usuario

**Exemplo de corpo de requisição**

```js
{
    nome :  'Gabriel Silva Soares' ,
    cpf :  '123.456.789-10'  ,
    email :  'exemplo123@gmail.com' ,
    senha :  'exemplo123.' ,
    dataNascimento :  05/08/2002 ,
    cep :  '06900-000'  ,
    logradouro :  'Rua João Dias' ,
    numero :  9 ,
    bairro :  'Joinville' ,
    complento :  'Igreja Católica' ,
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 201 | usuario cadastrado com sucesso
| 400 | os campos enviados são inválidos

---

###  Atualizar cadastro

`PUT` /api/cadastro/{id}

**Exemplo de Corpo da Resposta**

```js
{
    nome :  'Yan Rodrigues Guimaraes' ,
    cpf :  '321.654.987-01'  ,
    email :  'exemplo10@gmail.com' ,
    senha :  'exemplo1234.' ,
    dataNascimento :  08/09/2004 ,
    cep :  '06700-000'  ,
    logradouro :  'Rua João Gomes' ,
    numero :  10 ,
    bairro :  'Filipinho' ,
    complento :  'Igreja Assembleia de Deus' ,
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | dados atualizados com sucesso
| 404 | não existe usuario com o id informado

---

###  Cadastrar consulta

`POST` /api/agenda

**Campos da requisição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
|Data | date |sim | a data da consulta do usuario
|Hora | string |sim| o horario da consulta do usuario


**Exemplo de corpo de requisição**

```js
{
    data:  05/08/2023 ,
    hora :  '8:30' ,
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 201 | consulta agendada com sucesso
| 400 | os campos enviados são inválidos

---
###  Listar Horarios

`GET` /api/agenda

**Exemplo de Corpo da Resposta**

```js
{
    data: 15/04/2023
    horariosDisponiveis :  ['10:30', '11:00','12:30'] ,
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | dados retornados com sucesso
| 404 | não existe horarios disponiveis

---

###  Login

`GET` /api/login

**Exemplo de Corpo da Resposta**

```js
{
    email :  'exemplo123@gmail.com' ,
    senha :  'exemploStep12.' ,
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | login efetuado com sucesso
| 404 | dados invalidos

---