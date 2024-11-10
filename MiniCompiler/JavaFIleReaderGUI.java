import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main extends JFrame {
    private JTextArea textArea1, textArea2;
    private JButton openButton;
    private JPanel currentPanel;

    public Main() {
        initComponents();
    }

    // Initializing UI Components
    private void initComponents() {
        setTitle("File Reader");
        setSize(new Dimension(1280, 720));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        currentPanel = mainPanel();
        add(currentPanel);
    }

    // mainPanel serves as a container
    public JPanel mainPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 1280, 720);
        panel.setBackground(new Color(255, 192, 203));
        panel.add(contentPanel());
        return panel;
    }

    // contentPanel serves as a holder of UI elements
    public JPanel contentPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(30, 30, 1200, 620);
        panel.setBackground(new Color(255, 192, 203));

        openButton = new JButton("Open Java File");
        openButton.setBounds(10, 50, 500, 60);
        openButton.setBackground(new Color(0, 0, 0));
        openButton.setForeground(Color.WHITE);
        openButton.setFont(new Font("Arial", Font.BOLD, 12));
        openButton.setFocusable(false);
        panel.add(openButton);

        // Result text area
        textArea1 = new JTextArea();
        textArea1.setBounds(540, 50, 500, 60);
        textArea1.setEditable(false);
        textArea1.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        scrollPane1.setBounds(540, 50, 1180, 60);
        panel.add(scrollPane1);

        // Open file area
        textArea2 = new JTextArea();
        textArea2.setBounds(540, 130, 1180, 480);
        textArea2.setEditable(false);
        textArea2.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane2 = new JScrollPane(textArea2);
        scrollPane2.setBounds(540, 130, 1180, 480);
        panel.add(scrollPane2);

        openButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(Main.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    FileReaderUtility fileReaderUtility = new FileReaderUtility(textArea2); // Calls readFile in the FileReaderUtility class
                    fileReaderUtility.readFile(file);
                    openButton.setEnabled(false); // Disable the button after opening a file
                }
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
