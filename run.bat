
@echo off
echo Compiling Java files...


echo Running program...
java -cp "bin;res" main.Main

if not exist bin mkdir bin(
    @echo file does not exist
) 

javac -d bin -cp res src\**\*.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    exit /b
)
pause
