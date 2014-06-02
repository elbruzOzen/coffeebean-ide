
CoffeeBean is written on Eclipse Kepler 4.3.2

To run project, 

-Extract the file inside archive.
-Import project into your workspace in Eclipse(File>Import)
-Select the file you have extracted
-If Eclipse can not find main method, open core>Launcher.java then run it again.

Files are saved in the directory of .classpath(Under CoffeBean folder)

CoffeeBean requires JDK and JRE installed on computer with properly configured system variables.
It also uses default Web Browser(For online Search) and Video Player(For video tutorials) of your OS.
There is no video tutorial added because of project size.You can run mp4 files which is under tutorials folder.
There will be a video file on Demo for demonstration of this functionality. 

CofeeBean is a simple and functional code editor with auto-tabbing, colourful key words and easy compile and run options.
It provides tutorial files which can be opened by tutorial pane. Online search functionality and example code templates.

The project is completely working. No bugs are observed up until now. We are searching for problems.


Help Document indicates the main necessities for full functionality.

CoffeeBean IDE V1 Help Document - 12.05.2014

------------------------------------------------------------------------------------------------------

Setting Up CoffeeBean

To execute the program and full functionality, you need to follow the following steps:

-Please install the latest version of Java(JRE) on your computer.
-Make sure you are using a recent version of Windows.
-Please install the latest version of Java Software Development Kit(JDK). 
-Open Command Manager( cmd.exe) and execute "java -version" then "javac -version"
-Make sure you have received proper version information for both commands.
-If these commands are not recognized, add JDK and JRE directories into your system "path" variable.

For more detailed information:
http://docs.oracle.com/javase/tutorial/essential/environment/paths.html

-Try these commands again and make sure you have received proper version information for both commands.

Now you are ready to begin.

-----------------------------------------------------------------------------------------------------

Simple algorithm for writing code

-Write the code
-Save your file by using File/Save option.(Or File/Save All)
-Build your file by using File/Build option.(Or File/Build All)
-Correct all problems in your code by checking your console
-Make sure your program files are built successfully.
-Select the file which contains main method
-Click File/Run to execute your program.

Alternatively, try shortcut buttons.Explanation is given as a tool text tip on button.

-----------------------------------------------------------------------------------------------------

To open the files in your directory

-Click File/Open
-Navigate through your file
-Click OK

Caution: Make sure that all of your Java files and Byte Code Files are in the same directory with your the Jar File
with your program. This will make it easy to control the project and enable JVM to execute all files properly.

-----------------------------------------------------------------------------------------------------

To open tutorials

-Open and select the tutorial on the Tutorial Box
-Follow the tutorial and go to the other parts


-Video tutorials under tutorials folder is now shown properly.(Fixed before demo)
(MP4 and MKV video formats, no video has been added because of project size.)

--------------------------------------------------------------------------------------------------------

For Java Documentation click Java Doc Button. If you have an internet connection, new browser will be navigated to
on-line documentation.

For search your problems, choose your engine, enter your keyword or problem on search field and click "Search" button, alternatively
hit enter.

Note: Engines are kept in a file under extra/config/engine.dat
-Do not harm this file unnecessarily, you lost your engines except default one. (File is created by default again).
-By following format, you can add your own engines( Name + newLine + address ). Do not distort format or create empty lines.

---------------------------------------------------------------------------------------------------------

CoffeeBean remembers your EditorFont and BackgroundColor selections. They are kept under extra/config/font.dat and 
under extra/config/color.dat files

! Preferably, do not try to modify them, it is useless. These  symbols are for computers to understand, not for humans. If you want to restore
factory settings, simply delete files and open CoffeeBean again.

----------------------------------------------------------------------------------------------------------------