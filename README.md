
Here’s an updated version of your README file where you can include an image on the header section:

markdown
Copy code
# Mini Java Compiler - Lexical, Syntax, and Semantic Analysis  
 
<img src="[path-to-your-image.jpg](https://github.com/user-attachments/assets/a7829040-756b-499d-a2d6-fbdb536ce4bb)" alt="Compiler Logo" width="100" height="100" align="left" style="margin-right: 10px;" />  

Welcome to our **Mini Compiler Project**!  

This project is a lightweight Java-based compiler designed to demonstrate the three critical phases of compilation:  

1. **Lexical Analysis**: Breaking down the source code into meaningful tokens while validating their format.  
2. **Syntax Analysis**: Ensuring the code adheres to the rules of Java's syntax by validating token order and structure.  
3. **Semantic Analysis**: Verifying the semantic correctness of declarations, such as type compatibility between variables and their values.  

---

## Key Features  
- **Supports Multiple Lines**: Validates multiple Java variable declarations in a single run.  
- **Java Primitive Data Types**: Handles data types like `int`, `float`, `double`, `char`, `boolean`, and `String`.  
- **Flexible Input**: Works with or without spaces around operators like `=` and `;`.  
- **Detailed Error Reporting**: Provides clear feedback for missing semicolons, invalid identifiers, data type mismatches, and more.  

---

## How Does It Work?  
Example code:  
- `int x = 1;`  --> Passes all the phases (Lexical, Syntax, Semantic).  
- `x int 1 ; =` --> Only passes Lexical Analysis Phase.  
- `int x = 2.0;` --> Only passes Lexical Analysis and Syntax Analysis.  

---
