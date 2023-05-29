<p align="center"> 
<img src="img/viceri.png" width="718" height="217" />
</p>

# Desafio Backend – Java
### O objetivo desse desafio é a criação de uma API REST para um aplicativo de tarefas (TO-DO).

## **Ferramentas e Programas Utilizados**
```
Intellij
PL/SQL Developer
Spring Boot     
Java
Oracle 19c
Spring Security
Java-Jwt
Openapi Swagger 
````
## Swagger
<p align="center"> 
<img src="img/swagger.png" width="950" height="540" />
</p>

Para Utilizar o Swagger é necessário fazer o login, e inserir o token na opção de **Autorize**
Em cada requisição é necessário informar o token.

## Banco de Dados

Todo script para criação do banco de dados se encontra na pasta **resources/db/migration**

**A aplicação utiliza o componente FlyWay para fazer o versionamento do banco de dados, ao executar o programa a primeira
vez será criado automaticamente as tabelas no banco de dados.**

## Métodos
Requisições para a API devem seguir os padrões:

| Método   | Descrição                                             |
|----------|-------------------------------------------------------|
| `GET`    | Retorna informações de um ou mais registros.          |
| `POST`   | Utilizado para criar um novo registro.                |
| `PUT`    | Atualiza dados de um registro ou altera sua situação. |
| `DELETE` | Remove um registro do sistema.                        |

## Respostas

| Código | Descrição                                                          |
|--------|--------------------------------------------------------------------|
| `200`  | Requisição executada com sucesso (success).                        |
| `201`  | Requisição executada com sucesso (success).                        |
| `400`  | Erros de validação ou os campos informados não existem no sistema. |
| `401`  | Não autorizado.                                                    |
| `403`  | Proibido.                                                          |
| `404`  | Não Encontrado.                                                    |
| `409`  | Conflito.                                                          |
| `405`  | Método não implementado.                                           |

# Recursos da Api

| Tipo       | End Point                   |
|------------|-----------------------------|
| `GET`      | /api/tarefas                |
| `GET`      | /api/tarefas/pendentes      |
| `POST`     | /api/tarefas                |
| `POST`     | /api/tarefas/{id}/concluida |
| `PUT`      | /api/tarefas/{id}           |
| `DELETE`   | /api/tarefas/{id}           |
| ---------- | --------------------------  |
| `POST`     | /api/auth/registro          |
| `POST`     | /api/auth/login             |

### Valores aceito no campo _Prioridade_
O Campo **Prioridade** por se tratar de um eNum somente é aceito os seguintes valores
````
  ALTA
  MEDIA
  BAIXA
````

# Tarefas [_/api/tarefas_]

Lista todas a tarefas.

### Listar [**GET** _/api/tarefas_]


Response 200 (application/json)

        [
            {
            "Id": 1,
            "Prioridade": "ALTA",
            "Descricao": "Fazer Desafio Viceri",
            "DtaTarefa": "27/05/2023",
            "Conculida": true,
            "DtaConcluida": 28/05/2023 10:15:02,
            "DtaInsert": "27/05/2023 14:03:36",
            "DtaUpdate": "28/05/2023 10:15:02"
            },
            {
            "Id": 2,
            "Prioridade": "ALTA",
            "Descricao": "Terminar Readme Viceri",
            "DtaTarefa": "27/05/2023",
            "Conculida": false,
            "DtaConcluida": null,
            "DtaInsert": "28/05/2023 09:01:45",
            "DtaUpdate": "28/05/2023 09:01:45"
            }
        ]

# Tarefas Pendentes [_/api/tarefas/pendentes_]

Lista todas a tarefas pendentes.

### Listar [**GET** _/api/tarefas/pendentes_]

Query Param opcional para filrar pela prioridade
````
    prioridade
````


Response 200 (application/json)

        [
            {
            "Id": 2,
            "Prioridade": "ALTA",
            "Descricao": "Terminar Readme Viceri",
            "DtaTarefa": "27/05/2023",
            "Conculida": false,
            "DtaConcluida": null,
            "DtaInsert": "28/05/2023 09:01:45",
            "DtaUpdate": "28/05/2023 09:01:45"
            }
        ]

# Cadastrar Tarefas [_/api/tarefas_]

Cadastra Tarefa.

### Listar [**POST** _/api/tarefas_]


Request (application/json)

        {
            "Prioridade": "ALTA",
            "Descricao": "Terminar Readme Viceri",
            "DtaTarefa": "27/05/2023"
        }

Response 201 (application/json)

        {
            "Id": 2,
            "Prioridade": "ALTA",
            "Descricao": "Terminar Readme Viceri",
            "DtaTarefa": "27/05/2023",
            "Conculida": false,
            "DtaConcluida": null,
            "DtaInsert": "28/05/2023 09:01:45",
            "DtaUpdate": "28/05/2023 09:01:45"
        }

# Tarefa Conculida  [_/api/tarefas/{id}/concluida_]

Marcar Tarefa como concluída 

### Listar [**POST** _/api/tarefas/{id}/concluida_]


Response 200 (application/json)

        {
            "Id": 1,
            "Prioridade": "ALTA",
            "Descricao": "Fazer Desafio Viceri",
            "DtaTarefa": "27/05/2023",
            "Conculida": true,
            "DtaConcluida": 28/05/2023 10:15:02,
            "DtaInsert": "27/05/2023 14:03:36",
            "DtaUpdate": "28/05/2023 10:15:02"
        }

### Alterar [**PUT** _/api/tarefas/{id}_]

Atualiza tarefa

Request (application/json)

    {
            "Prioridade": "ALTA",
            "Descricao": "Fazer Desafio Viceri",
            "DtaTarefa": "27/05/2023",
    }

Response 200 (application/json)

    {
            "Id": 1,
            "Prioridade": "ALTA",
            "Descricao": "Fazer Desafio Viceri",
            "DtaTarefa": "27/05/2023",
            "Conculida": false,
            "DtaConcluida": null,
            "DtaInsert": "27/05/2023 14:03:36",
            "DtaUpdate": "28/05/2023 10:15:02"
    }

### Deletar [**DELETE** _/api/tarefas/{id}_]

Deleta tarefa

    Response 200 (application/json)

# Cadastrar Usuario [_/api/auth/registro_]

Cadastrar Usuario

### Cadastrar [**POST** _/api/auth/registro_]


Request (application/json)

        {
            "Nome" : "Rodolfo Rodrigues Bortolozo",
            "Email" : "rodolfobortolozo@gmail.com",
            "Senha" : "123456"
        }

Response 201 (application/json)

        {
            "viceri-token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJWSUNFUkkgU0VJRE9SIiwic3ViIjoiVE8tRE8iLCJpYXQiOjE2ODUzMDM2ODcsImV4cCI6MTY4NTM5MDA4NywiZW1haWwiOiIxYWRtaW5AYWRtaW4uY29tIn0.m4ut-RqMML8mJ3jeXwVkFkOMgj9CfvO2-Lh4wv65neA"
        }

# Cadastrar Usuario [_/api/auth/login_]

Cadastrar Usuario

### Cadastrar [**POST** _/api/auth/login_]


Request (application/json)

        {
            "Email" : "rodolfobortolozo@gmail.com",
            "Senha" : "123456"
        }

Response 201 (application/json)

        {
            "viceri-token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJWSUNFUkkgU0VJRE9SIiwic3ViIjoiVE8tRE8iLCJpYXQiOjE2ODUzMDM2ODcsImV4cCI6MTY4NTM5MDA4NywiZW1haWwiOiIxYWRtaW5AYWRtaW4uY29tIn0.m4ut-RqMML8mJ3jeXwVkFkOMgj9CfvO2-Lh4wv65neA"
        }