import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AccentButton extends JButton {
    private final Color normal;
    private final Color hover;

    public AccentButton(String text, Color color) {
        super(text);
        normal = color;
        hover = color.brighter();
        setFont(Theme.font(12, true));
        setForeground(Color.WHITE);
        setBackground(normal);
        setOpaque(true);
        setContentAreaFilled(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setBorder(new EmptyBorder(11, 18, 11, 18));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBackground(hover);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                setBackground(normal);
            }
        });
    }
}