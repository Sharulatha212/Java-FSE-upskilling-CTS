@echo off

:: Create directories for compiled classes
mkdir mods\com.utils
mkdir mods\com.greetings

:: Compile the utils module
javac -d mods/com.utils ^
    src/com.utils/module-info.java ^
    src/com.utils/com/utils/StringUtils.java

:: Compile the greetings module
javac --module-path mods -d mods/com.greetings ^
    src/com.greetings/module-info.java ^
    src/com.greetings/com/greetings/Main.java

:: Run the application
java --module-path mods -m com.greetings/com.greetings.Main 