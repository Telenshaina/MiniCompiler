import java.util.List;

public class SyntaxAnalyzer {

    private LexicalAnalyzer lexicalAnalyzer;

    // Constructor
    public SyntaxAnalyzer() {
        this.lexicalAnalyzer = new LexicalAnalyzer();  // Initialize LexicalAnalyzer
    }

    // Analyze the syntax of the provided code
    public String analyzeSyntax(String code) {
        StringBuilder resultBuilder = new StringBuilder();
        String[] lines = code.split("\n");

        boolean hasError = false;

        try {
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].trim();
                if (!line.isBlank()) {
                    try {
                        // Use LexicalAnalyzer for tokenizing the line
                        List<String> tokens = lexicalAnalyzer.tokenize(line);
                        validateTokenOrder(tokens);  // Validate token order for syntax

                        resultBuilder.append("Line " + (i + 1) + ": \"").append(line).append("\"\n");
                        resultBuilder.append("Syntax: Valid\n\n");
                    } catch (Exception e) {
                        resultBuilder.append("Line " + (i + 1) + ": \"").append(line).append("\"\n");
                        resultBuilder.append("Error: ").append(e.getMessage()).append("\n\n");
                        hasError = true;
                    }
                }
            }

            // Add the final result message at the end
            String finalResult = hasError ? "SYNTAX ANALYSIS FAILED\n" : "Your Code has Passed the\nSYNTAX ANALYSIS";
            resultBuilder.append(finalResult);

            return resultBuilder.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Validate the order of tokens (syntax rules)
    private void validateTokenOrder(List<String> tokens) throws Exception {
        if (tokens.size() < 4 || !tokens.get(tokens.size() - 1).equals(";")) {
            throw new Exception("Invalid syntax: Missing semicolon or insufficient tokens.");
        }

        String equalsSign = tokens.get(2);  // "=" should be the third token

        // Ensure '=' is present
        if (!equalsSign.equals("=")) {
            throw new Exception("Some tokens are misplaced");
        }

        // Ensure the last token is a semicolon
        if (!tokens.get(tokens.size() - 1).equals(";")) {
            throw new Exception("Missing semicolon at the end.");
        }
    }
}
