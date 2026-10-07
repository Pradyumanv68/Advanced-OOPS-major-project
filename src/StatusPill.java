import javax.swing.*;
import java.awt.*;

public class StatusPill extends JPanel {
    public StatusPill(String text, Color color) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 7, 4));
        setOpaque(true);
        setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 28));
        JLabel dot = Theme.label("●", 9, color, true);
        JLabel label = Theme.label(text, 10, color, true);
        add(dot);
        add(label);
    }
}