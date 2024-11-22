import java.util.ArrayList;
import java.util.List;

public class LexicalAnalysis {

    public static String analyze(String input) {
        try {
            List<String> tokens = tokenize(input);
            parse(tokens);
            return "Your Code has passed the \nLEXICAL ANALYSIS.";
        } catch (Exception e) {
            return "Your Code did not pass the\nLEXICAL ANALYSIS\n\nError: " + e.getMessage();
        }
    }

    private static List<String> tokenize(String input) throws Exception {
        String[] split = input.split("\\s+|(?=[=;])|(?<=[=;])");
        List<String> tokens = new ArrayList<>();
        for (String token : split) {
            if (!token.isBlank()) {
                tokens.add(token);
            }
        }

        if (!tokens.contains(";")) {
            throw new Exception("Missing semicolon.");
        }

        return tokens;
    }

    private static void parse(List<String> tokens) throws Exception {
        if (tokens.size() < 4 || !tokens.get(tokens.size() - 1).equals(";")) {
            throw new Exception("Invalid syntax.");
        }

        String dataType = tokens.get(0);
        String identifier = tokens.get(1);
        String equalsSign = tokens.get(2);
        String value = tokens.get(3);

        if (!isValidDataType(dataType)) {
            throw new Exception("Invalid data type.");
        }

        if (!isValidIdentifier(identifier)) {
            throw new Exception("Invalid identifier.");
        }

        if (!equalsSign.equals("=")) {
            throw new Exception("Missing '='.");
        }

        if (!isValidValue(dataType, value)) {
            throw new Exception("Invalid value for type " + dataType + ".");
        }
    }

    private static boolean isValidDataType(String dataType) {
        return dataType.matches("int|String|double|float|char|boolean");
    }

    private static boolean isValidIdentifier(String identifier) {
        return identifier.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }

    private static boolean isValidValue(String dataType, String value) {
        switch (dataType) {
            case "int":
                return value.matches("\\d+");
            case "double":
            case "float":
                return value.matches("\\d+(\\.\\d+)?");
            case "char":
                return value.matches("'.'"); 
            case "String":
                return value.matches("\".*\"");
            case "boolean":
                return value.matches("true|false");
            default:
                return false;
        }
    }
}
