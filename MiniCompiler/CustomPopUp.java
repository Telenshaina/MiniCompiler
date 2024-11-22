import javax.swing.*;
import java.awt.*;

public class CustomPopUp {
    
    public static void showInvalidFileMessage(JFrame parent) {
        // Customize the appearance of the JOptionPane
        UIManager.put("OptionPane.background", new Color(0xF1D2DF));
        UIManager.put("Panel.background", new Color(0xF1D2DF));
        UIManager.put("OptionPane.messageForeground", new Color(0x3D052B));
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14));

        // Custom message with HTML formatting
        String message = "<html>"
                + "<b>Invalid File Format!</b><br>"
                + "<font color='red'><b>File not supported!</b></font><br>"
                + "Upload a <font color='red'><b> Java </b></font>file format."
                + "</html>";

        // Display the customized dialog
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showCreditsMessage(JFrame parent) {
        UIManager.put("OptionPane.background", new Color(0xF1D2DF));
        UIManager.put("Panel.background", new Color(0xF1D2DF));
        UIManager.put("OptionPane.messageForeground", new Color(0x3D052B));
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));

        // Custom message with HTML formatting
        String message = "<html>"
                + "<font color='#E8009D' size='6'><b>Programmed By:</b></font><br><br>"
                + "Telen, Shaina Blessy Meir T.<br>"
                + "Buri, Faye Camille<br>"
                + "Llana, Aliyah Aira A.<br><br>"
                + "</html>";

        // Display the customized dialog
        JOptionPane.showMessageDialog(parent, message, "Credits", JOptionPane.INFORMATION_MESSAGE);
    }
}
