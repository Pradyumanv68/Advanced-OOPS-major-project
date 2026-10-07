import java.text.SimpleDateFormat;
import java.util.*;

public final class AppData {
    private AppData() {}

    public static final List<Student> students = new ArrayList<>();
    public static final List<AttendanceRecord> records = new ArrayList<>();

    static {
        students.add(new Student("STU001","Pradyuman Verma","CSE - Data Science","RFID1001",96));
        students.add(new Student("STU002","Kabeer Chhabra","CSE - Data Science","RFID1002",94));
        students.add(new Student("STU003","Piyush Halder","CSE - Data Science","RFID1003",91));
        students.add(new Student("STU004","Aarav Singh","Computer Science","RFID1004",88));
        students.add(new Student("STU005","Rahul Sharma","Computer Science","RFID1005",82));
        students.add(new Student("STU006","Ananya Kapoor","Information Technology","RFID1006",95));
        students.add(new Student("STU007","Riya Mehta","CSE - Data Science","RFID1007",97));
        students.add(new Student("STU008","Dev Sharma","CSE","RFID1008",90));

        add(students.get(0), "MATCHED", "MATCHED", "VERIFIED");
        add(students.get(1), "MATCHED", "MATCHED", "VERIFIED");
        add(students.get(2), "MATCHED", "MATCHED", "VERIFIED");
        add(students.get(3), "MATCHED", "MATCHED", "VERIFIED");
        add(students.get(4), "NO MATCH", "MATCHED", "REJECTED");
        add(students.get(5), "MATCHED", "MATCHED", "VERIFIED");
        add(students.get(6), "MATCHED", "MATCHED", "VERIFIED");
    }

    public static void add(Student s, String face, String card, String status) {
        String time = new SimpleDateFormat("dd MMM • HH:mm:ss").format(new Date());
        records.add(new AttendanceRecord(time, s.getId(), s.getName(),
                s.getDepartment(), s.getRfid(), face, card, status));
    }

    public static int verifiedCount() {
        int n = 0;
        for (AttendanceRecord r : records) if ("VERIFIED".equals(r.getStatus())) n++;
        return n;
    }

    public static int rejectedCount() {
        int n = 0;
        for (AttendanceRecord r : records) if ("REJECTED".equals(r.getStatus())) n++;
        return n;
    }
}