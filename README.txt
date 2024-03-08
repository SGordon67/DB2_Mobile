Same project as DB2, reworked for mobile app.

Scott Gordon
Joe Gelsomini

To get set up:
  -Copy source code into folder with given directory structure.
  -Have your xampp server running
  -Copy PHPandSQL folder ito the proper directory for xampp: /xampp/htdocs/dashboard 
  -name the database db2
  -Run the file DB2.sql to create and populate the tables with the relevant information
  -run android studio and open the existing project from the code provided
  -Find and select the BASE/App/Db2_Phase3 directory and click OK to open the project
  -In res/values/strings.xml in Android Studio, you must change "url" to the directory containing the PHP and SQL files
    -You must get the ip address for your server. We found ours in the netstat section of the xampp controller.
    -Should be formatted something like "http://999.999.9.9:80/dashboard/DB2_phase3/PHPandSQL/" with your ip instead
  -Setup a device simulator (for development we used pixel 2) to run the app in Android Studio.
  -Run the app using the Run app button near the top of the Android Studio window.
