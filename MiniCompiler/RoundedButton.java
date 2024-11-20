import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    private Color buttonColor;
    private int cornerRadius;

    // Constructor for creating the rounded button with custom text, color, and corner radius
    public RoundedButton(String text, Color color, int width, int height) {
        super(text);
        this.buttonColor = color;
        this.cornerRadius = Math.min(width, height) / 2; // Ensures a proper rounded corner
        setContentAreaFilled(false);  // Make the background transparent so we can paint it ourselves
        setFocusPainted(false);       // Remove the focus border
        setBorderPainted(false);      // Remove the border
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (this.isEnabled()) {
            g.setColor(buttonColor);  // Set the color for the button when enabled
        } else {
            g.setColor(new Color(0xB27087));  // Set a different color for the disabled state
        }
        
        // Fill the button with the selected color and rounded corners
        g.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        super.paintComponent(g);  // Paint the text or other default button properties
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        repaint(); // Repaint the button when its state changes
    }
}
