package br.com.thiagosantos.agenda.entities;

public class Contato {
    private int id;
    private String nome;
    private Long telefone;
    private String email;

    public Contato(){

    }

    public Contato(int id,Long telefone, String nome, String email) {
        this.id = id;
        this.telefone = telefone;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void listarContato() {
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
        System.out.println("-----------------------");
    }
}
