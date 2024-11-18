import javax.swing.*;
import java.awt.*;

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
        panel.setBackground(Color.BLUE);
        // panel.setBackground(new Color(0xF6F6F6));

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

        openButton = new JButton("Open File");
        openButton.setBounds(20, 420, 300, 60);
        openButton.setBackground(new Color(0xD83E76)); // Initial color for enabled state
        openButton.setForeground(Color.WHITE);
        openButton.setFont(new Font("Arial", Font.BOLD, 12));
        openButton.setFocusable(false);
        panel.add(openButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(20, 500, 300, 60);
        clearButton.setBackground(new Color(0xD83E76)); // Initial color for enabled state
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 12));
        clearButton.setFocusable(false);
        panel.add(clearButton);

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
        fileArea.setBounds(380, 160, 800, 400);
        fileArea.setEditable(false);
        fileArea.setFont(new Font("Courier New", Font.PLAIN, 14));

        // Default instructions
        fileArea.setText(DefaultInstructions.getInstructions());
        
        JScrollPane scrollPane2 = new JScrollPane(fileArea);
        scrollPane2.setBounds(380, 160, 800, 400);
        panel.add(scrollPane2);

        // Add action listeners using the ActionHandler class
        ActionHandler actionHandler = new ActionHandler(openButton, clearButton, fileArea);
        openButton.addActionListener(actionHandler);
        clearButton.addActionListener(actionHandler);

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
