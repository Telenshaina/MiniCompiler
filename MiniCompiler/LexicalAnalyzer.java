import java.util.*;

public class LexicalAnalyzer {

    // Tokenizer function to split input into tokens
    public List<String> tokenize(String input) throws Exception {
        // Split by spaces or semicolon/equal signs, keeping them as separate tokens
        String[] split = input.split("\\s+|(?=[=;])|(?<=[=;])");
        List<String> tokens = new ArrayList<>();
        for (String token : split) {
            if (!token.isBlank()) {
                tokens.add(token);
            }
        }

        // Validate that a semicolon is present
        if (!tokens.contains(";")) {
            throw new Exception("Missing semicolon.");
        }

        // Check for necessary components (data type, identifier, '=', value)
        validateTokens(tokens);

        return tokens;
    }

    // Validate the tokens based on lexical rules, without strict order
    private void validateTokens(List<String> tokens) throws Exception {
        boolean hasDataType = false;
        boolean hasIdentifier = false;
        boolean hasEqualsSign = false;
        boolean hasValue = false;

        // Iterate through the tokens to check if the necessary components exist
        for (String token : tokens) {
            if (isValidDataType(token)) {
                hasDataType = true;
            } else if (isValidIdentifier(token)) {
                hasIdentifier = true;
            } else if (token.equals("=")) {
                hasEqualsSign = true;
            } else if (isValidValue(token)) {
                hasValue = true;
            }
        }

        // Ensure all necessary components are found
        if (!hasDataType) {
            throw new Exception("Missing data type.");
        }
        if (!hasIdentifier) {
            throw new Exception("Missing identifier.");
        }
        if (!hasEqualsSign) {
            throw new Exception("Missing '='.");
        }
        if (!hasValue) {
            throw new Exception("Missing value.");
        }
    }

    // Validates the data type
    private boolean isValidDataType(String dataType) {
        return dataType.matches("int|String|double|float|char|boolean");
    }

    // Validates the identifier
    private boolean isValidIdentifier(String identifier) {
        return identifier.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }

    // Validates the value based on the data type
    private boolean isValidValue(String value) {
        // Check if the value matches a valid pattern for any type
        return value.matches("\\d+") ||  // Integer
               value.matches("\\d+(\\.\\d+)?") ||  // Double/Float
               value.matches("'.'") ||  // Character
               value.matches("\".*\"") ||  // String
               value.matches("true|false");  // Boolean
    }
}
