package br.com.thiagosantos.agenda.view;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        setTitle("Agenda de Contatos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));

        JButton btnEntrar = new JButton("Entrar");
        JButton btnEncerrar = new JButton("Encerrar");

        btnEntrar.setPreferredSize(new Dimension(200, 50));
        btnEncerrar.setPreferredSize(new Dimension(200, 50));

        add(btnEntrar);
        add(btnEncerrar);

        btnEntrar.addActionListener(e -> {
            TelaAgenda telaAgenda = new TelaAgenda(this);
            telaAgenda.setVisible(true);
            this.setVisible(false);
        });

        // Ação do botão Encerrar
        btnEncerrar.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}