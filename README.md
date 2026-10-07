# SentinelScan — Smart Campus Security

SentinelScan is a dual-verification attendance concept combining CCTV face recognition with proximity RFID.

## Team
- Pradyuman Verma
- Kabeer Chhabra
- Piyush Halder
- Nihal Rai

## Core Flow
CCTV/IP Camera → Face Recognition
RFID Reader → Arduino → ESP8266/ESP32 → Wi-Fi/LAN
Both identities → Cross Verification → Attendance Database → Excel/CSV Report

## Hardware
- IP Camera
- Long-range RFID reader
- RFID cards
- Arduino
- ESP8266 / ESP32 Wi-Fi module
- Router
- Computer
- Jumper wires, breadboard and 5V power

## Software / Tech
- OpenCV + face-recognition pipeline
- Java backend / Javalin
- Database
- Java UI prototype
- Excel/CSV reporting
- Wi-Fi/LAN networking

## Setup Plan
1. Wire the RFID reader to Arduino.
2. Add the Wi-Fi module and connect it to the router.
3. Assign IP addresses to the camera, Arduino/module and computer.
4. Send card IDs to the server and open the camera stream.
5. Test the complete flow at the entrance.

> Prototype note: hardware and service integrations are being developed incrementally. 
