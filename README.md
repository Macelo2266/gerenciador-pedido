Gerenciador de Pedidos

Este projeto é uma API REST para gerenciamento de pedidos, desenvolvida com Java 21 e Spring Boot 3.5.6, com foco em boas práticas de backend, segurança, organização em camadas e controle de acesso por perfis.

A aplicação permite o gerenciamento de usuários, produtos, fornecedores, categorias e pedidos, utilizando autenticação JWT e autorização baseada em papéis (roles), simulando um cenário real de sistemas corporativos.

🚀 Tecnologias Utilizadas

Java 21

Spring Boot 3.5.6

Spring Security com autenticação JWT (JSON Web Token)

Spring Data JPA (Hibernate)

PostgreSQL

Jakarta Validation

Maven

🔐 Segurança e Perfis de Acesso

A aplicação utiliza autenticação Stateless com JWT, garantindo segurança e escalabilidade.

Perfis de Usuário

ADMIN

Acesso total ao sistema

Cadastro, edição e exclusão de produtos, fornecedores e categorias

CLIENTE

Acesso restrito

Visualização de produtos e fornecedores

Criação e gerenciamento dos próprios pedidos

Controle de Rotas

Rotas Públicas

/auth/** → login

POST /usuarios → cadastro inicial

Rotas Privadas (ADMIN)

/produtos/**

/fornecedores/**

/categorias/**

Rotas Privadas (Usuários Autenticados)

/pedidos/**

🏗 Estrutura de Entidades

Usuário

Credenciais de acesso e perfil (ADMIN ou CLIENTE)

Produto

Nome, preço e estoque

Relacionado a Categoria e Fornecedor

Categoria

Agrupamento lógico de produtos

Fornecedor

Informações comerciais e de contato

Pedido

Data do pedido

Data de entrega

Relacionamento Many-to-Many com produtos

⚙️ Configuração do Ambiente

O projeto utiliza PostgreSQL como banco de dados e faz uso de variáveis de ambiente para a conexão.

Exemplo de configuração no application.properties:

spring.datasource.url=jdbc:postgresql://${DB_HOST}/gerenciador-pedidos
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

jwt.secret=sua-chave-secreta-aqui

🛠 Como Executar o Projeto
Pré-requisitos

Java 21+

Maven

PostgreSQL

Passos
# Clonar o repositório
git clone https://github.com/Macelo2266/gerenciador-pedido.git

# Entrar no diretório
cd gerenciador-pedido

# Executar a aplicação
./mvnw spring-boot:run


A aplicação estará disponível em:

http://localhost:8080

📌 Principais Endpoints
🔑 Autenticação

POST /auth/login
→ Retorna o token JWT

👤 Usuários

POST /usuarios
→ Cadastro de usuário

GET /usuarios
→ Listagem (requer autenticação)

🧾 Pedidos

POST /pedidos
→ Criação de pedido com lista de produtos

PUT /pedidos/{id}/entregar
→ Atualiza a data de entrega

📦 Produtos

GET /produtos
→ Lista produtos com categoria e fornecedor

📈 Objetivo do Projeto

Consolidar conhecimentos em desenvolvimento backend com Java, aplicando conceitos como:

APIs REST

Segurança com JWT

Persistência com JPA e PostgreSQL

Modelagem de dados

Arquitetura em camadas

👨‍💻 Autor

Marcelo Araújo
Estudante de Sistemas de Informação – UFRPE
Backend Developer | Java | Spring Boot | MySQL

🔗 GitHub:
https://github.com/Macelo2266

🔗 LinkedIn:
(https://www.linkedin.com/in/macelo-araujo-dev/)
