package compiler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main extends JFrame {
    private JLabel phases, result;
    private JTextArea resultArea, fileArea;
    private JButton openButton, clearButton, lexicalButton, syntaxButton, semanticButton;
    private JPanel mainPanel;

    public Main() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Java Mini Compiler");
        setSize(new Dimension(1280, 720));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = mainPanel();
        add(mainPanel);
    }

    public JPanel mainPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 1280, 720);
        panel.setBackground(new Color(0xF6F6F6));

        panel.add(contentPanel());

        return panel;
    }

    public JPanel contentPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(30, 30, 1200, 620);
        // panel.setBackground(Color.BLUE);
        panel.setBackground(new Color(0xF6F6F6));

        openButton = new JButton("Open File");
        openButton.setBounds(20, 420, 300, 60);
        openButton.setBackground(new Color(0, 0, 0));
        openButton.setForeground(Color.WHITE);
        openButton.setFont(new Font("Arial", Font.BOLD, 12));
        openButton.setFocusable(false);
        panel.add(openButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(20, 500, 300, 60);
        clearButton.setBackground(Color.BLACK);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 12));
        clearButton.setFocusable(false);
        panel.add(clearButton);

        phases = new JLabel("Phases:");
        phases.setBounds(20, 5, 300, 60);
        phases.setForeground(Color.BLACK);
        phases.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(phases);

        lexicalButton = new JButton("Lexical Analysis");
        lexicalButton.setBounds(20, 45, 300, 60);
        lexicalButton.setBackground(Color.BLACK);
        lexicalButton.setForeground(Color.WHITE);
        lexicalButton.setFont(new Font("Arial", Font.BOLD, 12));
        lexicalButton.setFocusable(false);
        panel.add(lexicalButton);

        syntaxButton = new JButton("Syntax Analysis");
        syntaxButton.setBounds(450, 45, 300, 60);
        syntaxButton.setBackground(Color.BLACK);
        syntaxButton.setForeground(Color.WHITE);
        syntaxButton.setFont(new Font("Arial", Font.BOLD, 12));
        syntaxButton.setFocusable(false);
        panel.add(syntaxButton);

        semanticButton = new JButton("Semantic Analysis");
        semanticButton.setBounds(880, 45, 300, 60);
        semanticButton.setBackground(Color.BLACK);
        semanticButton.setForeground(Color.WHITE);
        semanticButton.setFont(new Font("Arial", Font.BOLD, 12));
        semanticButton.setFocusable(false);
        panel.add(semanticButton);

        // Result text area
        result = new JLabel("Result:");
        result.setBounds(20, 120, 300, 60);
        result.setForeground(Color.BLACK);
        result.setFont(new Font("Arial", Font.BOLD, 15));

        resultArea = new JTextArea();
        resultArea.setBounds(20, 160, 300, 180);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane1 = new JScrollPane(resultArea);
        scrollPane1.setBounds(20, 160, 300, 180);

        panel.add(result);
        panel.add(scrollPane1);

        // Open file area
        fileArea = new JTextArea();
        fileArea.setBounds(380, 160, 800, 450);
        fileArea.setEditable(false);
        fileArea.setFont(new Font("Arial", Font.PLAIN, 14));

        // Default instructions
        // openButton is functional; user may opt to clear the fileArea
        fileArea.setText("Welcome to our Mini Java Compiler!\n\n"
            + "Start by opening a Java file by clicking the “Open File” button.\n\n"
            + "Each button will only be enabled if a certain step is completed or terminated.\n\n"
            + "The result will be displayed above this code text area.\n\n"
            + "If you want to clear all the syntax and result, just click the “Clear” button.");
        
        JScrollPane scrollPane2 = new JScrollPane(fileArea);
        scrollPane2.setBounds(380, 160, 800, 450);
        panel.add(scrollPane2);

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(Main.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    FileReaderUtility fileReaderUtility = new FileReaderUtility(fileArea); // Calls readFile in the FileReaderUtility class
                    fileReaderUtility.readFile(file);
                    openButton.setEnabled(false); // Disable the button after opening a file
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileArea.setText(""); // Clears the text in fileArea
                openButton.setEnabled(true); // Re-enables the openButton to choose a new file
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
