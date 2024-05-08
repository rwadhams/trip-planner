@echo off
echo Please enter a Daily Trip Planner file number suffix below...
echo.
set /p id=Enter File Number: 

echo.

call TripPlannerApplication.bat daily %id%

echo.

pause

call OneDriveBackup.bat
pause
