package br.com.thiagosantos.agenda.view;

import br.com.thiagosantos.agenda.controller.PersistenciaController;
import br.com.thiagosantos.agenda.controller.PersistenciaController2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaListingContacts extends JFrame {
    private PersistenciaController2 persistenciaController = new PersistenciaController2();

    private TelaAgenda telaAgenda;

    public TelaListingContacts(TelaAgenda telaAgenda){

        //Caracteristica do JFrame
        setTitle("Agenda Contatos");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Caracteristica do JPanel
        JPanel painel = new JPanel();
        painel.setSize(300,200);
        painel.setLayout(new BorderLayout());
        painel.setBackground(Color.GRAY);

        listingContacs();

        add(painel);
        setVisible(true);
    }
    private void listingContacs() {
        PersistenciaController persistenciaController = new PersistenciaController();
        String lista = persistenciaController.listContacts();

        System.out.println("Lista recebida: ");
        System.out.println(lista); // deve imprimir os mesmos dados do terminal

        if (lista == null || lista.isEmpty()) {
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
