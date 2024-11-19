import java.awt.*;
import javax.swing.*;

public class UIHelper {
    // Used constants to match UI design preferences
    private static final Color BUTTON_COLOR = new Color(0xD83E76);

    // Helper method that simplifies button creation
    public static JButton createButton(String text, int x, int y, int width, int height) {
        RoundedButton button = new RoundedButton(text, BUTTON_COLOR, 30, 30);
        button.setBounds(x, y, width, height);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Verdana", Font.BOLD, 14));

        return button;
    }

    public static JLabel createLabel(String content, int x, int y, int width, int height) {
        JLabel label = new JLabel(content);
        label.setBounds(x, y, width, height);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Verdana", Font.BOLD, 15));
        
        return label;
    }
}
