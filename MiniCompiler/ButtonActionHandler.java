import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class ButtonActionHandler implements ActionListener {
    private Main main;
    private JTextArea fileArea, resultArea;
    private JButton openButton, clearButton, credits, switchMode, lexicalButton, syntaxButton, semanticButton;

    // Constructor that takes the main GUI components
    public ButtonActionHandler(Main main, JTextArea fileArea, JTextArea resultArea, 
                               JButton openButton, JButton clearButton, JButton credits, JButton switchMode,
                               JButton lexicalButton, JButton syntaxButton, JButton semanticButton) {
        this.main = main;
        this.fileArea = fileArea;
        this.resultArea = resultArea;
        this.openButton = openButton;
        this.clearButton = clearButton;
        this.credits = credits;
        this.switchMode = switchMode;
        this.lexicalButton = lexicalButton;
        this.syntaxButton = syntaxButton;
        this.semanticButton = semanticButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source == openButton) {
            openFile();
        } else if (source == clearButton) {
            clearFile();
        } else if (source == credits) {
            showCredits();
        } else if (source == switchMode) {
            switchMode();
        } else if (source == lexicalButton) {
            performLexicalAnalysis();
        } else if (source == syntaxButton) {
            performSyntaxAnalysis();
        } else if (source == semanticButton) {
            performSemanticAnalysis();
        }
    }

    // Handle openButton action
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(main);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Only accepts *.java files
            if (isValidFile(file)) {
            FileReaderUtility fileReaderUtility = new FileReaderUtility(fileArea);
            fileReaderUtility.readFile(file);
            openButton.setEnabled(false);
            clearButton.setEnabled(true);
            lexicalButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(main, "Invalid file format!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isValidFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".java");
    }

    // Handle clearButton action
    private void clearFile() {
        fileArea.setText(""); // Clears the text in fileArea
        resultArea.setText(""); // Clears the text in resultArea
        openButton.setEnabled(true); // Re-enables the openButton to choose a new file

        // All other buttons are disabled
        clearButton.setEnabled(false);
        lexicalButton.setEnabled(false);
        syntaxButton.setEnabled(false);
        semanticButton.setEnabled(false);
    }

    private void showCredits() {
        // Insert showCredits functionality
    }

    private void switchMode() {
        // Insert switchMode functionality
    }

    // Placeholder for lexical analysis
    private void performLexicalAnalysis() {
        // Insert lexical analysis functionality here
        appendResult("LEXICAL ANALYSIS");
        lexicalButton.setEnabled(false);
        syntaxButton.setEnabled(true);
    }

    private void performSyntaxAnalysis() {
        // Insert syntax analysis functionality here
        appendResult("SYNTAX ANALYSIS");
        syntaxButton.setEnabled(false);
        semanticButton.setEnabled(true);
    }

    private void performSemanticAnalysis() {
        // Insert semantic analysis functionality here
        appendResult("SEMANTIC ANALYSIS");
        semanticButton.setEnabled(false);
    }

    // Method to append text to the resultArea
    private void appendResult(String message) {
        resultArea.setText("");
        resultArea.setForeground(new Color(0X129F57));
        resultArea.append("\nYour code has passed the\n" + message + "!");
    }
}
