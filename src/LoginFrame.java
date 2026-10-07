import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame {
    private final JFrame frame = new JFrame("SentinelScan • Secure Access");

    public LoginFrame() {
        frame.setSize(1120, 700);
        frame.setMinimumSize(new Dimension(980, 620));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel root = new JPanel(new GridLayout(1, 2));
        root.setBackground(Theme.BG);

        JPanel brand = new JPanel();
        brand.setBackground(Theme.BG);
        brand.setBorder(new EmptyBorder(70, 70, 60, 50));
        brand.setLayout(new BoxLayout(brand, BoxLayout.Y_AXIS));

        brand.add(Theme.label("◈ SENTINELSCAN", 25, Theme.CYAN, true));
        brand.add(Box.createVerticalStrut(35));
        brand.add(Theme.label("SMART CAMPUS", 39, Theme.TEXT, true));
        brand.add(Theme.label("SECURITY PLATFORM", 39, Theme.TEXT, true));
        brand.add(Box.createVerticalStrut(16));
        brand.add(Theme.label("Dual-factor attendance verification", 16, Theme.MUTED, false));
        brand.add(Theme.label("using face recognition + proximity RFID.", 16, Theme.MUTED, false));
        brand.add(Box.createVerticalStrut(48));

        brand.add(feature("◉", "LIVE CAMERA VERIFICATION", "Identity captured from CCTV"));
        brand.add(Box.createVerticalStrut(16));
        brand.add(feature("▣", "PROXIMITY RFID", "Contactless card verification"));
        brand.add(Box.createVerticalStrut(16));
        brand.add(feature("✓", "DUAL VERIFICATION", "Attendance only after both match"));

        brand.add(Box.createVerticalGlue());
        brand.add(Theme.label("SENTINELSCAN  •  CAMPUS SECURITY", 11, Theme.MUTED, false));

        JPanel loginArea = new JPanel(new GridBagLayout());
        loginArea.setBackground(new Color(10, 16, 34));

        RoundPanel card = new RoundPanel(24);
        card.setBackground(Theme.SURFACE);
        card.setPreferredSize(new Dimension(430, 485));
        card.setBorder(new EmptyBorder(38, 40, 38, 40));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel shield = Theme.label("⬢", 42, Theme.CYAN, true);
        shield.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel title = Theme.label("Secure Sign In", 27, Theme.TEXT, true);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel sub = Theme.label("Administrator access portal", 13, Theme.MUTED, false);
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(shield);
        card.add(Box.createVerticalStrut(8));
        card.add(title);
        card.add(Box.createVerticalStrut(4));
        card.add(sub);
        card.add(Box.createVerticalStrut(28));

        card.add(Theme.label("USERNAME", 10, Theme.MUTED, true));
        JTextField user = Theme.textField("Username");
        user.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        card.add(user);
        card.add(Box.createVerticalStrut(15));

        card.add(Theme.label("PASSWORD", 10, Theme.MUTED, true));
        JPasswordField pass = new JPasswordField();
        pass.setFont(Theme.font(13, false));
        pass.setForeground(Theme.TEXT);
        pass.setBackground(new Color(9, 16, 34));
        pass.setCaretColor(Theme.CYAN);
        pass.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.LineBorder(Theme.BORDER, 1, true),
                new EmptyBorder(9, 12, 9, 12)
        ));
        pass.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        card.add(pass);

        card.add(Box.createVerticalStrut(24));
        AccentButton login = new AccentButton("SIGN IN   →", Theme.CYAN);
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(login);
        card.add(Box.createVerticalStrut(14));

        JLabel demo = Theme.label("Demo credentials  •  admin / admin", 11, Theme.MUTED, false);
        demo.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(demo);

        login.addActionListener(e -> {
            if ("admin".equals(user.getText()) && "admin".equals(new String(pass.getPassword()))) {
                frame.dispose();
                new DashboardFrame();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Access Denied", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginArea.add(card);
        root.add(brand);
        root.add(loginArea);
        frame.add(root);
        frame.setVisible(true);
    }

    private JPanel feature(String icon, String title, String sub) {
        JPanel p = new JPanel(new BorderLayout(12, 0));
        p.setOpaque(false);
        p.setMaximumSize(new Dimension(500, 54));
        p.add(Theme.label(icon, 18, Theme.CYAN, true), BorderLayout.WEST);

        JPanel x = new JPanel();
        x.setOpaque(false);
        x.setLayout(new BoxLayout(x, BoxLayout.Y_AXIS));
        x.add(Theme.label(title, 12, Theme.TEXT, true));
        x.add(Theme.label(sub, 11, Theme.MUTED, false));
        p.add(x, BorderLayout.CENTER);
        return p;
    }
}