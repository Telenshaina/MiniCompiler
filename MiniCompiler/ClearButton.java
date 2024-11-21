import javax.swing.*;

public class ClearButton extends JButton {
    public ClearButton(String text, int width, int height) {
        super(text);
        setContentAreaFilled(false);  // Make the background transparent
        setFocusPainted(false);       // Remove the focus border
        setBorderPainted(false);      // Remove the border
    }
}
