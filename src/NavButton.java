import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NavButton extends JButton {
    private final Color base = Theme.SIDEBAR;

    public NavButton(String icon, String text) {
        super(icon + "    " + text);
        setFont(Theme.font(12, true));
        setForeground(Theme.MUTED);
        setBackground(base);
        setOpaque(true);
        setContentAreaFilled(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setHorizontalAlignment(SwingConstants.LEFT);
        setBorder(new EmptyBorder(12, 15, 12, 10));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBackground(new Color(19, 34, 62));
                setForeground(Theme.CYAN);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                setBackground(base);
                setForeground(Theme.MUTED);
            }
        });
    }
}