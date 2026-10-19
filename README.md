# Fintech DAO

Camada de acesso a dados (DAO) de um sistema de finanças pessoais, desenvolvida em **Java** com banco de dados **Oracle** via **JDBC**. Projeto acadêmico em grupo (Grupo 45) do curso de Análise e Desenvolvimento de Sistemas da FIAP.

O sistema controla usuários, categorias, despesas e investimentos. Esta etapa do projeto cobre o mapeamento das entidades em classes Java e a comunicação com o banco por meio do padrão DAO.

## Tecnologias

- Java 21
- Maven
- JDBC com driver Oracle `ojdbc11` (versão 23.5.0.24.07)
- Banco de dados Oracle

## Funcionalidades

Para cada uma das quatro entidades (`Usuario`, `Categoria`, `Despesa` e `Investimento`) existe um DAO com:

- `insert`: grava um novo registro e devolve o id gerado pelo banco no próprio objeto;
- `getAll`: lista todos os registros, com ordenação definida na consulta.

A classe `Teste` é um roteiro de demonstração: cadastra 5 usuários, 5 categorias, 5 despesas e 5 investimentos e depois lista o conteúdo de cada tabela.

## Estrutura do projeto

```
src/main/java/br/com/fintech/
├── app/       Teste.java            roteiro de demonstração (método main)
├── config/    ConnectionFactory.java  abre a conexão com o Oracle
├── dao/       UsuarioDAO, CategoriaDAO, DespesaDAO, InvestimentoDAO
└── model/     Usuario, Categoria, Despesa, Investimento
```

## Modelo de dados

As consultas do código usam as tabelas e colunas abaixo. O script de criação das tabelas (DDL) não está incluído neste repositório.

| Tabela | Colunas |
| --- | --- |
| `usuario` | `id_usuario`, `nome`, `email`, `senha_hash`, `data_cadastro` |
| `categoria` | `id_categoria`, `nome`, `tipo` (`RECEITA` ou `DESPESA`) |
| `despesa` | `id_despesa`, `id_usuario`, `id_categoria`, `valor`, `data_despesa`, `descricao` |
| `investimento` | `id_investimento`, `id_usuario`, `nome`, `tipo`, `valor_investido`, `data_investimento`, `rentabilidade` (opcional) |

## Boas práticas aplicadas

- `PreparedStatement` em todas as consultas, evitando concatenação de valores no SQL.
- `try-with-resources` para fechar conexões, comandos e resultados.
- Credenciais fora do código: a conexão lê `DB_URL`, `DB_USER` e `DB_PASSWORD` do ambiente ou o arquivo `db.properties`, que está no `.gitignore`.
- Campos monetários com `BigDecimal` e datas com `LocalDate`.

## Como executar

> **Atenção:** o código foi desenvolvido para o banco de dados Oracle da FIAP e **só funciona com um usuário e senha válidos nesse banco** (acesso de aluno da FIAP). Sem essas credenciais, a conexão é recusada e a aplicação não executa. Por isso, as credenciais não fazem parte do repositório.

**Pré-requisitos:** JDK 21, Maven e usuário e senha de acesso ao banco Oracle da FIAP, com as tabelas acima já criadas.

1. Clone o repositório:

   ```bash
   git clone https://github.com/velosogabrielgav-del/fintech-dao.git
   cd fintech-dao
   ```

2. Configure a conexão de uma das duas formas:

   - copie `src/main/resources/db.properties.example` para `src/main/resources/db.properties` e preencha `db.url`, `db.user` e `db.password`; ou
   - defina as variáveis de ambiente `DB_URL`, `DB_USER` e `DB_PASSWORD`.

3. Baixe as dependências e compile:

   ```bash
   mvn compile
   ```

4. Execute a classe `br.com.fintech.app.Teste` pela sua IDE (IntelliJ, Eclipse ou VS Code).
