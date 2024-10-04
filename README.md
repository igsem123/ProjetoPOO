---
# Gestão de Casamentos

![Badge](https://img.shields.io/badge/Java-17-blue) ![Badge](https://img.shields.io/badge/Status-Em%20Desenvolvimento-orange)

### Sobre o projeto

Este software tem como objetivo auxiliar na gestão e organização de casamentos, oferecendo funcionalidades como:
- **Cadastro e login de usuários (Noivos, Cerimonialistas, Convidados, etc.)**
- **Gestão de fornecedores** (criação, edição, e gerenciamento de débitos)
- **Mural de recados**
- **Gestão de presentes**
- **Confirmação de presença (RSVP)**

O sistema foi desenvolvido em **Java** com uma arquitetura modularizada para facilitar a manutenção e futuras expansões.

### Funcionalidades
- **Login no sistema**: Usuários podem acessar o sistema com email e senha.
- **Cadastro de novos usuários**: O sistema permite o cadastro de diferentes tipos de usuários, como Noivo(a), Cerimonialista, Administrador e Convidado.
- **Gestão de fornecedores**: Gerencie os fornecedores do casamento, com a opção de cadastrar, editar e consultar informações financeiras.
- **Perfil de usuário**: Os usuários podem visualizar e editar seus perfis.
- **Confirmação de presença**: Convidados podem confirmar presença no evento.

### Tecnologias utilizadas

- **Java 17**: Linguagem principal usada no desenvolvimento.
- **Scanner**: Usado para a interação com o usuário via terminal.
- **Arquitetura baseada em DAO**: Separação entre a lógica de persistência de dados e a lógica de negócios.
- **Padrão Builder**: Usado para construir os menus e interfaces via console.

### Como executar o projeto

#### Pré-requisitos

- **Java 17** ou superior instalado
- IDE ou ambiente de desenvolvimento com suporte a projetos Java (Ex: IntelliJ, Eclipse)

#### Passos para rodar o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
   
2. Navegue até o diretório do projeto:
   ```bash
   cd seu-repositorio
   ```

3. Compile o projeto:
   ```bash
   javac -d bin src/**/*.java
   ```

4. Execute o programa:
   ```bash
   java -cp bin br.com.gestao.casamento.controller.Main
   ```

### Exemplo de uso

1. No primeiro acesso, o usuário será solicitado a realizar o **login** ou **cadastro** no sistema.
2. Após o login, o sistema exibirá um menu com opções baseadas no tipo de usuário (Noivo(a), Cerimonialista, Convidado, etc).
3. Noivos e Cerimonialistas têm acesso à **gestão de fornecedores**, onde podem cadastrar ou editar fornecedores, bem como verificar o estado das dívidas.
4. Convidados podem acessar o **perfil** e confirmar presença no evento através da opção "Confirmar Presença".
5. O sistema salva todas as alterações realizadas durante a sessão e permite futuras consultas e edições.

### Estrutura do Projeto

```bash
├── src
│   ├── br.com.gestao.casamento.controller
│   │   ├── Main.java           # Ponto de entrada do sistema
│   ├── br.com.gestao.casamento.dao
│   │   ├── PessoaDAO.java       # Acesso a dados de pessoas
│   │   ├── FornecedorDAO.java   # Acesso a dados de fornecedores
│   └── br.com.gestao.casamento.model
│       ├── Pessoa.java          # Classe que representa usuários
│       ├── Fornecedor.java      # Classe que representa fornecedores
```
---
