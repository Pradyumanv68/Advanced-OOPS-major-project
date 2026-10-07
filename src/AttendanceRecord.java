public class AttendanceRecord {
    private final String time, id, name, department, rfid, face, card, status;

    public AttendanceRecord(String time, String id, String name, String department,
                            String rfid, String face, String card, String status) {
        this.time = time;
        this.id = id;
        this.name = name;
        this.department = department;
        this.rfid = rfid;
        this.face = face;
        this.card = card;
        this.status = status;
    }

    public String getTime() { return time; }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getRfid() { return rfid; }
    public String getFace() { return face; }
    public String getCard() { return card; }
    public String getStatus() { return status; }
}