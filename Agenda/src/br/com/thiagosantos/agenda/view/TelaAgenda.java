package br.com.thiagosantos.agenda.view;
import br.com.thiagosantos.agenda.controller.PersistenciaController;
import br.com.thiagosantos.agenda.entities.Contato;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAgenda extends JFrame {
    private PersistenciaController persistenciaController = new PersistenciaController();

    private TelaInicial telaInicial;

    public TelaAgenda(TelaInicial telaInicial) {

        // Característica do JFrame
        setTitle("Agenda Contatos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Característica do JPanel
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // Layout vertical

        // Ícone dos botões
        ImageIcon iconeVoltar = new ImageIcon("C:\\Users\\thiago.santos\\IdeaProjects\\AnaliseProjetoDeSistemas\\Agenda\\src\\br\\com\\thiagosantos\\agenda\\sources\\seta-esquerda.png");

        // Características dos botões
        JButton jbAdicionarContato = new JButton("Adicionar Contato");
        JButton jbListarContato = new JButton("Listar Contato");
        JButton jbRemoverContato = new JButton("Remover Contato");
        JButton jbEditarContato = new JButton("Editar Contato");
        JButton jbVoltarTelaInicial = new JButton(iconeVoltar);

        // Adicionando os botões ao painel
        JButton[] botoes = {jbAdicionarContato, jbListarContato, jbRemoverContato, jbEditarContato, jbVoltarTelaInicial};

        for (JButton botao : botoes) {
            botao.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza os botões
            botao.setMaximumSize(new Dimension(200, 100)); // Tamanho máximo
            botao.setMinimumSize(new Dimension(100, 50)); // Tamanho mínimo
            painel.add(botao); // Adiciona os botões ao JPanel
            painel.add(Box.createVerticalStrut(20)); // Espaçamento vertical
        }

        // Ações dos botões
        jbAdicionarContato.addActionListener(e -> adicionarContato());

        jbVoltarTelaInicial.addActionListener(e -> {
            TelaInicial voltarTelaInicial = new TelaInicial();
            setVisible(true);
            this.setVisible(false);
        });

        jbListarContato.addActionListener(e -> listingContacs());
        /*
        // Botão para ocultar/mostrar os botões
        JButton jbToggleSidebar = new JButton("Ocultar/Mostrar Botões");
        jbToggleSidebar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Alterna a visibilidade do painel
                painel.setVisible(!painel.isVisible());
                // Revalida e repinta o frame para atualizar o layout
                revalidate();
                repaint();
            }
        });
        jbToggleSidebar.setPreferredSize(new Dimension(100,100));
        // Adiciona o botão de alternância ao JFrame
        add(jbToggleSidebar, BorderLayout.NORTH); // Adiciona o botão no topo
         */
        add(painel, BorderLayout.WEST); // Adiciona o JPanel ao JFrame
        setVisible(true); // Torna a janela visível
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
    private void listingContacs() {
        String lista = persistenciaController.listContacts();


        if (lista == null) {
            JOptionPane.showMessageDialog(null, "Nenhum contato encontrado.");
        } else {
            JTextArea textArea = new JTextArea(lista);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(350, 200));
            JOptionPane.showMessageDialog(null, scrollPane, "Agenda de Contatos", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
