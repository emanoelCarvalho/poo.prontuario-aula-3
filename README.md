---

# 🗂️ POO.Prontuário

Este projeto foi desenvolvido como parte de uma atividade da disciplina **Programação Orientada a Objetos (POO)**. O objetivo era implementar um sistema simples de prontuário médico seguindo o padrão **DAO (Data Access Object)**, utilizando **Java puro** e **JDBC** para comunicação com um banco de dados **MySQL**.

## 🎯 Objetivo

O projeto tem como finalidade principal aprofundar os conhecimentos em **Programação Orientada a Objetos** e explorar o padrão de projeto **DAO**, que separa a lógica de negócios da lógica de acesso a dados.

---

## 🛠️ Tecnologias Utilizadas

- **Java SE** (Java puro)  
- **MySQL**  
- **JDBC** (Java Database Connectivity)  
- **Driver MySQL** para JDBC  

---

## 📂 Estrutura do Projeto

```plaintext
.
├── src
│   ├── dao
│   │   ├── ProntuarioDAO.java
│   │   └── ConexaoBD.java
│   ├── model
│   │   └── Prontuario.java
│   └── main
│       └── Main.java
└── README.md
```

- **dao**: Contém as classes responsáveis pela conexão e manipulação de dados no banco.  
- **model**: Contém as classes que representam os modelos de dados.  
- **main**: Contém a classe principal que executa o programa.

---

## 🚀 Funcionalidades

- **Conexão com o Banco de Dados**: Realizada através de uma classe específica que utiliza JDBC.  
- **CRUD de Prontuários**:
  - **Criar**: Inserir novos registros de prontuários.
  - **Ler**: Consultar prontuários existentes.
  - **Atualizar**: Alterar informações de prontuários.
  - **Excluir**: Remover prontuários do banco de dados.

---

## ⚙️ Pré-requisitos

- **Java JDK** (versão 8 ou superior)  
- **MySQL** (servidor configurado e em execução)  
- **Driver JDBC para MySQL** (adicionado ao classpath)

---

## 📥 Instalação e Configuração

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu_usuario/poo.prontuario.git
   cd poo.prontuario
   ```

2. **Configuração do Banco de Dados:**  
   Crie uma base de dados no MySQL e ajuste as credenciais no código (`ConexaoBD.java`):

   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/nome_do_banco";
   private static final String USER = "seu_usuario";
   private static final String PASSWORD = "sua_senha";
   ```

3. **Compilação e Execução:**

   Compile os arquivos Java:

   ```bash
   javac -cp .:mysql-connector-java-x.x.x.jar src/main/Main.java
   ```

   Execute o programa:

   ```bash
   java -cp .:mysql-connector-java-x.x.x.jar src/main/Main
   ```

---

## 🔍 Como Funciona o Padrão DAO

O padrão **DAO** permite separar a lógica de persistência dos objetos da lógica de negócios, facilitando a manutenção e evolução do sistema.  
Exemplo simplificado:

- **Model**: Representa o objeto de prontuário.
  
  ```java
  public class Prontuario {
      private int id;
      private String nomePaciente;
      private String diagnostico;
      // Getters e Setters...
  }
  ```

- **DAO**: Contém métodos para acessar o banco de dados.

  ```java
  public class ProntuarioDAO {
      public void inserir(Prontuario prontuario) {
          // Código JDBC para inserir o prontuário no banco.
      }
  }
  ```

---

## 📩 Sugestões e Contato

Para sugestões ou dúvidas, envie um e-mail para **hemanoel718@gmail.com**.

---
