package br.com.thiagosantos.agenda.view;
import br.com.thiagosantos.agenda.controller.PersistenciaController;
import br.com.thiagosantos.agenda.controller.PersistenciaController2;
import br.com.thiagosantos.agenda.entities.Contato;
import br.com.thiagosantos.agenda.entities.Persistencia2;

import javax.swing.*;
import java.awt.*;

public class TelaAgenda extends JFrame {
    private PersistenciaController2 persistenciaController = new PersistenciaController2();

    private TelaInicial telaInicial;

    public TelaAgenda(TelaInicial telaInicial) {

        //Caracteristica do JFrame
        setTitle("Agenda Contatos");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Caracteristica do JPanel
        JPanel painel = new JPanel();
        painel.setSize(300,200);
        painel.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
        painel.setBackground(Color.GRAY);

        //Icone botoes
        ImageIcon iconeVoltar = new ImageIcon("C:\\Users\\thiago.santos\\IdeaProjects\\AnaliseProjetoDeSistemas\\Agenda\\src\\br\\com\\thiagosantos\\agenda\\sources\\seta-esquerda.png");


        //Caracteristicas dos botoes
        JButton jbAdicionarContato = new JButton("Adicionar Contato");
        JButton jbListarContato = new JButton("Listar Contato");
        JButton jbRemoverContato = new JButton("Remover Contato");
        JButton jbEditarContato = new JButton("Editar Contato");
        JButton jbVoltarTelaInicial = new JButton(iconeVoltar);
        JButton[] botoes = {jbAdicionarContato,jbListarContato,jbRemoverContato,jbEditarContato,jbVoltarTelaInicial};

        for (JButton botao : botoes) {
            botao.setPreferredSize(new Dimension(100, 100));
            painel.add(botao); // Adiciona os botoes ao JPanel
            painel.add(Box.createVerticalStrut(10));
        }

        //Ações dos botoes
        jbAdicionarContato.addActionListener(e -> adicionarContato());

        jbVoltarTelaInicial.addActionListener(e -> {
            TelaInicial voltarTelaInicial = new TelaInicial();
            setVisible(true);
            this.setVisible(false);
        });

        jbListarContato.addActionListener(e -> {
            TelaListingContacts tLc = new TelaListingContacts(this);
            setVisible(true);
            this.setVisible(false);
        });


        //Ações do JFrame, para adicionar o painel e deixar a janela visivel
        add(painel); //Adiciona o JPanel ao JFrame;
        setVisible(true);
    }
    private void adicionarContato(){

        int id = persistenciaController.getNextId();

        String nome = JOptionPane.showInputDialog("Digite o nome do contato: (nome e sobrenome)");
        while(nome == null || !nome.contains("")){
            JOptionPane.showMessageDialog(null, "ERRO: Nome inválido!");
            nome = JOptionPane.showInputDialog("Digite o nome do contato:");
        }
        while(!nome.matches("^[^\\d]*$") || !nome.matches("^[a-zA-ZÀ-ÿ\\s]*$")){
            JOptionPane.showMessageDialog(null, "ERRO: nome não pode conter números ou caracteres especiais!");
            nome = JOptionPane.showInputDialog("Digite o nome do contato:");
        }
        String telefone = JOptionPane.showInputDialog("Digite seu número de telefone");
        while(!telefone.matches("\\d{11}")){
            JOptionPane.showMessageDialog(null, "ERRO: Número de telefone inválido!");
            telefone = JOptionPane.showInputDialog("Digite seu número de telefone");
        }

        String email = JOptionPane.showInputDialog("Informe o email:");
        while (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$") || persistenciaController.emailIsExists(email)) {
            if (email != null && persistenciaController.emailIsExists(email)) {
                JOptionPane.showMessageDialog(this, "Email já existente.");
            } else {
                JOptionPane.showMessageDialog(this, "Email inválido.");
            }
            email = JOptionPane.showInputDialog("Informe o email:");
        }
        while(persistenciaController.emailIsExists(email)){
            JOptionPane.showMessageDialog(null, "ERRO: E-mail informado já existe!");
            email = JOptionPane.showInputDialog("Digite o e-mail do contato: ");
        }

        Contato contato = new Contato(id, Long.parseLong(telefone), nome, email);
        persistenciaController.addContacts(contato);
        JOptionPane.showMessageDialog(this, "Contato adicionado com sucesso!");
    }
}
