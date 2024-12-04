# Mini Java Compiler - Lexical, Syntax, and Semantic Analysis  

Welcome to our **Mini Compiler Project**!  

This project is a lightweight Java-based compiler designed to demonstrate the three critical phases of compilation:  

1. **Lexical Analysis**: Breaking down the source code into meaningful tokens while validating their format.  
2. **Syntax Analysis**: Ensuring the code adheres to the rules of Java's syntax by validating token order and structure.  
3. **Semantic Analysis**: Verifying the semantic correctness of declarations, such as type compatibility between variables and their values.  

## Key Features  
- **Supports Multiple Lines**: Validates multiple Java variable declarations in a single run.  
- **Java Primitive Data Types**: Handles data types like int, float, double, char, boolean, and String.  
- **Flexible Input**: Works with or without spaces around operators like = and ;.  
- **Detailed Error Reporting**: Provides clear feedback for missing semicolons, invalid identifiers, data type mismatches, and more.  

## How does it works?  
Example code: 
int x = 1;  --> passes all the phases (Lexical, Syntax, Semantic).
x int 1 ; = --> only passes Lexical Analysis Phase.
int x = 2.0; --> only passes Lexical Analysis and Syntax Analysis.
