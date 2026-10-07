import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public final class Theme {
    private Theme() {}

    public static final Color BG = new Color(7, 11, 25);
    public static final Color SIDEBAR = new Color(9, 14, 31);
    public static final Color SURFACE = new Color(15, 22, 43);
    public static final Color SURFACE_2 = new Color(19, 29, 55);
    public static final Color SURFACE_3 = new Color(24, 36, 67);
    public static final Color BORDER = new Color(38, 54, 84);
    public static final Color TEXT = new Color(239, 244, 252);
    public static final Color MUTED = new Color(143, 158, 184);
    public static final Color CYAN = new Color(47, 199, 255);
    public static final Color BLUE = new Color(82, 117, 255);
    public static final Color GREEN = new Color(46, 207, 135);
    public static final Color RED = new Color(247, 89, 104);
    public static final Color GOLD = new Color(238, 182, 73);

    public static Font font(int size, boolean bold) {
        return new Font("Segoe UI", bold ? Font.BOLD : Font.PLAIN, size);
    }

    public static JLabel label(String text, int size, Color color, boolean bold) {
        JLabel l = new JLabel(text);
        l.setFont(font(size, bold));
        l.setForeground(color);
        return l;
    }

    public static JTextField textField(String placeholder) {
        JTextField f = new JTextField();
        f.setFont(font(13, false));
        f.setForeground(TEXT);
        f.setBackground(new Color(9, 16, 34));
        f.setCaretColor(CYAN);
        f.setBorder(new CompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(9, 12, 9, 12)
        ));
        f.setToolTipText(placeholder);
        return f;
    }

    public static JComboBox<String> combo(String... items) {
        JComboBox<String> c = new JComboBox<>(items);
        c.setFont(font(12, false));
        c.setForeground(TEXT);
        c.setBackground(SURFACE_2);
        c.setBorder(new LineBorder(BORDER, 1, true));
        return c;
    }

    public static void table(JTable t) {
        t.setRowHeight(42);
        t.setFont(font(12, false));
        t.setForeground(TEXT);
        t.setBackground(SURFACE);
        t.setGridColor(BORDER);
        t.setSelectionBackground(new Color(28, 67, 105));
        t.setSelectionForeground(TEXT);
        t.setShowVerticalLines(false);
        t.setFillsViewportHeight(true);

        t.getTableHeader().setFont(font(11, true));
        t.getTableHeader().setForeground(MUTED);
        t.getTableHeader().setBackground(new Color(19, 29, 53));
        t.getTableHeader().setPreferredSize(new Dimension(0, 42));
        t.getTableHeader().setReorderingAllowed(false);
    }

    public static JScrollPane tableScroll(JTable t) {
        JScrollPane sp = new JScrollPane(t);
        sp.setBorder(new LineBorder(BORDER, 1, true));
        sp.setBackground(SURFACE);
        sp.getViewport().setBackground(SURFACE);
        return sp;
    }
}