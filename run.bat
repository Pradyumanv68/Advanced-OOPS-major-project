@echo off
cd /d "%~dp0"
if not exist out mkdir out
echo.
echo ==========================================
echo       SENTINELSCAN PROFESSIONAL
echo ==========================================
echo Compiling...
javac -encoding UTF-8 -d out src\*.java
if errorlevel 1 (
  echo.
  echo Compilation failed.
  pause
  exit /b 1
)
echo Starting application...
java -cp out SentinelScanApp
pause
