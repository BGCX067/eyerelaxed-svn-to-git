runscreen.sh is the main application which runs our java app.
This is a bash script. Please modify according to your shell.
The lock file is needed to make sure we dont run multiple instances of our application.
I could have checked this in Java but its simpler in shell script.

screen.properties
imagepath is the directory where all your images are
sleeptime is the amount of time you want each image to display (sorry weird naming convention)

crontab
for unix edit the crontab with crontab -e and enter the line mentioned
*/20 runs the application every 20 minutes.This is a relatively unknown trick for the crontab to run
every X minute. Modify this to make it whatever timeframe you want.
Example */35 would make it run every 35 minutes. 
env DISPLAY=:0. This sets the display for the application to display in the first display! :)

Assumptions: I assume the bash file is in home directory. If not please modify
> the crontab for the bash file path
> change the first line in the runscreen.sh to the new path


Licence: Use wherever, whatever, however you wish. I assume no liability for any malfunction or loss 
in any way.
