# NotesPro

API do projeto NotesPro = Sistema para gerenciar tarefas

## Requisitos

- [ ] CRUD de Tarefas
- [ ] CRUD de Usuários
- [ ] Filtrar as tarefas que foram concluidas
- [ ] Filtrar as tarefas que estão pendentes
- [ ] Filtrar as tarefas que estão atrasadas
- [ ] Autenticação

## Documentação

### Endpoints

- [Listar Tarefas](#listar-tarefas)
- [Cadastrar Tarefas](#cadastrar-tarefas)
- [Apagar Tarefas](#apagar-tarefas)
- [Atualizar Tarefas](#atualizar-tarefas)
- [Filtrar Tarefas Pendentes](filtrar-tarefas-pendentes)
- [Filtrar Tarefas concluidas](filtrar-tarefas-Concluidas)
- [Filtrar Tarefas Atrasadas](filtrar-tarefas-atrasadas)

- [Cadastrar Usuario](#cadastrar-usuario)
- [Apagar Usuario](#apagar-usuario)
- [Atualizar Usuario](#atualizar-usuario)

### Listar Matérias

`GET` /tarefa

Retorna um array com todas as tarefas cadastradas.

### Exemplo de Resposta

```js
[
  {
    id: 1,
    nome: "Ir para a academia",
    status: "pendente",
    descricao: "Não esquecer de pedir um novo treino",
    data: "26-12-2024"
  },
  {
    id: 2,
    nome: "Buscar o Junior na escola",
    status: "pendente",
    descricao: "Ir para a escola do buscar o Junior e perguntar pra prof se ele se comportou",
    data: "12-05-2024"
  },
];
```

#### Código de Status

| código | descricao                                                 |
| ------ | --------------------------------------------------------- |
| 200    | Tarefas retornadas com sucesso                           |
| 401    | Usuário não autenticado. Realize a autenticação em /login |

---

### Cadastrar Tarefa

`POST` /tarefa

Cadastrar uma nova tarefa para o usuário logado com os dados
enviados no corpo da requisição.

#### Corpo da Requisição

| campos | tipo   | obrigatório | descrição                               |
| ------ | ------ | :---------: | --------------------------------------- |
| nome   | string |     ✅      | Um nome curto para a tarefa             |
| status  | string |     ✅     | O status sera algo automatizado, assim que a tarefa for cadastrada ela sera cadastrada como pendente |
| descrição  | string |     ❌  | Algo mais detalhado sobre a tarefa |
| data  | date |     ✅      | A data limete para a realização da tarefa |

```js
{
    "nome": "Buscar o carro",
    "status": "pendente",
    "descricao": "Buscar o carro no mecanico na Rua: Padre Marcelo Fabio de Papa",
    "data": "15-05-2024"
}
```

### Exemplo de Resposta

```js

{
    "id": 1,
    "nome": "Buscar o carro",
    "status": "pendente",
    "descricao": "Buscar o carro no mecanico na Rua: Padre Marcelo Fabio de Papa",
    "data": "15-05-2024"
},

```

#### Código de Status

| código | descricao                                                        |
| ------ | ---------------------------------------------------------------- |
| 200    | Tarefa cadastrada com sucesso                                   |
| 400    | Validação falhou. Verifique e preencha todos os campos obrigatorios |
| 401    | Usuário não autenticado. Realize a autenticação em /login        |

---

### Filtro de Tarefas

`GET` /tarefa/`{status}`

Retorna as tarefas de acordo com o parametro `status` informado no
parâmetro da url.

### Exemplo de Resposta

```js
// requisição para /tarefa/atrasada
{
    "id": 1,
    "nome": "Marcar um checkup no medico",
    "status": "atrasada",
    "descricao": "não esquecer de perguntar se é normal a dor no joelho",
    "data": "15-02-2024"
},

```

#### Código de Status

| código | descricao                                                      |
| ------ | -------------------------------------------------------------- |
| 200    | Dados da tarefa retornados com sucesso                      |
| 401    | Usuário não autenticado. Realize a autenticação em /login      |
| 404    | Não existe tarefa com o `status` informado. |

---

### Apagar Matéria

`DELETE` /tarefa/`{id}`

Apaga a tarefa indicada pelo `id` enviado no parâmetro de path.

#### Código de Status

| código | descricao                                                      |
| ------ | -------------------------------------------------------------- |
| 200    | Tarefa apagada com sucesso                                  |
| 401    | Usuário não autenticado. Realize a autenticação em /login      |
---

### Atualizar Matéria

`PUT` /tarefa/`{id}`

Atualizar os dados da tarefa com o `id` informado no path,
utilizando os novos dados enviados no corpo da requisição

#### Corpo da Requisição

| campos | tipo   | obrigatório | descrição                               |
| ------ | ------ | :---------: | --------------------------------------- |
| nome   | string |     ✅      | Um nome curto para a tarefa             |
| status  | string |     ✅     | O status sera algo automatizado, assim que a tarefa for cadastrada ela sera cadastrada como pendente |
| descrição  | string |     ❌  | Algo mais detalhado sobre a tarefa |
| data  | date |     ✅      | A data limete para a realização da tarefa |

```js
{
    "nome": "Buscar o carro",
    "status": "pendente",
    "descricao": "Buscar o carro no mecanico na Rua: Padre Marcelo Fabio de Papa",
    "data": "15-05-2024"
}
```

### Exemplo de Resposta

```js

{
    "id": 1,
    "nome": "Buscar o carro",
    "status": "pendente",
    "descricao": "Buscar o carro no mecanico na Rua: Padre Marcelo Fabio de Papa",
    "data": "15-05-2024"
},

```

#### Código de Status

| código | descricao                                                        |
| ------ | ---------------------------------------------------------------- |
| 200    | Tarefa atualizada com sucesso                                 |
| 400    | Validação falhou. Validação falhou. Verifique e preencha todos os campos obrigatorios  |
| 401    | Usuário não autenticado. Realize autenticação em /login          |

