# SentinelScan Professional UI

A deeply redesigned Java Swing prototype for the SentinelScan smart campus security and attendance system.

## Design
- Dark enterprise security dashboard
- Custom-painted rounded cards and buttons
- Cyan/blue/gold security accent system
- Responsive sidebar + top bar
- Dashboard KPIs
- Attendance analytics
- Live verification terminal
- Student management
- Attendance monitoring
- Alerts and audit log
- Reports
- Settings
- Search/filter controls
- Toast-style status feedback
- No external libraries required

## Run
JDK 17+ recommended.

PowerShell:
```powershell
.\run.bat
```

Manual:
```powershell
if (!(Test-Path out)) { New-Item -ItemType Directory out | Out-Null }
javac -encoding UTF-8 -d out src\*.java
java -cp out SentinelScanApp
```

Login:
admin / admin

## Prototype note
The live camera and RFID screens are UI simulations. Real CCTV/OpenCV, RFID hardware, Wi-Fi/LAN, Spring Boot and MySQL integrations can be connected to the service layer later.
