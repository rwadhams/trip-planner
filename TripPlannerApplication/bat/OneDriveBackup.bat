@echo off
REM OneDriveBackup for TripPlanner data files and reports

if not exist "%userprofile%\OneDrive\Documents\App_Data_and_Reporting_Backups\TripPlanner\" mkdir %userprofile%\OneDrive\Documents\App_Data_and_Reporting_Backups\TripPlanner

xcopy *.xml %userprofile%\OneDrive\Documents\App_Data_and_Reporting_Backups\TripPlanner /Y

xcopy out\*.* %userprofile%\OneDrive\Documents\App_Data_and_Reporting_Backups\TripPlanner\out /I /Y
