package br.com.thiagosantos.agenda.entities;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Persistencia {
    private String arquivo = "contatos.txt";
    private String arquivoBackup = "backup_contato.txt";
    private Contato contato;
    private String novoArquivo;

    public Persistencia(String novoArquivo) {
        this.novoArquivo = novoArquivo;
    }
    public Persistencia() {

    }
    public Persistencia(Contato contato) {
        this.contato = new Contato();
    }

    public boolean criarArquivo() {
        File file = new File(arquivo);
        try {
            if (validaArquivo(arquivo)){
                return false;
            }else{
                file.createNewFile();
                System.out.println("✅ Arquivo criado com sucesso! ✅");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Contato inserir(Contato contato) {
        BufferedWriter bf = null;
        try {
            bf = new BufferedWriter(new FileWriter(arquivo, true));
            String linha = "ID: " + contato.getId() + "; Nome: " + contato.getNome() + "; Telefone: " + contato.getTelefone() + "; Email: " + contato.getEmail() + ";";
            bf.write(linha);
            bf.newLine();

            System.out.println("✅ Contato inserido com sucesso. ✅");
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contato;
    }

    public String listarContatos() {
        StringBuilder sb = new StringBuilder();
        String[] parts = null;
        try (BufferedReader buffer = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            System.out.println("-------📋Lista de Contatos📋-------");

            while ((linha = buffer.readLine()) != null) {
                parts = linha.split(";");

                for (String part : parts) {
                    System.out.println(part.trim());
                }
                System.out.println("----------------------------");
            }
        } catch (IOException e) {
            System.out.println("❌ Erro ao ler contatos: ❌" + e.getMessage());
        }
        return Arrays.toString(parts);
    }

    public void editarContato(int id, Contato novoContato) {
        List<String> linhas = new ArrayList<>();
        boolean contatoEncontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("ID: " + id + ";")) {
                    String[] partes = linha.split(";");

                    String idString = partes[0].split(": ")[1].trim();
                    int idAtual = novoContato.getId();
                    try {
                        idAtual = Integer.parseInt(idString);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Erro: ID inválido! ❌");
                        return;
                    }
                    String nomeAtual = partes[1].split(": ")[1].trim();
                    Long telefoneAtual = Long.valueOf(partes[2].split(": ")[1].trim());
                    String emailAtual = partes[3].split(": ")[1].trim();

                    int idNovo;
                    if (novoContato.getId() != 0) {
                        idNovo = novoContato.getId();
                    } else {
                        idNovo = idAtual;
                    }
                    String nomeNovo = Objects.requireNonNullElse(novoContato.getNome(), nomeAtual);
                    Long telefoneNovo = Objects.requireNonNullElse(novoContato.getTelefone(), telefoneAtual);
                    String emailNovo = Objects.requireNonNullElse(novoContato.getEmail(), emailAtual);


                    linha = "ID: " + idNovo + "; Nome: " + nomeNovo + "; Telefone: " + telefoneNovo + "; Email: " + emailNovo + ";";
                    contatoEncontrado = true;
                }
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("❌ Erro ao ler o arquivo: ❌" + e.getMessage());
        }

        if (!contatoEncontrado) {
            System.out.println("❌ Contato não encontrado! ❌");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
            System.out.println("✅ Contato atualizado com sucesso. ✅");
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar alterações: ❌" + e.getMessage());
        }
    }
    public void removerContato(int idParaRemover) {
        List<String> linhas = new ArrayList<>();
        boolean contatoEncontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("ID: " + idParaRemover)) {
                    contatoEncontrado = true;
                    continue;
                }
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!contatoEncontrado) {
            System.out.println("❌ Contato não encontrado! ❌");
        }
        if (contatoEncontrado == true){
            System.out.println("✅ Contato removido com sucesso! ✅");
        }
        // Escreve novamente o arquivo com as linhas restantes (sem o contato removido)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public boolean emailIsEmpty(String email){
        try{
            BufferedReader bf = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = bf.readLine()) != null){
                if (linha.contains("Email: "+email)){
                    return true;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
    public String buscarNome(String nomeAValidar) {
        boolean encontrado = false;
        String linha = null;
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                System.out.println("╔════════════════════════════════════════════╗");
                System.out.println("║             📋 LISTA CONTATOS 📋           ║");
                System.out.println("╠════════════════════════════════════════════╣");
                for (String parte : partes) {
                    if (parte.trim().toLowerCase().startsWith("nome:")) {
                        String nome = parte.replace("Nome:", "").trim();
                        if (nome.toLowerCase().startsWith(nomeAValidar.toLowerCase())) {
                            System.out.println(linha);
                            encontrado = true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Erro ao ler o arquivo: ❌" + e.getMessage());
        }

        if (!encontrado) {
            System.out.println("❌ Nenhum nome encontrado para sugestão. ❌");
        }
        return linha;
    }
    public void editarOuExcluirPorTelefone(String telefoneBusca, boolean editar, Contato novoContato) {
        List<String> linhas = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.contains("Telefone: " + telefoneBusca)) {
                    encontrado = true;
                    if (editar) {
                        String[] partes = linha.split(";");

                        String idString = partes[0].split(": ")[1].trim();
                        int idAtual = novoContato.getId();
                        try {
                            idAtual = Integer.parseInt(idString);
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Erro: ID inválido! ❌");
                            return;
                        }
                        String nomeAtual = partes[1].split(": ")[1].trim();
                        Long telefoneAtual = Long.valueOf(partes[2].split(": ")[1].trim());
                        String emailAtual = partes[3].split(": ")[1].trim();

                        int idNovo = novoContato.getId() != 0  ? novoContato.getId() : idAtual;
                        String nomeNovo = Objects.requireNonNullElse(novoContato.getNome(), nomeAtual);
                        Long telefoneNovo = Objects.requireNonNullElse(novoContato.getTelefone(), telefoneAtual);
                        String emailNovo = Objects.requireNonNullElse(novoContato.getEmail(), emailAtual);

                        linha = "ID: " + idNovo
                                + "; Nome: " + nomeNovo
                                + "; Telefone: " + telefoneNovo
                                + "; Email: " + emailNovo + ";";
                        System.out.println("✅ Contato atualizado com sucesso! ✅");
                    } else {
                        System.out.println("✅ Contato excluído com sucesso! ✅");
                        continue;
                    }
                }
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("❌ Erro ao ler o arquivo: ❌" + e.getMessage());
            return;
        }

        if (!encontrado) {
            System.out.println("❌ Contato não encontrado pelo telefone. ❌");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (String l : linhas) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar alterações: ❌" + e.getMessage());
        }
    }
    public void exportarBackup() {
        Path origem = Paths.get(arquivo);
        Path destino = Paths.get(arquivoBackup); // Cria um objeto Path que representa o arquivo de backup (destino)

        try {
            Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING );
            System.out.println("✅ Backup exportado com sucesso para "+destino+" ✅");
        } catch (IOException e) {
            System.out.println("❌ Erro ao exportar backup: ❌" + e.getMessage());
        }
    }

    public int getProximoId() {
        if (contato == null) {
            return 1;
        }

        return contato.getId() + 1;
    }


    public boolean validaArquivo(String arquivo){
        File file = new File(arquivo);
        if (file.exists()){
            return true;
        }
        return false;
    }
    public void criarOutroArquivo(String novoArquivo){
        File file = new File(novoArquivo);
        try {
            if (file.exists()){
                System.out.println("❌ ERRO: Este nome de arquivo já existe! ❌");
            }else{
                file.createNewFile();
                System.out.println("✅ Arquivo criado com sucesso! ✅");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void definirArquivo(String nomeArquivo) {
        if (nomeArquivo == null || nomeArquivo.trim().isEmpty()) {
            System.out.println("❌ Nome de arquivo inválido!");
            return;
        }

        this.arquivo = nomeArquivo.trim();

        File file = new File(this.arquivo);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("✅ Arquivo '" + this.arquivo + "' criado com sucesso.");
            } catch (IOException e) {
                System.out.println("❌ Erro ao criar o arquivo '" + this.arquivo + "': " + e.getMessage());
            }
        } else {
            System.out.println("📁 Agora usando o arquivo existente: " + this.arquivo);
        }
    }
    public void listarArquivosExistentes() {
        File diretorio = new File("."); // Diretório atual
        File[] arquivos = diretorio.listFiles((dir, nome) -> nome.endsWith(".txt"));

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("📁 Nenhum arquivo .txt encontrado no diretório atual.");
            return;
        }

        System.out.println("📂 Arquivos disponíveis no diretório atual:");
        for (File arquivo : arquivos) {
            System.out.println(" - " + arquivo.getName());
        }
    }
    public String getArquivoAtual() {
        return this.arquivo;
    }
}
