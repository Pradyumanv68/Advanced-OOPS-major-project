import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DashboardFrame {
    private final JFrame frame = new JFrame("SentinelScan • Smart Campus Security");
    private final JPanel content = new JPanel(new BorderLayout());
    private final JLabel clock = Theme.label("", 11, Theme.MUTED, false);
    private final JLabel pageTitle = Theme.label("", 24, Theme.TEXT, true);

    public DashboardFrame() {
        frame.setSize(1450, 880);
        frame.setMinimumSize(new Dimension(1120, 720));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        content.setBackground(Theme.BG);
        frame.add(buildSidebar(), BorderLayout.WEST);
        frame.add(content, BorderLayout.CENTER);

        new Timer(1000, e -> clock.setText(
                new SimpleDateFormat("EEE, dd MMM yyyy   •   HH:mm:ss").format(new Date())
        )).start();

        showDashboard();
        frame.setVisible(true);
    }

    private JPanel buildSidebar() {
        JPanel side = new JPanel();
        side.setPreferredSize(new Dimension(255, 0));
        side.setBackground(Theme.SIDEBAR);
        side.setBorder(new EmptyBorder(25, 14, 20, 14));
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));

        side.add(Theme.label("◈ SENTINELSCAN", 21, Theme.TEXT, true));
        side.add(Box.createVerticalStrut(5));
        side.add(Theme.label("SMART CAMPUS SECURITY", 9, Theme.CYAN, true));
        side.add(Box.createVerticalStrut(30));

        addNav(side, "⌂", "Overview", e -> showDashboard());
        addNav(side, "♙", "Students", e -> showStudents());
        addNav(side, "✓", "Attendance", e -> showAttendance());
        addNav(side, "◉", "Live Verification", e -> showScanner());
        addNav(side, "!", "Alerts & Audit", e -> showAlerts());
        addNav(side, "▣", "Reports", e -> showReports());
        addNav(side, "⚙", "Settings", e -> showSettings());

        side.add(Box.createVerticalGlue());

        RoundPanel system = new RoundPanel(15);
        system.setBackground(new Color(11, 39, 40));
        system.setBorder(new EmptyBorder(12, 12, 12, 12));
        system.setLayout(new BorderLayout());
        JPanel st = new JPanel();
        st.setOpaque(false);
        st.setLayout(new BoxLayout(st, BoxLayout.Y_AXIS));
        st.add(Theme.label("SYSTEM STATUS", 9, Theme.MUTED, true));
        st.add(Box.createVerticalStrut(4));
        st.add(Theme.label("●  ALL SYSTEMS OPERATIONAL", 10, Theme.GREEN, true));
        system.add(st, BorderLayout.CENTER);
        side.add(system);

        return side;
    }

    private void addNav(JPanel side, String icon, String name, java.awt.event.ActionListener action) {
        NavButton b = new NavButton(icon, name);
        b.addActionListener(action);
        side.add(b);
        side.add(Box.createVerticalStrut(4));
    }

    private void header(String title, String subtitle) {
        content.removeAll();

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.setBorder(new EmptyBorder(25, 30, 18, 30));

        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        pageTitle.setText(title);
        left.add(pageTitle);
        left.add(Box.createVerticalStrut(4));
        left.add(Theme.label(subtitle, 12, Theme.MUTED, false));

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        right.setOpaque(false);
        right.add(clock);

        AccentButton notification = new AccentButton("NOTIFICATIONS  3", Theme.SURFACE_2);
        AccentButton profile = new AccentButton("ADMIN  ▾", Theme.SURFACE_2);
        right.add(notification);
        right.add(profile);

        top.add(left, BorderLayout.WEST);
        top.add(right, BorderLayout.EAST);
        content.add(top, BorderLayout.NORTH);
    }

    private JPanel mainPanel() {
        JPanel p = new JPanel(new BorderLayout(16, 16));
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(0, 30, 28, 30));
        return p;
    }

    private void showDashboard() {
        header("Command Center", "Real-time attendance, verification and campus security overview");
        JPanel main = mainPanel();

        JPanel kpis = new JPanel(new GridLayout(1, 4, 14, 14));
        kpis.setOpaque(false);
        kpis.add(kpi("REGISTERED STUDENTS", "1,248", "+8.2% this month", "01", Theme.CYAN));
        kpis.add(kpi("PRESENT TODAY", "1,092", "87.5% attendance", "02", Theme.GREEN));
        kpis.add(kpi("REJECTED EVENTS", "17", "Needs review", "03", Theme.RED));
        kpis.add(kpi("VERIFIED SCANS", "1,075", "Face + RFID matched", "04", Theme.GOLD));

        JPanel center = new JPanel(new GridLayout(1, 2, 16, 16));
        center.setOpaque(false);
        center.add(buildVerificationCard());
        center.add(buildHealthCard());

        JPanel bottom = new JPanel(new GridLayout(1, 2, 16, 16));
        bottom.setOpaque(false);
        bottom.add(buildActivityCard());
        bottom.add(buildAnalyticsCard());

        JPanel body = new JPanel(new BorderLayout(16, 16));
        body.setOpaque(false);
        body.add(kpis, BorderLayout.NORTH);
        body.add(center, BorderLayout.CENTER);
        body.add(bottom, BorderLayout.SOUTH);

        main.add(body, BorderLayout.CENTER);
        content.add(main, BorderLayout.CENTER);
        refresh();
    }

    private JPanel kpi(String title, String value, String foot, String code, Color color) {
        RoundPanel p = new RoundPanel();
        p.setBackground(Theme.SURFACE);
        p.setBorder(new EmptyBorder(16, 18, 16, 18));
        p.setLayout(new BorderLayout(8, 8));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(Theme.label(title, 10, Theme.MUTED, true), BorderLayout.WEST);
        top.add(Theme.label(code, 11, color, true), BorderLayout.EAST);

        p.add(top, BorderLayout.NORTH);
        p.add(Theme.label(value, 30, Theme.TEXT, true), BorderLayout.CENTER);
        p.add(Theme.label(foot, 10, color, false), BorderLayout.SOUTH);
        return p;
    }

    private JPanel buildVerificationCard() {
        RoundPanel p = new RoundPanel();
        p.setBackground(Theme.SURFACE);
        p.setBorder(new EmptyBorder(18, 18, 18, 18));
        p.setLayout(new BorderLayout(14, 14));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(Theme.label("LIVE VERIFICATION", 14, Theme.TEXT, true), BorderLayout.WEST);
        top.add(new StatusPill("READY", Theme.GREEN), BorderLayout.EAST);
        p.add(top, BorderLayout.NORTH);

        JPanel body = new JPanel(new GridLayout(1, 2, 15, 0));
        body.setOpaque(false);

        RoundPanel camera = new RoundPanel(15);
        camera.setBackground(new Color(6, 11, 25));
        camera.setLayout(new GridBagLayout());
        JPanel camInner = new JPanel();
        camInner.setOpaque(false);
        camInner.setLayout(new BoxLayout(camInner, BoxLayout.Y_AXIS));
        JLabel cam = Theme.label("◉", 32, Theme.CYAN, true);
        cam.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel camTitle = Theme.label("CAMERA READY", 13, Theme.TEXT, true);
        camTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel camSub = Theme.label("Waiting for identity...", 10, Theme.MUTED, false);
        camSub.setAlignmentX(Component.CENTER_ALIGNMENT);
        camInner.add(cam); camInner.add(Box.createVerticalStrut(8)); camInner.add(camTitle); camInner.add(Box.createVerticalStrut(3)); camInner.add(camSub);
        camera.add(camInner);
        body.add(camera);

        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.add(Theme.label("LATEST VERIFIED IDENTITY", 9, Theme.MUTED, true));
        details.add(Box.createVerticalStrut(5));
        details.add(Theme.label("Pradyuman Verma", 19, Theme.TEXT, true));
        details.add(Theme.label("STU001   •   CSE - Data Science", 10, Theme.MUTED, false));
        details.add(Box.createVerticalStrut(12));
        details.add(checkLine("FACE RECOGNITION", "MATCHED", Theme.GREEN));
        details.add(checkLine("RFID PROXIMITY", "MATCHED", Theme.GREEN));
        details.add(checkLine("CROSS VERIFICATION", "VERIFIED", Theme.CYAN));
        details.add(Box.createVerticalGlue());

        AccentButton scan = new AccentButton("START NEW SCAN  →", Theme.CYAN);
        scan.addActionListener(e -> showScanner());
        details.add(scan);
        body.add(details);

        p.add(body, BorderLayout.CENTER);
        return p;
    }

    private JPanel checkLine(String a, String b, Color c) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(5, 0, 5, 0));
        p.add(Theme.label(a, 10, Theme.MUTED, false), BorderLayout.WEST);
        p.add(Theme.label("●  " + b, 10, c, true), BorderLayout.EAST);
        return p;
    }

    private JPanel buildHealthCard() {
        RoundPanel p = new RoundPanel();
        p.setBackground(Theme.SURFACE);
        p.setBorder(new EmptyBorder(18, 18, 18, 18));
        p.setLayout(new BorderLayout(8, 8));
        p.add(Theme.label("INFRASTRUCTURE HEALTH", 14, Theme.TEXT, true), BorderLayout.NORTH);

        JPanel rows = new JPanel();
        rows.setOpaque(false);
        rows.setLayout(new BoxLayout(rows, BoxLayout.Y_AXIS));

        rows.add(health("CCTV CAMERA", "ONLINE", "98 ms", Theme.GREEN));
        rows.add(health("RFID READER", "ONLINE", "32 ms", Theme.GREEN));
        rows.add(health("FACE ENGINE", "READY", "0.91 conf.", Theme.CYAN));
        rows.add(health("DATABASE", "CONNECTED", "Healthy", Theme.GREEN));
        rows.add(health("NETWORK", "CONNECTED", "24 Mbps", Theme.GOLD));

        p.add(rows, BorderLayout.CENTER);
        return p;
    }

    private JPanel health(String a, String b, String c, Color color) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setBorder(new EmptyBorder(7, 0, 7, 0));
        JPanel l = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        l.setOpaque(false);
        l.add(Theme.label("●", 10, color, true));
        l.add(Theme.label(a, 10, Theme.MUTED, true));
        row.add(l, BorderLayout.WEST);
        JPanel r = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        r.setOpaque(false);
        r.add(Theme.label(c, 9, Theme.MUTED, false));
        r.add(Theme.label(b, 10, color, true));
        row.add(r, BorderLayout.EAST);
        return row;
    }

    private JPanel buildActivityCard() {
        RoundPanel p = new RoundPanel();
        p.setBackground(Theme.SURFACE);
        p.setBorder(new EmptyBorder(16, 16, 16, 16));
        p.setLayout(new BorderLayout(8, 10));
        p.add(Theme.label("RECENT ACTIVITY", 14, Theme.TEXT, true), BorderLayout.NORTH);

        JPanel list = new JPanel();
        list.setOpaque(false);
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));

        list.add(activity("12:41", "Pradyuman Verma", "Dual verification passed", Theme.GREEN));
        list.add(activity("12:39", "Kabeer Chhabra", "Dual verification passed", Theme.GREEN));
        list.add(activity("12:37", "Unknown identity", "RFID / face mismatch", Theme.RED));
        list.add(activity("12:35", "Piyush Halder", "Dual verification passed", Theme.GREEN));

        p.add(list, BorderLayout.CENTER);
        return p;
    }

    private JPanel activity(String time, String name, String desc, Color color) {
        JPanel r = new JPanel(new BorderLayout());
        r.setOpaque(false);
        r.setBorder(new EmptyBorder(6, 0, 6, 0));
        r.add(Theme.label(time, 10, Theme.MUTED, false), BorderLayout.WEST);

        JPanel x = new JPanel();
        x.setOpaque(false);
        x.setLayout(new BoxLayout(x, BoxLayout.Y_AXIS));
        x.add(Theme.label(name, 11, Theme.TEXT, true));
        x.add(Theme.label(desc, 9, Theme.MUTED, false));
        r.add(x, BorderLayout.CENTER);

        r.add(Theme.label("●", 10, color, true), BorderLayout.EAST);
        return r;
    }

    private JPanel buildAnalyticsCard() {
        RoundPanel p = new RoundPanel();
        p.setBackground(Theme.SURFACE);
        p.setBorder(new EmptyBorder(16, 16, 16, 16));
        p.setLayout(new BorderLayout(8, 10));
        p.add(Theme.label("ATTENDANCE TREND", 14, Theme.TEXT, true), BorderLayout.NORTH);

        JPanel chart = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth(), h = getHeight();
                g2.setColor(Theme.BORDER);
                for (int i = 1; i < 5; i++) {
                    int y = 15 + i * (h - 30) / 5;
                    g2.drawLine(0, y, w, y);
                }
                int[] vals = {68, 74, 71, 82, 78, 89, 87};
                int prevX = 0, prevY = h - 15 - (vals[0] * (h - 30) / 100);
                g2.setColor(Theme.CYAN);
                g2.setStroke(new BasicStroke(3f));
                for (int i = 1; i < vals.length; i++) {
                    int x = i * (w - 20) / (vals.length - 1) + 5;
                    int y = h - 15 - (vals[i] * (h - 30) / 100);
                    g2.drawLine(prevX + 5, prevY, x, y);
                    g2.fillOval(x - 4, y - 4, 8, 8);
                    prevX = x; prevY = y;
                }
                g2.dispose();
            }
        };
        chart.setOpaque(false);
        p.add(chart, BorderLayout.CENTER);
        p.add(Theme.label("MON      TUE      WED      THU      FRI      SAT      SUN", 8, Theme.MUTED, false), BorderLayout.SOUTH);
        return p;
    }

    private void showStudents() {
        header("Student Directory", "Manage enrolled identities, RFID assignments and attendance profiles");
        JPanel main = mainPanel();

        JPanel tools = new JPanel(new BorderLayout(10, 0));
        tools.setOpaque(false);
        JTextField search = Theme.textField("Search by name, student ID or RFID");
        tools.add(search, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        actions.setOpaque(false);
        AccentButton refresh = new AccentButton("REFRESH", Theme.SURFACE_2);
        AccentButton add = new AccentButton("+  REGISTER STUDENT", Theme.CYAN);
        actions.add(refresh);
        actions.add(add);
        tools.add(actions, BorderLayout.EAST);
        main.add(tools, BorderLayout.NORTH);

        String[] cols = {"ID", "STUDENT", "DEPARTMENT", "RFID", "ATTENDANCE", "STATUS"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        Runnable load = () -> {
            model.setRowCount(0);
            String q = search.getText().trim().toLowerCase();
            for (Student s : AppData.students) {
                if (q.isBlank() ||
                    s.getName().toLowerCase().contains(q) ||
                    s.getId().toLowerCase().contains(q) ||
                    s.getRfid().toLowerCase().contains(q)) {
                    model.addRow(new Object[]{
                            s.getId(), s.getName(), s.getDepartment(),
                            s.getRfid(), s.getAttendance() + "%", "● ACTIVE"
                    });
                }
            }
        };
        load.run();

        DocumentListener dl = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { load.run(); }
            public void removeUpdate(DocumentEvent e) { load.run(); }
            public void changedUpdate(DocumentEvent e) { load.run(); }
        };
        search.getDocument().addDocumentListener(dl);
        refresh.addActionListener(e -> load.run());
        add.addActionListener(e -> registerStudent());

        JTable table = new JTable(model);
        Theme.table(table);
        main.add(Theme.tableScroll(table), BorderLayout.CENTER);

        content.add(main, BorderLayout.CENTER);
        refresh();
    }

    private void registerStudent() {
        JTextField id = Theme.textField("STU009");
        JTextField name = Theme.textField("Full name");
        JTextField dept = Theme.textField("Department");
        JTextField rfid = Theme.textField("RFID1009");

        JPanel p = new JPanel(new GridLayout(4, 2, 10, 12));
        p.setBackground(Theme.SURFACE);
        p.add(Theme.label("Student ID", 11, Theme.MUTED, true)); p.add(id);
        p.add(Theme.label("Full Name", 11, Theme.MUTED, true)); p.add(name);
        p.add(Theme.label("Department", 11, Theme.MUTED, true)); p.add(dept);
        p.add(Theme.label("RFID ID", 11, Theme.MUTED, true)); p.add(rfid);

        if (JOptionPane.showConfirmDialog(frame, p, "Register New Student",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
            if (id.getText().isBlank() || name.getText().isBlank() || rfid.getText().isBlank()) {
                JOptionPane.showMessageDialog(frame, "Student ID, name and RFID are required.");
                return;
            }
            AppData.students.add(new Student(id.getText().trim(), name.getText().trim(),
                    dept.getText().trim(), rfid.getText().trim(), 0));
            JOptionPane.showMessageDialog(frame, "Student profile registered.");
            showStudents();
        }
    }

    private void showAttendance() {
        header("Attendance Monitor", "Review dual-factor verification events and export records");
        JPanel main = mainPanel();

        JPanel tools = new JPanel(new BorderLayout(10, 0));
        tools.setOpaque(false);
        JTextField search = Theme.textField("Search student or RFID");
        tools.add(search, BorderLayout.CENTER);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        right.setOpaque(false);
        JComboBox<String> filter = Theme.combo("ALL STATUS", "VERIFIED", "REJECTED");
        right.add(filter);
        AccentButton export = new AccentButton("EXPORT CSV", Theme.SURFACE_2);
        right.add(export);
        tools.add(right, BorderLayout.EAST);
        main.add(tools, BorderLayout.NORTH);

        String[] cols = {"TIME", "STUDENT", "DEPARTMENT", "RFID", "FACE", "CARD", "RESULT"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        Runnable load = () -> {
            model.setRowCount(0);
            String q = search.getText().trim().toLowerCase();
            String f = String.valueOf(filter.getSelectedItem());
            for (AttendanceRecord r : AppData.records) {
                boolean text = q.isBlank() || r.getName().toLowerCase().contains(q) || r.getRfid().toLowerCase().contains(q);
                boolean stat = "ALL STATUS".equals(f) || f.equals(r.getStatus());
                if (text && stat) {
                    model.addRow(new Object[]{r.getTime(), r.getName(), r.getDepartment(),
                            r.getRfid(), r.getFace(), r.getCard(), r.getStatus()});
                }
            }
        };

        load.run();
        search.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { load.run(); }
            public void removeUpdate(DocumentEvent e) { load.run(); }
            public void changedUpdate(DocumentEvent e) { load.run(); }
        });
        filter.addActionListener(e -> load.run());
        export.addActionListener(e -> exportCSV());

        JTable table = new JTable(model);
        Theme.table(table);
        main.add(Theme.tableScroll(table), BorderLayout.CENTER);

        content.add(main, BorderLayout.CENTER);
        refresh();
    }

    private void showScanner() {
        header("Live Verification Terminal", "Simulated CCTV + RFID dual-factor verification workflow");
        JPanel main = mainPanel();

        JPanel grid = new JPanel(new GridLayout(1, 2, 18, 0));
        grid.setOpaque(false);

        RoundPanel camera = new RoundPanel(20);
        camera.setBackground(new Color(5, 10, 23));
        camera.setBorder(new EmptyBorder(18, 18, 18, 18));
        camera.setLayout(new BorderLayout(10, 10));

        JPanel cameraTop = new JPanel(new BorderLayout());
        cameraTop.setOpaque(false);
        cameraTop.add(Theme.label("CAMERA FEED", 14, Theme.TEXT, true), BorderLayout.WEST);
        cameraTop.add(new StatusPill("LIVE", Theme.GREEN), BorderLayout.EAST);
        camera.add(cameraTop, BorderLayout.NORTH);

        JPanel feed = new JPanel(new GridBagLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(17, 31, 57));
                g2.setStroke(new BasicStroke(1));
                int cx = getWidth() / 2, cy = getHeight() / 2;
                g2.drawRect(cx - 110, cy - 145, 220, 290);
                g2.drawLine(cx - 135, cy, cx - 95, cy);
                g2.drawLine(cx + 95, cy, cx + 135, cy);
                g2.drawLine(cx, cy - 170, cx, cy - 130);
                g2.drawLine(cx, cy + 130, cx, cy + 170);
                g2.dispose();
            }
        };
        feed.setBackground(new Color(7, 14, 29));
        feed.add(Theme.label("AWAITING FACE", 13, Theme.MUTED, true));
        camera.add(feed, BorderLayout.CENTER);

        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        right.add(Theme.label("VERIFICATION PIPELINE", 14, Theme.TEXT, true));
        right.add(Box.createVerticalStrut(15));

        JLabel face = Theme.label("01   FACE RECOGNITION     WAITING", 12, Theme.MUTED, true);
        JLabel rfid = Theme.label("02   RFID PROXIMITY       WAITING", 12, Theme.MUTED, true);
        JLabel cross = Theme.label("03   CROSS VERIFICATION   WAITING", 12, Theme.MUTED, true);
        JLabel result = Theme.label("READY TO SCAN", 24, Theme.GOLD, true);

        right.add(stepCard(face));
        right.add(Box.createVerticalStrut(8));
        right.add(stepCard(rfid));
        right.add(Box.createVerticalStrut(8));
        right.add(stepCard(cross));
        right.add(Box.createVerticalStrut(20));
        right.add(result);
        right.add(Box.createVerticalStrut(15));

        AccentButton scan = new AccentButton("START VERIFICATION", Theme.CYAN);
        scan.addActionListener(e -> {
            Student s = AppData.students.get(new Random().nextInt(AppData.students.size()));
            face.setText("01   FACE RECOGNITION     MATCHED");
            face.setForeground(Theme.GREEN);
            rfid.setText("02   RFID PROXIMITY       MATCHED");
            rfid.setForeground(Theme.GREEN);
            cross.setText("03   CROSS VERIFICATION   VERIFIED");
            cross.setForeground(Theme.CYAN);
            result.setText("✓  ATTENDANCE VERIFIED");
            result.setForeground(Theme.GREEN);
            AppData.add(s, "MATCHED", "MATCHED", "VERIFIED");
            JOptionPane.showMessageDialog(frame, s.getName() + "\n\nFace: MATCHED\nRFID: " + s.getRfid() + "\nResult: VERIFIED",
                    "Verification Complete", JOptionPane.INFORMATION_MESSAGE);
        });
        right.add(scan);
        right.add(Box.createVerticalGlue());
        right.add(Theme.label("Rule: FACE MATCH  +  RFID MATCH  =  VERIFIED", 10, Theme.MUTED, false));

        grid.add(camera);
        grid.add(right);
        main.add(grid, BorderLayout.CENTER);

        content.add(main, BorderLayout.CENTER);
        refresh();
    }

    private JPanel stepCard(JLabel label) {
        RoundPanel p = new RoundPanel(13);
        p.setBackground(Theme.SURFACE);
        p.setBorder(new EmptyBorder(12, 12, 12, 12));
        p.setLayout(new BorderLayout());
        p.add(label, BorderLayout.CENTER);
        return p;
    }

    private void showAlerts() {
        header("Alerts & Audit Log", "Security events, mismatches and operational notifications");
        JPanel main = mainPanel();

        JPanel list = new JPanel();
        list.setOpaque(false);
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));

        list.add(alert("HIGH", "Rejected verification", "Face did not match the RFID owner", "01 Oct • 10:42:18", Theme.RED));
        list.add(Box.createVerticalStrut(10));
        list.add(alert("WARN", "RFID reader latency", "Reader response exceeded 300 ms", "01 Oct • 09:18:05", Theme.GOLD));
        list.add(Box.createVerticalStrut(10));
        list.add(alert("INFO", "Database synchronization", "Attendance records synchronized successfully", "01 Oct • 08:55:41", Theme.CYAN));
        list.add(Box.createVerticalStrut(10));
        list.add(alert("INFO", "System startup", "CCTV, RFID and database services initialized", "01 Oct • 08:00:03", Theme.GREEN));

        main.add(new JScrollPane(list) {{
            setBorder(null);
            getViewport().setBackground(Theme.BG);
            setBackground(Theme.BG);
        }}, BorderLayout.CENTER);

        content.add(main, BorderLayout.CENTER);
        refresh();
    }

    private JPanel alert(String level, String title, String message, String time, Color color) {
        RoundPanel p = new RoundPanel();
        p.setBackground(Theme.SURFACE);
        p.setBorder(new EmptyBorder(14, 16, 14, 16));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 92));
        p.setLayout(new BorderLayout(14, 0));

        p.add(Theme.label(level, 10, color, true), BorderLayout.WEST);

        JPanel x = new JPanel();
        x.setOpaque(false);
        x.setLayout(new BoxLayout(x, BoxLayout.Y_AXIS));
        x.add(Theme.label(title, 13, Theme.TEXT, true));
        x.add(Box.createVerticalStrut(5));
        x.add(Theme.label(message, 11, Theme.MUTED, false));
        p.add(x, BorderLayout.CENTER);

        p.add(Theme.label(time, 10, Theme.MUTED, false), BorderLayout.EAST);
        return p;
    }

    private void showReports() {
        header("Reports & Analytics", "Operational metrics and attendance reporting");
        JPanel main = mainPanel();

        JPanel cards = new JPanel(new GridLayout(2, 2, 16, 16));
        cards.setOpaque(false);
        cards.add(metric("ATTENDANCE RATE", "87.5%", "Current day", Theme.CYAN));
        cards.add(metric("VERIFICATION RATE", "98.4%", "Face + RFID agreement", Theme.GREEN));
        cards.add(metric("REJECTED EVENTS", "17", "Flagged for review", Theme.RED));
        cards.add(metric("SYSTEM UPTIME", "99.8%", "Infrastructure health", Theme.GOLD));

        JPanel south = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        south.setOpaque(false);
        AccentButton export = new AccentButton("EXPORT ATTENDANCE CSV  ↓", Theme.CYAN);
        export.addActionListener(e -> exportCSV());
        south.add(export);

        JPanel body = new JPanel(new BorderLayout(15, 15));
        body.setOpaque(false);
        body.add(cards, BorderLayout.CENTER);
        body.add(south, BorderLayout.SOUTH);

        main.add(body, BorderLayout.CENTER);
        content.add(main, BorderLayout.CENTER);
        refresh();
    }

    private JPanel metric(String title, String value, String sub, Color c) {
        RoundPanel p = new RoundPanel();
        p.setBackground(Theme.SURFACE);
        p.setBorder(new EmptyBorder(20, 20, 20, 20));
        p.setLayout(new BorderLayout());
        p.add(Theme.label(title, 10, Theme.MUTED, true), BorderLayout.NORTH);
        p.add(Theme.label(value, 34, c, true), BorderLayout.CENTER);
        p.add(Theme.label(sub, 10, Theme.MUTED, false), BorderLayout.SOUTH);
        return p;
    }

    private void showSettings() {
        header("System Settings", "Configure verification, reporting and network preferences");
        JPanel main = mainPanel();

        RoundPanel card = new RoundPanel();
        card.setBackground(Theme.SURFACE);
        card.setBorder(new EmptyBorder(20, 22, 20, 22));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        card.add(setting("Face recognition", "Enable face verification before attendance", true));
        card.add(setting("RFID requirement", "Require a valid RFID match", true));
        card.add(setting("Automatic reports", "Generate daily attendance reports", true));
        card.add(setting("Security alerts", "Flag mismatched identity events", true));
        card.add(setting("Live camera feed", "Show camera preview in verification terminal", true));

        card.add(Box.createVerticalStrut(20));
        card.add(Theme.label("INTEGRATION ENDPOINTS", 10, Theme.MUTED, true));
        card.add(Box.createVerticalStrut(8));
        card.add(Theme.label("CCTV   192.168.1.100", 12, Theme.TEXT, false));
        card.add(Theme.label("RFID   USB / NETWORK", 12, Theme.TEXT, false));
        card.add(Theme.label("API    http://localhost:8080/api/v1", 12, Theme.TEXT, false));
        card.add(Theme.label("DB     MySQL / SentinelScan", 12, Theme.TEXT, false));

        main.add(card, BorderLayout.CENTER);
        content.add(main, BorderLayout.CENTER);
        refresh();
    }

    private JPanel setting(String title, String desc, boolean selected) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(9, 0, 9, 0));

        JPanel x = new JPanel();
        x.setOpaque(false);
        x.setLayout(new BoxLayout(x, BoxLayout.Y_AXIS));
        x.add(Theme.label(title, 12, Theme.TEXT, true));
        x.add(Theme.label(desc, 10, Theme.MUTED, false));

        JCheckBox box = new JCheckBox();
        box.setSelected(selected);
        box.setOpaque(false);

        p.add(x, BorderLayout.WEST);
        p.add(box, BorderLayout.EAST);
        return p;
    }

    private void exportCSV() {
        try (FileWriter w = new FileWriter("sentinelscan_attendance.csv")) {
            w.write("Time,Student ID,Student,Department,RFID,Face,Card,Result\n");
            for (AttendanceRecord r : AppData.records) {
                w.write(r.getTime() + "," + r.getId() + "," + r.getName() + "," +
                        r.getDepartment() + "," + r.getRfid() + "," + r.getFace() + "," +
                        r.getCard() + "," + r.getStatus() + "\n");
            }
            JOptionPane.showMessageDialog(frame,
                    "Report exported as sentinelscan_attendance.csv",
                    "Export Complete", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refresh() {
        content.revalidate();
        content.repaint();
    }
}