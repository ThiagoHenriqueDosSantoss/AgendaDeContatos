# 📒 Agenda de Contatos

Este projeto consiste em uma aplicação simples e funcional para gerenciamento de contatos pessoais, desenvolvida como parte da disciplina de Análise e Projeto de Sistemas. 
A aplicação permite realizar operações básicas como cadastrar, consultar, atualizar e remover contatos, promovendo o entendimento prático dos conceitos estudados durante a diciplina como
implementação de expressões regulares, manipulações de arquivos, tratamento de exceções e boas praticas de programação orientada a objetos.

## 🖥️ Tecnologias Utilizadas

### 🧠 Lógica e Back-end
- Java — linguagem principal utilizada para a construção da lógica de negócio e funcionamento interno da aplicação.

### 🖼️ Interface Gráfica (Front-end)
- Java Swing — utilizado como biblioteca para o desenvolvimento da interface gráfica da aplicação (em desenvolvimento).
*** 
## 📋 Funcionalidades do Sistema
O sistema de agenda de contatos possui um menu interativo com as seguintes funcionalidades:

#### [1] ➤ ➕ Adicionar contato
Permite cadastrar um novo contato, solicitando ao usuário informações como nome, telefone, e-mail, entre outros campos definidos no programa.
Fazendo validações com reggex para que seja informado um nome e sobrenome, um telefone de 11 digitos(incluindo dd) e se o email está sendo escrito da forma correta(com @ e .com)

#### [2] ➤ 📋 Listar contatos
Exibe todos os contatos cadastrados no arquivo atual de forma organizada, facilitando a visualização geral da agenda.

#### [3] ➤ ✏️ Editar contato existente
Permite modificar os dados de um contato já existente. O usuário pode escolher qual contato editar a partir do ID ou número de telefone e quais campos deseja atualizar.
As validações nesta função ocorrem de forma que seja verificado se o ID ou número informado foram cadastrados de fato.

#### [4] ➤ ❌ Remover contato
Remove um contato da agenda com base em uma identificação única (ID). O contato é excluído permanentemente do arquivo atual.

#### [5] ➤ 🔍 Busca interativa
Oferece uma busca dinâmica por nome. Ideal para localizar rapidamente um contato específico.

#### [6] ➤ 💾 Exportar backup
Cria um backup do arquivo de contatos atual, salvando uma cópia em um diretório definido. Isso garante a segurança dos dados e permite restauração futura.

#### [7] ➤ 📄 Criar outro arquivo
Permite criar um novo arquivo de agenda, útil para separar contatos por categorias, usuários ou outros critérios. O novo arquivo pode ser manipulado independentemente dos anteriores.

#### [8] ➤ 🗂️ Listar arquivos disponíveis
Exibe uma lista de todos os arquivos de agenda disponíveis no diretório padrão, facilitando a escolha de qual arquivo será usado.

#### [9] ➤ 📂 Selecionar arquivo para manipular
Permite ao usuário selecionar um dos arquivos existentes como o ativo para leitura, edição ou exclusão de contatos.
***
**Pré-requisitos:** Java 22 

```sh
# Clonar repositório
git clone https://github.com/ThiagoHenriqueDosSantoss/AgendaDeContatos.git

# Entrar na pasta do projeto back-end
cd AgendaDeContatos/src/br/com/thiagosantos/agenda/application

# Executar o projeto
javac Program.java
```

## 👨‍💻 Autor

### Thiago Henrique Dos Santos
