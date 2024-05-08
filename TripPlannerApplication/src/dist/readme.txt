Trip Planner Application
========================
Setup
-----
After deploying App (Unzipping), run OneTimeSetup.bat


Pre-execution
-------------
Edit and create Trip Planning files in the \bin directory.

Sample files are provided in \bin directory called DailyTripPlannerBase.xml and MonthlyTripPlannerBase.xml

File naming is strict.
Follow this pattern, DailyTripPlanner<nn>.xml where <nn> are numbers ranging from 00 to 99.
Follow this pattern, MonthlyTripPlanner<nn>.xml where <nn> are numbers ranging from 00 to 99.


Execution
---------
Run CallDailyTripPlannerApplication or CallMonthlyTripPlannerApplication
You will be prompted for the file number. Again, 00 to 99.

The report will be available in the \out directory.
All previous reports will be overridden unless you explicitly rename the report before re-running the application.
