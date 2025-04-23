package br.com.thiagosantos.agenda.controller;

import br.com.thiagosantos.agenda.entities.Contato;
import br.com.thiagosantos.agenda.entities.Persistencia;

public class PersistenciaController2 {
    private Persistencia persistenciaEntities;

    public PersistenciaController2(){
        this.persistenciaEntities = new Persistencia();
    }

    public void addContacts(Contato contato){
        persistenciaEntities.inserir(contato);
    }

    public String listContacts(){
        String response = persistenciaEntities.listarContatos();
        return response;
    }

    public void editContacts(int id, Contato contato){
        persistenciaEntities.editarContato(id,contato);
    }

    public void removeContacts(int id){
        persistenciaEntities.removerContato(id);
    }

    public boolean emailIsExists(String email){
        if (persistenciaEntities.emailIsEmpty(email)){
            return true;
        }else{
            return false;
        }
    }
    public String finfByName(String name){
        String response = persistenciaEntities.buscarNome(name);
        return response;
    }
    public void editOrRemoveOnNumber(String telefoneBusca, boolean editar, Contato novoContato){
        persistenciaEntities.editarOuExcluirPorTelefone(telefoneBusca,editar,novoContato);
    }
    public void saveBackup(){
        persistenciaEntities.exportarBackup();
    }
    public void criarArquivo(){
        persistenciaEntities.criarArquivo();
    }
    public int getNextId(){
        int response = persistenciaEntities.getProximoId();
        return response;
    }
    public void criarOutroArquivo(String novoArquivo){
        persistenciaEntities.criarOutroArquivo(novoArquivo);
    }
    public void definirArquivo(String nomeArquivo){
        this.persistenciaEntities.definirArquivo(nomeArquivo);
    }
    public void listarArquivosExistentes(){
        this.persistenciaEntities.listarArquivosExistentes();
    }
    public String getArquivoAtual() {
        return persistenciaEntities.getArquivoAtual();
    }
}
