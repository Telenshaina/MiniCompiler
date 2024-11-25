import java.util.List;

public class SemanticAnalyzer {

    // Validate the semantic correctness of the code
    public String analyzeSemantic(String code) {
        StringBuilder resultBuilder = new StringBuilder();
        String[] lines = code.split("\n");
        boolean hasError = false;

        try {
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].trim();
                if (!line.isBlank()) {
                    try {
                        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
                        List<String> tokens = lexicalAnalyzer.tokenize(line);

                        // Perform semantic checks on the tokens
                        if (!validateSemantic(tokens)) {
                            resultBuilder.append("Line " + (i + 1) + ": \"").append(line).append("\"\n");
                            resultBuilder.append("Error: Invalid value for the given type\n\n");
                            hasError = true;
                        } else {
                            resultBuilder.append("Line " + (i + 1) + ": \"").append(line).append("\"\n");
                            resultBuilder.append("Semantic: Valid\n\n");
                        }
                    } catch (Exception e) {
                        resultBuilder.append("Line " + (i + 1) + ": \"").append(line).append("\"\n");
                        resultBuilder.append("Error: ").append(e.getMessage()).append("\n\n");
                        hasError = true;
                    }
                }
            }

            if (hasError) {
                resultBuilder.append("SEMANTIC ANALYSIS FAILED\n\n");
            } else {
                resultBuilder.append("Your Code has Passed the\nSEMANTIC ANALYSIS\n\n");
            }

            return resultBuilder.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Validate the semantic correctness of the tokens
    private boolean validateSemantic(List<String> tokens) throws Exception {
        if (tokens.size() < 4) {
            throw new Exception("Insufficient tokens.");
        }

        String dataType = tokens.get(0);
        String value = tokens.get(3);

        return isValidValueForType(dataType, value);
    }

    // Validate if the value matches the given data type
    private boolean isValidValueForType(String dataType, String value) {
        switch (dataType) {
            case "int":
                return value.matches("\\d+");
            case "double":
            case "float":
                return value.matches("\\d+(\\.\\d+)?");
            case "char":
                return value.matches("'.'"); // Single character in single quotes
            case "String":
                return value.matches("\".*\""); // Enclosed in double quotes
            case "boolean":
                return value.matches("true|false");
            default:
                return false;
        }
    }
}
