import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Main extends JFrame {
    private JLabel phases, result;
    private JTextArea resultArea, fileArea;
    private JButton lexicalButton, syntaxButton, semanticButton, openButton, clearButton, credits, switchMode;

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

        panel.add(headerPanel());
        panel.add(contentPanel());

        return panel;
    }

    // headerPanel serves as a holder of UI elements in the header
    public JPanel headerPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 1280, 60);
        panel.setBackground(new Color(0xD83E76));

        credits = UIHelper.createHeaderComponent("Credits", 925, 15, 100, 30);
        panel.add(credits);

        switchMode = UIHelper.createHeaderComponent("Switch to Dark Mode", 1035, 15, 190, 30);
        panel.add(switchMode);

        return panel;
    }

    // contentPanel serves as a holder of UI elements
    public JPanel contentPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(30, 30, 1200, 620);
        panel.setBackground(new Color(0xF6D4EB));

        // Compiler Phases Buttons
        phases = UIHelper.createLabel("Phases:", 20, 50, 75, 30);
        panel.add(phases);

        lexicalButton = UIHelper.createButton("Lexical Analysis", 20, 80, 300, 60);
        lexicalButton.setEnabled(false);
        panel.add(lexicalButton);

        syntaxButton = UIHelper.createButton("Syntax Analysis", 450, 80, 300, 60);
        syntaxButton.setEnabled(false);
        panel.add(syntaxButton);

        semanticButton = UIHelper.createButton("Semantic Analysis", 880, 80, 300, 60);
        semanticButton.setEnabled(false);
        panel.add(semanticButton);

        // Result text area
        result = UIHelper.createLabel("Result:", 20, 165, 75, 30);

        resultArea = new JTextArea();
        resultArea.setBounds(20, 195, 300, 180);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Verdana", Font.BOLD, 16));
        resultArea.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(0xD83E76), 1), // Add border
                new EmptyBorder(10, 10, 10, 10))); // Set margins
        JScrollPane scrollPane1 = new JScrollPane(resultArea);
        scrollPane1.setBounds(20, 195, 300, 180);

        panel.add(result);
        panel.add(scrollPane1);

        // Open File Button
        openButton = UIHelper.createButton("Open File", 20, 455, 300, 60);
        panel.add(openButton);

        // Clear Button
        clearButton = UIHelper.createButton("Clear", 20, 535, 300, 60);
        clearButton.setEnabled(false); // Disabled by default, even with the default instructions is displayed
        panel.add(clearButton);

        // Open file area
        fileArea = new JTextArea();
        fileArea.setBounds(380, 195, 800, 400);
        fileArea.setEditable(false);
        fileArea.setFont(new Font("Courier New", Font.PLAIN, 15));
        fileArea.setForeground(new Color(0x3D052B));
        fileArea.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(0xD83E76), 1), // Add border
                new EmptyBorder(10, 10, 10, 10))); // Set margin

        // Default instructions
        fileArea.setText(DefaultInstructions.getInstructions());
        
        JScrollPane scrollPane2 = new JScrollPane(fileArea);
        scrollPane2.setBounds(380, 195, 800, 400);
        panel.add(scrollPane2);

        ButtonActionHandler actionHandler = new ButtonActionHandler(this, fileArea, resultArea, openButton, clearButton, 
                                                                    credits, switchMode, lexicalButton, syntaxButton, semanticButton);
        openButton.addActionListener(actionHandler);
        clearButton.addActionListener(actionHandler);
        credits.addActionListener(actionHandler);
        switchMode.addActionListener(actionHandler);
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
