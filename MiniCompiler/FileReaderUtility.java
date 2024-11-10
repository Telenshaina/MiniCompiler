// Reads the file and displays its content on the JTextArea

import javax.swing.*;
import java.io.*;

public class FileReaderUtility {
    private JTextArea textArea;

    // Allows the class to update the content of the JTextArea
    public FileReaderUtility(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            textArea.setText(""); // Clears the text area before appending/adding new content
            String line;

            // Reads each line from the file and appends it to the textArea
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
