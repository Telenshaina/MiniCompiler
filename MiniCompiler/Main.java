import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    private JLabel phases, result;
    private JTextArea resultArea, fileArea;
    private JButton lexicalButton, syntaxButton, semanticButton, openButton, clearButton;

    public Main() {
        initComponents();
    }

    // Initializing UI Components
    private void initComponents() {
        setTitle("Java Mini Compiler");
        setSize(new Dimension(1280, 720));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(mainPanel());
    }

    // mainPanel serves as a container
    public JPanel mainPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 1280, 720);
        panel.setBackground(new Color(0xF6D4EB));
        panel.add(contentPanel());

        return panel;
    }

    // contentPanel serves as a holder of UI elements
    public JPanel contentPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(30, 30, 1200, 620);
        panel.setBackground(new Color(0xF6D4EB));

        

        // Compiler Phases Buttons
        phases = UIHelper.createLabel("Phases:", 20, 45, 75, 30);
        panel.add(phases);

        lexicalButton = UIHelper.createButton("Lexical Analysis", 20, 75, 300, 60);
        lexicalButton.setEnabled(false);
        panel.add(lexicalButton);

        syntaxButton = UIHelper.createButton("Syntax Analysis", 450, 75, 300, 60);
        syntaxButton.setEnabled(false);
        panel.add(syntaxButton);

        semanticButton = UIHelper.createButton("Semantic Analysis", 880, 75, 300, 60);
        semanticButton.setEnabled(false);
        panel.add(semanticButton);

        // Result text area
        result = UIHelper.createLabel("Result:", 20, 160, 75, 30);

        resultArea = new JTextArea();
        resultArea.setBounds(20, 190, 300, 180);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Verdana", Font.BOLD, 16));
        resultArea.setBorder(BorderFactory.createLineBorder(new Color(0xD83E76), 1)); // Add border
        JScrollPane scrollPane1 = new JScrollPane(resultArea);
        scrollPane1.setBounds(20, 190, 300, 180);

        panel.add(result);
        panel.add(scrollPane1);

        // Open File Button
        openButton = UIHelper.createButton("Open File", 20, 450, 300, 60);
        panel.add(openButton);

        // Clear Button
        clearButton = UIHelper.createButton("Clear", 20, 530, 300, 60);
        clearButton.setEnabled(false); // Disabled by default, even with the default instructions is displayed
        panel.add(clearButton);

        // Open file area
        fileArea = new JTextArea();
        fileArea.setBounds(380, 190, 800, 400);
        fileArea.setEditable(false);
        fileArea.setFont(new Font("Courier New", Font.PLAIN, 16));
        fileArea.setBorder(BorderFactory.createLineBorder(new Color(0xD83E76), 1)); // Add border

        // Default instructions
        fileArea.setText(DefaultInstructions.getInstructions());
        
        JScrollPane scrollPane2 = new JScrollPane(fileArea);
        scrollPane2.setBounds(380, 190, 800, 400);
        panel.add(scrollPane2);

        ButtonActionHandler actionHandler = new ButtonActionHandler(this, fileArea, resultArea, openButton, clearButton, 
                                                                    lexicalButton, syntaxButton, semanticButton);
        openButton.addActionListener(actionHandler);
        clearButton.addActionListener(actionHandler);
        lexicalButton.addActionListener(actionHandler);
        syntaxButton.addActionListener(actionHandler);
        semanticButton.addActionListener(actionHandler);

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
