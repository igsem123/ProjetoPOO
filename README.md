Aqui está a versão atualizada do seu README com base nas funcionalidades mais recentes do projeto:

---

# Gestão de Casamentos

![Badge](https://img.shields.io/badge/Java-17-blue) ![Badge](https://img.shields.io/badge/Status-Em%20Desenvolvimento-orange)

### Sobre o projeto

Este software tem como objetivo auxiliar na gestão e organização de casamentos, oferecendo funcionalidades robustas e flexíveis, tais como:
- **Cadastro e login de usuários** (Noivos, Cerimonialistas, Convidados, Administradores)
- **Gestão de fornecedores** (cadastro, edição e gerenciamento de pagamentos)
- **Mural de recados**
- **Gestão de presentes**
- **Confirmação de presença (RSVP)**
- **Controle de pagamentos** (à vista ou parcelado, com atualização automática de status de fornecedores)

O sistema foi desenvolvido em **Java** com uma arquitetura modularizada para facilitar a manutenção e futuras expansões.

### Funcionalidades
- **Login no sistema**: Usuários acessam o sistema com email e senha.
- **Cadastro de novos usuários**: Permite o cadastro de Noivos(as), Cerimonialistas, Administradores e Convidados.
- **Gestão de fornecedores**: Cadastro, edição e consulta de fornecedores. O sistema gerencia os pagamentos e atualiza o status dos fornecedores conforme o pagamento é efetuado (à vista ou agendado).
- **Perfil de usuário**: Os usuários podem visualizar e editar seus perfis.
- **Confirmação de presença**: Convidados confirmam ou desconfirmam presença no evento, com a possibilidade de confirmar convidados familiares.
- **Controle de pagamentos**: Administra pagamentos aos fornecedores com agendamento e verificação automática diária ou manual para atualizações do estado financeiro.
- **Mural de recados**: Usuários podem visualizar e postar recados.
- **Gestão de presentes**: Gerenciamento de presentes que foram comprados pelos convidados.

### Tecnologias utilizadas

- **Java 17**: Linguagem principal usada no desenvolvimento.
- **Scanner**: Utilizado para interação com o usuário via terminal.
- **Arquitetura baseada em DAO**: Camada de persistência separada da lógica de negócio, utilizando DAOs em memória.
- **Padrão Builder**: Implementado para construir menus e interfaces via console.
- **LocalDate e LocalDateTime**: Usado para controle de datas e agendamentos no sistema.

### Como executar o projeto

#### Pré-requisitos

- **Java 17** ou superior instalado
- IDE ou ambiente de desenvolvimento com suporte a projetos Java (ex: IntelliJ, Eclipse)

#### Passos para rodar o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/igsem123/ProjetoPOO.git
   ```
   
2. Navegue até o diretório do projeto:
   ```bash
   cd ProjetoPOO
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
2. Após o login, o sistema exibirá um menu com opções baseadas no tipo de usuário (Noivo(a), Cerimonialista, Convidado, Administrador).
3. Noivos e Cerimonialistas têm acesso à **gestão de fornecedores**, onde podem cadastrar, editar e verificar o status dos pagamentos dos fornecedores.
4. Convidados acessam seu **perfil** e podem confirmar ou desconfirmar presença no evento.
5. O sistema atualiza automaticamente o status dos pagamentos agendados e permite o controle manual via administradores.

### Estrutura do Projeto

```bash
├── src
│   ├── br.com.gestao.casamento.controller
│   │   ├── Main.java                      # Ponto de entrada do sistema
│   ├── br.com.gestao.casamento.dao
│   │   ├── PessoaDAO.java                 # Acesso a dados de pessoas
│   │   ├── FornecedorDAO.java             # Acesso a dados de fornecedores
│   │   ├── EventoDAO.java                 # Acesso a dados de evento
│   │   ├── ConvidadoIndividualDAO.java    # Acesso a dados de convites individuais
│   │   ├── ConvidadoFamiliarDAO.java      # Acesso a dados de convites familiares
│   │   ├── MuralDeRecadosDAO.java         # Acesso a dados de recados dos convidados aos noivos
│   │   ├── PresentesDAO.java              # Acesso a dados de presentes
│   │   ├── PagamentosDAO.java             # Acesso a dados de pagamentos
│   ├── br.com.gestao.casamento.model
│   │   ├── Pessoa.java                    # Classe que representa usuários
│   │   ├── Fornecedor.java                # Classe que representa fornecedores
│   │   ├── Evento.java                    # Classe que representa eventos
│   │   ├── ConvidadoIndividual.java       # Classe que representa convidados individuais
│   │   ├── ConvidadoFamilia.java          # Classe que representa famílias de convidados
│   │   ├── MuralDeRecados.java            # Classe que representa os recados
│   │   ├── Presentes.java                 # Classe que representa os presentes
│   │   ├── Pagamentos.java                # Classe que representa os pagamentos
│   │   ├── Calendario.java                # Classe que representa o calendário do sistema
│   ├── br.com.gestao.casamento.view
│   │   ├── GUI.java                       # Classe que representa a interface visual do sistema

```

---

Por.: Igsem123
