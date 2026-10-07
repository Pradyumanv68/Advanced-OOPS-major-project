public class Student {
    private final String id;
    private final String name;
    private final String department;
    private final String rfid;
    private final int attendance;
    private String status;

    public Student(String id, String name, String department, String rfid, int attendance) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.rfid = rfid;
        this.attendance = attendance;
        this.status = "ACTIVE";
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getRfid() { return rfid; }
    public int getAttendance() { return attendance; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}