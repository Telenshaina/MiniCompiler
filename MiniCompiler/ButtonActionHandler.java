package compiler;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import javax.swing.*;

public class ButtonActionHandler implements ActionListener {
    private Main main;
    private JTextArea fileArea, resultArea;
    private JButton openButton, clearButton, credits, lexicalButton, syntaxButton, semanticButton;

    // Constructor to initialize GUI components
    public ButtonActionHandler(Main main, JTextArea fileArea, JTextArea resultArea, 
                               JButton openButton, JButton clearButton, JButton credits,
                               JButton lexicalButton, JButton syntaxButton, JButton semanticButton) {
        this.main = main;
        this.fileArea = fileArea;
        this.resultArea = resultArea;
        this.openButton = openButton;
        this.clearButton = clearButton;
        this.credits = credits;
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

            if (isValidFile(file)) {
                FileReaderUtility fileReaderUtility = new FileReaderUtility(fileArea);
                fileReaderUtility.readFile(file);

                openButton.setEnabled(false);
                clearButton.setEnabled(true);
                lexicalButton.setEnabled(true);
            } else {
                CustomPopUp.showInvalidFileMessage(main);
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

        // Disable other buttons
        clearButton.setEnabled(false);
        lexicalButton.setEnabled(false);
        syntaxButton.setEnabled(false);
        semanticButton.setEnabled(false);
    }

    private void showCredits() {
        CustomPopUp.showCreditsMessage(main);
    }

    // Perform lexical analysis
    private void performLexicalAnalysis() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        String code = fileArea.getText();
    
        // Split the code into lines for processing
        String[] lines = code.split("\n");
        StringBuilder resultBuilder = new StringBuilder();
        boolean hasError = false;  // Track if there's at least one error
    
        try {
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].trim();
                if (!line.isBlank()) {
                    try {
                        // Try to tokenize the line
                        List<String> tokens = lexicalAnalyzer.tokenize(line);
                        resultBuilder.append("Line " + (i + 1) + ": \"").append(line).append("\"\n");
                        resultBuilder.append("Tokens: ").append(tokens).append("\n");
                        resultBuilder.append("No error\n\n");  // No error for this line
                    } catch (Exception e) {
                        // If an error occurs, show the error message for this line
                        resultBuilder.append("Line " + (i + 1) + ": \"").append(line).append("\"\n");
                        resultBuilder.append("Error: ").append(e.getMessage()).append("\n\n");
                        hasError = true;  // At least one line has an error
                    }
                }
            }
    
            // If any error occurred, set result to red and disable the syntax button
            if (hasError) {
                resultArea.setForeground(Color.RED);
                resultBuilder.append("LEXICAL ANALYSIS FAILED\n\n");
                resultArea.setText(resultBuilder.toString());
                syntaxButton.setEnabled(false);  // Disable the syntax button on error
            } else {
                // If no errors, set result to green and show success
                resultArea.setForeground(new Color(0x129F57));  // Green color for success
                resultBuilder.append("Your Code has Passed the\nLEXICAL ANALYSIS\n\n");
                resultArea.setText(resultBuilder.toString());
                syntaxButton.setEnabled(true);  // Enable the syntax button
            }
            lexicalButton.setEnabled(false);  // Disable lexical button after analysis
    
        } catch (Exception e) {
            // Display a general error message if the entire analysis failed
            resultArea.setForeground(Color.RED);
            resultArea.setText("LEXICAL ANALYSIS FAILED\n" + e.getMessage());
            lexicalButton.setEnabled(true);
            syntaxButton.setEnabled(false);  // Disable syntax analysis in case of a fatal failure
        }
    }
    
    // Perform syntax analysis
    private void performSyntaxAnalysis() {
        String code = fileArea.getText();
        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer();
        String analysisResult = syntaxAnalyzer.analyzeSyntax(code);

        resultArea.setText(analysisResult);

        // Check if there was an error in the analysis
        if (analysisResult.contains("SYNTAX ANALYSIS FAILED")) {
            resultArea.setForeground(Color.RED);  // Set text color to red on error
        } else {
            resultArea.setForeground(new Color(0x129F57));  // Set text color to green on success
        }
        syntaxButton.setEnabled(false);  // Disable syntax button after analysis
        semanticButton.setEnabled(true); // Enable semantic analysis
    }

    // Perform semantic analysis
    private void performSemanticAnalysis() {
        String code = fileArea.getText();
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        String analysisResult = semanticAnalyzer.analyzeSemantic(code);

        resultArea.setText(analysisResult);

        // Check if there was an error in the analysis
        if (analysisResult.contains("SEMANTIC ANALYSIS FAILED")) {
            resultArea.setForeground(Color.RED);  // Set text color to red on error
        } else {
            resultArea.setForeground(new Color(0x129F57));  // Set text color to green on success
        }
        semanticButton.setEnabled(false); // Disable semantic button after analysis
    }
}
