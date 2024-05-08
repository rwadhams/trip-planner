@echo off
echo Please enter a Monthly Trip Planner file number suffix below...
echo.
set /p id=Enter File Number: 

echo.

call TripPlannerApplication.bat monthly %id%

echo.

pause

call OneDriveBackup.bat
pause
