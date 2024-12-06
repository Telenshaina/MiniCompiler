import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {

    // Tokenizer function to split input into tokens
    public List<String> tokenize(String input) throws Exception {
        // Use regex to match tokens while respecting quoted strings and special characters
        List<String> tokens = new ArrayList<>();
        String regex = "\"[^\"]*\"|[a-zA-Z_][a-zA-Z0-9_]*|\\d+(\\.\\d+)?[fF]?|[=;]|true|false|'.'";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        // Print tokens for debugging
        System.out.println("Tokens: " + tokens);

        // Validate the tokens based on lexical rules
        validateTokens(tokens);

        return tokens; // Return tokens without enforcing order or additional constraints
    }

    // Validates the tokens based on lexical rules, checking for missing components
    private void validateTokens(List<String> tokens) throws Exception {
        boolean hasDataType = false;
        boolean hasIdentifier = false;
        boolean hasEqualsSign = false;
        boolean hasValue = false;
        boolean hasSemicolon = false;

        // Ensure exactly 5 tokens
        if (tokens.size() != 5) {
            throw new Exception("Your variable declaration is incomplete.");
        }

        // Track tokens found, but allow flexibility in order
        for (String token : tokens) {
            System.out.println("Processing token: " + token); // Debugging output
            if (token.equals(";")) {
                hasSemicolon = true;
            } else if (isValidDataType(token)) {
                hasDataType = true;
            } else if (isValidIdentifier(token)) {
                hasIdentifier = true;
            } else if (token.equals("=")) {
                hasEqualsSign = true;
            } else if (isValidValue(token)) {
                hasValue = true;
            } else {
                // If we encounter a token that is not one of the valid types, it's invalid
                throw new Exception("Unexpected token: " + token);
            }
        }

        // Debugging output for component checks
        System.out.println("hasDataType: " + hasDataType);
        System.out.println("hasIdentifier: " + hasIdentifier);
        System.out.println("hasEqualsSign: " + hasEqualsSign);
        System.out.println("hasValue: " + hasValue);
        System.out.println("hasSemicolon: " + hasSemicolon);

        // Ensure that all necessary components are found
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
        if (!hasSemicolon) {
            throw new Exception("Missing semicolon.");
        }
    }

    // Validates the data type
    private boolean isValidDataType(String dataType) {
        // Added "byte" and "short" along with existing types
        return dataType.matches("int|String|double|float|char|boolean|byte|short");
    }

    // Validates the identifier
    private boolean isValidIdentifier(String identifier) {
        return identifier.matches("[a-zA-Z_][a-zA-Z0-9_]*") &&
               !identifier.equals("true") &&
               !identifier.equals("false"); // Ensure it's not a reserved value
    }

    // Validates the value based on the data type
    private boolean isValidValue(String value) {
        return value.matches("\\d+") || // Integer
               value.matches("\\d+(\\.\\d+)?[fF]?") || // Float/Double
               value.matches("'.'") || // Character
               value.matches("\"[^\"]*\"") || // String with spaces
               value.equals("true") || value.equals("false"); // Boolean (true/false)
    }
}
