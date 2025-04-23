package br.com.thiagosantos.agenda.application;

import br.com.thiagosantos.agenda.controller.PersistenciaController;
import br.com.thiagosantos.agenda.entities.Contato;
import br.com.thiagosantos.agenda.entities.Persistencia;

import java.util.Objects;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Persistencia persistencia = new Persistencia();
        PersistenciaController persistenciaController = new PersistenciaController();
        int op;

        persistenciaController.criarArquivo();
        int contadorId = 1;
        String path = "contatos.txt";

        while(true) {
            System.out.println("                ____\n" +
                    "                 _.' :  `._\n" +
                    "             .-.'`.  ;   .'`.-.\n" +
                    "    __      / : ___\\ ;  /___ ; \\      __\n" +
                    "  ,'_ \"\"--.:__;\".-.\";: :\".-.\":__;.--\"\" _`,\n" +
                    "  :' `.t\"\"--.. '<@.`;_  ',@>` ..--\"\"j.' `;\n" +
                    "       `:-.._J '-.-'L__ `-- ' L_..-;'\n" +
                    "         \"-.__ ;  .-\"  \"-.  : __.-\"\n" +
                    "             L ' /.------.\\ ' J\n" +
                    "              \"-.   \"--\"   .-\"\n" +
                    "             __.l\"-:_JL_;-\";.__\n" +
                    "          .-j/'.;  ;\"\"\"\"  / .'\\\"-.\n" +
                    "        .' /:`. \"-.:     .-\" .';  `.\n" +
                    "     .-\"  / ;  \"-. \"-..-\" .-\"  :    \"-.\n" +
                    "  .+\"-.  : :      \"-.__.-\"      ;-._   \\\n" +
                    "  ; \\  `.; ;                    : : \"+. ;\n" +
                    "  :  ;   ; ;                    : ;  : \\:\n" +
                    " : `.\"-; ;  ;                  :  ;   ,/;\n" +
                    "  ;    -: ;  :                ;  : .-\"'  :\n" +
                    "  :\\     \\  : ;             : \\.-\"      :\n" +
                    "   ;`.    \\  ; :            ;.'_..--  / ;\n" +
                    "   :  \"-.  \"-:  ;          :/.\"      .'  :\n" +
                    "     \\       .-`.\\        /t-\"\"  \":-+.   :\n" +
                    "      `.  .-\"    `l    __/ /`. :  ; ; \\  ;\n" +
                    "        \\   .-\" .-\"-.-\"  .' .'j \\  /   ;/\n" +
                    "         \\ / .-\"   /.     .'.' ;_:'    ;\n" +
                    "          :-\"\"-.`./-.'     /    `.___.'\n" +
                    "                \\ `t  ._  / \n" +
                    "                 \"-.t-._:'\n");
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║         📒 AGENDA DE CONTATOS 📒           ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║ [1] ➤ ➕ Adicionar contato                ║");
            System.out.println("║ [2] ➤ 📋 Listar contatos                  ║");
            System.out.println("║ [3] ➤ ✏️ Editar contato existente         ║");
            System.out.println("║ [4] ➤ ❌ Remover contato                  ║");
            System.out.println("║ [5] ➤ 🔍 Busca interativa                 ║");
            System.out.println("║ [6] ➤ 💾 Exportar backup                  ║");
            System.out.println("║ [7] ➤ 📄 Criar outro arquivo              ║");
            System.out.println("║ [8] ➤ 🗂️ Listar arquivos disponíveis      ║");
            System.out.println("║ [9] ➤ 📂 Selecionar arquivo para manipular║");
            System.out.println("║ [0] ➤ 🚪 Encerrar programa                ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.println("🔧 Arquivo atual em uso: " + persistenciaController.getArquivoAtual());
            op = scanner.nextInt();
            scanner.nextLine();
            if (op == 1) {
                int id = contadorId++;
                System.out.println("Informe o nome: ");
                String nome = scanner.nextLine();
                while (!nome.contains(" ")) {
                    System.out.println(" ❌ ERRO! Digite o nome completo (nome e sobrenome): ❌");
                    nome = scanner.nextLine();
                }
                System.out.println("Informe o telefone: ");
                Long telefone = scanner.nextLong();
                while(!String.valueOf(telefone).matches("^\\d{11}$")){
                    System.out.println(" ❌ ERRO: Número de telefone inválido! ❌");
                    System.out.println("Informe o novo telefone: ");
                    telefone = scanner.nextLong();
                }
                scanner.nextLine();  // Limpar o buffer
                System.out.println("Informe o e-mail: ");
                String email = scanner.nextLine();
                while(!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
                    System.out.println("ERRO: Email inválido! ❌");
                    System.out.println("Informe o novo e-mail: ");
                    email = scanner.nextLine();
                }
                while(persistenciaController.emailIsExists(email)){
                    System.out.println("❌ ERRO: Email informado já existe! ❌");
                    System.out.println("Informe o e-mail: ");
                    email = scanner.nextLine();
                }
                Contato contato = new Contato(id, telefone, nome, email);
                persistenciaController.addContacts(contato);
            }

            if (op == 2) {
                persistenciaController.listContacts();
            }

            if (op == 3) {
                System.out.println("Editar por:\n[1] - ID\n[2] - Telefone");
                int escolhaEdicao = scanner.nextInt();
                scanner.nextLine();

                if (escolhaEdicao == 1) {
                    System.out.println("Informe o ID do contato que deseja alterar: ");
                    int idAntigo = scanner.nextInt();
                    scanner.nextLine();
                    Contato novoContato = new Contato();
                    novoContato.setId(idAntigo);
                    System.out.print("Deseja editar [1] ou excluir [2]? ");
                    int acao = scanner.nextInt();
                    scanner.nextLine();

                    if (acao == 1) {
                        System.out.println("Deseja editar o NOME? [1] - Sim / [2] - Não");
                        int editarNome = scanner.nextInt();
                        scanner.nextLine();
                        if (editarNome == 1) {
                            System.out.println("Informe o novo nome: ");
                            String novoNome = scanner.nextLine();
                            while (novoNome.length() < 5 || !novoNome.contains(" ")) {
                                System.out.println("❌ Nome inválido! Digite o nome completo (nome e sobrenome): ❌");
                                novoNome = scanner.nextLine().trim();
                            }
                            novoContato.setNome(novoNome);
                        }

                        System.out.println("Deseja editar o TELEFONE? [1] - Sim / [2] - Não");
                        int editarTelefone = scanner.nextInt();
                        scanner.nextLine();
                        if (editarTelefone == 1) {
                            System.out.println("Informe o novo telefone: ");
                            Long novoTelefone = scanner.nextLong();
                            while (!String.valueOf(novoTelefone).matches("^\\d{11}$")) {
                                System.out.println("❌ ERRO: Número de telefone inválido! ❌");
                                System.out.println("Informe o novo telefone: ");
                                novoTelefone = scanner.nextLong();
                            }
                            scanner.nextLine();
                            novoContato.setTelefone(novoTelefone);
                        }

                        System.out.println("Deseja editar o E-MAIL? [1] - Sim / [2] - Não");
                        int editarEmail = scanner.nextInt();
                        scanner.nextLine();
                        if (editarEmail == 1) {
                            System.out.println("Informe o novo e-mail: ");
                            String novoEmail = scanner.nextLine();
                            while (!novoEmail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                                System.out.println("❌ ERRO: Email inválido! ❌");
                                System.out.println("Informe o novo e-mail: ");
                                novoEmail = scanner.nextLine();
                            }
                            while (persistenciaController.emailIsExists(novoEmail)) {
                                System.out.println("❌ Email informado já existe! ❌");
                                System.out.println("Informe o novo e-mail: ");
                                novoEmail = scanner.nextLine();
                            }
                            novoContato.setEmail(novoEmail);
                        }

                        persistenciaController.editContacts(idAntigo, novoContato);
                    }else {
                        persistenciaController.removeContacts(idAntigo);
                    }
                }

                if (escolhaEdicao == 2) {
                    System.out.print("Digite o telefone do contato: ");
                    String telefoneBusca = scanner.nextLine();

                    System.out.print("Deseja editar [1] ou excluir [2]? ");
                    int acao = scanner.nextInt();
                    scanner.nextLine();

                    if (acao == 1) {
                        Contato novoContato = new Contato();
                        System.out.println("Deseja editar o nome? [1] - Sim / [2] - Não");
                        int editarNome = scanner.nextInt();
                        scanner.nextLine();
                        if(editarNome == 1) {
                            System.out.print("Novo nome: ");
                            String novoNome = scanner.nextLine();
                            while (novoNome.length() < 5 || !novoNome.contains(" ")) {
                                System.out.println("❌ Nome inválido! Digite o nome completo (nome e sobrenome): ❌");
                                novoNome = scanner.nextLine().trim();
                            }
                            novoContato.setNome(novoNome);
                        }
                        System.out.println("Deseja editar o telefone? [1] - Sim / [2] - Não");
                        int editTelefone = scanner.nextInt();
                        scanner.nextLine();
                        if (editTelefone == 1) {
                            System.out.print("Novo telefone: ");
                            Long novoTelefone = scanner.nextLong();
                            while (!String.valueOf(novoTelefone).matches("^\\d{11}$")) {
                                System.out.println("❌ ERRO: Número de telefone inválido! ❌");
                                System.out.print("Informe o novo telefone: ");
                                novoTelefone = scanner.nextLong();
                            }
                            scanner.nextLine(); // limpa o buffer
                            novoContato.setTelefone(novoTelefone);
                        }
                        System.out.println("Deseja editar o E-MAIL? [1] - Sim / [2] - Não");
                        int editEmail = scanner.nextInt();
                        scanner.nextLine();
                        if (editEmail == 1) {
                            System.out.print("Novo email: ");
                            String novoEmail = scanner.nextLine();
                            while (!novoEmail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                                System.out.println("❌ ERRO: Email inválido! ❌");
                                System.out.print("Informe o novo e-mail: ");
                                novoEmail = scanner.nextLine();
                            }

                            while (persistenciaController.emailIsExists(novoEmail)) {
                                System.out.println("❌ Email informado já existe! ❌");
                                System.out.print("Informe o novo e-mail: ");
                                novoEmail = scanner.nextLine();
                            }

                            novoContato.setEmail(novoEmail);
                        }
                        persistenciaController.editOrRemoveOnNumber(telefoneBusca, true, novoContato);
                    } else {
                        persistenciaController.editOrRemoveOnNumber(telefoneBusca, false, null);
                    }
                }
            }
            if (op == 4){
                System.out.println("Informe o id do contato que deseja alterar: ");
                int idAntigo = scanner.nextInt();
                System.out.println("Deseja realmente remover o contato? [s] - Sim / [n] - Não");
                String opRemoção = scanner.next();
                if (Objects.equals(opRemoção,"s")){
                    persistenciaController.removeContacts(idAntigo);
                }
            }
            if (op == 5){
                System.out.println("Digite o nome do contato: ");
                String nome = scanner.next();
                persistenciaController.finfByName(nome);
            }
            if (op == 6){
                persistenciaController.saveBackup();
            }
            if (op == 7){
                System.out.println("Digite um nome para o arquivo!");
                String nomeOutroArquivo = scanner.next();
                persistenciaController.criarOutroArquivo(nomeOutroArquivo);
            }
            if (op == 8) {
                persistenciaController.listarArquivosExistentes();
            }

            if (op == 9) {
                System.out.println("Digite o nome do arquivo que deseja usar (ex: contatos.txt): ");
                String nomeArquivo = scanner.nextLine();
                persistenciaController.definirArquivo(nomeArquivo);
            }

            if (op == 0) {
                System.out.println("   __\n" +
                        "                |  '>._\n" +
                        "                |_,'  .`-.\n" +
                        "                |/  ,',-`.`.\n" +
                        "                :  \\| (`,\\_\\\n" +
                        "                 \\__ `-:_\\',.\\-.______\n" +
                        "                 /  ``--._(  `. \\`.  `.`-. _\n" +
                        "           __ .-'     `-.  `.  `.\\ `.  `. `.\\\n" +
                        "         ,'.-`.`--.__     `. `.  \\\\. `.  `. \\)\n" +
                        "       ,-'._    `-._ `-..__ :  `. \\ `__`.--`.\\\n" +
                        "      |     `-.     `.  `,.`--.__`.\\/==\\-`---`\\\n" +
                        "      |        `-.   _`-'._`._   `-:\\--'`.     \\\n" +
                        "     _/`,.__     _`-'     ,'  ;.    `.    `.`.  \\\n" +
                        "  _.|/,' \\  `--.'_  `-._.' ,-'  `._   `._   `.;  \\\n" +
                        " \\  `_.-'         `-.__ ,-'        `,   `:.   `.  \\\n" +
                        "  `-'              `-.__       `.`.   `:.   `. \\\n" +
                        "                            `-.__    `'`.   `:.   `.\\\n" +
                        "                                 `-.__   `._   `.   `\\\n" +
                        "                                      `-.__ `.   `.   \\\n" +
                        "                                           `-.:.   `.  \\\n" +
                        "                                               ,`:._ `. \\\n" +
                        "                                              `. _||`:.`.\\\n" +
                        "                                                `.`.`_: `'\n" +
                        "                                                  `-'");
                System.out.println("E N C E R R A N D O . . .");
                break;
            }
        }
        scanner.close();
    }
}
