@echo off
echo Please enter a Trip Planner file number suffix below...
echo.
set /p id=Enter File Number: 

echo.

call TripPlannerApplication.bat %id%

echo.

pause

call OneDriveBackup.bat
pause
