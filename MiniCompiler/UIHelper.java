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

    public static JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setForeground(new Color(0x3D052B));
        label.setFont(new Font("Verdana", Font.BOLD, 15));
        
        return label;
    }

    public static JButton createHeaderComponent(String text, int x, int y, int width, int height) {
        // JButton button = new JButton(text);
        ClearButton button = new ClearButton(text, width, height);
        button.setBounds(x, y, width, height);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Verdana", Font.BOLD, 13));

        return button;
    }
}
